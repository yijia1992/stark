package io.github.talelin.merak.bo;

import java.util.Date;

public class Data {
    private Date time;
    private String imsi;
    private String laci;
    private double lng;
    private double lat;

    //职住分析时标记工作地或住地
    private int workOrHome;

    //通勤距离
    private double workHomeDis;

    //驻留时间，以分钟来算。
    private long dur;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getLaci() {
        return laci;
    }

    public void setLaci(String laci) {
        this.laci = laci;
    }

    public long getDur() {
        return dur;
    }

    public void setDur(long dur) {
        this.dur = dur;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getWorkOrHome() {
        return workOrHome;
    }

    public void setWorkOrHome(int workOrHome) {
        this.workOrHome = workOrHome;
    }

    public double getWorkHomeDis() {
        return workHomeDis;
    }

    public void setWorkHomeDis(double workHomeDis) {
        this.workHomeDis = workHomeDis;
    }

    @Override
    public String toString() {
        return "Data{" +
                "time=" + time +
                ", imsi='" + imsi + '\'' +
                ", laci='" + laci + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", workOrHome=" + workOrHome +
                ", workHomeDis=" + workHomeDis +
                ", dur=" + dur +
                '}';
    }
}
