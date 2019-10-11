package com.hzy.testaucsample;


import java.util.List;

/**
 * 社区商城首页商品列表数据
 * */
public class CommunityMallHomeBean {

    /**
     * code : 1
     * data : {"current":1,"pages":1,"records":[{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-09-07 19:02:15","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":9,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:55","lowPrice":2099,"productIntroduction":"Redmi 9","purchases":null,"reviews":null,"shopId":1,"spuName":"红米 55 天下无敌","spuNo":"20190907190215759421577","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099},{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-09-07 19:00:39","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":8,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:55","lowPrice":2099,"productIntroduction":"Redmi 9","purchases":null,"reviews":null,"shopId":1,"spuName":"红米 99 天下无敌","spuNo":"20190907190038759421573","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099},{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-09-07 18:16:35","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":6,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:54","lowPrice":2099,"productIntroduction":"Redmi 9","purchases":null,"reviews":null,"shopId":1,"spuName":"红米 88 天下无敌","spuNo":"20190907181634759421571","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099},{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-09-07 16:05:27","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":5,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:53","lowPrice":2099,"productIntroduction":"Redmi 9","purchases":null,"reviews":null,"shopId":1,"spuName":"红米K20","spuNo":"201909071605261141045","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099},{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-08-26 18:26:30","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":1,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:49","lowPrice":2099,"productIntroduction":"红米K20 性能怪兽","purchases":"3","reviews":"1","shopId":1,"spuName":"红米K20","spuNo":"spu-123456","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099}],"searchCount":true,"size":10,"total":5}
     * msg : 操作成功
     * success : true
     */

    private String code;
    private DataBean data;
    private String msg;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * current : 1
         * pages : 1
         * records : [{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-09-07 19:02:15","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":9,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:55","lowPrice":2099,"productIntroduction":"Redmi 9","purchases":null,"reviews":null,"shopId":1,"spuName":"红米 55 天下无敌","spuNo":"20190907190215759421577","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099},{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-09-07 19:00:39","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":8,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:55","lowPrice":2099,"productIntroduction":"Redmi 9","purchases":null,"reviews":null,"shopId":1,"spuName":"红米 99 天下无敌","spuNo":"20190907190038759421573","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099},{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-09-07 18:16:35","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":6,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:54","lowPrice":2099,"productIntroduction":"Redmi 9","purchases":null,"reviews":null,"shopId":1,"spuName":"红米 88 天下无敌","spuNo":"20190907181634759421571","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099},{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-09-07 16:05:27","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":5,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:53","lowPrice":2099,"productIntroduction":"Redmi 9","purchases":null,"reviews":null,"shopId":1,"spuName":"红米K20","spuNo":"201909071605261141045","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099},{"attributeNotIncludeValue":null,"attributeToGroupValue":null,"billingWay":1,"brandId":1,"categoryId":34,"categoryName":"手机通讯","communityId":1,"createTime":"2019-08-26 18:26:30","deliveryAddress":"河南省郑州市","fromTheShelves":1,"goodsDetails":"","id":1,"insetsShow":"/goodsShuffling/2019/09/05/1567655377304854.jpg","lastUpdateTime":"2019-09-11 16:05:49","lowPrice":2099,"productIntroduction":"红米K20 性能怪兽","purchases":"3","reviews":"1","shopId":1,"spuName":"红米K20","spuNo":"spu-123456","spuThumbnail":"/goodsShuffling/2019/09/05/1567655377304854.jpg","subtitle":"小米的性能怪兽","topPrice":2099}]
         * searchCount : true
         * size : 10
         * total : 5
         */

        private int current;
        private int pages;
        private boolean searchCount;
        private int size;
        private int total;
        private List<RecordsBean> records;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * attributeNotIncludeValue : null
             * attributeToGroupValue : null
             * billingWay : 1
             * brandId : 1
             * categoryId : 34
             * categoryName : 手机通讯
             * communityId : 1
             * createTime : 2019-09-07 19:02:15
             * deliveryAddress : 河南省郑州市
             * fromTheShelves : 1
             * goodsDetails :
             * id : 9
             * insetsShow : /goodsShuffling/2019/09/05/1567655377304854.jpg
             * lastUpdateTime : 2019-09-11 16:05:55
             * lowPrice : 2099
             * productIntroduction : Redmi 9
             * purchases : null
             * reviews : null
             * shopId : 1
             * spuName : 红米 55 天下无敌
             * spuNo : 20190907190215759421577
             * spuThumbnail : /goodsShuffling/2019/09/05/1567655377304854.jpg
             * subtitle : 小米的性能怪兽
             * topPrice : 2099
             */

            private Object attributeNotIncludeValue;
            private Object attributeToGroupValue;
            private int billingWay;
            private int brandId;
            private int categoryId;
            private String categoryName;
            private int communityId;
            private String createTime;
            private String deliveryAddress;
            private int fromTheShelves;
            private String goodsDetails;
            private int id;
            private String insetsShow;
            private String lastUpdateTime;
            private int lowPrice;
            private String productIntroduction;
            private String purchases;
            private Object reviews;
            private int shopId;
            private String spuName;
            private String spuNo;
            private String spuThumbnail;
            private String subtitle;
            private int topPrice;

            public Object getAttributeNotIncludeValue() {
                return attributeNotIncludeValue;
            }

            public void setAttributeNotIncludeValue(Object attributeNotIncludeValue) {
                this.attributeNotIncludeValue = attributeNotIncludeValue;
            }

            public Object getAttributeToGroupValue() {
                return attributeToGroupValue;
            }

            public void setAttributeToGroupValue(Object attributeToGroupValue) {
                this.attributeToGroupValue = attributeToGroupValue;
            }

            public int getBillingWay() {
                return billingWay;
            }

            public void setBillingWay(int billingWay) {
                this.billingWay = billingWay;
            }

            public int getBrandId() {
                return brandId;
            }

            public void setBrandId(int brandId) {
                this.brandId = brandId;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public int getCommunityId() {
                return communityId;
            }

            public void setCommunityId(int communityId) {
                this.communityId = communityId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDeliveryAddress() {
                return deliveryAddress;
            }

            public void setDeliveryAddress(String deliveryAddress) {
                this.deliveryAddress = deliveryAddress;
            }

            public int getFromTheShelves() {
                return fromTheShelves;
            }

            public void setFromTheShelves(int fromTheShelves) {
                this.fromTheShelves = fromTheShelves;
            }

            public String getGoodsDetails() {
                return goodsDetails;
            }

            public void setGoodsDetails(String goodsDetails) {
                this.goodsDetails = goodsDetails;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInsetsShow() {
                return insetsShow;
            }

            public void setInsetsShow(String insetsShow) {
                this.insetsShow = insetsShow;
            }

            public String getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(String lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public int getLowPrice() {
                return lowPrice;
            }

            public void setLowPrice(int lowPrice) {
                this.lowPrice = lowPrice;
            }

            public String getProductIntroduction() {
                return productIntroduction;
            }

            public void setProductIntroduction(String productIntroduction) {
                this.productIntroduction = productIntroduction;
            }

            public String getPurchases() {
                return purchases;
            }

            public void setPurchases(String purchases) {
                this.purchases = purchases;
            }

            public Object getReviews() {
                return reviews;
            }

            public void setReviews(Object reviews) {
                this.reviews = reviews;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getSpuName() {
                return spuName;
            }

            public void setSpuName(String spuName) {
                this.spuName = spuName;
            }

            public String getSpuNo() {
                return spuNo;
            }

            public void setSpuNo(String spuNo) {
                this.spuNo = spuNo;
            }

            public String getSpuThumbnail() {
                return spuThumbnail;
            }

            public void setSpuThumbnail(String spuThumbnail) {
                this.spuThumbnail = spuThumbnail;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public int getTopPrice() {
                return topPrice;
            }

            public void setTopPrice(int topPrice) {
                this.topPrice = topPrice;
            }
        }
    }
}
