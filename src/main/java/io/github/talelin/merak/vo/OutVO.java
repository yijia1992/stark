package io.github.talelin.merak.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutVO {
    private static final long serialVersionUID = 1L;

    private int value;
    private String name;

}
