package com.web.factory;

import com.web.asset.Asset;
import com.web.asset.AssetPojo;
import com.web.utility.PropertyLoader;


public class CrawlerFactory {
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_STATUS_CODE = "statusCode";
    private static final String PROPERTY_ASSET = "asset";
    private static final String PROPERTY_MAX_LINKS_PER_PAGE = "maxLinksPerPage";
    private static final String PROPERTY_MAX_DEPTH = "maxDepth";

    private CrawlerFactory() {
    }


    public static CrawlerConfig getConfig() {
        return CrawlerConfig.newBuilder().withAsset(getAsset())
                .withUrl(getProperty(PROPERTY_URL))
                .withStatusCode(Integer.valueOf(getProperty(PROPERTY_STATUS_CODE)))
                .withMaxDepth(Integer.valueOf(getProperty(PROPERTY_MAX_DEPTH)))
                .withMaxLinksPerPage(Integer.valueOf(getProperty(PROPERTY_MAX_LINKS_PER_PAGE)))
                .withAssetName(getProperty(PROPERTY_ASSET)).build();
    }


    private static AssetPojo getAsset() {
        return Asset.valueOf(getProperty(PROPERTY_ASSET)).getAssets();
    }

    private static String getProperty(String property) {
        return PropertyLoader.retrieveProperty(PropertyLoader.loadResource("classpath:/config.properties")).getProperty(property);
    }
}
