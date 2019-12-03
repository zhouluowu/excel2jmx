package com.djtest.apitest.model;

/**
 *
 */

public class TableBean {

    private boolean isrun;
    private String name;
    private String path;
    private String method;
    private String paramType;
    private String params;
    private String checkParam;
    private String fieldType; // 参数类型 如字符串、数字、日期等
    private boolean unique; //是否唯一性
    private boolean isNullable;
    private String min;
    private String max;
    private String expect;
    private String specailList;
    // 前置用例
    private boolean isPre;
    private String prepath;
    private String premethod;
    private String preparamType;
    private String preparams;
    private String presave;

    public boolean getisrun() {
        return isrun;
    }

    public void setIsrun(boolean isrun) {
        this.isrun = isrun;
    }

    public String getPath() {
        return path;
    }

    public  void setPath(String path) {
        this.path=path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCheckParam() {
        return checkParam;
    }

    public void setCheckParam(String checkParam) {
        this.checkParam = checkParam;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }



    public boolean isNullAble() {
        return isNullable;
    }

    public void setNullAble(boolean nullAble) {
        isNullable = nullAble;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getSpecailList() {
        return specailList;
    }

    public void setSpecailList(String specailList) {
        this.specailList = specailList;
    }
    public boolean getisPre() {
        return isPre;
    }

    public void setisPre(boolean pre) {
        isPre = pre;
    }

    public String getPrepath() {
        return prepath;
    }

    public void setPrepath(String prepath) {
        this.prepath = prepath;
    }

    public String getPremethod() {
        return premethod;
    }

    public void setPremethod(String premethod) {
        this.premethod = premethod;
    }

    public String getPreparamType() {
        return preparamType;
    }

    public void setPreparamType(String preparamType) {
        this.preparamType = preparamType;
    }

    public String getPreparams(){
        return preparams;
    }

    public void setPreparams(String preparams){
        this.preparams=preparams;
    }

    public String getPresave() {
        return presave;
    }

    public void setPresave(String presave) {
        this.presave = presave;
    }

    @Override
    public String toString(){
        return this.isrun
                + "\t" + this.name
                + "\t" + this.path
                + "\t" + this.method
                + "\t" + this.paramType
                + "\t" + this.params
                + "\t" + this.checkParam
                + "\t" + this.fieldType
                + "\t" + this.unique
                + "\t" + this.isNullable
                + "\t" + this.min
                + "\t" + this.max
                + "\t" + this.specailList
                + "\t" + this.isPre
                + "\t" + this.prepath
                + "\t" + this.premethod
                + "\t" + this.preparamType
                + "\t" + this.preparams
                + "\t" + this.presave;
    }
}