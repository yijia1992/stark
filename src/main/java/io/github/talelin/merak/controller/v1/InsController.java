package io.github.talelin.merak.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.merak.common.utils.ResponseUtil;
import io.github.talelin.merak.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.merak.dto.ins.InsAddDTO;
import io.github.talelin.merak.dto.ins.InsUpdateDTO;
import io.github.talelin.merak.dto.resident.FamilyEditDTO;
import io.github.talelin.merak.dto.resident.UpdateResidentDTO;
import io.github.talelin.merak.mapper.ResidentMapper;
import io.github.talelin.merak.model.InsDO;
import io.github.talelin.merak.model.ResidentDO;
import io.github.talelin.merak.model.UserDO;
import io.github.talelin.merak.service.FileService;
import io.github.talelin.merak.service.InsService;
import io.github.talelin.merak.service.ResidentService;
import io.github.talelin.merak.service.UserService;
import io.github.talelin.merak.vo.InsVO;
import io.github.talelin.merak.vo.ResidentFamilyVO;
import io.github.talelin.merak.vo.ResidentVO;
import io.github.talelin.merak.vo.UnifyResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/ins")
@Validated
public class InsController {

    @Autowired
    private InsService insService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResidentMapper residentMapper;

    @PostMapping("")
    public UnifyResponseVO createIns(@RequestBody @Validated InsAddDTO validator) {
        //bookService.createBook(validator);
        insService.createIns(validator);
        return ResponseUtil.generateUnifyResponse(21);
    }

    @GetMapping("")
    public List<InsVO> getBooks() {
        List<InsVO> vos = insService.findAll();
        return vos;
    }

    @GetMapping("/{id}")
    public InsDO getIns(@PathVariable(value = "id") @Positive(message = "{id}") int id) {
        InsDO ins = insService.getById(id);
        if (ins == null) {
            throw new NotFoundException("没有找到账户", 10022);
        }
        return ins;
    }

    //这里的id是系统用户的id
    //用于显示用户资料用
    @GetMapping("/info/{id}")
    public InsDO getInfo(@PathVariable(value = "id") @Positive(message = "id必须为正整数") Long id){
        UserDO udo = userService.getById(id);
        String username = udo.getUsername();
        ResidentDO rdo = residentMapper.selectByUsername(username);
        InsDO doo = this.getIns(rdo.getInsId());
        return doo;
    }

    @PutMapping("/{id}")
    public UnifyResponseVO updateIns(@PathVariable("id") @Positive(message = "{id}") int id, @RequestBody @Validated InsUpdateDTO validator) {
        InsDO ins = insService.getById(id);
        if (ins == null) {
            throw new NotFoundException("未找到账户", 10022);
        }
        insService.updateIns(ins,validator);
        return ResponseUtil.generateUnifyResponse(23);
    }


    @DeleteMapping("/{id}")
    public UnifyResponseVO deleteIns(@PathVariable("id") @Positive(message = "{id}") Integer id) {
        InsDO ins = insService.getById(id);
        if (ins == null) {
            throw new NotFoundException("没有此账户", 10022);
        }
       // bookService.deleteById(book.getId());
        insService.deleteById(id);
        return ResponseUtil.generateUnifyResponse(22);
    }



}
