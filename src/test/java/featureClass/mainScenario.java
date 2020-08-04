package featureClass;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class mainScenario {
    WebDriver driver;

    @Given("^I am a new customer$")
    public void new_customer() throws Throwable {

        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
    }

    @And("^access to MoneyLion website$")
    public void accessing_MoneyLion_site() throws Throwable {

       driver.get("https://www.moneylion.com/");
    }


    @When("^I hover on \"About Us\" and click \"About Us\" at the top of the webpage$")
    public void hoverOn_AboutUs() throws Throwable {


    }

    @Then("^I should be redirected to the MoneyLion page$")
    public void redirected_to_MoneyLion_page() throws Throwable {

    }

    @And("^I should be able to see \"Offices located in New York, San Francisco, Salt Lake City, and Kuala Lumpur\" text displayed under \"COME JOIN US\"$")
    public void offices_location_under_come_join_us() throws Throwable {
        driver.close();

    }
}
