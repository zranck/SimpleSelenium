package com.zac.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    @Test
    public void loginTest() {
        System.out.println("Starting logIn test");
        // create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        sleep(3000); // for demo purposes

        driver.manage().window().maximize();

        // open the page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        sleep(3000); // for demo purposes
        System.out.println("Page is opened.");

        // enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        // enter password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        // log in button | using xpath just for demo purposes
        WebElement logInButton = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        logInButton.click();

        // verifications
        // new url
        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);

        // log out button is visible
        WebElement logOutButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a"));
        Assert.assertTrue(logOutButton.isDisplayed(), "logOutButton not displayed");

        // successful log in message
        WebElement successMessage = driver.findElement(By.id("flash"));
        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSuccessMessage = successMessage.getText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),"Unexpected success message \n" +actualSuccessMessage);

        sleep(3000);

        // close browser
        driver.quit();
    }

    private void sleep(long millis) {
        // Purposely adding this implicit wait for demo purposes
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
