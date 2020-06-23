package com.lzx.work.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import net.sourceforge.pinyin4j.multipinyin.MultiPinyinConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @author lzx
 */
public class MyUtil {

    static {
        //自定义外挂词库
        Optional.ofNullable(Thread.currentThread().getContextClassLoader().getResource("hanyuTopinyin.txt")).ifPresent(url -> MultiPinyinConfig.multiPinyinPath = url.getPath());
    }

    private static final String REGEX_OF_CHINESE = "[\\u4e00-\\u9fa5]+";

    /**
     * 汉语转拼音
     */
    public static String hanyuToPinyin(String hanyu){
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isBlank(hanyu)){
            return sb.toString();
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        /*
         * HanyuPinyinToneType.WITH_TONE_NUMBER 用数字表示声调，例如：liu2
         * HanyuPinyinToneType.WITH_TONE_MARK 用声调符号表示，例如：liú（必须设置WITH_U_UNICODE，否则会抛出异常）
         * HanyuPinyinToneType.WITHOUT_TONE 无声调表示，例如：liu
         */
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        /*
         * HanyuPinyinCaseType.LOWERCASE:输出小写
         * HanyuPinyinCaseType.UPPERCASE:输出大写
         */
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        /*
         * WITH_V：用v表示ü
         * WITH_U_AND_COLON：用"u:"表示ü
         * WITH_U_UNICODE：直接用ü
         */
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        try {
            char[] hanyuCharArr = hanyu.toCharArray();
            for (char hanyuChar : hanyuCharArr) {
                if(String.valueOf(hanyuChar).matches(REGEX_OF_CHINESE)) {
                    //如果是多音字,返回多个拼音,这里只取第一个
                    String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(hanyuChar, format);
                    sb.append(pinyinArr[0]);
                }else{
                    sb.append(hanyuChar);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return sb.toString();
    }

}
