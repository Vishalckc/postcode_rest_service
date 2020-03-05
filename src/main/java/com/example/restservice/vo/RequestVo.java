package com.example.restservice.vo;

import com.example.restservice.model.Address;

import java.util.List;

public class RequestVo {
    private String name;
    private List<AddressVo> addressVO;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AddressVo> getAddressVO() {
        return addressVO;
    }

    public void setAddressVO(List<AddressVo> addressVO) {
        this.addressVO = addressVO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
