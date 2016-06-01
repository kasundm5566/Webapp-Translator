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

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class TranslatorTestSelenium {
    WebDriver driver;
    String url;

    @BeforeTest
    public void start() throws InterruptedException {
        driver = new ChromeDriver();
        url = "http://localhost:8080/";
        driver.get(url);
        wait(1000);

        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys("kdm");
        wait(1000);

        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        wait(1000);

        driver.findElement(By.id("loginButton")).click();
        wait(1000);
    }

    @AfterTest
    public void end() throws InterruptedException {
        driver.findElement(By.id("navi")).click();
        wait(500);
        driver.findElement(By.id("out")).click();
        wait(1000);
        driver.quit();
    }

    @DataProvider(name = "provider")
    public Object[][] transData() {
        return new Object[][]{
                {"English", "French", "child", "enfant"},
                /*{"French", "English", "enfant", "child"},
                {"French", "Japanese", "enfant", "子ども"},
                {"Japanese", "French", "子ども", "Les enfants"},
                {"Japanese", "English", "子ども", "Children"},
                {"English", "French", " ", ""}*/
        };
    }

    @Test(dataProvider = "provider",testName = "translateTestSelenium")
    public void testTranslate(String fromLang, String toLang, String text, String expected) throws InterruptedException {
        driver.findElement(By.id("fromtext")).clear();
        driver.findElement(By.id("fromtext")).sendKeys(text);
        wait(1000);

        Select comboFromLanguages = new Select(driver.findElement(By.id("fromlang")));
        comboFromLanguages.selectByVisibleText(fromLang);
        wait(1000);

        Select comboToLanguages = new Select(driver.findElement(By.id("tolang")));
        comboToLanguages.selectByVisibleText(toLang);
        wait(1000);

        driver.findElement(By.id("translateButton")).click();
        wait(500);


        WebElement textarea = driver.findElement(By.id("totext"));
        String actual = textarea.getText();
        Assert.assertEquals(actual, expected, "Evaluate the translated text with expected result.");
    }

    public void wait(int miliseconds) throws InterruptedException {
        Thread.sleep(miliseconds);
    }
}
