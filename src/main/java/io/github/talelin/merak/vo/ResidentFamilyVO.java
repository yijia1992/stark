package io.github.talelin.merak.vo;

import io.github.talelin.merak.model.ResidentDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentFamilyVO {

    private Long id;

    private String username;
    /**
     * 用户大名
     */
    private String name;

    //性别 0/1
    private int gender;

    private String sgender;

    //年龄
    private int age;

    private int isTemp;


    //父母之一
    private int parentId;

    //父母之一的身份证号
    private String parentNo;

    //父母之一的名字,用于展示
    private String parentName;

    private ResidentDO father;

    private ResidentDO mather;

    private ResidentDO lover;

    private int loverId;

    private String loverName;

    private String loverNo;

    private List<ResidentDO> children;

    private int childrenCount;

    //列表里有几个，就有几个孩子。
//    public int getChildrenCount() {
//        return this.children.size()>0?this.children.size():0;
//    }

    public String getSgender() {
        return this.gender==1?"男":"女";

    }
}
