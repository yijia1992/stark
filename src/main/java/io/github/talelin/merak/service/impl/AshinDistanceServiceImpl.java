package io.github.talelin.merak.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.merak.mapper.AshinDistanceMapper;
import io.github.talelin.merak.mapper.AshinStayMapper;
import io.github.talelin.merak.model.AshinDistanceDo;
import io.github.talelin.merak.model.AshinStayDo;
import io.github.talelin.merak.service.AshinDistanceService;
import io.github.talelin.merak.service.AshinStayService;
import io.github.talelin.merak.vo.DistanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AshinDistanceServiceImpl extends ServiceImpl<AshinDistanceMapper, AshinDistanceDo> implements AshinDistanceService {
    @Override
    public DistanceVO getLevelCount() {
        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        List<AshinDistanceDo> list = this.list();
        for (AshinDistanceDo doo:list){
            if (doo.getDis()>0&&doo.getDis()<3000){
                count1++;
            }else if(doo.getDis()>3000&&doo.getDis()<7000){
                count2++;
            }else if(doo.getDis()>7000&&doo.getDis()<11000){
                count3++;
            }else if(doo.getDis()>11000&&doo.getDis()<16000){
                count4++;
            }else if(doo.getDis()>16000&&doo.getDis()<25000){
                count5++;
            }else{
                count6++;
            }
        }
        DistanceVO vo = new DistanceVO();
        vo.setCount1(count1);
        vo.setCount2(count2);
        vo.setCount3(count3);
        vo.setCount4(count4);
        vo.setCount5(count5);
        vo.setCount6(count6);
        return vo;

    }


    //    @Autowired
//    private AshinStayMapper ashinStayMapper;
//
//    @Override
//    public List<AshinStayDo> getStaysByImsi(String imsi) {
//        List<AshinStayDo> list = ashinStayMapper.queryStaysByImsi(imsi);
//        return list;
//    }
//
//    @Override
//    public List<AshinStayDo> getAllStays() {
//        List<AshinStayDo> list = this.list();
//        return list;
//    }


//    @Override
//    public List<List<PointVO>> getPonitsGroup() {
//        List<List<PointVO>> result = new ArrayList<List<PointVO>>();
//        List<AshinBaseDo> dos = this.list();
//        //列表中根据某个属性分成多个组
//        Map<String, ArrayList<AshinBaseDo>> resmap= separete(dos);
//        for (Map.Entry<String, ArrayList<AshinBaseDo>> entry : resmap.entrySet()) {
//
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            System.out.println("~~~");
//            List<AshinBaseDo> list = entry.getValue();
//            List<PointVO> pvos = new ArrayList<>();
//            for (AshinBaseDo doo :list){
//                PointVO po = new PointVO();
//                BeanUtil.copyProperties(doo,po);
//                pvos.add(po);
//            }
//            result.add(pvos);
//        }
//        return result;
//
//    }
//
//    @Override
//    public List<List<CoordVO>> getCoords(AshinDataDTO dto) {
//        List<List<CoordVO>> result = new ArrayList<List<CoordVO>>();
//        List<CoordVO> coords = new ArrayList<>();
//        List<AshinBaseDo> dos = this.list();
//        System.out.println(dos);
//        List<AshinBaseDo> newdos = new ArrayList<>();
//        for (AshinBaseDo doo:dos){
//            if (dto.getEndTime()!=null&&dto.getStartTime()!=null){
//                if (isEffectiveDate(doo.getTime(),dto.getStartTime(),dto.getEndTime())){
//                    newdos.add(doo);
//                }
//            }else{
//                Date start = null;
//                Date end = null;
//                if (dto.getStartTime()==null){
//                    start = new Date(5000);
//                }
//                if (dto.getEndTime()==null){
//                    //dto.setEndTime(new Date());
//                     end = new Date();
//                }
//                if (isEffectiveDate(doo.getTime(),
//                        dto.getStartTime()==null?start:dto.getStartTime(),
//                        dto.getEndTime()==null?end:dto.getEndTime())){
//                    newdos.add(doo);
//                }
//            }
//
//        }
//        for (AshinBaseDo ndoo : newdos){
//            CoordVO vo = new CoordVO();
//            double[] coord = new double[2];
//            coord[0] = ndoo.getLng();
//            coord[1] = ndoo.getLat();
//            vo.setCoord(coord);
//            coords.add(vo);
//        }
//        result.add(coords);
//
//        return result;
//    }
//
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
