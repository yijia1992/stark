package io.github.talelin.merak.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.merak.dto.ashin.AshinDataDTO;
import io.github.talelin.merak.model.AshinBaseDo;
import io.github.talelin.merak.model.AshinFlowDo;
import io.github.talelin.merak.vo.CoordVO;
import io.github.talelin.merak.vo.PointVO;

import java.util.List;

/**
 * 用户业务
 *
 * @author Bizzbee
 * @since 2020-3-10
 */
public interface AshinFlowService extends IService<AshinFlowDo> {

    List<AshinFlowDo> getFlows();

//    boolean createAct(ActAddDTO validator);
//
//    List<ActVO> findAll();
//
//    boolean deleteById(int id);


//    boolean createIns(InsAddDTO dto);
//
//    List<InsVO> findAll();
//
//    boolean deleteById(int id);
//
//
//    boolean updateIns(InsDO ins, InsUpdateDTO dto);

}
