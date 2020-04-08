package io.github.talelin.merak.dto.resident;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class UpdateResidentDTO {

//    @NotEmpty(message = "{book.title.not-empty}")
//    @Size(max = 50, message = "{book.title.size}")
//    private String title;
//
//    @NotEmpty(message = "{book.author.not-empty}")
//    @Size(max = 50, message = "{book.author.size}")
//    private String author;
//
//    @NotEmpty(message = "{book.summary.not-empty}")
//    @Size(max = 1000, message = "{book.summary.size}")
//    private String summary;
//
//    @Size(max = 100, message = "{book.image.size}")
//    private String image;
    @NotNull(message = "必须有id")
    private Long id;

    /**
     * 用户名，唯一，用于登陆
     * User表中username的外键
     */
//    @NotEmpty(message = "必须填写用户名")
//    private String username;

    /**
     * 用户大名
     */
    @NotEmpty(message = "必须填写姓名")
    private String name;

    //性别 0/1
    @NotNull(message = "必须选择性别")
    private int gender;

    //年龄
    @NotNull(message = "必须填写年龄")
    private int age;

    //身份证号 居民身份是否可用以身份证号是否存在为准
    @NotEmpty(message = "必须填写身份证")
    private String userNo;

    //头像地址
    private String avatar;

    private int parentId;

    private int loverId;

    private int insId;

    private int waterId;

    private int gasId;

    private int isPoor;

    private int isTemp;
    /**
     * 电话
     */
    private String phone;



}
