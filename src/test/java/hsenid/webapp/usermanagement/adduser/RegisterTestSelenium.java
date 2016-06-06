package hsenid.webapp.usermanagement.adduser;

import hsenid.webapp.usermanagement.searchuser.Delete;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "usersProvider")
    public Object[][] userDetails() {
        return new Object[][]{
                new Object[]{"TESTNG", "Australia", "Perth", 1995, "Jan", 24, "testng1", "12345678&", "12345678&", new String[]{"Customer Care"}, "testng1@test.com", "94771234567", true},
                new Object[]{"TESTNGG", "Sri Lanka", "Colombo", 1993, "Sep", 28, "testng2", "123456&", "1235678&", new String[]{"Blocked", "Translator"}, "testng2@test.com", "94771234567", false},
        };
    }

    @BeforeTest
    public void initiateTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        url = "http://localhost:8080/";
        driver.get(url);
        wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys("kdm");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginpassword")));
        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("123");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
        driver.findElement(By.id("loginButton")).click();
    }

    @Test(dataProvider = "usersProvider", testName = "registerUserTest")
    public void test(String firstName, String country, String city, int year, String month, int date, String userName, String password, String repassword, String[] groups, String email, String tel, boolean expected) throws InterruptedException {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("userManagement")));
        driver.findElement(By.id("userManagement")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("addUserDrp")));
        driver.findElement(By.id("addUserDrp")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("fname")));
        driver.findElement(By.id("fname")).sendKeys(firstName);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("countrySelect")));
        Select countrySelect = new Select(driver.findElement(By.id("countrySelect")));
        countrySelect.selectByVisibleText(country);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("citySelect")));
        Select citySelect = new Select(driver.findElement(By.id("citySelect")));
        citySelect.selectByVisibleText(city);

        selectDate("date", year, month, date);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("accoutDetailsTab")));
        driver.findElement(By.id("accoutDetailsTab")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(userName);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("pass")));
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("repass")));
        driver.findElement(By.id("repass")).clear();
        driver.findElement(By.id("repass")).sendKeys(repassword);

        selectMultiple(groups);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("contactDetailsTab")));
        driver.findElement(By.id("contactDetailsTab")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("tel")));
        driver.findElement(By.id("tel")).clear();
        driver.findElement(By.id("tel")).sendKeys(tel);

        driver.findElement(By.id("submit")).click();

        Thread.sleep(500);
        boolean actual = false;
        if (!driver.findElement(By.id("popup")).isDisplayed()) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addOk")));
            Thread.sleep(500);
            driver.findElement(By.id("addOk")).click();
            Thread.sleep(500);
            actual = driver.findElement(By.id("addUserSuccess")).isDisplayed();
        }
        Assert.assertEquals(actual,expected,"Check whether the user is added.");
    }

    @AfterTest
    public void endTest() {
        driver.quit();
    }

    public void selectDate(String dateFieldId, int year, String month, int day) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("date")));
        driver.findElement(By.id(dateFieldId)).click();

        String xpathYear = "//span[contains(.,'" + year + "')]";
        String xpathMonth = "//span[contains(.,'" + month + "')]";
        String xpathDay = "//table/tbody/tr/td[contains(.,'" + day + "')]";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathYear)));
        driver.findElement(By.xpath(xpathYear)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMonth)));
        driver.findElement(By.xpath(xpathMonth)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathDay)));
        driver.findElement(By.xpath(xpathDay)).click();
    }

    public void selectMultiple(String[] groups) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='btn-group']")));
        driver.findElement(By.xpath("//div[@class='btn-group']")).click();
        for (int i = 0; i < groups.length; i++) {
            driver.findElement(By.xpath("//input[@value='" + groups[i] + "']")).click();
        }
    }
}
