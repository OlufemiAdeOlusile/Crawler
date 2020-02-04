package com.web.asset;


public enum Asset {
    PAGES {
        @Override
        public AssetPojo getAssets() {
            return AssetPojo.newBuilder().withAttribute("href").withTagName("a").build();
        }
    },
    JS {
        @Override
        public AssetPojo getAssets() {
            return AssetPojo.newBuilder().withAttribute("src").withTagName("script").build();
        }
    },
    CSS {
        @Override
        public AssetPojo getAssets() {
            return AssetPojo.newBuilder().withAttribute("href").withTagName("link").build();
        }
    },
    IMAGES {
        @Override
        public AssetPojo getAssets() {
            return AssetPojo.newBuilder().withAttribute("src").withTagName("img").build();
        }
    };


    public abstract AssetPojo getAssets();

}
