package io.github.talelin.merak.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.merak.bo.Data;
import io.github.talelin.merak.mapper.AshinOutMapper;
import io.github.talelin.merak.mapper.AshinStayMapper;
import io.github.talelin.merak.mapper.AshinTypeMapper;
import io.github.talelin.merak.model.AshinBaseDo;
import io.github.talelin.merak.model.AshinOutDo;
import io.github.talelin.merak.model.AshinStayDo;
import io.github.talelin.merak.model.AshinTypeDo;
import io.github.talelin.merak.service.AshinBaseService;
import io.github.talelin.merak.service.AshinOutService;
import io.github.talelin.merak.service.AshinStayService;
import io.github.talelin.merak.service.AshinTypeService;
import io.github.talelin.merak.utils.DistanceCal;
import io.github.talelin.merak.vo.OutVO;
import io.github.talelin.merak.vo.PointVO;
import org.gavaghan.geodesy.Ellipsoid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AshinTypeServiceImpl extends ServiceImpl<AshinTypeMapper, AshinTypeDo> implements AshinTypeService {
    @Autowired
    private AshinBaseService ashinBaseService;
    @Autowired
    private AshinOutService ashinOutService;
    @Override
    public void getTypeCount() {
        List<AshinBaseDo> bdo = ashinBaseService.list();
        List<AshinTypeDo> tdo = this.list();
        //未知出行方式0
        int unkCount=0;
        //公交出行方式1
        int busCount=0;
        //地铁2
        int underCount = 0;
        Map<String, ArrayList<AshinBaseDo>> resmap= separete(bdo);
        for (Map.Entry<String, ArrayList<AshinBaseDo>> entry : resmap.entrySet()) {

//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            System.out.println("~~~");
            List<AshinBaseDo> list = entry.getValue();
            if (anaType(list,tdo)==0){
                unkCount++;
            }else if (anaType(list,tdo)==1){
                busCount++;
            }else{
                underCount++;
            }
        }
        List<AshinOutDo> res = new ArrayList<>();
        AshinOutDo o1 = new AshinOutDo();
        o1.setName("非公交地铁");
        o1.setValue(unkCount);
        AshinOutDo o2 = new AshinOutDo();
        o2.setName("公交");
        o2.setValue(busCount);
        AshinOutDo o3 = new AshinOutDo();
        o3.setName("地铁");
        o3.setValue(underCount);
        res.add(o1);
        res.add(o2);
        res.add(o3);
        ashinOutService.saveBatch(res);

    }

    public int anaType(List<AshinBaseDo> bdo,List<AshinTypeDo> tdo){
        //未知出行方式0
        int unkCount=0;
        //公交出行方式1
        int busCount=0;
        //地铁2
        int underCount = 0;

        for (AshinBaseDo doo:bdo){
            Data from = new Data();
            from.setLat(doo.getLat());
            from.setLng(doo.getLng());
            int haveType=0;
            for (AshinTypeDo tdoo : tdo){
                Data to = new Data();
                to.setLng(tdoo.getLng());
                to.setLat(tdoo.getLat());
                DistanceCal cal = new DistanceCal(from,to);
                double result = cal.getDistanceMeter(cal.source,cal.target, Ellipsoid.Sphere);
                if (result<200){
                    if (tdoo.getType().equals("地铁")){
                        underCount++;
                        haveType=1;
                        break;
                    }else if (tdoo.getType().equals("公交")){
                        busCount++;
                        haveType=1;
                        break;
                    }
                }
            }
            if (haveType==0){
                unkCount++;
            }
        }
//        System.out.println("under"+underCount);
//        System.out.println("unk"+unkCount);
//        System.out.println("bus"+busCount);
        if (underCount>=unkCount&&underCount>=busCount){
            return 2;
        }else if (busCount>=unkCount&&busCount>=underCount){
            return 1;
        }else{
            return 0;
        }

    }

    //列表中根据某个属性分成多个组，这里根据imsi
    public Map<String, ArrayList<AshinBaseDo>> separete(List list){
        TreeMap tm=new TreeMap();

        for(int i=0;i<list.size();i++){
            AshinBaseDo s=(AshinBaseDo)list.get(i);
            if(tm.containsKey(s.getImsi())){
                ArrayList l11=(ArrayList)tm.get(s.getImsi());
                l11.add(s);
            }else{
                ArrayList tem=new ArrayList();
                tem.add(s);
                tm.put(s.getImsi(), tem);
            }

        }
        return tm;
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
