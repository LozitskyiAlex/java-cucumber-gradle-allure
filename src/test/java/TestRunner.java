import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.BrowserPerTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"
        })
@Listeners({ BrowserPerTest.class})
public class TestRunner extends AbstractTestNGCucumberTests {

    AllureSelenide allureSelenide;

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeClass
    public void setUp() {
        timeout = 20000;
        startMaximized = true;
        browser = "chrome";
//        browserSize = "1024x768";
        fileDownload = PROXY;
        proxyEnabled = true;
        System.setProperty("chromeoptions.args", "--disable-gpu");
        System.setProperty("chromeoptions.args", "--no-sandbox");
        System.setProperty("chromeoptions.args", "--disable-dev-shm-usage");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setAcceptInsecureCerts(true);
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePref = new HashMap<>();
        chromePref.put("profile.default_content_settings.popups", 0);
        chromePref.put("download.default_directory", System.getProperty("user.dir") + File.separator + "build"
                + File.separator + "downloads");
        chromePref.put("safebrowsing.enabled", true);
//        options.addArguments("--safebrowsing-disable-download-protection");
//        options.addArguments("safebrowsing-disable-extension-blacklist");
        options.setExperimentalOption("prefs", chromePref);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        browserCapabilities = capabilities;
        headless = true;
        //addListener(new Highlighter());

    }

    @BeforeMethod
    public void addAllureListener() {
        allureSelenide = new AllureSelenide().enableLogs(LogType.DRIVER, Level.ALL);
        SelenideLogger.addListener("allure", allureSelenide);
    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
        SelenideLogger.removeListener("allure");
    }
}
