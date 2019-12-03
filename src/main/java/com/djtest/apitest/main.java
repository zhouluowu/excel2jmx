package com.djtest.apitest;


import com.djtest.apitest.util.SaveFileUtil;
import org.dom4j.DocumentException;

import java.text.ParseException;

public class main {
    /*
            功能：定义执行输入参数，有如下方式
            1.java -jar jmeter-center.jar tableSroucePath,SheetName,savePath

         */
    public static void main(String[] args) throws ParseException, DocumentException {
         if(args.length>=3){
            SaveFileUtil.save(args[0].split(";"),args[1].split(";"),args[2]);
             System.out.println("生成用例成功");
        }else{
            System.out.println("请正确输入参数:");
            System.out.println("\t1：");
            System.out.println("\t\t参数1：tableSroucePath，测试用例模板文件的存放路径");
            System.out.println("\t\t参数2：SheetName，excel子表名称");
            System.out.println("\t\t参数2：savePath，保存jmeter文件的路径，需要包含文件名");
        }


    }
}
