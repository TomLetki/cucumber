package pl.testeroprogramowania.steps;

;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pl.testeroprogramowania.pages.HomePage;
import pl.testeroprogramowania.pages.LoggedUserPage;
import pl.testeroprogramowania.pages.MyAccountPage;
import pl.testeroprogramowania.utils.DriverFactory;


import java.util.concurrent.TimeUnit;

public class StepDefs {

    protected String email;


    @Given("User with unique email address")
    public void userWithUniqueEmailAddress() {
        int random = (int) (Math.random() * 1000);
        email = "test" + random + "@select.com";

    }

    @When("User registers in app")
    public void userRegistersInApp() {
        new HomePage(DriverFactory.getDriver())
                .openMyAccountPage()
                .registerUserValidData(email, "test33@select.com")
                .getDashboardLink();
    }

    @Then("User should be redirected to logged user page")
    public void userShouldBeRedirectedToLoggedUserPage() {
        WebElement dashboardLink = new LoggedUserPage(DriverFactory.getDriver()).getDashboardLink();
        Assert.assertEquals(dashboardLink.getText(), "Dashboard");
    }

    @Given("User with existent email address")
    public void userWithExistentEmailAddress() {
        email="test1@select.com";
    }

    @Then("An error will bi displayed {string}, user ist still on login page")
    public void anErrorWillBiDisplayedUserIstStillOnLoginPage(String arg0) {
        WebElement error = new MyAccountPage(DriverFactory.getDriver()).getError();
        Assert.assertTrue(error.getText().contains(" An account is already registered with your email address"));

    }

    @When("User logs to the aplication")
    public void userLogsToTheAplication() {
        new HomePage(DriverFactory.getDriver())
                .openMyAccountPage()
                .logInValidData(email, "test33@select.com");
    }

    }

