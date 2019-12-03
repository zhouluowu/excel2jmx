package com.djtest.apitest.model;

import java.util.List;

public class JmxBean {

    private String caseName;
    private String desc;
    private String method;
    private String path;
    private List<ArgsBean> params;
    private String assertMsg;
    private String saveName; // 保存的名称
    private String saveExpression; // jsonpath的表达式
    private String type;

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMethod() {
        if (method != null){
            return method.toUpperCase();
        }
        return method;
    }

    public void setMethod(String method) {
        if(method == null){
            this.method=method;
        }
        this.method = method.toUpperCase();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ArgsBean> getParams() {
        return params;
    }

    public void setParams(List<ArgsBean> params) {
        this.params = params;
    }

    public String getAssertMsg() {
        return assertMsg;
    }

    public void setAssertMsg(String assertMsg) {
        this.assertMsg = assertMsg;
    }


    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSaveExpression() {
        return saveExpression;
    }

    public void setSaveExpression(String saveExpression) {
        this.saveExpression = saveExpression;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
