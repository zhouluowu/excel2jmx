package com.djtest.apitest.util;

import java.util.Random;

public class StringUtil {

    /**
     * 生成指定长度字符串
     * @param length
     * @return
     */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        // length为几位密码
        for (int i = 0; i < length; i++) {
            // 判断生成数字还是字母(字母有大小写区别)
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static String getStringRandom(String length) {
        return getStringRandom(Integer.valueOf(length));
    }

    public static void main(String[] args){
        System.out.println(getStringRandom(10));
    }
}
