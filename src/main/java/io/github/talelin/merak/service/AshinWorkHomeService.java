package io.github.talelin.merak.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.merak.model.AshinStayDo;
import io.github.talelin.merak.model.AshinWorkHomeDo;
import io.github.talelin.merak.vo.WorkHomeVO;

import java.util.List;

/**
 * 用户业务
 *
 * @author Bizzbee
 * @since 2020-3-10
 */
public interface AshinWorkHomeService extends IService<AshinWorkHomeDo> {
    WorkHomeVO getVo();
    //获得所有人所有的定位点（分组）
//    List<List<PointVO>> getPonitsGroup();
//
//    //热力图用
//    List<List<CoordVO>> getCoords(AshinDataDTO dto);
//      List<AshinStayDo> getStaysByImsi(String imsi);
//
//      List<AshinStayDo> getAllStays();

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
