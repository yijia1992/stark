package io.github.talelin.merak.dto.ins;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class InsAddDTO {

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
//    @NotNull(message = "必须有id")
//    private Long id;
    //通过身份证号关联用户账户
    @NotEmpty(message = "身份证号不能为空")
    private String userNo;

    //账户状态  1.生效 2.欠费
    @NotNull(message = "必须设置账户状态")
    private Integer status;

    //账户余额
    @NotNull(message = "必须填写账户余额")
    @Positive
    private BigDecimal money;

    @NotEmpty(message = "缴费途径不能为空")
    private String type;




}
