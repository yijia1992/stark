package io.github.talelin.merak.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.merak.dto.ashin.AshinDataDTO;
import io.github.talelin.merak.mapper.AshinBaseMapper;
import io.github.talelin.merak.mapper.AshinFlowMapper;
import io.github.talelin.merak.model.AshinBaseDo;
import io.github.talelin.merak.model.AshinFlowDo;
import io.github.talelin.merak.service.AshinBaseService;
import io.github.talelin.merak.service.AshinFlowService;
import io.github.talelin.merak.vo.CoordVO;
import io.github.talelin.merak.vo.PointVO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AshinFlowServiceImpl extends ServiceImpl<AshinFlowMapper, AshinFlowDo> implements AshinFlowService {
    @Override
    public List<AshinFlowDo> getFlows() {
        List<AshinFlowDo> list = this.list();
        return list;
    }


//    /**
//     *
//     * @param nowTime   当前时间
//     * @param startTime	开始时间
//     * @param endTime   结束时间
//     * @return
//     * @author sunran   判断当前时间在时间区间内
//     */
//    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
//        if (nowTime.getTime() == startTime.getTime()
//                || nowTime.getTime() == endTime.getTime()) {
//            return true;
//        }
//
//        Calendar date = Calendar.getInstance();
//        date.setTime(nowTime);
//
//        Calendar begin = Calendar.getInstance();
//        begin.setTime(startTime);
//
//        Calendar end = Calendar.getInstance();
//        end.setTime(endTime);
//
//        if (date.after(begin) && date.before(end)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    //列表中根据某个属性分成多个组，这里根据imsi
//    public Map<String,ArrayList<AshinBaseDo>> separete(List list){
//        TreeMap tm=new TreeMap();
//
//        for(int i=0;i<list.size();i++){
//            AshinBaseDo s=(AshinBaseDo)list.get(i);
//            if(tm.containsKey(s.getImsi())){
//                ArrayList l11=(ArrayList)tm.get(s.getImsi());
//                l11.add(s);
//            }else{
//                ArrayList tem=new ArrayList();
//                tem.add(s);
//                tm.put(s.getImsi(), tem);
//            }
//
//        }
//        return tm;
//    }

//    public static void main(String[] args) {
//        new AshinBaseServiceImpl().getPonitsGroup();
//    }



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
