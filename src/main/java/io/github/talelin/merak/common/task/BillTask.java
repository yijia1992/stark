package io.github.talelin.merak.common.task;

//模拟每月水电燃气费用

import io.github.talelin.merak.model.BillDO;
import io.github.talelin.merak.model.ResidentDO;
import io.github.talelin.merak.service.BillService;
import io.github.talelin.merak.service.ResidentService;
import io.github.talelin.merak.utils.MuUtil;
import io.github.talelin.merak.vo.ResidentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
@EnableScheduling
public class BillTask {

    @Autowired
    private ResidentService residentService;
    @Autowired
    private BillService billService;

    /**默认是fixedDelay 上一次执行完毕时间后执行下一轮*/
    //每月11日下午15点38分生成所有水电燃气
    @Scheduled(cron = "0 38 15 11 * ?")
    public void run() throws InterruptedException {

        List<ResidentDO> vos = residentService.findAll();
        //生成水费
        genBill(vos,1);
        //电费
        genBill(vos,2);
        //燃气
        genBill(vos,3);
    }

    @Transactional
    public void genBill(List<ResidentDO> dos, int type){
        for (ResidentDO doo : dos) {
            BillDO bdo = new BillDO();
            BigDecimal money = MuUtil.getRandomRedPacketBetweenMinAndMax(new BigDecimal(0), new BigDecimal(1000));
            bdo.setMoney(money);
            bdo.setRid(doo.getId());
            bdo.setTime(MuUtil.getFormatTime("yyyy-MM"));
            bdo.setType(type);
            billService.save(bdo);
        }
    }


//    /**fixedRate:上一次开始执行时间点之后5秒再执行*/
//    @Scheduled(fixedRate = 5000)
//    public void run1() throws InterruptedException {
//        Thread.sleep(6000);
//        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用fixedRate  {}"+(System.currentTimeMillis()/1000));
//    }
//
//    /**fixedDelay:上一次执行完毕时间点之后5秒再执行*/
//    @Scheduled(fixedDelay = 5000)
//    public void run2() throws InterruptedException {
//        Thread.sleep(7000);
//        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用fixedDelay  {}"+(System.currentTimeMillis()/1000));
//    }
//
//    /**第一次延迟2秒后执行，之后按fixedDelay的规则每5秒执行一次*/
//    @Scheduled(initialDelay = 2000, fixedDelay = 5000)
//    public void run3(){
//        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用initialDelay  {}"+(System.currentTimeMillis()/1000));
//    }
}


//        每隔5秒执行一次：*/5 ** ?
//
//        每隔1分钟执行一次：0 */1 *?
//
//        0 0 10,14,16 ? 每天上午10点，下午2点，4点
//
//        0 0/30 9-17 ? 朝九晚五工作时间内每半小时
//
//        0 0 12 ? * WED 表示每个星期三中午12点
//
//        “0 0 12 ?” 每天中午12点触发
//
//        “0 15 10 ? “ 每天上午10:15触发
//
//        “0 15 10 ?” 每天上午10:15触发
//
//        “0 15 10 ? *” 每天上午10:15触发
//
//        “0 15 10 ? 2005” 2005年的每天上午10:15触发
//
//        “0 *14 ** ?” 在每天下午2点到下午2:59期间的每1分钟触发
//
//        “0 0/5 14 ?” 在每天下午2点到下午2:55期间的每5分钟触发
//
//        “0 0/5 14,18 ?” 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
//
//        “0 0-5 14 ?” 在每天下午2点到下午2:05期间的每1分钟触发
//
//        “0 10,44 14 ? 3 WED” 每年三月的星期三的下午2:10和2:44触发
//
//        “0 15 10 ? * MON-FRI” 周一至周五的上午10:15触发
//
//        “0 15 10 15 * ?” 每月15日上午10:15触发
//
//        “0 15 10 L * ?” 每月最后一日的上午10:15触发
//
//        “0 15 10 ? * 6L” 每月的最后一个星期五上午10:15触发
//
//        “0 15 10 ? * 6L 2002-2005” 2002年至2005年的每月的最后一个星期五上午10:15触发
//
//        “0 15 10 ? * 6#3” 每月的第三个星期五上午10:15触发

