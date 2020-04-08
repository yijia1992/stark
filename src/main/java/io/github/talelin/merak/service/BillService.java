package io.github.talelin.merak.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.merak.dto.act.ActAddDTO;
import io.github.talelin.merak.model.ActDO;
import io.github.talelin.merak.model.BillDO;
import io.github.talelin.merak.vo.ActVO;
import io.github.talelin.merak.vo.BillVO;

import java.util.List;

/**
 * 用户业务
 *
 * @author Bizzbee
 * @since 2020-3-10
 */
public interface BillService extends IService<BillDO> {


    List<BillVO> getBillsByType(int type);

    List<BillVO> getBills();


    //这里的id是系统用户id
    List<BillVO> getMyBillsByType(int uid,int type);



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
