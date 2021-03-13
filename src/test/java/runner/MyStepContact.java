package runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MyStepContact {
    private AppiumDriver driver;

    @Given("yo tengo abierto mi aplicacion de contacto")
    public void yoTengoAbiertoMiAplicacionDeContacto() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "EynarH");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("appPackage", "com.android.contacts");
        capabilities.setCapability("appActivity", ".activities.PeopleActivity");
        capabilities.setCapability("platformName", "Android");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @When("yo hago click en el boton {string}")
    public void yoHagoClickEnElBoton(String nambreControl) {
        switch (nambreControl) {
            case "add":
                driver.findElement(By.id("com.android.contacts:id/hw_fab")).click();
                break;
            case "done":
                driver.findElement(By.id("android:id/icon2")).click();
                break;
            case "back":
                driver.findElement(By.id("com.android.contacts:id/backImg")).click();
                break;

        }
    }

    @And("yo lleno la caja de texto {} con el valor {string}")
    public void yoLlenoLaCajaDeTextoNameConElValor(String control, String value) {
        switch (control) {
            case "name":
                ((MobileElement)driver.findElement(By.xpath("//android.widget.EditText[@text='Name']"))).sendKeys(value);

                break;
            case "org":
                driver.findElement(By.xpath("//android.widget.EditText[@text='Organization']")).sendKeys(value);

                break;
            case "phone":
                driver.findElement(By.xpath("//android.widget.EditText[@text='Phone number']")).sendKeys(value);

                break;

        }

    }

    @Then("el contacto {string} deberia ser mostrado")
    public void elContactoDeberiaSerMostrado(String expectedResult) {
        boolean isDisplayed=driver.findElement(By.xpath("//android.widget.TextView[@text='"+expectedResult+"']")).isDisplayed();
        Assert.assertTrue("ERROR el contacto no es mostrado en la lista",isDisplayed);
        driver.quit();
    }
}
