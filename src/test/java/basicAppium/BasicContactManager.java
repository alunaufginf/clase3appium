package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasicContactManager {


    private AppiumDriver driver;

    @Before
    public void beforeTest() throws MalformedURLException {
        // configuracion para la conexion
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","EynarH");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("appPackage","com.android.contacts");
        capabilities.setCapability("appActivity",".activities.PeopleActivity");
        capabilities.setCapability("platformName","Android");

        // driver apunte a nuestro appiumDesktop
        driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        // implicit: un timepo de espera para todos los compontes de UI
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void verify_contact_is_added() throws InterruptedException {

        String nombre="AAA9AREMOVE";
        // click [+] button
        driver.findElement(By.id("com.android.contacts:id/hw_fab")).click();

        Thread.sleep(2000);
        //*** set [name]
        ((MobileElement)driver.findElement(By.xpath("//android.widget.EditText[@text='Name']"))).sendKeys(nombre);
        // set [org]
        driver.findElement(By.xpath("//android.widget.EditText[@text='Organization']")).sendKeys("Org");
        // set [phone]
        driver.findElement(By.xpath("//android.widget.EditText[@text='Phone number']")).sendKeys("98789787897");
         //click [/] done
        driver.findElement(By.id("android:id/icon2")).click();

        // *** Verificacion en el view del cotacto
        int numberContact=driver.findElements(By.xpath("//android.widget.TextView[@text='"+nombre+"']")).size();
        Assert.assertEquals("ERROR en contacto",1,numberContact);

        // volver a la lista de contactos
        driver.findElement(By.id("com.android.contacts:id/backImg")).click();

        // *** verificacion en la lista de contactos


        // Explicit wait es para un control especifico --> condiciones
        WebDriverWait explicitWait= new WebDriverWait(driver,20);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='"+nombre+"']")));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='"+nombre+"']")))  ;


        boolean isDisplayed=driver.findElement(By.xpath("//android.widget.TextView[@text='"+nombre+"']")).isDisplayed();
        Assert.assertTrue("ERROR el contacto no es mostrado en la lista",isDisplayed);

        //swipe
        TouchAction action = new TouchAction(driver);

        WebElement startControl=driver.findElement(By.xpath("//*[@resource-id='android:id/list']/android.view.ViewGroup[3]"));
        WebElement endControl=driver.findElement(By.xpath("//*[@resource-id='android:id/list']/android.view.ViewGroup[1]"));

        // X Y del startControl
        int startX=startControl.getLocation().getX();
        int startY=startControl.getLocation().getY();
        // X Y del endControl
        int endX=endControl.getLocation().getX();
        int endY=endControl.getLocation().getY();

        action.press(PointOption.point(startX,startY)).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).
                moveTo(PointOption.point(endX,endY)).release().perform();

    }

    @After
    public void afterTest(){
        driver.quit();
    }

}
