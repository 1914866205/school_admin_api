package com.soft1851.smart.campus.utils;

import com.soft1851.smart.campus.model.entity.Examination;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xunmi
 * @ClassName DateUtil
 * @Description 有关时间操作的工具类
 * @Date 2020/6/1
 * @Version 1.0
 **/
public class DateUtil {
    /**
     * 获取当前时间（Timestamp）
     *
     * @return
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Boolean getTimeCompare(Timestamp startTime, Timestamp endTime, List<Examination> examinationList) {
        //转换开始时间字符串为Timestamp
        Timestamp s = startTime;
        System.out.println("****待加入的开始时间"+s);
        //转换结束时间字符串为Timestamp
        Timestamp e = endTime;
        System.out.println("****待加入的结束时间"+e);
        //遍历examinationList

        for (int i = 0; i < examinationList.size(); i++) {
            Examination examination = examinationList.get(i);
            Timestamp examStartTime = examination.getStartTime();
            Timestamp examFinishTime = examination.getFinishTime();
            System.out.println("****已存在教务"+i+"的开始时间"+examStartTime);
            System.out.println("****已存在教务"+i+"的结束时间"+examFinishTime);
            //与本学期每一个科目的时间作比较
            //每条数据的startTime 和 finishTime
            //需要设置新的考务的 startTime 和 finishTime
            Boolean left = e.before(examStartTime) && s.before(examStartTime);
            System.out.println("*****低于最低值"+left);
            Boolean right = s.after(examFinishTime) && e.after(examFinishTime);
            System.out.println("*****高于最高值"+right);
            if (!(left || right)) {
                return false;
            }
        }
        return true;
    }
}
