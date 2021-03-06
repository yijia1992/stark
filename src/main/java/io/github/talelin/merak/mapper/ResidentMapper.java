package io.github.talelin.merak.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.merak.common.mybatis.Page;
import io.github.talelin.merak.model.ResidentDO;
import io.github.talelin.merak.model.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Bizzbee
 * @since 2019-12-02
 */
@Repository
public interface ResidentMapper extends BaseMapper<ResidentDO> {

    /**
     * 查询用户名为$username的人数
     *
     * @param username 用户名
     * @return 人数
     */
    int selectCountByUsername(String username);

    //查看用户修改的身份证号是否与别人相同
    int selectCountByUserNo(String userNo,Long id);

    //查询默认的父母（其中一个）
    ResidentDO selectByUserNo(String userNo);

    //查询默认的父母（其中一个）
    ResidentDO selectByUsername(String username);

    List<ResidentDO> getchildren(long id1,long id2);

}
