package com.djtest.apitest.util;

import org.dom4j.Element;


public class JmeterAssertionUtil {

    public static Element add(Element hashTree, String assert_msg){
        if(assert_msg==null){
            return hashTree;
        }
        // 新增标签ResponseAssertion
        Element responseAssertion = hashTree.addElement("ResponseAssertion");
        responseAssertion.addAttribute("guiclass","AssertionGui");
        responseAssertion.addAttribute("testclass","ResponseAssertion");
        responseAssertion.addAttribute("testname","响应断言");
        responseAssertion.addAttribute("enabled","true");
        //在标签下新增collectionProp标签
        Element collectionProp = responseAssertion.addElement("collectionProp");
        collectionProp.addAttribute("name","Asserion.test_strings");
        //在collectionProp新增stringProp
        Element stringProp = collectionProp.addElement("stringProp");
        stringProp.addAttribute("name","test");
        stringProp.setText(assert_msg);

        //在responseAssertion下添加stringProp
        Element msg = responseAssertion.addElement("stringProp");
        msg.addAttribute("name","Assertion.test_field");
        msg.setText("Assertion.response_data");

        //添加boolProp、intProp
        Element boolProp = responseAssertion.addElement("boolProp");
        boolProp.addAttribute("name","Assertion.assume_success");
        boolProp.setText("false");

        Element intProp = responseAssertion.addElement("intProp");
        intProp.addAttribute("name","Assertion.assume_success");
        intProp.setText("16");

        hashTree.addElement("hashTree");

        return hashTree;
    }

}
