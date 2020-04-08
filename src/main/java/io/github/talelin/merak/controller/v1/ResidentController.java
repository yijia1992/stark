package io.github.talelin.merak.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.RouteMeta;
import io.github.talelin.merak.bo.FileBO;
import io.github.talelin.merak.common.utils.ResponseUtil;
import io.github.talelin.merak.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.merak.dto.resident.FamilyEditDTO;
import io.github.talelin.merak.dto.resident.UpdateResidentDTO;
import io.github.talelin.merak.mapper.ResidentMapper;
import io.github.talelin.merak.model.BookDO;
import io.github.talelin.merak.model.ResidentDO;
import io.github.talelin.merak.model.UserDO;
import io.github.talelin.merak.service.BookService;
import io.github.talelin.merak.service.FileService;
import io.github.talelin.merak.service.ResidentService;
import io.github.talelin.merak.service.UserService;
import io.github.talelin.merak.utils.PathUtil;
import io.github.talelin.merak.utils.StringUtil;
import io.github.talelin.merak.vo.ResidentFamilyVO;
import io.github.talelin.merak.vo.ResidentVO;
import io.github.talelin.merak.vo.UnifyResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/resident")
@Validated
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResidentMapper residentMapper;





    //没有新增接口，居民的初始账户在创建用户时同时创建

    @GetMapping("/{id}")
    public ResidentDO getResident(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Long id) {
        ResidentDO resident = residentService.getById(id);
        if (resident == null) {
            throw new NotFoundException("未找到相应居民", 10021);
        }
        return resident;
    }

    //这里的id是系统用户的id
    //用于显示用户资料用
    @GetMapping("/info/{id}")
    public ResidentDO getInfo(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Long id){
        UserDO udo = userService.getById(id);
        String username = udo.getUsername();
        ResidentDO rdo = residentMapper.selectByUsername(username);
        ResidentDO doo = this.getResident(rdo.getId());
        return doo;
    }



    @PostMapping("")
    public UnifyResponseVO updateResident(@RequestBody @Validated UpdateResidentDTO validator) {
        residentService.updateResident(validator);
        return ResponseUtil.generateCreatedResponse("修改居民成功");
    }

    @GetMapping("")
    public List<ResidentVO> getResidents() {
        List<ResidentVO> residentVos = residentService.findAllVO();
        return residentVos;
    }

    @DeleteMapping("/{id}")
    public UnifyResponseVO deleteResident(@PathVariable("id") @Positive(message = "{id}") Long id) {
        ResidentDO resident = residentService.getById(id);
        if (resident == null) {
            throw new NotFoundException("book not found", 10022);
        }
        residentService.deleteById(resident.getId());
        return ResponseUtil.generateUnifyResponse(12);
    }

    @GetMapping("family")
    public List<ResidentFamilyVO> getFamilyList() {
        List<ResidentFamilyVO> residentFamilyVOS = residentService.getFamilyList();
        return residentFamilyVOS;
    }

    @GetMapping("women/{temp}")
    public List<ResidentFamilyVO> getWomenList(@PathVariable(value = "temp") int temp) {
        List<ResidentFamilyVO> residentFamilyVOS = residentService.getWomenInfo(temp);
        return residentFamilyVOS;
    }

    //获得修改家庭关系初始数据
    @GetMapping("family/{id}")
    public ResidentFamilyVO getFamily(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Long id) {
        ResidentFamilyVO fvo = residentService.getRelation(id);
        return fvo;
    }

    //修改家庭关系
    @PostMapping("family")
    public UnifyResponseVO updateRelation(@RequestBody @Validated FamilyEditDTO dto) {
        //residentService.updateResident(validator);
        residentService.updateRelation(dto);
        return ResponseUtil.generateCreatedResponse("修改家庭成功");
    }

    //用于家在家庭关系看板的数据
    @GetMapping("/family/panel/{id}")
    public ResidentFamilyVO getRelations(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Long id) {
        ResidentFamilyVO relation = residentService.getRelationPanel(id);
        if(relation==null){
            throw new NotFoundException("没有该用户", 10021);
        }
        return relation;

    }










}
