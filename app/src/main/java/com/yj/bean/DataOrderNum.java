package com.yj.bean;

/**
 * Created by LK on 2017/12/1.
 *
 * @author LK
 */

public class DataOrderNum {

    /**
     * year : {"money":"3254835.03","num":"5924"}
     * month : {"money":"4893.10","num":"8"}
     * day : {"money":"4893.10","num":"8"}
     */

    private YearBean year;
    private MonthBean month;
    private DayBean day;

    public YearBean getYear() {
        return year;
    }

    public void setYear(YearBean year) {
        this.year = year;
    }

    public MonthBean getMonth() {
        return month;
    }

    public void setMonth(MonthBean month) {
        this.month = month;
    }

    public DayBean getDay() {
        return day;
    }

    public void setDay(DayBean day) {
        this.day = day;
    }

    public static class YearBean {

        /**
         * money : 3254835.03
         * num : 5924
         */

        private String money;
        private String num;

        public int getMoney() {
            return Double.valueOf(money).intValue();
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }

    public static class MonthBean {
        /**
         * money : 4893.10
         * num : 8
         */

        private String money;
        private String num;

        public int getMoney() {
            return Double.valueOf(money).intValue();
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }

    public static class DayBean {

        /**
         * money : 4893.10
         * num : 8
         */

        private String money;
        private String num;

        public int getMoney() {
            return Double.valueOf(money).intValue();
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }


}
