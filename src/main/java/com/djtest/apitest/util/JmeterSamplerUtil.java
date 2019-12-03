package com.djtest.apitest.util;

import com.djtest.apitest.model.ArgsBean;
import com.djtest.apitest.model.JmxBean;
import org.dom4j.Element;

public class JmeterSamplerUtil {
    /*
      功能：生成测试用例文件的httpSamplerProxy
            httpSamplerProxy是jmeter测试 REST API的重中之中

      输入参数：
          Element httpSamplerProxy      测试用例httpSamplerProxy节点
          JmeterCaseModel jmeterCase    测试用例结构化数据对象
    */
    public static Element add(Element httpSamplerProxy, JmxBean jmeterCase) {
        boolean isBodyRaw = false;
        String path = jmeterCase.getPath();
        String testName = "";

        //elementProp
        Element elementProp = httpSamplerProxy.addElement("elementProp");
        elementProp.addAttribute("name", "HTTPsampler.Arguments");
        elementProp.addAttribute("elementType", "Arguments");

        //collectionProp of elementProp
        Element collectionProp = elementProp.addElement("collectionProp");
        collectionProp.addAttribute("name", "Arguments.arguments");

        if ("json".equalsIgnoreCase(jmeterCase.getType())) {
            isBodyRaw=true;
            Element argElementProp = collectionProp.addElement("elementProp");
            argElementProp.addAttribute("elementType", "HTTPArgument");
            argElementProp.addAttribute("name", "");
            //<stringProp name="Argument.value">75km8j2o7jv42yyqgiwpzkyj4fhvuqt</stringProp>
            Element argElementStringProp_1 = argElementProp.addElement("stringProp");
            argElementStringProp_1.addAttribute("name", "Argument.value");
            String arg_json ="{";
            int i=0;
            for (ArgsBean arg : jmeterCase.getParams()) {
                arg_json=arg_json + "\""+arg.getName()+"\""+":\""+arg.getValue()+"\"";
                if (i<jmeterCase.getParams().size()-1) {
                    arg_json += ",";
                }
                i++;
            }
            argElementStringProp_1.setText(arg_json+"}");
        }else {
            for (ArgsBean arg : jmeterCase.getParams()) {
                Element argElementProp = collectionProp.addElement("elementProp");
                argElementProp.addAttribute("elementType", "HTTPArgument");
//                if (!"json".equalsIgnoreCase(jmeterCase.getType())) {
//                isBodyRaw = true;
//                argElementProp.addAttribute("name", "");
//            } else {
                    argElementProp.addAttribute("name", arg.getName());
                    //<boolProp name="HTTPArgument.use_equals">true</boolProp>
                    Element argElementBoolProp_1 = argElementProp.addElement("boolProp");
                    argElementBoolProp_1.addAttribute("name", "HTTPArgument.use_equals");
                    argElementBoolProp_1.setText("true");

                    //<stringProp name="Argument.name">tenantId</stringProp>
                    Element argElementStringProp_0 = argElementProp.addElement("stringProp");
                    argElementStringProp_0.addAttribute("name", "Argument.name");
                    argElementStringProp_0.setText(arg.getName());
//                }

                //<boolProp name="HTTPArgument.always_encode">true</boolProp>
                Element argElementBoolProp_0 = argElementProp.addElement("boolProp");
                argElementBoolProp_0.addAttribute("name", "HTTPArgument.always_encode");
                argElementBoolProp_0.setText("false");

                //<stringProp name="Argument.value">75km8j2o7jv42yyqgiwpzkyj4fhvuqt</stringProp>
                Element argElementStringProp_1 = argElementProp.addElement("stringProp");
                argElementStringProp_1.addAttribute("name", "Argument.value");
                argElementStringProp_1.setText(arg.getValue());

                //<stringProp name="Argument.metadata">=</stringProp>
                Element argElementStringProp_2 = argElementProp.addElement("stringProp");
                argElementStringProp_2.addAttribute("name", "Argument.metadata");
                argElementStringProp_2.setText("=");
            }
        }

        //属性
        httpSamplerProxy.addAttribute("guiclass", "HttpTestSampleGui");
        httpSamplerProxy.addAttribute("testclass", "HTTPSamplerProxy");
        httpSamplerProxy.addAttribute("enabled", "true");
        httpSamplerProxy.addAttribute("testname", jmeterCase.getCaseName());

        if (!isBodyRaw) {
            elementProp.addAttribute("guiclass", "HTTPArgumentsPanel");
            elementProp.addAttribute("testclass", "Arguments");
            elementProp.addAttribute("testname", "User Defined Variables");
            elementProp.addAttribute("enabled", "true");
        }
        //下一级节点
        //这个是判断是否消息体参数，如果是，需要增加<boolProp name="HTTPSampler.postBodyRaw">true</boolProp>
        if (isBodyRaw) {
            Element boolProp_0 = httpSamplerProxy.addElement("boolProp");
            boolProp_0.addAttribute("name", "HTTPSampler.postBodyRaw");
            boolProp_0.setText("true");

        }
        //<boolProp name="HTTPSampler.follow_redirects">false</boolProp>
        Element boolProp_1 = httpSamplerProxy.addElement("boolProp");
        boolProp_1.addAttribute("name", "HTTPSampler.follow_redirects");
        boolProp_1.setText("false");

        //<boolProp name="HTTPSampler.auto_redirects">true</boolProp>
        Element boolProp_2 = httpSamplerProxy.addElement("boolProp");
        boolProp_2.addAttribute("name", "HTTPSampler.auto_redirects");
        boolProp_2.setText("true");

        //<boolProp name="HTTPSampler.use_keepalive">true</boolProp>
        Element boolProp_3 = httpSamplerProxy.addElement("boolProp");
        boolProp_3.addAttribute("name", "HTTPSampler.use_keepalive");
        boolProp_3.setText("true");

        //<boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
        Element boolProp_4 = httpSamplerProxy.addElement("boolProp");
        boolProp_4.addAttribute("name", "HTTPSampler.DO_MULTIPART_POST");
        boolProp_4.setText("false");

        //<stringProp name="HTTPSampler.domain"></stringProp>
        Element stringProp_0 = httpSamplerProxy.addElement("stringProp");
        stringProp_0.addAttribute("name", "HTTPSampler.domain");
        stringProp_0.setText("");

        //<stringProp name="HTTPSampler.port"></stringProp>
        Element stringProp_1 = httpSamplerProxy.addElement("stringProp");
        stringProp_1.addAttribute("name", "HTTPSampler.port");
        stringProp_1.setText("");

        //<stringProp name="HTTPSampler.protocol">https</stringProp>
        Element stringProp_2 = httpSamplerProxy.addElement("stringProp");
        stringProp_2.addAttribute("name", "HTTPSampler.protocol");
        stringProp_2.setText("");

        //<stringProp name="HTTPSampler.contentEncoding"></stringProp>
        Element stringProp_3 = httpSamplerProxy.addElement("stringProp");
        stringProp_3.addAttribute("name", "HTTPSampler.contentEncoding");
        stringProp_3.setText("");

        //<stringProp name="HTTPSampler.path">/apimanager/api/v1/spring/cloud/gw</stringProp>
        Element stringProp_4 = httpSamplerProxy.addElement("stringProp");
        stringProp_4.addAttribute("name", "HTTPSampler.path");
        stringProp_4.setText(path);

        //<stringProp name="HTTPSampler.method">POST</stringProp>
        Element stringProp_5 = httpSamplerProxy.addElement("stringProp");
        stringProp_5.addAttribute("name", "HTTPSampler.method");
        stringProp_5.setText(jmeterCase.getMethod().toUpperCase());

        //<<stringProp name="HTTPSampler.embedded_url_re"></stringProp>
        Element stringProp_6 = httpSamplerProxy.addElement("stringProp");
        stringProp_6.addAttribute("name", "HTTPSampler.embedded_url_re");
        stringProp_6.setText("");

        //<stringProp name="HTTPSampler.implementation"></stringProp>
        Element stringProp_7 = httpSamplerProxy.addElement("stringProp");
        stringProp_7.addAttribute("name", "HTTPSampler.implementation");
        stringProp_7.setText("Java");

        //<stringProp name="HTTPSampler.connect_timeout"></stringProp>
        Element stringProp_8 = httpSamplerProxy.addElement("stringProp");
        stringProp_8.addAttribute("name", "HTTPSampler.connect_timeout");
        stringProp_8.setText("");

        //<stringProp name="HTTPSampler.response_timeout"></stringProp>
        Element stringProp_9 = httpSamplerProxy.addElement("stringProp");
        stringProp_9.addAttribute("name", "HTTPSampler.response_timeout");
        stringProp_9.setText("");


        return httpSamplerProxy;
    }

}
