package hsenid.webapp.usermanagement.adduser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by hsenid on 6/1/16.
 */
public class CheckUserNameTestSelenium {
    WebDriver driver;
    String url;

    @BeforeTest
    public void initTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        String url = "http://localhost:8080/";
        driver.get(url);
        wait(700);

        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys("kdm");
        wait(700);

        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("123");
        wait(700);

        driver.findElement(By.id("loginButton")).click();
        wait(700);

        driver.findElement(By.id("userManagement")).click();
        wait(700);

        driver.findElement(By.id("addUserDrp")).click();
        wait(700);

        driver.findElement(By.id("accoutDetailsTab")).click();
        wait(700);
    }

    @AfterTest
    public void endTest(){
        driver.quit();
    }

    @Test
    public void execTest() throws InterruptedException {
        String val="test";
        for (int i = 0; i < val.length(); i++){
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            driver.findElement(By.id("username")).sendKeys(s);
            wait(1000);
        }
        wait(700);
        driver.quit();
    }

    public void wait(int miliseconds) throws InterruptedException {
        Thread.sleep(miliseconds);
    }
}
