package io.github.talelin.merak.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.merak.model.InsDO;
import io.github.talelin.merak.model.ResidentDO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Bizzbee
 * @since 2019-12-02
 */
@Repository
public interface InsMapper extends BaseMapper<InsDO> {

    int selectCountByRid(long rid);




    /**
     * 查询用户名为$username的人数
     *
     * @param username 用户名
     * @return 人数
     */
//    int selectCountByUsername(String username);
//
//    //查看用户修改的身份证号是否与别人相同
//    int selectCountByUserNo(String userNo, Long id);
//
//    //查询默认的父母（其中一个）
//    ResidentDO selectByUserNo(String userNo);
//
//    List<ResidentDO> getchildren(long id1, long id2);

}
