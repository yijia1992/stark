package io.github.talelin.merak.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.merak.dto.act.ActAddDTO;
import io.github.talelin.merak.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.merak.dto.ins.InsAddDTO;
import io.github.talelin.merak.dto.ins.InsUpdateDTO;
import io.github.talelin.merak.model.ActDO;
import io.github.talelin.merak.model.InsDO;
import io.github.talelin.merak.vo.ActVO;
import io.github.talelin.merak.vo.InsVO;

import java.util.List;

/**
 * 用户业务
 *
 * @author Bizzbee
 * @since 2020-3-10
 */
public interface ActService extends IService<ActDO> {

    boolean createAct(ActAddDTO validator);

    List<ActVO> findAll();

    boolean deleteById(int id);


//    boolean createIns(InsAddDTO dto);
//
//    List<InsVO> findAll();
//
//    boolean deleteById(int id);
//
//
//    boolean updateIns(InsDO ins, InsUpdateDTO dto);

}
