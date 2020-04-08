package io.github.talelin.merak.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.talelin.merak.model.ResidentDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentVO {
    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 用户名，唯一，用于登陆
     * User表中username的外键
     */
    private String username;

    /**
     * 用户大名
     */
    private String name;

    //性别 0/1
    private int gender;

    //年龄
    private int age;

    //身份证号 居民身份是否可用以身份证号是否存在为准
    private String userNo;

    private Long parentId;

    private String avatar;

    private Long loverId;

    private int insId;

    private int waterId;

    private int gasId;

    private int isPoor;

    private String sPoor;

    private int isTemp;

    //暂住、常住
   private String sTemp;
    /**
     * 电话
     */
    private String phone;

    public String getsPoor() {
        return isPoor==1?"低保户":"非低保";
    }

    public String getsTemp() {
        return isTemp==1?"暂住":"常住";
    }

    /**
     * 邮箱
     */
    // private String email;

    private Date createTime;


    private Date updateTime;


    private Date deleteTime;


}
