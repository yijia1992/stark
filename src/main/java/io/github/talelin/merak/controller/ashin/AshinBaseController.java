package io.github.talelin.merak.controller.ashin;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.merak.dto.ashin.AshinDataDTO;
import io.github.talelin.merak.model.AshinDistanceDo;
import io.github.talelin.merak.model.AshinFlowDo;
import io.github.talelin.merak.model.AshinOutDo;
import io.github.talelin.merak.model.AshinStayDo;
import io.github.talelin.merak.service.*;
import io.github.talelin.merak.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/ashin/base")
@Validated
public class AshinBaseController {
    @Autowired
    private AshinBaseService ashinBaseService;
    @Autowired
    private AshinStayService ashinStayService;
    @Autowired
    private AshinWorkHomeService ashinWorkHomeService;
    @Autowired
    private AshinDistanceService ashinDistanceService;
    @Autowired
    private AshinFlowService ashinFlowService;
    @Autowired
    private AshinOutService ashinOutService;


//  路径图
    @GetMapping("")
    public List<List<PointVO>> getPath() {
        List<List<PointVO>> res = ashinBaseService.getPonitsGroup();
//
        return res;
    }

//    [
//            [{
//        "coord": [120.14322240845, 30.236064370321],
//        "elevation": 21
//    }, {
//        "coord": [120.14280555506, 30.23633761213],
//        "elevation": 5
//    }, {
//        "coord": [120.14307598649, 30.236125905084],
//        "elevation": 30.7
//    }]


    @PostMapping("hot")
    public List<List<CoordVO>> getHot(@RequestBody AshinDataDTO dto) {
        List<List<CoordVO>> res = ashinBaseService.getCoords(dto);
        return res;
    }

    //驻点展示
    @GetMapping("/stay")
    public List<AshinStayDo> getStays() {
        List<AshinStayDo> res = ashinStayService.getAllStays();
        if (res.size()==0) {
            throw new NotFoundException("没有找到数据", 10022);
        }
        return res;
    }

    //职住点展示
    @GetMapping("/workhome")
    public WorkHomeVO getWorkHome() {
        WorkHomeVO vo= ashinWorkHomeService.getVo();
        if (vo.getHomes().size()==0&&vo.getHomes().size()==0) {
            throw new NotFoundException("没有找到数据", 10022);
        }
        return vo;
    }

    //职住点展示
    @GetMapping("/flow")
    public List<AshinFlowDo> getFlow() {
        List<AshinFlowDo> doos = ashinFlowService.getFlows();
        if (doos.size()==0) {
            throw new NotFoundException("没有找到数据", 10022);
        }
        return doos;
    }

    //人流量展示
    @GetMapping("/dis")
    public DistanceVO getDis() {
        DistanceVO vo= ashinDistanceService.getLevelCount();
        if (vo==null) {
            throw new NotFoundException("没有找到数据", 10022);
        }
        return vo;
    }

    @GetMapping("/type")
    public List<AshinOutDo> getType() {
        //ashinTypeService.getTypeCount();
        List<AshinOutDo> list = ashinOutService.getList();
        if (list.size()==0) {
            throw new NotFoundException("没有找到数据", 10022);
        }
        return list;

    }



//    @Autowired
//    private ActService actService;
//
//    //添加或者修改计生服务
//    @PostMapping("")
//    public UnifyResponseVO createActOrUpdate(@RequestBody @Validated ActAddDTO validator) {
//        actService.createAct(validator);
//        return ResponseUtil.generateUnifyResponse(31);
//    }

//    @GetMapping("/{id}")
//    public BookDO getBook(@PathVariable(value = "id") @Positive(message = "{id}") Long id) {
//        BookDO book = bookService.getById(id);
//        if (book == null) {
//            throw new NotFoundException("book not found", 10022);
//        }
//        return book;
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
