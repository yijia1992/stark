package io.github.talelin.merak.utils;

import io.github.talelin.merak.bo.Data;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

//通过经纬度计算距离
public class DistanceCal {
    public GlobalCoordinates source;
    public GlobalCoordinates target;

    public DistanceCal(Data from, Data to) {

        this.source = new GlobalCoordinates(from.getLat(),from.getLng());
        this.target = new GlobalCoordinates(to.getLat(),to.getLng());
    }
    public double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){

        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();
    }

    public static void main(String[] args) {
        Data from = new Data();
        Data to = new Data();
        from.setLat(41.80778122);
        from.setLng(123.4159698);
        to.setLat(41.8152504);
        to.setLng(123.4121704);
        DistanceCal cal = new DistanceCal(from,to);
        double result = cal.getDistanceMeter(cal.source,cal.target,Ellipsoid.Sphere);
        System.out.println(result+"米");
    }

}
