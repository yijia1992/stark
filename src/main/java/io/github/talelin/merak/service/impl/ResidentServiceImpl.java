package io.github.talelin.merak.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.FailedException;
import io.github.talelin.autoconfigure.exception.ForbiddenException;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.autoconfigure.exception.ParameterException;
import io.github.talelin.merak.common.LocalUser;
import io.github.talelin.merak.common.mybatis.Page;
import io.github.talelin.merak.dto.resident.FamilyEditDTO;
import io.github.talelin.merak.dto.resident.UpdateResidentDTO;
import io.github.talelin.merak.dto.user.ChangePasswordDTO;
import io.github.talelin.merak.dto.user.RegisterDTO;
import io.github.talelin.merak.dto.user.UpdateInfoDTO;
import io.github.talelin.merak.mapper.ResidentMapper;
import io.github.talelin.merak.mapper.UserGroupMapper;
import io.github.talelin.merak.mapper.UserMapper;
import io.github.talelin.merak.model.*;
import io.github.talelin.merak.service.*;
import io.github.talelin.merak.vo.ResidentFamilyVO;
import io.github.talelin.merak.vo.ResidentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pedro
 * @since 2019-11-30
 */
@Service
public class ResidentServiceImpl extends ServiceImpl<ResidentMapper, ResidentDO> implements ResidentService {

    @Autowired
    private ResidentMapper residentMapper;

    //在创建用户时勾选创建具名资料，生成初始账户
    @Override
    public boolean createResidentByUsername(RegisterDTO reg) {
        ResidentDO res = new ResidentDO();
        res.setUsername(reg.getUsername());
        this.baseMapper.insert(res);

        return true;
    }

    @Override
    public ResidentDO getById(Long id) {
        ResidentDO residentDO = residentMapper.selectById(id);
        return residentDO;
    }

    @Override
    public Boolean updateResident(UpdateResidentDTO dto) {
        //如果用户修改的身份证号系统中已有
        if (residentMapper.selectCountByUserNo(dto.getUserNo(),dto.getId())>0){
            throw new ForbiddenException("身份证号与他人重复");
        }
        ResidentDO resident = new ResidentDO();
        BeanUtil.copyProperties(dto,resident);
        return residentMapper.updateById(resident)>0;
    }

    @Override
    public List<ResidentDO> findAll() {
        List<ResidentDO> residentDOs = residentMapper.selectList(null);

        return residentDOs;
    }

    @Override
    public List<ResidentVO> findAllVO() {
        List<ResidentDO> residentDOs = residentMapper.selectList(null);
        List<ResidentVO> residentVOS = new ArrayList<>();
        for(ResidentDO doo : residentDOs){
            ResidentVO vo = new ResidentVO();
            BeanUtil.copyProperties(doo,vo);
            residentVOS.add(vo);
        }
        return residentVOS;
    }

    @Override
    public boolean deleteById(Long id) {
        return residentMapper.deleteById(id) > 0;
    }

    @Override
    public List<ResidentFamilyVO> getFamilyList() {
        List<ResidentFamilyVO> fvos = new ArrayList<>();
        List<ResidentDO> residentDOS = this.findAll();
        for(ResidentDO doo : residentDOS){
            ResidentFamilyVO fvo =new ResidentFamilyVO();
            BeanUtil.copyProperties(doo,fvo);
            //查询父母之一
            ResidentDO parent = this.getById(fvo.getParentId());
            if (parent!=null){
                //用于展示列表，只需要名字
                fvo.setParentName(parent.getName());
            }
            ResidentDO lover = this.getById(fvo.getLoverId());
            if (lover!=null){
                //用于展示列表，只需要名字
                fvo.setLoverName(lover.getName());
            }
            fvos.add(fvo);

        }
        return fvos;
    }

    @Override
    public List<ResidentFamilyVO> getWomenInfo(int istemp) {
        List<ResidentFamilyVO> fvos = new ArrayList<>();
        List<ResidentDO> residentDOS = this.findAll();

        for(ResidentDO doo : residentDOS){
            if (doo.getGender()==1||doo.getIsTemp()!=istemp){
                continue;
            }
            ResidentFamilyVO fvo = this.getRelationPanel(doo.getId());
            fvo.setChildrenCount(fvo.getChildren().size());
            if (fvo.getChildrenCount()!=0) {
                fvos.add(fvo);
            }
        }
        return fvos;
    }

    @Override
    public ResidentFamilyVO getRelation(Long id) {
        ResidentDO doo = this.getById(id);
        ResidentFamilyVO fvo = new ResidentFamilyVO();
        if(doo!=null){
            BeanUtil.copyProperties(doo,fvo);

            ResidentDO parent = this.getById(fvo.getParentId());
            ResidentDO lover = this.getById(fvo.getLoverId());
            if(parent!=null) fvo.setParentNo(parent.getUserNo());
            if(lover!=null) fvo.setLoverNo(lover.getUserNo());
        }else{
            throw new NotFoundException("用户不存在");
        }

        return fvo;
    }

    //修改家庭关系
    @Override
    public boolean updateRelation(FamilyEditDTO dto) {
        //验证传入的身份证是否是有效用户的,除去本人的情况下
        if (null!=dto.getParent_no()&&!dto.getParent_no().isEmpty()) {
            int l = residentMapper.selectCountByUserNo(dto.getParent_no(), dto.getId());
            if (l==0){
                throw new NotFoundException("身份证无效");
            }
        }
        if(null!=dto.getLover_no()&&!dto.getLover_no().isEmpty()){
            int p = residentMapper.selectCountByUserNo(dto.getLover_no(), dto.getId());
            if (p==0){
                throw new NotFoundException("身份证无效");
            }
        }
//        if(residentMapper.selectById(dto.getId()).getUserNo().equals(dto.getLover_no())||residentMapper.selectById(dto.getId()).getUserNo().equals(dto.getParent_no())){
//            throw new ForbiddenException("亲属不能设置成本人的身份证");
//        }
        ResidentDO parent = residentMapper.selectByUserNo(dto.getParent_no());
        ResidentDO lover = residentMapper.selectByUserNo(dto.getLover_no());
        ResidentDO doo = new ResidentDO();
        doo.setId(dto.getId());
        if(parent!=null) doo.setParentId(parent.getId());
        if(lover!=null) doo.setLoverId(lover.getId());
        this.baseMapper.updateById(doo);
        return true;
    }

    @Override
    public ResidentFamilyVO getRelationPanel(Long id) {
        ResidentDO doo = this.getById(id);
        ResidentFamilyVO fvo = new ResidentFamilyVO();
        if(doo!=null){
            BeanUtil.copyProperties(doo,fvo);

            ResidentDO lover = this.getById(fvo.getLoverId());
            if (lover!=null){
                fvo.setLover(lover);
            }

            ResidentDO parent1 = this.getById(fvo.getParentId());
            ResidentDO parent2 = null;
            //先把父母之一设置上
            if (parent1!=null){
                if (parent1.getGender()==1){
                    fvo.setFather(parent1);
                }else{
                    fvo.setMather(parent1);
                }
                //通过父母之一的配偶id找到另一个
                parent2 = this.getById(parent1.getLoverId());
                if(parent2!=null){
                    if (parent2.getGender()==1){
                        fvo.setFather(parent2);
                    }else{
                        fvo.setMather(parent2);
                    }
                }else{
                    //如果没有parent2，
                    //为了保证设置孩子mapper不是空指针
                    //新建一个
                    parent2 = new ResidentDO();
                }
            }
            //设置孩子

            List<ResidentDO> children = residentMapper.getchildren(fvo.getId(), fvo.getLoverId()!=0?fvo.getLoverId():-1);

            fvo.setChildren(children);
        }else{
            throw new NotFoundException("用户不存在");
        }

        return fvo;
    }
}
