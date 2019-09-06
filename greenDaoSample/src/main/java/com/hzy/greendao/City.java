package com.hzy.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "tb_city",createInDb = false)
public class City {
    private Integer cityid;
    private String cityname;
    private String zipcode;
    private Integer provinceid;
    private Long datecreated;
    private Long dateupdated;
    private String citycode;
    @Generated(hash = 1687867371)
    public City(Integer cityid, String cityname, String zipcode, Integer provinceid,
            Long datecreated, Long dateupdated, String citycode) {
        this.cityid = cityid;
        this.cityname = cityname;
        this.zipcode = zipcode;
        this.provinceid = provinceid;
        this.datecreated = datecreated;
        this.dateupdated = dateupdated;
        this.citycode = citycode;
    }
    @Generated(hash = 750791287)
    public City() {
    }
    public Integer getCityid() {
        return this.cityid;
    }
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }
    public String getCityname() {
        return this.cityname;
    }
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
    public String getZipcode() {
        return this.zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public Integer getProvinceid() {
        return this.provinceid;
    }
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }
    public Long getDatecreated() {
        return this.datecreated;
    }
    public void setDatecreated(Long datecreated) {
        this.datecreated = datecreated;
    }
    public Long getDateupdated() {
        return this.dateupdated;
    }
    public void setDateupdated(Long dateupdated) {
        this.dateupdated = dateupdated;
    }
    public String getCitycode() {
        return this.citycode;
    }
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

}
