package com.qf.end.OneKm.dto;

public class Express {

    public Long expressId;     //运单号      唯一ID
    public String postType;    //邮递类型    顺丰、速通、圆通
    public String sender;      //发件人      默认匿名
    public String startAddress; //发件地址    默认匿名
    public String address;   //收件地址
    public String phone;       //收件人电话

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
