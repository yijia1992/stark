package io.github.talelin.merak.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.merak.common.mybatis.Page;
import io.github.talelin.merak.dto.resident.FamilyEditDTO;
import io.github.talelin.merak.dto.resident.UpdateResidentDTO;
import io.github.talelin.merak.dto.user.ChangePasswordDTO;
import io.github.talelin.merak.dto.user.RegisterDTO;
import io.github.talelin.merak.dto.user.UpdateInfoDTO;
import io.github.talelin.merak.model.GroupDO;
import io.github.talelin.merak.model.PermissionDO;
import io.github.talelin.merak.model.ResidentDO;
import io.github.talelin.merak.model.UserDO;
import io.github.talelin.merak.vo.ResidentFamilyVO;
import io.github.talelin.merak.vo.ResidentVO;

import java.util.List;
import java.util.Map;

/**
 * 用户业务
 *
 * @author pedro
 * @since 2019-11-30
 */
public interface ResidentService extends IService<ResidentDO> {

    //注册时如果勾选创建具名，那么创建一个只有username的原始记录
    boolean createResidentByUsername(RegisterDTO reg);

    ResidentDO getById(Long id);

    Boolean updateResident(UpdateResidentDTO dto);

    List<ResidentDO> findAll();
    List<ResidentVO> findAllVO();

    boolean deleteById(Long id);

    List<ResidentFamilyVO> getFamilyList();

    //
    List<ResidentFamilyVO> getWomenInfo(int istemp);



    //获取父母之一的no和配偶的no
    ResidentFamilyVO getRelation(Long id);

    //修改家庭关系
    boolean updateRelation(FamilyEditDTO dto);

    ResidentFamilyVO getRelationPanel(Long id);
}
