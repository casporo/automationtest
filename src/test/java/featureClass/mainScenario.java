package featureClass;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class mainScenario {
    WebDriver driver;
    WebDriverWait wait;



    @Given("^I am a new customer$")
    public void new_customer() throws Throwable {

        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    @And("^access to MoneyLion website$")
    public void accessing_MoneyLion_site() throws Throwable {
       driver.get("https://www.moneylion.com/");
    }

    @When("^I hover on \"About Us\" and click \"About Us\" at the top of the webpage$")
    public void hoverOn_AboutUs() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement AboutUsNavigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[3]/a")));
        AboutUsNavigation.click();

        WebElement AboutUs = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[3]/div/ul/li[1]/a")));
        AboutUs.click();
    }

    @Then("^I should be redirected to the MoneyLion page$")
    public void redirected_to_MoneyLion_page() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[1]")));

    }

    @And("^I should be able to see \"Offices located in New York, San Francisco, Salt Lake City, and Kuala Lumpur\" text displayed under \"COME JOIN US\"$")
    public void offices_location_under_come_join_us() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement ComeJoinUs = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[4]/div[2]/span")));
        Actions actions = new Actions(driver);
        actions.moveToElement(ComeJoinUs);
        actions.perform();

        WebElement New_York = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[1]/div[2]")));
        String NYC = New_York.getAttribute("textContent");

        WebElement San_Francisco = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[2]/div[2]/span")));
        String SF = San_Francisco.getAttribute("textContent");

        WebElement Salt_Lake_City = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[3]/div[2]")));
        String SLC = Salt_Lake_City.getAttribute("textContent");

        WebElement Kuala_Lumpur = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[4]/div[2]")));
        String KL = Kuala_Lumpur.getAttribute("textContent");

        System.out.println(NYC);
        System.out.println(SF);
        System.out.println(SLC);
        System.out.println(KL);

        driver.close();

    }
}
