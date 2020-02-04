package com.web.crawler;

import com.web.factory.CrawlerConfig;
import com.web.urlconnection.HttpConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


class CrawlerLeg {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerLeg.class);
    private static final Integer PAGE_LOAD_TIMEOUT = 10;


    protected List<String> getMaxUnbrokenLinks(CrawlerConfig config, String url, WebDriver driver) {
        Set<String> allLinks = extractLinksFromWebsite(url, config.getAsset().getTagName(), config.getAsset().getAttribute(), driver);

        List<String> brokenLinks = extractBrokenLinks(allLinks, config.getStatusCode());

        if (!brokenLinks.isEmpty()) {
            LOGGER.info("WE HAVE BROKEN {} ON {} \n", config.getAssetName(), url);
        }

        allLinks.removeAll(brokenLinks);
        return allLinks.stream().limit(config.getMaxLinksPerPage()).collect(Collectors.toList());
    }


    private List<String> extractBrokenLinks(Set<String> allLinks, int statusCode) {
        return allLinks.stream().filter(f -> !new HttpConnection().verifyStatusCode(f, statusCode)).collect(Collectors.toList());
    }


    private Set<String> extractLinksFromWebsite(String url, String tagName, String attribute, WebDriver driver) {
        openWebPage(url, driver);

        LOGGER.info("\nCRAWLING {} \n", url);

        List<WebElement> pageLinks = driver.findElements(By.tagName(tagName));

        String parsedUrl = urlParser(url);

        return pageLinks.stream().map(f -> f.getAttribute(attribute)).filter(f -> f != null && f.contains(parsedUrl)).collect(Collectors.toSet());

    }


    private void openWebPage(String url, WebDriver driver) {
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }


    private String urlParser(String url) {
        try {
            URL aURL = new URL(url);
            return aURL.getHost();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Not a valid url", e);
        }
    }


}
