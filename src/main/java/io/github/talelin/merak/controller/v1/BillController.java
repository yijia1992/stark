package io.github.talelin.merak.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.merak.common.utils.ResponseUtil;
import io.github.talelin.merak.dto.act.ActAddDTO;
import io.github.talelin.merak.model.ActDO;
import io.github.talelin.merak.model.BillDO;
import io.github.talelin.merak.service.ActService;
import io.github.talelin.merak.service.BillService;
import io.github.talelin.merak.vo.ActVO;
import io.github.talelin.merak.vo.BillVO;
import io.github.talelin.merak.vo.UnifyResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/bill")
@Validated
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/{type}")
    public List<BillVO> getBillsByType(@PathVariable(value = "type") int type) {
        List<BillVO> vos = billService.getBillsByType(type);
        return vos;
    }

    @GetMapping("pay/{id}")
    public UnifyResponseVO pay(@PathVariable(value = "id") int id) {
         BillDO bdo = new BillDO();
         bdo.setId(id);
         //改为已缴费
         bdo.setStatus(1);
         billService.updateById(bdo);
         return ResponseUtil.generateUnifyResponse(43);
    }

    //用于用户展示自己的费用
    @GetMapping("/{id}/{type}")
    public List<BillVO> getmyBills(@PathVariable(value = "id") int id,@PathVariable(value = "type") int type) {
        List<BillVO> vos = billService.getMyBillsByType(id,type);
        return vos;
    }

    @GetMapping("")
    public List<BillVO> getBills() {
        List<BillVO> vos = billService.getBills();
        return vos;
    }




//    //添加或者修改计生服务
//    @PostMapping("")
//    public UnifyResponseVO createActOrUpdate(@RequestBody @Validated ActAddDTO validator) {
//        actService.createAct(validator);
//        return ResponseUtil.generateUnifyResponse(31);
//    }
//
//    @GetMapping("")
//    public List<ActVO> getActs() {
//        List<ActVO> vos = actService.findAll();
//        return vos;
//    }
//
//    @DeleteMapping("/{id}")
//    public UnifyResponseVO deleteIns(@PathVariable("id") @Positive(message = "{id}") Integer id) {
//        ActDO ins = actService.getById(id);
//        if (ins == null) {
//            throw new NotFoundException("没有此账户", 10022);
//        }
//        // bookService.deleteById(book.getId());
//        actService.removeById(id);
//        return ResponseUtil.generateUnifyResponse(32);
//    }





}
