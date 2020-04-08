package io.github.talelin.merak.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.merak.model.AshinBaseDo;
import io.github.talelin.merak.model.AshinStayDo;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Bizzbee
 * @since 2019-12-02
 */
@Repository
public interface AshinStayMapper extends BaseMapper<AshinStayDo> {

//    int selectCountByUsername(String username);
//
//    //查看用户修改的身份证号是否与别人相同
//    int selectCountByUserNo(String userNo, Long id);
//
//    //查询默认的父母（其中一个）
//    ResidentDO selectByUserNo(String userNo);
//
//    List<ResidentDO> getchildren(long id1, long id2);

      List<AshinStayDo> queryStaysByImsi(String imsi);

}
