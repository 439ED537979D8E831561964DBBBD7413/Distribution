package com.yj.bean;


import com.google.gson.annotations.SerializedName;

/**
 * Created by LK on 2017/12/1.
 *
 * @author LK
 */

public class OrderItem {


    /**
     * id : 6482
     * mainorder : 201711290951531015
     * uid : 286
     * addtime : 1511918227
     * dotime : 1512177146
     * status : 3
     * money : 670.00
     * class : 9
     * num : 33
     * shopname : 旺佳百货
     * contacts : 谢社平
     * mobile : 13728188708
     * tel : 13728188708
     * refunds : null
     */

    private String id;
    private String mainorder;
    private String uid;
    private String addtime;
    private String dotime;
    private String status;
    private String money;
    @SerializedName("class")
    private String classX;
    private String num;
    private String shopname;
    private String contacts;
    private String mobile;
    private String tel;
    private Object refunds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainorder() {
        return mainorder;
    }

    public void setMainorder(String mainorder) {
        this.mainorder = mainorder;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getDotime() {
        return dotime;
    }

    public void setDotime(String dotime) {
        this.dotime = dotime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMoney() {
        return Double.valueOf(money).intValue();
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Object getRefunds() {
        return refunds;
    }

    public void setRefunds(Object refunds) {
        this.refunds = refunds;
    }
}
