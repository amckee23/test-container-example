package uk.co.amckee.testcontainers.selenium;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class HomeTest extends BaseIntegrationTest {

    @Test
    public void simplePageAsset() throws Exception {

        driver().get(getAppUrl());
        WebElement header = driver().findElementById("header");

        assertEquals("Welcome to MVC", header.getText());
    }
}
