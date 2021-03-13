package runner;

import io.appium.java_client.AppiumDriver;
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

public class MyStepCalculadora {
    private AppiumDriver driver;

    @Given("yo tengo abierto mi calculadora app")
    public void yoTengoAbiertoMiCalculadoraApp() throws MalformedURLException {
        // configuracion para la conexion
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","EynarH");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("appPackage","com.android.calculator2");
        capabilities.setCapability("appActivity",".Calculator");
        capabilities.setCapability("platformName","Android");

        // driver apunte a nuestro appiumDesktop
        driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        // implicit:
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @When("yo presiono el numero {int}")
    public void yoPresionoElBoton(int number) {
        switch (number){
            case 2:
                driver.findElement(By.xpath("//android.widget.Button[@text='2']")).click();
                break;
            case 9:
                driver.findElement(By.id("com.android.calculator2:id/digit_9")).click();
                break;
            default:
                break;

        }

    }

    @And("yo presiono el boton {}")
    public void yoPresionoElBotonSuma(String nombreControl) {
        if (nombreControl.equals("suma"))
            driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        else
            driver.findElement(By.id("com.android.calculator2:id/eq")).click();

    }

    @Then("el resultado deberia ser {string}")
    public void elResultadoDeberiaSer(String expectedResult) {
        String actualResult= driver.findElement(By.id("com.android.calculator2:id/formula")).getText();
        Assert.assertEquals("Error el resulta es incorrecto",expectedResult,actualResult);
        driver.quit();
    }
}
