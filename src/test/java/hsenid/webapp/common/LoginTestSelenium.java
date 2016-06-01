package hsenid.webapp.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by hsenid on 6/1/16.
 */
public class LoginTestSelenium {
    WebDriver driver;
    String url;

    @DataProvider(name = "provider")
    public Object[][] users() {
        return new Object[][]{
                {"test", "123", "Login Page"},
                {"test ", "123", "Login Page"},
                {"test ", " 123", "Login Page"},
                {" test", "123", "Login Page"},
                {" test", " 123", "Login Page"},
                {" test ", " 123 ", "Login Page"},
                {"tESt", "123", "Login Page"},
                {"kdm", "123", "Translator"},
                {"Kdm", "abc", "Login Page"},
                {"kdm", "Abc", "Login Page"},
                {"KDM", "ABC", "Login Page"},
                {"kdm", "ABC", "Login Page"},
                {"KDM", "abc", "Login Page"},
                {"123", "test", "Login Page"},
                {"KDM", "abc", "Login Page"},
        };
    }

    @BeforeTest
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        url = "http://localhost:8080/";
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

    @Test(dataProvider = "provider",testName ="loginTestSelenium")
    public void loginTestWithSelenium(String username, String password, String expected) throws InterruptedException {
        driver.get(url);
        wait(1000);

        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys(username);
        wait(1000);

        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        wait(1000);

        driver.findElement(By.id("loginButton")).click();
        String actualTitle = driver.getTitle();
        wait(500);

        Assert.assertEquals(actualTitle, expected, "Evaluate user validation results.");
    }

    public void wait(int miliseconds) throws InterruptedException {
        Thread.sleep(miliseconds);
    }
}
