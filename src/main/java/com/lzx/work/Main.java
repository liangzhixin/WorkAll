package com.lzx.work;

import com.lzx.work.util.HanyuPinyinUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("中"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("垚"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("吕"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("ni中文hao"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("吸"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("哈"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("你"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("我"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("他"));
        System.out.println(HanyuPinyinUtil.hanyuToPinyin("谁"));
    }
}
