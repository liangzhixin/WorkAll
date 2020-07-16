package com.lzx.work;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws Exception{
        testChar();
    }

    /**
     * 同一个字符在不同编码下所占字节不同
     *    Unicode编码下一个char占2个字节（Java默认使用Unicode编码）
     *    GBK编码下一个char占2个字节
     *    UTF-8编码下一个char占3个字节
     */
    private static void testChar() throws Exception{
        String s = "中文";
        //默认编码
        System.out.println(Charset.defaultCharset().name());

        char[] chars = s.toCharArray();
        for (char c : chars) {
            System.out.println(c);
            String temp = c + "";
            System.out.println(Arrays.toString(temp.getBytes()));
            System.out.println(Arrays.toString(temp.getBytes(StandardCharsets.UTF_8)));
            System.out.println(Arrays.toString(temp.getBytes("GBK")));
        }
    }
}
