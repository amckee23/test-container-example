package uk.co.amckee.testcontainers.selenium;

import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;

import java.io.File;

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING;

public class BaseIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseIntegrationTest.class);

    @Rule
    public GenericContainer mvcApp = new GenericContainer("uk.co.amckee/test-container-mvc:latest").withExposedPorts(8080);

    @Rule
    public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_FAILING, new File("build"));

    private RemoteWebDriver driver;

    @Before
    public void setUpTests() throws Exception {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        capabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(chrome.getSeleniumAddress(), capabilities);
    }

    protected RemoteWebDriver driver() {
        return this.driver;
    }

    protected String getAppUrl() {
        final String appUrl = "http://" + mvcApp.getIpAddress() + ":" + mvcApp.getMappedPort(8080) + "/home";
        LOGGER.info("Returning default app url as: {}", appUrl);
        return appUrl;
    }
}
