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
public class BillVO {
    private static final long serialVersionUID = 1L;


    private Integer id;
    //具名id
    private Long rid;

    //用户的姓名
    private String name;

    //账单费用
    private BigDecimal money;
    //xx年xx月
    private String time;

    //1.水 2.电 3.燃气
    private Integer type;

    private String stype;

    private int status;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    @TableLogic
    private Date deleteTime;

//    public String getSstartTime() {
//        return MuUtil.getFormatTime(this.startTime);
//    }


    public String getStype() {
        if (this.type==1){
            return "水费";
        }else if(this.type==2){
            return "电费";
        }else{
            return "燃气费";
        }

    }
}
