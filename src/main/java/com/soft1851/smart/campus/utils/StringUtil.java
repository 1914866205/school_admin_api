package com.soft1851.smart.campus.utils;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/2
 * @Version 1.0
 */
public class StringUtil {

    public static String ToPinYinAndGetFirstChar(String str) {
        String pinyinStr = "";
        char[] newChar  = str.toCharArray();
        HanyuPinyinOutputFormat hpf = new HanyuPinyinOutputFormat();
        hpf.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hpf.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        int len = newChar.length;
        for (int i = 0; i < len; i++) {
            if (newChar[i] > 128) {

            }
        }
        return null;
    }
}
