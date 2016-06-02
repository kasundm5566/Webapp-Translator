package hsenid.webapp.translator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class TranslatorTestSelenium {
    WebDriver driver;
    String url;

    @BeforeTest
    public void start() {
        driver = new ChromeDriver();
        url = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);

        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys("kdm");

        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("123");

        driver.findElement(By.id("loginButton")).click();
    }

    @AfterTest
    public void end() throws InterruptedException {
        driver.findElement(By.id("navi")).click();
        driver.findElement(By.id("out")).click();
        driver.quit();
    }

    @DataProvider(name = "provider")
    public Object[][] transData() {
        return new Object[][]{
                {"English", "French", "child", "enfant"},
                {"French", "English", "enfant", "child"},
                {"French", "Japanese", "enfant", "子ども"},
                {"Japanese", "French", "子ども", "Les enfants"},
                {"Japanese", "English", "子ども", "Children"},
                {"English", "French", " ", ""}
        };
    }

    @Test(dataProvider = "provider",testName = "translateTestSelenium")
    public void testTranslate(String fromLang, String toLang, String text, String expected) {
        driver.findElement(By.id("fromtext")).clear();
        driver.findElement(By.id("fromtext")).sendKeys(text);

        Select comboFromLanguages = new Select(driver.findElement(By.id("fromlang")));
        comboFromLanguages.selectByVisibleText(fromLang);

        Select comboToLanguages = new Select(driver.findElement(By.id("tolang")));
        comboToLanguages.selectByVisibleText(toLang);

        driver.findElement(By.id("translateButton")).click();

        WebElement textarea = driver.findElement(By.id("totext"));
        String actual = textarea.getText();

        Assert.assertEquals(actual, expected, "Evaluate the translated text with expected result.");
    }
}
