package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pageObject.testPage;

public class TestPageStepDefs {

    @Given("^I go to home page$")
    public void goToHomePage() {
        testPage.openHomePage();
    }

    @And("^I wait (.*) second$")
    public void waitFor(int second) throws InterruptedException {
        testPage.waitFor(second);
    }

}
