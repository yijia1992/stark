package io.github.talelin.merak.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MuUtil {
    public static  String genId(){
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String time = df.format(new Date());
        Random random = new Random();
        int randomNum = random.nextInt(999) % 900 + 100;
        StringBuilder sb = new StringBuilder("wd");
        return sb.append(time).append(randomNum).toString();
    }
    public static  String genOrderId(){
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String time = df.format(new Date());
        Random random = new Random();
        int randomNum = random.nextInt(999) % 900 + 100;
        StringBuilder sb = new StringBuilder("od");
        return sb.append(time).append(randomNum).toString();
    }

    public static Date getDateTime() throws ParseException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        Date dateTime = format.parse(dateStr);
        return dateTime;
    }

    public static String getFormatTime(Date date) {
        String value = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        value =dateFormat.format(date);
        return value;
    }
    public static String getFormatTime(String parttern) {
        String value = null;
        DateFormat dateFormat = new SimpleDateFormat(parttern);
        value =dateFormat.format(new Date());
        return value;
    }

    //bigdecimal类型的随机数
    public static BigDecimal getRandomRedPacketBetweenMinAndMax(BigDecimal min, BigDecimal max){
        float minF = min.floatValue();
        float maxF = max.floatValue();
        //生成随机数
        BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);
        //返回保留两位小数的随机数。不进行四舍五入
        return db.setScale(2,BigDecimal.ROUND_DOWN);
    }


}
