package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasicAppiumTest2 {

    private AppiumDriver driver;

    @Before
    public void beforeTest() throws MalformedURLException {
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

    @Test
    public void verify_calculator(){
        // click button 9
        driver.findElement(By.xpath("//android.widget.Button[@text='9']")).click();
        // click button +
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='plus']")).click();
        // click button 2
        driver.findElement(By.xpath("//android.widget.Button[@text='2']")).click();
        // click button =
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='equals']")).click();
        // resultado 11 -- verificacion
        String expectedResult="11";
        String actualResult= driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.android.calculator2:id/formula']")).getText();
        Assert.assertEquals("Error el resulta es incorrecto",expectedResult,actualResult);
    }

    @After
    public void afterTest(){
        driver.quit();
    }

}
