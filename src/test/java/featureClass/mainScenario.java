package featureClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
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
    //==================================================== JAVA CLASS GUIDE ===============================================================================
    //======= This file contains the Selenium code to run the following scenarios: ========================================================================
    //======= Scenario 1: Able to access MoneyLion about page successfully ================================================================================
    //======= Scenario 2: Able to verify the portfolio types ==============================================================================================
    //======= Scenario 3: Able to verify grade for Credit Utilization =====================================================================================

    //=========================== Main Selenium Codes to start ChromeDriver and to open MoneyLion site" ===================================================

    WebDriver driver;
    @Given("^I am a new customer$")
    public void new_customer() throws Throwable {
        // Starts ChromeDriver for Selenium
        WebDriverManager.getInstance(CHROME).setup();
        // Opens ChromeDriver in maximised window
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    @And("^access to MoneyLion website$")
    public void accessing_MoneyLion_site() throws Throwable {
        //Driver accessing MoneyLion site
        driver.get("https://www.moneylion.com/");
       screenshot.takeSnapShot(driver, "./src/test/screenshots/moneylion.jpg");
    }

    //=========================== Selenium codes for Scenario "Able to access MoneyLion about page successfully"============================================

    @When("^I hover on \"About Us\" and click \"About Us\" at the top of the webpage$")
    public void hoverOn_AboutUs() throws Throwable {

        //Waits for About Us navigation tab to be present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement AboutUsNavigation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[3]/a")));
        //Clicks About Us navigation tab
        AboutUsNavigation.click();
        screenshot.takeSnapShot(driver, "./src/test/screenshots/about_us.jpg");
        //Waits for About Us button to pop up under About Us tab
        WebElement AboutUs = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[3]/div/ul/li[1]/a")));
        //Clicks About Us button in the About Us tab
        AboutUs.click();
    }

    @Then("^I should be redirected to the MoneyLion page$")
    public void redirected_to_MoneyLion_page() throws Throwable {
        //Waits for About Us page to load before proceeding
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[1]")));
        screenshot.takeSnapShot(driver, "./src/test/screenshots/moneylion_page.jpg");
    }

    @And("^I should be able to see \"Offices located in New York, San Francisco, Salt Lake City, and Kuala Lumpur\" text displayed under \"COME JOIN US\"$")
    public void offices_location_under_come_join_us() throws Throwable {

        //Waits for Offices element to be present before moving to Offices element
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement ComeJoinUs = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[4]/div[2]/span")));
        Actions actions = new Actions(driver);
        actions.moveToElement(ComeJoinUs);
        actions.perform();
        screenshot.takeSnapShot(driver, "./src/test/screenshots/cities.jpg");
        //Scans for New York element and extract text from "New York"
        WebElement New_York = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[1]/div[2]")));
        String NYC = New_York.getAttribute("textContent");
        //Scans for San Francisco element and extract text from "San Francisco"
        WebElement San_Francisco = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[2]/div[2]/span")));
        String SF = San_Francisco.getAttribute("textContent");
        //Scans for San Lake City element and extract text from "Salt Lake City"
        WebElement Salt_Lake_City = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[3]/div[2]")));
        String SLC = Salt_Lake_City.getAttribute("textContent");
        //Scans for Kuala Lumpur element and extract text from "Kuala Lumpur"
        WebElement Kuala_Lumpur = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div[2]/a[4]/div[2]")));
        String KL = Kuala_Lumpur.getAttribute("textContent");
        //Closes Selenium webDriver
        driver.close();
        //Performs verification of extract text to ensure it meets the requirements
        if(!NYC.equals("New York City") || !SF.equals("San Francisco") || !SLC.equals("Salt Lake City") || !KL.equals("Kuala Lumpur")){
            Assert.fail();
        }
    }

    //=========================== Selenium codes for Scenario " Able to verify the portfolio types" ===========================================
    @When("^I hover on \"Products\" and click on \"Auto investing\" at the top of the webpage$")
    public void hoverOn_Products() throws Throwable {
        //Waits for Product navigation tab to be present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement products = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[1]/a")));
        //Clicks on Product navigation tab
        products.click();
        screenshot.takeSnapShot(driver, "./src/test/screenshots/products.jpg");
        //Waits for Auto investing link to be present
        WebElement autoInvesting = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[1]/nav/li[1]/div/ul/li[4]/a")));
        //Clicks auto investing link
        autoInvesting.click();
    }

    @And("^I scroll to view the personalised portfolio$")
    public void scrollto_personalied_portfolio() throws Throwable {
        //Waits for Auto Investing page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
        //Waits for Portfolio element to be present
        WebElement portfolio = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[4]/div/div")));
        //Moves towards personalised portfolio section
        Actions actions = new Actions(driver);
        actions.moveToElement(portfolio);
        actions.perform();
    }

    @Then("^there should be total of 7 portfolio available on the slider$")
    public void count_portfolio_present() throws Throwable {
        //Waits for portfolio slider element to be present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("slider-step-dot")));
        //Scans through the slider element can provides a count of portfolio present
        List<WebElement> portfolioList = driver.findElements(By.className("slider-step-dot"));
        screenshot.takeSnapShot(driver, "./src/test/screenshots/portfolio_present.jpg");
        //Verification if portfolio size is not equal to 7, test result is set to "Fail"
        if(portfolioList.size() != 7){
            Assert.fail();
        }
    }

    @When("^I select \'([^\"]*)\' portfolio on the slider$")
    public void portfolioSlider(String options) throws Throwable {
        //Verify slider is present
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
            //Moves slider to "Steady Income"
            Action action = move.dragAndDropBy(sliderOption1, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "./src/test/screenshots/selected_portfolio_first.jpg");
        }else if(options.equals("second")){
            //Moves slider to "Conservative"
            Action action = move.dragAndDropBy(sliderOption2, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "./src/test/screenshots/selected_portfolio_second.jpg");
        }
        else if(options.equals("third")){
            //Moves slider to "Moderately Conservative"
            Action action = move.dragAndDropBy(sliderOption3, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "./src/test/screenshots/selected_portfolio_third.jpg");
        }
        else if(options.equals("fourth")){
            //Moves slider to "Moderate"
            Action action = move.dragAndDropBy(sliderOption4, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "./src/test/screenshots/selected_portfolio_fourth.jpg");
        }
        else if(options.equals("fifth")){
            //Moves slider to "Moderately Aggressive"
            Action action = move.dragAndDropBy(sliderOption5, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "./src/test/screenshots/selected_portfolio_fifth.jpg");
        }
        else if(options.equals("sixth")){
            //Moves slider to "Aggressive"
            Action action = move.dragAndDropBy(sliderOption6, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "./src/test/screenshots/selected_portfolio_six.jpg");
        }
        else if(options.equals("seventh")){
            //Moves slider to "Equity Only"
            Action action = move.dragAndDropBy(sliderOption7, 0, 0).build();
            action.perform();
            screenshot.takeSnapShot(driver, "./src/test/screenshots/selected_portfolio_seventh.jpg");
        }
    }

    @Then("^I should be able to see \'([^\"]*)\' displayed$")
    public void portfolioName(String portfolioName) throws Exception {
        //Verifies active portfolio options is present
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //Looks for active portfolio element and extracts the text from it
        WebElement selectedPortfolio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.slider-item.is-active")));
        String displayedPortfolio = selectedPortfolio.getAttribute("textContent");
        driver.close();
        //Verifies whether expected result matches with result shown
        if(!portfolioName.equals(displayedPortfolio)){
            Assert.fail();
        }
    }

    //=========================== Selenium codes for Scenario " Able to verify grade for Credit Utilization" ==================================
    @When("^I click on \"Credit Builder Loans\" at the bottom of the page$")
    public void creditBuilderLoans() throws Exception {
        //Waits for Credit Builder Loan element to appear
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement creditBuilderLoans = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div[1]/div[2]/div[3]/a[3]")));
        //Moves towards Credit Builder Loan (at the bottom of the page)
        Actions actions = new Actions(driver);
        actions.moveToElement(creditBuilderLoans);
        actions.perform();
        //Clicks Credit Builder Loan (at the bottom of the page)
        creditBuilderLoans.click();
        //Puts webdriver on temporary sleep. This was used as a stopgap measure due to WATCH YOUR CREDIT BUILD graph popping up and interrupting the webdriver flow.
        Thread.sleep(500);
    }

    @And("^I scroll to view the \"Track Your Credit While You Build\" section$")
    public void TrackYourCredit() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        //Puts webdriver on temporary sleep. This was used as a stopgap measure due to WATCH YOUR CREDIT BUILD graph popping up and interrupting the webdriver flow.
        Thread.sleep(500);
        //Waits for Track Your Credit Section element to appear.
        WebElement TrackYourCreditSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]")));
        //Moves towards Track Your Credit Section.
        Actions actions = new Actions(driver);
        actions.moveToElement(TrackYourCreditSection);
        actions.perform();
        screenshot.takeSnapShot(driver, "./src/test/screenshots/trackyourcredit.jpg");
    }

    @And("^I select \"Credit Utilization\"$")
    public void creditUtilization() throws Exception {
        //Puts webdriver on temporary sleep. This was used as a stopgap measure due to WATCH YOUR CREDIT BUILD graph popping up and interrupting the webdriver flow.
        Thread.sleep(500);
        //Waits for Credit Utilization element to appear.
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement creditUtilization = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]/div[1]/ul/li[3]")));
        //Clicks on Credit Section link
        creditUtilization.click();
        //Waits for Track Your Credit Utilization title element to appear.
        WebElement creditUtilizationHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]/div[2]/div[3]/div[3]/h6")));
        //Moves window to credit utilisation graph. This measure was taken due to WATCH YOUR CREDIT BUILD graph popping up.
        Actions actions = new Actions(driver);
        actions.moveToElement(creditUtilizationHeader);
        actions.perform();
        screenshot.takeSnapShot(driver, "./src/test/screenshots/credit_utilisation.jpg");
    }

    @And("^I change the credit utilization percentage to 20%$")
    public void modifyPercentage() throws Exception {
        //Waits for Credit Utilization slider element to appear.
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement creditUtilizationSlider = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]/div[2]/div[3]/div[2]/div[2]")));
        //Moves slider so that reading shows 20%
        Actions move = new Actions(driver);
         Action action = move.dragAndDropBy(creditUtilizationSlider, -112, 0).build();
        action.perform();
    }
    @Then("^I should be able to see the grade is now displayed as B$")
    public void verifyGrade() throws Exception {
        //Waits for Credit Grade element to appear
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement creditGrade = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/section[3]/div[2]/div[3]/div[3]/span[1]")));
        //Extracts text from Credit Grade Element
        String GradeDisplayed = creditGrade.getAttribute("textContent");
        screenshot.takeSnapShot(driver, "./src/test/screenshots/portfolioGrade.jpg");
        driver.close();
        //Verifies whether Credit Grade is shown as expected
        if (!GradeDisplayed.equals("B")) {
            Assert.fail();
        }
    }



}


