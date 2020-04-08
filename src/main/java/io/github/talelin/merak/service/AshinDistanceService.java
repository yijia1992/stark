package io.github.talelin.merak.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.merak.dto.ashin.AshinDataDTO;
import io.github.talelin.merak.model.AshinBaseDo;
import io.github.talelin.merak.model.AshinDistanceDo;
import io.github.talelin.merak.vo.CoordVO;
import io.github.talelin.merak.vo.DistanceVO;
import io.github.talelin.merak.vo.PointVO;

import java.util.List;

/**
 * 用户业务
 *
 * @author Bizzbee
 * @since 2020-3-10
 */
public interface AshinDistanceService extends IService<AshinDistanceDo> {

    DistanceVO getLevelCount();

    //获得所有人所有的定位点（分组）
//    List<List<PointVO>> getPonitsGroup();
//
//    //热力图用
//    List<List<CoordVO>> getCoords(AshinDataDTO dto);

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
