package featureClass;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;


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
       screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\moneylion.jpg");
    }

    //=========================== Selenium codes for Scenario "Able to access MoneyLion about page successfully"============================================
    @When("^I hover on \"About Us\" and click \"About Us\" at the top of the webpage$")
    public void hoverOn_AboutUs() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement AboutUsNavigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[3]/a")));
        AboutUsNavigation.click();
        screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\about_us.jpg");
        WebElement AboutUs = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[3]/div/ul/li[1]/a")));
        AboutUs.click();
    }

    @Then("^I should be redirected to the MoneyLion page$")
    public void redirected_to_MoneyLion_page() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[1]")));
        screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\moneylion_page.jpg");

    }

    @And("^I should be able to see \"Offices located in New York, San Francisco, Salt Lake City, and Kuala Lumpur\" text displayed under \"COME JOIN US\"$")
    public void offices_location_under_come_join_us() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement ComeJoinUs = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[4]/div[2]/span")));
        Actions actions = new Actions(driver);
        actions.moveToElement(ComeJoinUs);
        actions.perform();

        screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\cities.jpg");
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

    //=========================== Selenium codes for Scenario " Able to verify the portfolio types" ===========================================
    @When("^I hover on \"Products\" and click on \"Auto investing\" at the top of the webpage$")
    public void hoverOn_Products() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement products = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[1]/a")));
        products.click();
        screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\products.jpg");

        WebElement autoInvesting = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[1]/div/ul/li[4]/a")));
        autoInvesting.click();
    }

    @And("^I scroll to view the personalised portfolio$")
    public void scrollto_personalied_portfolio() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));

        WebElement portfolio = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div/div")));
        Actions actions = new Actions(driver);
        actions.moveToElement(portfolio);
        actions.perform();
    }

    @Then("^there should be total of 7 portfolio available on the slider$")
    public void count_portfolio_present() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("slider-step-dot")));

        List<WebElement> portfolioList = driver.findElements(By.className("slider-step-dot"));
        System.out.println(portfolioList.size());
        screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\portfolio_present.jpg");

        if(portfolioList.size() == 7){
            System.out.println("Test Passed!");
        }else{
            System.out.println("Test Failed");
            Assert.fail();
        }
    }

    @When("^I select \'([^\"]*)\' portfolio on the slider$")
    public void portfolioSlider(String options) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div/div/div[1]/div/div[1]/div/span")));
        WebElement sliderOption1 = driver.findElement(By.xpath("/html/body/section[4]/div/div/div[1]/div/div[1]/span[1]"));
        WebElement sliderOption2 = driver.findElement(By.xpath("/html/body/section[4]/div/div/div[1]/div/div[1]/span[2]"));
        WebElement sliderOption3 = driver.findElement(By.xpath("/html/body/section[4]/div/div/div[1]/div/div[1]/span[3]"));
        WebElement sliderOption4 = driver.findElement(By.xpath("/html/body/section[4]/div/div/div[1]/div/div[1]/span[4]"));
        WebElement sliderOption5 = driver.findElement(By.xpath("/html/body/section[4]/div/div/div[1]/div/div[1]/span[5]"));
        WebElement sliderOption6 = driver.findElement(By.xpath("/html/body/section[4]/div/div/div[1]/div/div[1]/span[6]"));
        WebElement sliderOption7 = driver.findElement(By.xpath("/html/body/section[4]/div/div/div[1]/div/div[1]/span[7]"));
        Actions move = new Actions(driver);
        if(options.equals("first")){
            Action action = move.dragAndDropBy(sliderOption1, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\selected_portfolio_first.jpg");
        }else if(options.equals("second")){
            Action action = move.dragAndDropBy(sliderOption2, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\selected_portfolio_second.jpg");
        }
        else if(options.equals("third")){
            Action action = move.dragAndDropBy(sliderOption3, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\selected_portfolio_third.jpg");
        }
        else if(options.equals("fourth")){
            Action action = move.dragAndDropBy(sliderOption4, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\selected_portfolio_fourth.jpg");
        }
        else if(options.equals("fifth")){
            Action action = move.dragAndDropBy(sliderOption5, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\selected_portfolio_fifth.jpg");
        }
        else if(options.equals("sixth")){
            Action action = move.dragAndDropBy(sliderOption6, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\selected_portfolio_six.jpg");
        }
        else if(options.equals("seventh")){
            Action action = move.dragAndDropBy(sliderOption7, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\selected_portfolio_seventh.jpg");
        }

    }

    @Then("^I should be able to see \'([^\"]*)\' displayed$")
    public void portfolioName(String portfolioName) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement selectedPortfolio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.slider-item.is-active")));
        String displayedPortfolio = selectedPortfolio.getAttribute("textContent");

        if(portfolioName.equals(displayedPortfolio)){
            System.out.println("Test Passed!");
        }else{
            System.out.println("Test Fail as " + "Expected Result: "+ portfolioName + " not equal to Actual Result: " + displayedPortfolio);
            driver.close();
            Assert.fail();
        }
        driver.close();
    }

    //=========================== Selenium codes for Scenario " Able to verify grade for Credit Utilization" ==================================
    @When("^I click on \"Credit Builder Loans\" at the bottom of the page$")
    public void creditBuilderLoans() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement creditBuilderLoans = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div[1]/div[2]/div[3]/a[3]")));
        Actions actions = new Actions(driver);
        actions.moveToElement(creditBuilderLoans);
        actions.perform();
        creditBuilderLoans.click();
        Thread.sleep(500);
    }

    @And("^I scroll to view the \"Track Your Credit While You Build\" section$")
    public void TrackYourCredit() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        Thread.sleep(500);
        WebElement TrackYourCreditSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]")));
        Actions actions = new Actions(driver);
        actions.moveToElement(TrackYourCreditSection);
        actions.perform();
        screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\trackyourcredit.jpg");
    }

    @And("^I select \"Credit Utilization\"$")
    public void creditUtilization() throws Exception {
        Thread.sleep(500);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement creditUtilization = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]/div[1]/ul/li[3]")));
        creditUtilization.click();
        WebElement creditUtilizationHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]/div[2]/div[3]/div[3]/h6")));
        Actions actions = new Actions(driver);
        actions.moveToElement(creditUtilizationHeader);
        actions.perform();
        screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\credit_utilisation.jpg");
    }

    @And("^I change the credit utilization percentage to 20%$")
    public void modifyPercentage() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement creditUtilizationSlider = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]/div[2]/div[3]/div[2]/div[2]")));
        Actions move = new Actions(driver);
        Action action = move.dragAndDropBy(creditUtilizationSlider, -112, 0).build();
        action.perform();
    }
    @Then("^I should be able to see the grade is now displayed as B$")
    public void verifyGrade() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement creditGrade = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]/div[3]/div[3]/span[1]")));
        String GradeDisplayed = creditGrade.getAttribute("textContent");
        screenshot.takeSnapShot(driver, "D:\\J Projects\\moneylion\\src\\test\\screenshots\\portfolioGrade.jpg");
        driver.close();
        if (GradeDisplayed.equals("B")){
            System.out.println("Test Passed!");
        } else{
            System.out.println("Test Failed");
            Assert.fail();
        }



    }
}


