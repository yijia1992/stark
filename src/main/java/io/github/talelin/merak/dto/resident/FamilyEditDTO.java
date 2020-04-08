package io.github.talelin.merak.dto.resident;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class FamilyEditDTO {

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
    //@Size(max = 100, message = "{book.image.size}")
    @Positive(message = "{group.id.positive}")
    @NotNull(message = "{group.id.not-null}")
    private Long id;

    @Size(max = 100, message = "身份证长度超过限制")
    private String parent_no;
    @Size(max = 100, message = "身份证长度超过限制")
    private String lover_no;





}
