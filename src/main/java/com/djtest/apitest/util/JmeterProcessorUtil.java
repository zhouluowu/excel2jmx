package com.djtest.apitest.util;

import com.djtest.apitest.model.JmxBean;
import org.dom4j.Element;

import java.util.Collections;


public class JmeterProcessorUtil {

    public static Element add(Element hashTree, JmxBean testcase){
        if(testcase==null){
            return hashTree;
        }

        // 新增jsonpath处理器
        Element JSONPostProcessor = hashTree.addElement("JSONPostProcessor");
        JSONPostProcessor.addAttribute("guiclass","JSONPostProcessorGui");
        JSONPostProcessor.addAttribute("testclass","JSONPostProcessor");
        JSONPostProcessor.addAttribute("testname","JSON Extractor");
        JSONPostProcessor.addAttribute("enabled","true");

        //在jsonpath处理器新增stringProp
        Element stringProp = JSONPostProcessor.addElement("stringProp");
        stringProp.addAttribute("name","JSONPostProcessor.referenceNames");
        stringProp.setText(testcase.getSaveName());

        Element jsonPathExprs = JSONPostProcessor.addElement("stringProp");
        jsonPathExprs.addAttribute("name","JSONPostProcessor.jsonPathExprs");
        jsonPathExprs.setText(testcase.getSaveExpression());

        Element match_numbers = JSONPostProcessor.addElement("stringProp");
        match_numbers.addAttribute("name","JSONPostProcessor.match_numbers");
        int i = testcase.getSaveName().split(";").length;
        match_numbers.setText(String.join("", Collections.nCopies(i,"1;")));

        Element defaultValues = JSONPostProcessor.addElement("stringProp");
        defaultValues.addAttribute("name","JSONPostProcessor.defaultValues");
        defaultValues.setText(String.join("", Collections.nCopies(i,"Not Match;")));

        hashTree.addElement("hashTree");

        return hashTree;
    }

}
