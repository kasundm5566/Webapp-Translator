package hsenid.webapp.usermanagement.adduser;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hsenid on 6/3/16.
 */
public class RegisterTestSelenium {
    WebDriver driver;
    String url;
    WebDriverWait wait;

    @BeforeTest
    public void initiateTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        url = "http://localhost:8080/";
        driver.get(url);
        wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys("kdm");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginpassword")));
        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("123");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
        driver.findElement(By.id("loginButton")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("userManagement")));
        driver.findElement(By.id("userManagement")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("addUserDrp")));
        driver.findElement(By.id("addUserDrp")).click();
    }

    @Test
    public void test() throws InterruptedException {
        driver.findElement(By.id("fname")).sendKeys("TestNG");

        Select countrySelect = new Select(driver.findElement(By.id("countrySelect")));
        countrySelect.selectByVisibleText("Australia");

        Select citySelect = new Select(driver.findElement(By.id("citySelect")));
        citySelect.selectByVisibleText("Perth");

        selectDate("date", 1991, "Apr", 26);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("accoutDetailsTab")));
        driver.findElement(By.id("accoutDetailsTab")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("testng123");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass")));
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys("12345678#");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("repass")));
        driver.findElement(By.id("repass")).clear();
        driver.findElement(By.id("repass")).sendKeys("12345678#");

        String grps[]={"Blocked","Customer Care"};
        selectMultiple(grps);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contactDetailsTab")));
        driver.findElement(By.id("contactDetailsTab")).click();

        Thread.sleep(500);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("abc@ldf.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("tel")).sendKeys("94771234567");

        driver.findElement(By.id("submit")).click();
        Thread.sleep(300);
        driver.findElement(By.id("addOk")).click();
        /*boolean actual=false;
        if(!driver.findElement(By.id("popup")).isDisplayed()){

            Thread.sleep(200);
            actual=driver.findElement(By.id("addUserSuccess")).isDisplayed();
        }*/
//        System.out.println(driver.findElement(By.id("popup")).isDisplayed());
        System.out.println(driver.findElement(By.id("addUserSuccess")).isDisplayed());
        Thread.sleep(11500);
    }

    @AfterTest
    public void endTest() {
        driver.quit();
    }


    public void selectDate(String dateFieldId, int year, String month, int day) throws InterruptedException {
        driver.findElement(By.id(dateFieldId)).click();

        String xpathYear = "//span[contains(.,'" + year + "')]";
        String xpathMonth = "//span[contains(.,'" + month + "')]";
        String xpathDay = "//table/tbody/tr/td[contains(.,'" + day + "')]";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathYear)));
        driver.findElement(By.xpath(xpathYear)).click();
        Thread.sleep(500);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMonth)));
        driver.findElement(By.xpath(xpathMonth)).click();
        Thread.sleep(500);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathDay)));
        driver.findElement(By.xpath(xpathDay)).click();
        Thread.sleep(500);
    }

    public void selectMultiple(String[] groups){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='btn-group']")));
        driver.findElement(By.xpath("//div[@class='btn-group']")).click();
        for (int i = 0; i < groups.length; i++) {
            driver.findElement(By.xpath("//input[@value='"+groups[i]+"']")).click();
        }
    }
}
