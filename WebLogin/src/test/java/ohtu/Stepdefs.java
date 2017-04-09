package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Before
    public void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    } 

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @When("^incorrect username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void non_existing_username_is_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("valid username \"([^\"]*)\", password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void creation_with_valid_information(String username, String password, String confPassword) throws Throwable {
        createAccount(username, password, confPassword);
    }
    
    @Then("new user is created$")
    public void creation_succesful() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @When("short username \"([^\"]*)\", password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void creation_with_short_username(String username, String password, String confPassword) throws Throwable {
        createAccount(username, password, confPassword);
    }
    
    @Then("user is not created and error \"([^\"]*)\" is reported$")
    public void invalid_information_was_entered(String errorMsg) {
        pageHasContent(errorMsg);
    }
    
    @When("valid username \"([^\"]*)\" and short password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void creation_with_short_password(String username, String password, String confPassword) throws Throwable {
        createAccount(username, password, confPassword);
    }
    
    @When("valid username \"([^\"]*)\" and invalid password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void creation_with_invalid_username(String username, String password, String confPassword) throws Throwable {
        createAccount(username, password, confPassword);
    }
    
    @When("already in use username \"([^\"]*)\", password \"([^\"]*)\" and password confirmation \"([^\"]*)\" are given$")
    public void creation_with_already_in_use_username(String username, String password, String confPassword) throws Throwable {
        createAccount(username, password, confPassword);
    }
    
    @When("valid username \"([^\"]*)\" and valid password \"([^\"]*)\" and not matching password confirmation \"([^\"]*)\" are given$")
    public void creation_with_wrong_password_confirmation(String username, String password, String confPassword) throws Throwable {
        createAccount(username, password, confPassword);
    }
    
    @Given("^new user is selected$")
    public void new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
    }
    
    @Given("user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_succesfully_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
        
        createAccount(username, password, password);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
    @Given("user with username \"([^\"]*)\" and password \"([^\"]*)\" is unsuccessfully created$")
    public void user_not_succesfully_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();
        
        createAccount(username, password, password);
        
        element = driver.findElement(By.linkText("back to home"));
        element.click();
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
    
    private void createAccount(String username, String password, String confPassword) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confPassword);
        element = driver.findElement(By.name("signup"));
        element.submit(); 
    }
}
