package com.asus.sorkkin.sorkkin.dao;


import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

public class PhotoList implements Parcelable  {
    private String name;
    private String address;
    private String img;
    private String tel;
    private Double lat;
    private Double lng;

    public PhotoList() {
        this.name = name;
        this.address = address;
        this.img = img;
        this.tel = tel;
        this.lat = lat;
        this.lng = lng;
    }

    protected PhotoList(Parcel in) {
        name = in.readString();
        address = in.readString();
        img = in.readString();
        tel = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
    }

    public static final Creator<PhotoList> CREATOR = new Creator<PhotoList>() {
        @Override
        public PhotoList createFromParcel(Parcel in) {
            return new PhotoList(in);
        }

        @Override
        public PhotoList[] newArray(int size) {
            return new PhotoList[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng (Double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(img);
        dest.writeString(tel);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
    }
}
