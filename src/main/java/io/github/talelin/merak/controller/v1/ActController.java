package io.github.talelin.merak.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.RouteMeta;
import io.github.talelin.merak.common.utils.ResponseUtil;
import io.github.talelin.merak.dto.act.ActAddDTO;
import io.github.talelin.merak.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.merak.model.ActDO;
import io.github.talelin.merak.model.BookDO;
import io.github.talelin.merak.service.ActService;
import io.github.talelin.merak.service.BookService;
import io.github.talelin.merak.vo.ActVO;
import io.github.talelin.merak.vo.UnifyResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/act")
@Validated
public class ActController {

    @Autowired
    private ActService actService;

    //添加或者修改计生服务
    @PostMapping("")
    public UnifyResponseVO createActOrUpdate(@RequestBody @Validated ActAddDTO validator) {
        actService.createAct(validator);
        return ResponseUtil.generateUnifyResponse(31);
    }

//    @GetMapping("/{id}")
//    public BookDO getBook(@PathVariable(value = "id") @Positive(message = "{id}") Long id) {
//        BookDO book = bookService.getById(id);
//        if (book == null) {
//            throw new NotFoundException("book not found", 10022);
//        }
//        return book;
//    }
//
    @GetMapping("")
    public List<ActVO> getActs() {
        List<ActVO> vos = actService.findAll();
        return vos;
    }

    @DeleteMapping("/{id}")
    public UnifyResponseVO deleteIns(@PathVariable("id") @Positive(message = "{id}") Integer id) {
        ActDO ins = actService.getById(id);
        if (ins == null) {
            throw new NotFoundException("没有此账户", 10022);
        }
        // bookService.deleteById(book.getId());
        actService.removeById(id);
        return ResponseUtil.generateUnifyResponse(32);
    }


}
