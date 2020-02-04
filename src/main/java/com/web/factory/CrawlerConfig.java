package com.web.factory;

import com.web.asset.AssetPojo;


public class CrawlerConfig {
    private String url;
    private int statusCode;
    private AssetPojo asset;
    private int maxLinksPerPage;
    private int maxDepth;
    private String assetName;

    private CrawlerConfig(Builder builder) {
        url = builder.url;
        statusCode = builder.statusCode;
        asset = builder.asset;
        maxLinksPerPage = builder.maxLinksPerPage;
        maxDepth = builder.maxDepth;
        assetName = builder.assetName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getAssetName() {
        return assetName;
    }

    public String getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public AssetPojo getAsset() {
        return asset;
    }

    public int getMaxLinksPerPage() {
        return maxLinksPerPage;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public static final class Builder {
        private String url;
        private int statusCode;
        private AssetPojo asset;
        private int maxLinksPerPage;
        private int maxDepth;
        private String assetName;

        private Builder() {
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder withAsset(AssetPojo asset) {
            this.asset = asset;
            return this;
        }

        public Builder withMaxLinksPerPage(int maxLinksPerPage) {
            this.maxLinksPerPage = maxLinksPerPage;
            return this;
        }

        public Builder withMaxDepth(int maxDepth) {
            this.maxDepth = maxDepth;
            return this;
        }

        public Builder withAssetName(String assetName) {
            this.assetName = assetName;
            return this;
        }

        public CrawlerConfig build() {
            return new CrawlerConfig(this);
        }
    }
}
