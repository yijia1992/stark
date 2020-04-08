package io.github.talelin.merak.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.merak.dto.act.ActAddDTO;
import io.github.talelin.merak.mapper.ActMapper;
import io.github.talelin.merak.mapper.BillMapper;
import io.github.talelin.merak.mapper.BookMapper;
import io.github.talelin.merak.mapper.ResidentMapper;
import io.github.talelin.merak.model.ActDO;
import io.github.talelin.merak.model.BillDO;
import io.github.talelin.merak.model.ResidentDO;
import io.github.talelin.merak.model.UserDO;
import io.github.talelin.merak.service.ActService;
import io.github.talelin.merak.service.BillService;
import io.github.talelin.merak.service.ResidentService;
import io.github.talelin.merak.service.UserService;
import io.github.talelin.merak.utils.MuUtil;
import io.github.talelin.merak.vo.ActVO;
import io.github.talelin.merak.vo.BillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, BillDO> implements BillService {

    @Autowired
    private BillMapper billMapper;
    @Autowired
    private ResidentService residentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResidentMapper residentMapper;


    @Override
    public List<BillVO> getBillsByType(int type) {
        List<BillDO> dos = list();
        List<BillVO> vos = new ArrayList<>();
        for(BillDO doo : dos){
            if (doo.getType()!=type){
                continue;
            }
            BillVO vo = new BillVO();
            BeanUtil.copyProperties(doo,vo);
            String name = residentService.getById(doo.getRid()).getName();
            vo.setName(name);
            vos.add(vo);
        }

        return vos;
    }

    @Override
    public List<BillVO> getBills() {
        List<BillDO> dos = list();
        List<BillVO> vos = new ArrayList<>();
        for(BillDO doo : dos){
            BillVO vo = new BillVO();
            BeanUtil.copyProperties(doo,vo);
            String name = residentService.getById(doo.getRid()).getName();
            vo.setName(name);
            vos.add(vo);
        }

        return vos;
    }

    @Override
    public List<BillVO> getMyBillsByType(int uid, int type) {
        UserDO udo = userService.getById(uid);
        String username = udo.getUsername();
        ResidentDO rdo = residentMapper.selectByUsername(username);
        List<BillDO> dos = list();
        List<BillVO> vos = new ArrayList<>();
        for(BillDO doo : dos){
            if (doo.getType()!=type||doo.getRid()!=rdo.getId()){
                continue;
            }
            BillVO vo = new BillVO();
            BeanUtil.copyProperties(doo,vo);
            String name = residentService.getById(doo.getRid()).getName();
            vo.setName(name);
            vos.add(vo);
        }
        return vos;
    }


//    @Override
//    public boolean createBook(CreateOrUpdateBookDTO validator) {
//        BookDO book = new BookDO();
//        book.setAuthor(validator.getAuthor());
//        book.setTitle(validator.getTitle());
//        book.setImage(validator.getImage());
//        book.setSummary(validator.getSummary());
//        return bookMapper.insert(book) > 0;
//    }
//
//    @Override
//    public List<BookDO> getBookByKeyword(String q) {
//        List<BookDO> books = bookMapper.selectByTitleLikeKeyword(q);
//        return books;
//    }
//
//    @Override
//    public boolean updateBook(BookDO book, CreateOrUpdateBookDTO validator) {
//        book.setAuthor(validator.getAuthor());
//        book.setTitle(validator.getTitle());
//        book.setImage(validator.getImage());
//        book.setSummary(validator.getSummary());
//        return bookMapper.updateById(book) > 0;
//    }
//
//    @Override
//    public BookDO getById(Long id) {
//        BookDO book = bookMapper.selectById(id);
//        return book;
//    }
//
//    @Override
//    public List<BookDO> findAll() {
//        List<BookDO> books = bookMapper.selectList(null);
//        return books;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        return bookMapper.deleteById(id) > 0;
//    }
}
