package etsyMobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Login2Test {

    public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;

    String emailAdress="ayhankaykac19@gmail.com";
    String passWord="As123456";
    String aranacakKelime="wooden spoon";
    @Before
    public void setUp() throws InterruptedException {
        try {
            DesiredCapabilities capabilities =new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Pixel 3");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("udid","emulator-5554");
            capabilities.setCapability("platformVersion", "10");
            capabilities.setCapability("appPackage", "com.etsy.android");
            capabilities.setCapability("appActivity",
                    "com.etsy.android.ui.homescreen.HomescreenTabsActivity");
            //capabilities.setCapability("appActivity", "com.etsy.android.ui.user.auth.SignInActivity");
            capabilities.setCapability("skipUnlock","true");
            capabilities.setCapability("noReset","false");
            //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

            driver =new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
            wait=new WebDriverWait(driver,5);

        }
        catch (MalformedURLException urlException){
            System.out.println("HATALI URL...");
        }
        Thread.sleep(2000);
    }
    @Test
    public void girisTest() throws InterruptedException {

        //MAİL YAZDIR
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement emailBox = driver.findElement(By.id("com.etsy.android:id/clg_text_input"));
        //driver.getKeyboard().pressKey(emailAdress);
        emailBox.sendKeys(emailAdress);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //CONTINU BOTUN TIKLA
        WebElement continueButon = driver.findElementById("com.etsy.android:id/sign_in_button_email");
        continueButon.click();
        Thread.sleep(5000);

        //driver.getKeyboard().pressKey(); pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER );
        //((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //PASSWORD YAZDIR
        WebElement passwordBox = driver.findElement(By.className("android.widget.EditText"));

        passwordBox.sendKeys(passWord);
        //driver.getKeyboard().pressKey(passWord);

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //SIGN IN BUTON TIKLA
        WebElement signInButon = driver.findElement(By.className("android.widget.Button"));
        signInButon.click();

        Thread.sleep(2000);

        //kod gönder btn tıkla


        //Thread.sleep(3000);

    }

    /*@Test
    public void urunAramaTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement searchBox = driver.findElementById("com.etsy.android:id/search_src_text");
        //searchBox.sendKeys(aranacakKelime);
        driver.getKeyboard().pressKey(aranacakKelime);

        //driver.getKeyboard().pressKey(); pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER );
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //Giriş Yap Btn tıkla

        Thread.sleep(3000);

        //kod gönder btn tıkla

        //Thread.sleep(3000);

    }*/
    @After
    public void tearDown(){
        System.out.println("TEST BİTTİ...");
    }
}
