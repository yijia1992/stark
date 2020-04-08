package io.github.talelin.merak.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.merak.model.AshinStayDo;
import io.github.talelin.merak.model.AshinWorkHomeDo;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Bizzbee
 * @since 2019-12-02
 */
@Repository
public interface AshinWorkHomeMapper extends BaseMapper<AshinWorkHomeDo> {

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
