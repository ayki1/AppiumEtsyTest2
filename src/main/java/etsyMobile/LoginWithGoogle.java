package etsyMobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LoginWithGoogle {
    public AppiumDriver driver;
    public WebDriverWait wait;

    //String emailAdress="ayhankaykac19@gmail.com";
    //String passWord="As123456";
    String aranacakKelime=" wooden spoon 5";
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

            driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
            //wait=new WebDriverWait(driver,5);

        }
        catch (MalformedURLException urlException){
            System.out.println("HATALI URL...");
        }
        Thread.sleep(2000);
    }
    @Test
    public void girisTest() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //CONTINUE GOOGLE BOTUN TIKLA
        driver.findElement(By.id("com.etsy.android:id/btn_sign_in_google")).click();
        Thread.sleep(10000);
        //driver.getKeyboard().pressKey(); pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER );
        //((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // MEVCUT GOOGLE HESABI SEÇ
        WebElement googleHesabıSec = driver.findElement(By.
                className("android.widget.LinearLayout"));
        googleHesabıSec.click();
        //driver.getKeyboard().pressKey(passWord);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Thread.sleep(10000);

        //ÜRÜN ARAMASI YAP= "Wooden spoon"
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement searchBox = driver.findElement(By.className("android.widget.EditText"));
        searchBox.click();
        Thread.sleep(2000);
        //WebElement searchBox = driver.findElement(By.id("com.etsy.android:id/search_src_text"));
        //searchBox.sendKeys(aranacakKelime);
        driver.getKeyboard().pressKey(aranacakKelime);

        //driver.getKeyboard().pressKey(); pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER );
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Thread.sleep(7000);

        //BİR ÜRÜNÜN RESMİ TIKLA
        //driver.findElementByXPath("(//androidx.viewpager.widget.ViewPager[@content-desc=\"Listing image\"])[2]/android.widget.ImageView").click();
        //driver.findElement(By.className("android.widget.ImageView")).click();
        driver.findElement(By.id("com.etsy.android:id/listing_title")).click();
        Thread.sleep(5000);

        //ÜRÜNÜ ADINI VE FİYATINI YAZDIR
        WebElement urununAdi = driver.findElementById("com.etsy.android:id/listing_title");
        System.out.println("Ürün Adi = " + urununAdi.getText());
        WebElement urununFiyati = driver.findElementById("com.etsy.android:id/text_price");
        System.out.println("Ürün Fiyati= " + urununFiyati.getText());

        //ÜRÜN RENGİ SEÇİMİ İÇİN EKRANI YUKARI KAYDIR
        TouchAction yukariKaydir2=new TouchAction<>(driver)
                .press(PointOption.point(511,1694))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(561,801))
                .release().perform();
        Thread.sleep(2000);

        //ÜRÜN SEPETE EKLE
        WebElement sepeteEkle = driver.findElementByXPath
                ("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.TextSwitcher/android.widget.TextView");
        sepeteEkle.click();

        //SEPETE GİT
        TouchAction urunRengiTikla=new TouchAction<>(driver)
                .press(PointOption.point(970,1918))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .perform();
        /*WebElement sepeteGit =driver.findElement(By.className("android.widget.TextView"));
        sepeteGit.click();*/

    }
    @After
    public void tearDown(){
        System.out.println("TEST BİTTİ...");
    }
    /*
    public void screenshot(String path_screenshot) throws IOException {
        File srcFile=driver.getScreenshotAs(OutputType.FILE);
        String filename= UUID.randomUUID().toString();
        File targetFile=new File(path_screenshot + filename +".jpg");
        FileUtils.copyFile(srcFile,targetFile);
    }*/
}
