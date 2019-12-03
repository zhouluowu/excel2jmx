package com.djtest.apitest.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.djtest.apitest.model.ArgsBean;
import com.djtest.apitest.model.JmxBean;
import com.djtest.apitest.model.TableBean;
import org.dom4j.DocumentException;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table2JmxUtil {

    /*
    把excel里的数据转换jmeter类
     */


    public static List<JmxBean> table2jmx(String[] tableSourcePath,String[] sheets) throws DocumentException, ParseException {
       List<TableBean> data = readExcelData(TableBean.class,tableSourcePath,sheets);
       List<JmxBean> cases = new ArrayList<JmxBean>();
       for (TableBean t:data){
           cases.addAll(getJmxCase(t));
       }
       return cases;
    }

    /**
     * 获取excel的数据
     * @param clz
     * @param excelPathArr
     * @param sheetNameArr
     * @param <T>
     * @return
     * @throws DocumentException
     */
    private static <T> List<T> readExcelData(Class<T> clz, String[] excelPathArr, String[] sheetNameArr) throws DocumentException {
        List<T> allExcelData = new ArrayList<T>();// excel文件數組
        List<T> temArrayList = new ArrayList<T>();
        for (String excelPath : excelPathArr) {
            File file = Paths.get(System.getProperty("user.dir"), excelPath).toFile();
            temArrayList.clear();
            if (sheetNameArr.length == 0 || sheetNameArr[0] == "") {
                temArrayList = ExcelUtil.readExcel(clz, file.getAbsolutePath());
            } else {
                for (String sheetName : sheetNameArr) {
                    temArrayList.addAll(ExcelUtil.readExcel(clz,
                            file.getAbsolutePath(), sheetName));
                }
            }
//            temArrayList.forEach((bean) -> {
////                bean.setExcelName(file.getName());
//                System.out.println(bean.toString());
//            });
            allExcelData.addAll(temArrayList); // 将excel数据添加至list
//            System.out.println(allExcelData.toString());
        }
        return allExcelData;
    }

    /**
     * 处理非测试的参数
     * @return
     */
    private static  List<ArgsBean> getArgs(String params, String checkParam) {
        params=params.trim();
        List<ArgsBean> args_list = new ArrayList<ArgsBean>();
        try {
            if (!params.equalsIgnoreCase("") && params != null) {
                JSONObject paramsJson = JSONObject.parseObject(params);
                for (Map.Entry entry : paramsJson.entrySet()) {
                    ArgsBean a = new ArgsBean();
                    if (!entry.getKey().toString().equalsIgnoreCase(checkParam)) {
                        a.setName(entry.getKey().toString());
                        a.setValue(entry.getValue().toString());
                        args_list.add(a);
                    }
                }
            }
        }catch (JSONException e){
            System.out.println("请检查用例params字段和preparms字段，确保为json格式的字符串");
            System.out.println(params);
            e.printStackTrace();
        }

        return args_list;
    }


    private  static JmxBean getJmxCase(String name, String method, String path, List<ArgsBean> args,
                                       String assert_msg,String type){
        JmxBean jmxCase = new JmxBean();
        jmxCase.setCaseName(name);
        jmxCase.setMethod(method);
        jmxCase.setPath(path);
        jmxCase.setParams(args);
        jmxCase.setAssertMsg(assert_msg);
        jmxCase.setType(type);
        return jmxCase;
    }

    private static  List<JmxBean> getJmxCase(TableBean t) throws ParseException {
        if (t==null){
            return null;
        }
        List<JmxBean> cases = new ArrayList<JmxBean>();
//        List<ArgsBean> base_args = getArgs(t.getParams(),t.getCheckParam());
        if(t.getisrun()) {
            //空值校验
            addNULLCase(cases, t);

            // 边界校验还有唯一值的用例
            if(!t.getMin().equals("") && !t.getMax().equals("")) {
                addBoundaryCase(cases, t);
            }
            //特殊字符校验
            addSpecCase(cases, t);
        }
        return cases;
    }

    private static List<JmxBean> addPreCase(List<JmxBean> cases, TableBean t){
        if(t.getisPre()){
            JmxBean j= getJmxCase(t.getName()+"前置用例",t.getPremethod(),t.getPrepath(),
                    getArgs(t.getPreparams(),""),"true",t.getPreparamType());
            String[] str = dealSave(t.getPresave());
            if (str.length==2) {
                j.setSaveName(str[0]);
                j.setSaveExpression(str[1]);
            }
            cases.add(j);
        }
        return cases;
    }

    private  static List<JmxBean> addBoundaryCase(List<JmxBean> cases, TableBean t) throws ParseException{
        if (cases ==null ||t==null){
            return null;
        }
        if(t.getMin().equals("") || t.getMax().equals("")){return cases;} //没有最大最小边界就不做边界值校验
        List<ArgsBean> args,args1,args2,args3,args4;
        if (t.getFieldType().equalsIgnoreCase("手机号")) {
            args = getArgs(t.getParams(), t.getCheckParam());
            args.add(new ArgsBean(t.getCheckParam(), "13${__RandomString(8,1234567890,)}")); // 小于最小边界值
            args1 = getArgs(t.getParams(), t.getCheckParam());
            args1.add(new ArgsBean(t.getCheckParam(), "13${__RandomString(9,1234567890,savestr)}")); // 等于最小边界值
            args2 = getArgs(t.getParams(), t.getCheckParam());
            args2.add(new ArgsBean(t.getCheckParam(), "13${__RandomString(9,1234567890,)}")); // 等于最大边界值
            args3 = getArgs(t.getParams(), t.getCheckParam());
            args3.add(new ArgsBean(t.getCheckParam(), "13${__RandomString(10,1234567890,)}")); // 大于最大边界值
        }else if(t.getFieldType().equalsIgnoreCase("数字")) {
            args = getArgs(t.getParams(), t.getCheckParam());
            args.add(new ArgsBean(t.getCheckParam(), String.valueOf(new BigDecimal(t.getMin()).intValue() - 1))); // 小于最小边界值
            args1 = getArgs(t.getParams(), t.getCheckParam());
            args1.add(new ArgsBean(t.getCheckParam(), String.valueOf(new BigDecimal(t.getMin()).intValue()))); // 等于最小边界值
            args2 = getArgs(t.getParams(), t.getCheckParam());
            args2.add(new ArgsBean(t.getCheckParam(), String.valueOf(new BigDecimal(t.getMax()).intValue()))); // 等于最大边界值
            args3 = getArgs(t.getParams(), t.getCheckParam());
            args3.add(new ArgsBean(t.getCheckParam(), String.valueOf(new BigDecimal(t.getMax()).intValue()+1))); // 大于最大边界值
        }else if(t.getFieldType().equalsIgnoreCase("日期")) {
            args = getArgs(t.getParams(), t.getCheckParam());
            args.add(new ArgsBean(t.getCheckParam(), DateUtil.getDate(t.getMin(),-1))); // 小于最小边界值
            args1 = getArgs(t.getParams(), t.getCheckParam());
            args1.add(new ArgsBean(t.getCheckParam(), t.getMin())); // 等于最小边界值
            args2 = getArgs(t.getParams(), t.getCheckParam());
            args2.add(new ArgsBean(t.getCheckParam(), t.getMax())); // 等于最大边界值
            args3 = getArgs(t.getParams(), t.getCheckParam());
            args3.add(new ArgsBean(t.getCheckParam(), DateUtil.getDate(t.getMax(),1))); // 大于最大边界值
        }else { //字符串和其他的情况
            if (!t.getMin().equals("0.0")) {
                args = getArgs(t.getParams(), t.getCheckParam());
                args.add(new ArgsBean(t.getCheckParam(), StringUtil.getStringRandom(new BigDecimal(t.getMin()).intValue() - 1))); // 小于最小边界值
            }else{args=null;}
            args1 = getArgs(t.getParams(), t.getCheckParam());
            args1.add(new ArgsBean(t.getCheckParam(), "${__RandomString("+new BigDecimal(t.getMin()).intValue() + ",abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,savestr)}"));
//                    StringUtil.getStringRandom(new BigDecimal(t.getMin()).intValue()))); // 等于最小边界值
            args2 = getArgs(t.getParams(), t.getCheckParam());
            args2.add(new ArgsBean(t.getCheckParam(), "${__RandomString("+new BigDecimal(t.getMax()).intValue() + ",abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,)}")); // 等于最大边界值
            args3 = getArgs(t.getParams(), t.getCheckParam());
            args3.add(new ArgsBean(t.getCheckParam(), StringUtil.getStringRandom(new BigDecimal(t.getMax()).intValue() + 1))); // 大于最大边界值
        }
        if (args!=null) {
            addPreCase(cases, t); //添加前置用例
            cases.add(getJmxCase(t.getName() + "_最小边界值-1", t.getMethod(), t.getPath(),
                    args, "false", t.getParamType()));
        }
        addPreCase(cases,t); //添加前置用例
        cases.add(getJmxCase(t.getName()+"_等于最小边界值",t.getMethod(),t.getPath(),
                args1,"true",t.getParamType()));
        addPreCase(cases,t); //添加前置用例
        cases.add(getJmxCase(t.getName()+"_等于最大边界值",t.getMethod(),t.getPath(),
                args2,"true",t.getParamType()));
        addPreCase(cases,t); //添加前置用例
        cases.add(getJmxCase(t.getName()+"_最大边界值+1",t.getMethod(),t.getPath(),
                args3,"false",t.getParamType()));
        if (t.isUnique()){
            addPreCase(cases,t); //添加前置用例
            args4 = getArgs(t.getParams(), t.getCheckParam()); //参数值唯一性
            args4.add(new ArgsBean(t.getCheckParam(), "${savestr}"));
            cases.add(getJmxCase(t.getName()+"_唯一性校验",t.getMethod(),t.getPath(),
                    args4,"false",t.getParamType()));
        }

        return cases;
    }

    private static List<JmxBean> addNULLCase(List<JmxBean> cases, TableBean t){
        if (cases ==null ||t==null){
            return null;
        }
        addPreCase(cases,t); //添加前置用例
        if(t.isNullAble()){
            List<ArgsBean> args = getArgs(t.getParams(),t.getCheckParam());
            args.add(new ArgsBean(t.getCheckParam(),"")); // 参数值为空
            cases.add(getJmxCase(t.getName()+"_校验空值",t.getMethod(),t.getPath(),
                    args,"true",t.getParamType()));
        }else{
            List<ArgsBean> args = getArgs(t.getParams(),t.getCheckParam());
            args.add(new ArgsBean(t.getCheckParam(),"")); // 参数值为空
            cases.add(getJmxCase(t.getName()+"_校验空值",t.getMethod(),t.getPath(),
                    args,"false",t.getParamType()));
        }
        return cases;
    }

    private static List<JmxBean> addSpecCase(List<JmxBean> cases, TableBean t){
        if (cases ==null ||t==null){
            return null;
        }
        if (!t.getSpecailList().equalsIgnoreCase("") && t.getSpecailList() != null){
            addPreCase(cases,t); //添加前置用例
            List<ArgsBean> args = getArgs(t.getParams(),t.getCheckParam());
            String spec_str = t.getSpecailList();
            args.add(new ArgsBean(t.getCheckParam(),spec_str));
            cases.add(getJmxCase(t.getName()+"_特殊字符校验_"+spec_str,t.getMethod(),t.getPath(),
                    args,"false",t.getParamType()));

        }else{
//            List<ArgsBean> args = getArgs(t.getParams(),t.getCheckParam());
//            String spec_str = "/_*()&^%$#@!"; //特殊字符列表
//            args.add(new ArgsBean(t.getCheckParam(),spec_str));
//            cases.add(getJmxCase(t.getName()+"_特殊字符校验_"+spec_str,t.getMethod(),t.getPath(),
//                    args,"true",t.getParamType()));
        }
        return cases;
    }

    private static String[] dealSave(String save_str) {
        String names="";
        String expressions="";
        save_str=save_str.trim();
//        String[] s=save_str.split(";");
        Pattern pattern1 = Pattern.compile("([^;]*)=([^;]*)");
        Matcher m1=pattern1.matcher(save_str);

        while(m1.find()){
            if (names.equalsIgnoreCase("")){
                names+=m1.group(1);
            }else{
                names+=";"+m1.group(1);
            }
            if (expressions.equalsIgnoreCase("")){
                expressions+=m1.group(2);
            }else{
                expressions+=";"+m1.group(2);
            }
        }

        return new String[] {names,expressions};
    }


    public static void main(String[] args) throws DocumentException, ParseException {
//        System.out.println(StringUtil.getStringRandom(-1));
//        Table2JmxUtil m = new Table2JmxUtil();
//        m.readExcelData(TableBean.class,"\\demo\\api-testcase-demo.xls".split(";"),"Sheet1".split(";"));
          Table2JmxUtil.table2jmx("\\demo\\api-testcase-demo.xls".split(";"),"Sheet1".split(";"));
    }


}
