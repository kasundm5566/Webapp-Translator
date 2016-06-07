package hsenid.webapp.usermanagement.adduser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by hsenid on 6/1/16.
 */
public class CheckUserNameTestSelenium {
    WebDriver driver;
    String url;
    WebDriverWait wait;

    @BeforeTest
    public void initiateTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        url = "http://localhost:8080/";
        driver.get(url);
        wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys("kdm");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginpassword")));
        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("12345678*");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
        driver.findElement(By.id("loginButton")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("userManagement")));
        driver.findElement(By.id("userManagement")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("addUserDrp")));
        driver.findElement(By.id("addUserDrp")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("accoutDetailsTab")));
        driver.findElement(By.id("accoutDetailsTab")).click();
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

    @DataProvider(name = "provider")
    public Object[][] usernames() {
        return new Object[][]{
                {"kdm", "That user name is already used."},
                {"abc", "That user name is already used."},
                {"xyz", "User name is valid."},
                {"pqr", "User name is valid."},
                {"peter123", "That user name is already used."}
        };
    }

    @Test(dataProvider = "provider", testName = "checkUserNameTestSelenium")
    public void loginTestWithSelenium(String username, String expect) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("uname_error")));
        String actual=driver.findElement(By.id("uname_error")).getText();

        Assert.assertEquals(actual,expect,"Checking username availability");
    }
}
