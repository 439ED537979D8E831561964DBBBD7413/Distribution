package com.yj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LK on 2017/11/30.
 *
 * @author LK
 */

public class User implements Parcelable {

    /**
     * uid : 1
     * username : 13666666666
     * sex : 男
     * tel : 13662829560
     * idcard : 460028199311111111
     * address : 东莞市石排镇里仁路祺胜酒店4楼
     * token : addcebf98bea090814ecf1b2352f61d5
     */

    private String uid;
    private String username;
    private String sex;
    private String tel;
    private String idcard;
    private String address;
    private String token;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.username);
        dest.writeString(this.sex);
        dest.writeString(this.tel);
        dest.writeString(this.idcard);
        dest.writeString(this.address);
        dest.writeString(this.token);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.uid = in.readString();
        this.username = in.readString();
        this.sex = in.readString();
        this.tel = in.readString();
        this.idcard = in.readString();
        this.address = in.readString();
        this.token = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
