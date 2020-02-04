package com.web.driver;


import com.web.utility.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver", PropertyLoader.loadResource("classpath:/chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        return new ChromeDriver(options);
    }
}
