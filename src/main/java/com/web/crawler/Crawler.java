package com.web.crawler;

import com.web.driver.DriverManager;
import com.web.factory.CrawlerConfig;
import com.web.factory.CrawlerFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Crawler crawls pages limited to the depth of traversal while checking for broken links
 */
public class Crawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Crawler.class);
    private LinkedHashSet<String> pagesVisited = new LinkedHashSet<>();
    private Queue<String> linkQueue = new LinkedList<>();
    private LinkedHashMap<String, Integer> myPages = new LinkedHashMap<>();
    private int count;


    /**
     * Run Crawler
     */
    public static void main(String[] args) {
        WebDriver driver = new DriverManager().getDriver();
        new Crawler().crawl(CrawlerFactory.getConfig(), driver);
        driver.quit();
        LOGGER.info(">>>>> END OF CRAWLER");
        System.exit(0);
    }


    private void crawl(CrawlerConfig config, WebDriver driver) {
        count = 0;

        if (linkQueue.isEmpty()) {
            linkQueue.add(config.getUrl());
            index(config, config.getUrl(), driver);
            linkQueue.remove(config.getUrl());
        }


        while (count < config.getMaxDepth()) {
            count++;
            int size = linkQueue.size();

            IntStream.range(0, size).forEach(f -> {
                        if (!pagesVisited.contains(linkQueue.element())) {
                            index(config, linkQueue.element(), driver);
                        } else {
                            LOGGER.info("Duplicate Link {}", linkQueue.element());
                        }
                        linkQueue.poll();
                    }
            );

        }

        LOGGER.info("\n >>>>>>>  Depth of Traversal  <<<<<<<< \n");
        myPages.forEach((f, k) -> LOGGER.info("{} || {}", f, k));
    }


    private void index(CrawlerConfig config, String link, WebDriver driver) {
        pagesVisited.add(link);
        myPages.put(link, count);

        String parentPage = linkQueue.element();

        List<String> maxUnbrokenPages = new CrawlerLeg().getMaxUnbrokenLinks(config, parentPage, driver);

        if (!maxUnbrokenPages.isEmpty()) {
            LOGGER.info(">>>>>>> LIST OF GOOD {} ON {} FOUND ARE: <<<<<<< \n", config.getAssetName(), parentPage);

            maxUnbrokenPages.forEach(LOGGER::info);

            LOGGER.info("\n >>>> TRAVERSE {} <<<<<<< \n", parentPage);

            maxUnbrokenPages.stream().filter(f -> !pagesVisited.contains(f)).forEach(f -> {
                linkQueue.add(f);
                LOGGER.info(f);
            });

            LOGGER.info("\n >>>>> END OF TRAVERSAL <<<<<<< \n" );
        }else{
            LOGGER.info("NO UNBROKEN PAGES \n" );
        }

    }

}
