package com.example.dbman.db.model;

import java.io.Serializable;
import java.util.UUID;

public  class FactoryDesc implements Serializable{
            private UUID factoryid;
            private String factoryname;
            private String factorytype;
            private String province;
            private String city;
            private String district;
            private String address;
            private String postcode;
            private String firstman;
            private String remark;
            private String descn;
            private String telephone;

            public UUID getFactoryid() {
                return factoryid;
            }

            public void setFactoryid(UUID factoryid) {
                this.factoryid = factoryid;
            }

            public String getFactoryname() {
                return factoryname;
            }

            public void setFactoryname(String factoryname) {
                this.factoryname = factoryname;
            }

            public String getFactorytype() {
                return factorytype;
            }

            public void setFactorytype(String factorytype) {
                this.factorytype = factorytype;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPostcode() {
                return postcode;
            }

            public void setPostcode(String postcode) {
                this.postcode = postcode;
            }

            public String getFirstman() {
                return firstman;
            }

            public void setFirstman(String firstman) {
                this.firstman = firstman;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getDescn() {
                return descn;
            }

            public void setDescn(String descn) {
                this.descn = descn;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        
    }