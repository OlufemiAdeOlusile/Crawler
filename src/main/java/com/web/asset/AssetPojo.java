package com.web.asset;


public class AssetPojo {

    private String tagName;
    private String attribute;

    private AssetPojo(Builder builder) {
        tagName = builder.tagName;
        attribute = builder.attribute;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getTagName() {
        return tagName;
    }

    public String getAttribute() {
        return attribute;
    }

    public static final class Builder {
        private String tagName;
        private String attribute;

        private Builder() {
        }

        public Builder withTagName(String tagName) {
            this.tagName = tagName;
            return this;
        }

        public Builder withAttribute(String attribute) {
            this.attribute = attribute;
            return this;
        }

        public AssetPojo build() {
            return new AssetPojo(this);
        }
    }
}
