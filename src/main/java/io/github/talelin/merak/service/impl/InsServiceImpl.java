package io.github.talelin.merak.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.ForbiddenException;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.merak.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.merak.dto.ins.InsAddDTO;
import io.github.talelin.merak.dto.ins.InsUpdateDTO;
import io.github.talelin.merak.dto.resident.FamilyEditDTO;
import io.github.talelin.merak.dto.resident.UpdateResidentDTO;
import io.github.talelin.merak.dto.user.RegisterDTO;
import io.github.talelin.merak.mapper.InsMapper;
import io.github.talelin.merak.mapper.ResidentMapper;
import io.github.talelin.merak.model.BookDO;
import io.github.talelin.merak.model.InsDO;
import io.github.talelin.merak.model.ResidentDO;
import io.github.talelin.merak.service.InsService;
import io.github.talelin.merak.service.ResidentService;
import io.github.talelin.merak.vo.InsVO;
import io.github.talelin.merak.vo.ResidentFamilyVO;
import io.github.talelin.merak.vo.ResidentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pedro
 * @since 2019-11-30
 */
@Service
public class InsServiceImpl extends ServiceImpl<InsMapper, InsDO> implements InsService {

    @Autowired
    private InsMapper insMapper;
    @Autowired
    private ResidentService residentService;
    @Autowired
    private ResidentMapper residentMapper;

    @Transactional
    @Override
    public boolean createIns(InsAddDTO dto) {
        //通过身份证找到具名 账户
        ResidentDO doo = residentMapper.selectByUserNo(dto.getUserNo());
        if (doo==null){
            throw new NotFoundException("身份证无效");
        }
        int num = insMapper.selectCountByRid(doo.getId());
        if(num>0){
            throw new ForbiddenException("已经存在医保账户");
        }
        InsDO idoo = new InsDO();
        idoo.setRid(doo.getId());
        idoo.setMoney(dto.getMoney());
        idoo.setStatus(dto.getStatus());
        idoo.setType(dto.getType());
        this.insMapper.insert(idoo);
        doo.setInsId(idoo.getId());
        residentService.updateById(doo);
        return true;
    }

//    public void addInsIdToResdident(InsAddDTO dto){
//        this.insMapper.selectById()
//    }

    @Override
    public List<InsVO> findAll() {
        List<InsDO> doos = this.insMapper.selectList(null);
        List<InsVO> vos =new ArrayList<>();
        for (InsDO doo : doos){
            InsVO vo = new InsVO();
            ResidentDO rdo = residentService.getById(doo.getRid());
            BeanUtil.copyProperties(doo,vo);
            vo.setName(rdo.getName());
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public boolean deleteById(int id) {
        return this.insMapper.deleteById(id)>0;
    }

    @Override
    public boolean updateIns(InsDO ins, InsUpdateDTO dto) {
        ins.setType(dto.getType());
        ins.setStatus(dto.getStatus());
        ins.setMoney(dto.getMoney());
        return insMapper.updateById(ins)>0;
    }
}
