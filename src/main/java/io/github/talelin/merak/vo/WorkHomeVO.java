package io.github.talelin.merak.vo;

import io.github.talelin.merak.model.AshinWorkHomeDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkHomeVO {
    private static final long serialVersionUID = 1L;

   private List<AshinWorkHomeDo> homes;

   private List<AshinWorkHomeDo> works;



}
