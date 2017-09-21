package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 点击历史进店后展示的车辆信息 和车辆管理的车辆列表详情
 * Created by Administrator on 2017/6/14.
 */

public class GetCarnewDetailBean extends NetResult
{

    /**
     * data : {"carFrameNo":"","createDate":"","compulsoryDate":"","endDate":"","carPrice":null,"engineNo":null,"customer":{"customerName":"哦哦","mobilePhoneNo":"13866666666"},"customerLevel":{"customerLevelName":null},"promoter":{"promoterName":null},"consumeCard":[{"consumeCardNo":"123456","consumeCardName":"test","residualAmount":1500}],"brand":{"brandName":null}}
     */

    private DataBean data;

    public static GetCarnewDetailBean parse(String json) throws AppException
    {
        GetCarnewDetailBean res = new GetCarnewDetailBean();
        try
        {
            res = gson.fromJson(json, GetCarnewDetailBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * carFrameNo : 
         * createDate : 
         * compulsoryDate : 
         * endDate : 
         * carPrice : null
         * engineNo : null
         * customer : {"customerName":"哦哦","mobilePhoneNo":"13866666666"}
         * customerLevel : {"customerLevelName":null}
         * promoter : {"promoterName":null}
         * consumeCard : [{"consumeCardNo":"123456","consumeCardName":"test","residualAmount":1500}]
         * brand : {"brandName":null}
         */

        private String carFrameNo;
        private String createDate;
        private String compulsoryDate;
        private String endDate;
        private String carPrice;
        private String engineNo;
        private CustomerBean customer;
        private CustomerLevelBean customerLevel;
        private PromoterBean promoter;
        private BrandBean brand;
        private List<ConsumeCardBean> consumeCard;

        public String getCarFrameNo() {
            return carFrameNo;
        }

        public void setCarFrameNo(String carFrameNo) {
            this.carFrameNo = carFrameNo;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCompulsoryDate() {
            return compulsoryDate;
        }

        public void setCompulsoryDate(String compulsoryDate) {
            this.compulsoryDate = compulsoryDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(String carPrice) {
            this.carPrice = carPrice;
        }

        public String getEngineNo() {
            return engineNo;
        }

        public void setEngineNo(String engineNo) {
            this.engineNo = engineNo;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public CustomerLevelBean getCustomerLevel() {
            return customerLevel;
        }

        public void setCustomerLevel(CustomerLevelBean customerLevel) {
            this.customerLevel = customerLevel;
        }

        public PromoterBean getPromoter() {
            return promoter;
        }

        public void setPromoter(PromoterBean promoter) {
            this.promoter = promoter;
        }

        public BrandBean getBrand() {
            return brand;
        }

        public void setBrand(BrandBean brand) {
            this.brand = brand;
        }

        public List<ConsumeCardBean> getConsumeCard() {
            return consumeCard;
        }

        public void setConsumeCard(List<ConsumeCardBean> consumeCard) {
            this.consumeCard = consumeCard;
        }

        public static class CustomerBean {
            /**
             * customerName : 哦哦
             * mobilePhoneNo : 13866666666
             */

            private String customerName;
            private String mobilePhoneNo;

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getMobilePhoneNo() {
                return mobilePhoneNo;
            }

            public void setMobilePhoneNo(String mobilePhoneNo) {
                this.mobilePhoneNo = mobilePhoneNo;
            }
        }

        public static class CustomerLevelBean {
            /**
             * customerLevelName : null
             */

            private String customerLevelName;

            public String getCustomerLevelName() {
                return customerLevelName;
            }

            public void setCustomerLevelName(String customerLevelName) {
                this.customerLevelName = customerLevelName;
            }
        }

        public static class PromoterBean {
            /**
             * promoterName : null
             */

            private String promoterName;

            public String getPromoterName() {
                return promoterName;
            }

            public void setPromoterName(String promoterName) {
                this.promoterName = promoterName;
            }
        }

        public static class BrandBean {
            /**
             * brandName : null
             */

            private String brandName;

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }
        }

        public static class ConsumeCardBean {
            /**
             * consumeCardNo : 123456
             * consumeCardName : test
             * residualAmount : 1500
             */

            private String consumeCardNo;
            private String consumeCardName;
            private String residualAmount;

            public String getConsumeCardNo() {
                return consumeCardNo;
            }

            public void setConsumeCardNo(String consumeCardNo) {
                this.consumeCardNo = consumeCardNo;
            }

            public String getConsumeCardName() {
                return consumeCardName;
            }

            public void setConsumeCardName(String consumeCardName) {
                this.consumeCardName = consumeCardName;
            }

            public String getResidualAmount() {
                return residualAmount;
            }

            public void setResidualAmount(String residualAmount) {
                this.residualAmount = residualAmount;
            }
        }
    }
}
