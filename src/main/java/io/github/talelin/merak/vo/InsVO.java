package io.github.talelin.merak.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsVO {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //居民id
    private long rid;

    //姓名
    private String name;

    //账户状态  1.生效 2.欠费
    private Integer status;

    //用户状态的描述
    private String sstatus;

    //账户余额
    private BigDecimal money;

    //缴费途径：简化成直接填写公司或者个人缴费
    private String type;

    public String getSstatus() {
        return status==1?"正常缴费":"欠费";
    }

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Date deleteTime;



}
