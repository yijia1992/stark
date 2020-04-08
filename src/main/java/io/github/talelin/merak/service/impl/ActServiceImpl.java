package io.github.talelin.merak.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.merak.dto.act.ActAddDTO;
import io.github.talelin.merak.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.merak.mapper.ActMapper;
import io.github.talelin.merak.mapper.BookMapper;
import io.github.talelin.merak.model.ActDO;
import io.github.talelin.merak.model.BookDO;
import io.github.talelin.merak.service.ActService;
import io.github.talelin.merak.service.BookService;
import io.github.talelin.merak.utils.MuUtil;
import io.github.talelin.merak.vo.ActVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActServiceImpl extends ServiceImpl<ActMapper, ActDO> implements ActService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean createAct(ActAddDTO validator) {
        ActDO actDO = new ActDO();
        BeanUtil.copyProperties(validator,actDO);
        return saveOrUpdate(actDO);

    }

    @Override
    public List<ActVO> findAll() {
        List<ActVO> vos = new ArrayList<>();
        for(ActDO doo:this.list()){
            ActVO vo = new ActVO();
            BeanUtil.copyProperties(doo,vo);
            if (vo.getStartTime()!=null) {
                vo.setSstartTime(MuUtil.getFormatTime(vo.getStartTime()));
            }
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public boolean deleteById(int id) {
        return this.removeById(id);
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
