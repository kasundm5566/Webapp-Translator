package hsenid.webapp.usermanagement.adduser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by hsenid on 6/1/16.
 */
public class CheckUserNameTestSelenium {
    WebDriver driver;
    String url;

    @BeforeTest
    public void initiateTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        url = "http://localhost:8080/";
        driver.get(url);

        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys("kdm");

        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("123");

        driver.findElement(By.id("loginButton")).click();

        driver.findElement(By.id("userManagement")).click();

        driver.findElement(By.id("addUserDrp")).click();
        Thread.sleep(600);

        driver.findElement(By.id("accoutDetailsTab")).click();
        Thread.sleep(600);
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
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        Thread.sleep(300);
        String actual=driver.findElement(By.id("uname_error")).getText();
        Assert.assertEquals(actual,expect,"Checking username availability");
    }
}
