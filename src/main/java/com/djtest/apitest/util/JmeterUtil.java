package com.djtest.apitest.util;


import com.djtest.apitest.model.ArgsBean;
import com.djtest.apitest.model.JmxBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class JmeterUtil {

    public static Document getJmeterDoc(JmxBean testcase){
        // jmeter文件模板，包含了一个线程组，http请求默认值，http cookie管理器， http信息头管理器
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<jmeterTestPlan version=\"1.2\" properties=\"3.2\" jmeter=\"3.3 r1808647\">\n"
                + "  <hashTree>\n"
                + "    <TestPlan guiclass=\"TestPlanGui\" testclass=\"TestPlan\" testname=\"测试计划\" enabled=\"true\">\n"
                + "      <stringProp name=\"TestPlan.comments\"></stringProp>\n"
                + "      <boolProp name=\"TestPlan.functional_mode\">false</boolProp>\n"
                + "      <boolProp name=\"TestPlan.serialize_threadgroups\">false</boolProp>\n"
                + "      <elementProp name=\"TestPlan.user_defined_variables\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n"
                + "        <collectionProp name=\"Arguments.arguments\"/>\n"
                + "      </elementProp>\n"
                + "      <stringProp name=\"TestPlan.user_define_classpath\"></stringProp>\n"
                + "    </TestPlan>\n"
                + "    <hashTree>\n"
                + "      <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"线程组\" enabled=\"true\">\n"
                + "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n"
                + "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"循环控制器\" enabled=\"true\">\n"
                + "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n"
                + "          <stringProp name=\"LoopController.loops\">1</stringProp>\n"
                + "        </elementProp>\n"
                + "        <stringProp name=\"ThreadGroup.num_threads\">1</stringProp>\n"
                + "        <stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n"
                + "        <longProp name=\"ThreadGroup.start_time\">1573539493000</longProp>\n"
                + "        <longProp name=\"ThreadGroup.end_time\">1573539493000</longProp>\n"
                + "        <boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n"
                + "        <stringProp name=\"ThreadGroup.duration\"></stringProp>\n"
                + "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n"
                + "      </ThreadGroup>\n"
                + "      <hashTree>\n"
                + "        <ConfigTestElement guiclass=\"HttpDefaultsGui\" testclass=\"ConfigTestElement\" testname=\"HTTP请求默认值\" enabled=\"true\">\n"
                + "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n"
                + "            <collectionProp name=\"Arguments.arguments\"/>\n"
                + "          </elementProp>\n"
                + "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.protocol\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.path\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.concurrentPool\">6</stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n"
                + "        </ConfigTestElement>\n"
                + "        <hashTree/>\n"
                + "        <CookieManager guiclass=\"CookiePanel\" testclass=\"CookieManager\" testname=\"HTTP Cookie 管理器\" enabled=\"true\">\n"
                + "          <collectionProp name=\"CookieManager.cookies\"/>\n"
                + "          <boolProp name=\"CookieManager.clearEachIteration\">false</boolProp>\n"
                + "        </CookieManager>\n"
                + "        <hashTree/>\n"
                + "        <HeaderManager guiclass=\"HeaderPanel\" testclass=\"HeaderManager\" testname=\"HTTP信息头管理器\" enabled=\"true\">\n"
                + "          <collectionProp name=\"HeaderManager.headers\"/>\n"
                + "        </HeaderManager>\n"
                + "        <hashTree/>\n"
                + "      </hashTree>\n"
                + "    </hashTree>\n"
                + "    <WorkBench guiclass=\"WorkBenchGui\" testclass=\"WorkBench\" testname=\"工作台\" enabled=\"true\">\n"
                + "      <boolProp name=\"WorkBench.save\">true</boolProp>\n"
                + "    </WorkBench>\n"
                + "    <hashTree/>\n"
                + "  </hashTree>\n"
                + "</jmeterTestPlan>";
        try{
            // 字符串转成document格式，用来操作xml
            Document document = DocumentHelper.parseText(xml);
            // 获取当前对象的根节点
            Element root = document.getRootElement();
            //如果需要修改线程组信息，可以操作root来修改
            getNewRoot(root,testcase);
            System.out.println(document.asXML());
            return document;

        }catch (DocumentException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Document getJmeterDoc(List<JmxBean> testcases){
        // jmeter文件模板，包含了一个线程组，http请求默认值，http cookie管理器， http信息头管理器
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<jmeterTestPlan version=\"1.2\" properties=\"3.2\" jmeter=\"3.3 r1808647\">\n"
                + "  <hashTree>\n"
                + "    <TestPlan guiclass=\"TestPlanGui\" testclass=\"TestPlan\" testname=\"测试计划\" enabled=\"true\">\n"
                + "      <stringProp name=\"TestPlan.comments\"></stringProp>\n"
                + "      <boolProp name=\"TestPlan.functional_mode\">false</boolProp>\n"
                + "      <boolProp name=\"TestPlan.serialize_threadgroups\">false</boolProp>\n"
                + "      <elementProp name=\"TestPlan.user_defined_variables\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n"
                + "        <collectionProp name=\"Arguments.arguments\"/>\n"
                + "      </elementProp>\n"
                + "      <stringProp name=\"TestPlan.user_define_classpath\"></stringProp>\n"
                + "    </TestPlan>\n"
                + "    <hashTree>\n"
                + "      <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"线程组\" enabled=\"true\">\n"
                + "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n"
                + "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"循环控制器\" enabled=\"true\">\n"
                + "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n"
                + "          <stringProp name=\"LoopController.loops\">1</stringProp>\n"
                + "        </elementProp>\n"
                + "        <stringProp name=\"ThreadGroup.num_threads\">1</stringProp>\n"
                + "        <stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n"
                + "        <longProp name=\"ThreadGroup.start_time\">1573539493000</longProp>\n"
                + "        <longProp name=\"ThreadGroup.end_time\">1573539493000</longProp>\n"
                + "        <boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n"
                + "        <stringProp name=\"ThreadGroup.duration\"></stringProp>\n"
                + "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n"
                + "      </ThreadGroup>\n"
                + "      <hashTree>\n"
                + "        <ConfigTestElement guiclass=\"HttpDefaultsGui\" testclass=\"ConfigTestElement\" testname=\"HTTP请求默认值\" enabled=\"true\">\n"
                + "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n"
                + "            <collectionProp name=\"Arguments.arguments\"/>\n"
                + "          </elementProp>\n"
                + "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.protocol\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.path\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.concurrentPool\">6</stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n"
                + "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n"
                + "        </ConfigTestElement>\n"
                + "        <hashTree/>\n"
                + "        <CookieManager guiclass=\"CookiePanel\" testclass=\"CookieManager\" testname=\"HTTP Cookie 管理器\" enabled=\"true\">\n"
                + "          <collectionProp name=\"CookieManager.cookies\"/>\n"
                + "          <boolProp name=\"CookieManager.clearEachIteration\">false</boolProp>\n"
                + "        </CookieManager>\n"
                + "        <hashTree/>\n"
                + "        <HeaderManager guiclass=\"HeaderPanel\" testclass=\"HeaderManager\" testname=\"HTTP信息头管理器\" enabled=\"true\">\n"
                + "          <collectionProp name=\"HeaderManager.headers\"/>\n"
                + "        </HeaderManager>\n"
                + "        <hashTree/>\n"
                + "        <ResultCollector guiclass=\"ViewResultsFullVisualizer\" testclass=\"ResultCollector\" testname=\"察看结果树\" enabled=\"true\">"
                + "          <boolProp name=\"ResultCollector.error_logging\">false</boolProp>"
                + "          <objProp>"
                + "            <name>saveConfig</name>"
                + "            <value class=\"SampleSaveConfiguration\">"
                + "              <time>true</time>"
                + "              <latency>true</latency>"
                + "              <timestamp>true</timestamp>"
                + "              <success>true</success>"
                + "              <label>true</label>"
                + "              <code>true</code>"
                + "              <message>true</message>"
                + "              <threadName>true</threadName>"
                + "              <dataType>true</dataType>"
                + "              <encoding>false</encoding>"
                + "              <assertions>true</assertions>"
                + "              <subresults>true</subresults>"
                + "              <responseData>false</responseData>"
                + "              <samplerData>false</samplerData>"
                + "              <xml>false</xml>"
                + "              <fieldNames>true</fieldNames>"
                + "              <responseHeaders>false</responseHeaders>"
                + "              <requestHeaders>false</requestHeaders>"
                + "              <responseDataOnError>false</responseDataOnError>"
                + "              <saveAssertionResultsFailureMessage>true</saveAssertionResultsFailureMessage>"
                + "              <assertionsResultsToSave>0</assertionsResultsToSave>"
                + "              <bytes>true</bytes>"
                + "              <sentBytes>true</sentBytes>"
                + "              <threadCounts>true</threadCounts>"
                + "              <idleTime>true</idleTime>"
                + "              <connectTime>true</connectTime>"
                + "            </value>"
                + "          </objProp>"
                + "          <stringProp name=\"filename\"></stringProp>"
                + "        </ResultCollector>"
                + "        <hashTree/>\n"
                + "      </hashTree>\n"
                + "    </hashTree>\n"
                + "    <WorkBench guiclass=\"WorkBenchGui\" testclass=\"WorkBench\" testname=\"工作台\" enabled=\"true\">\n"
                + "      <boolProp name=\"WorkBench.save\">true</boolProp>\n"
                + "    </WorkBench>\n"
                + "    <hashTree/>\n"
                + "  </hashTree>\n"
                + "</jmeterTestPlan>";
        try{
            // 字符串转成document格式，用来操作xml
            Document document = DocumentHelper.parseText(xml);
            // 获取当前对象的根节点
            Element root = document.getRootElement();
            //如果需要修改线程组信息，可以操作root来修改
            for (JmxBean testcase:testcases) {
                getNewRoot(root, testcase);
            }
//            System.out.println(document.asXML());
            return document;

        }catch (DocumentException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取最终的结果
     * @return
     */
    private static Element getNewRoot(Element root, JmxBean testcase){
        Element hashTreeL1 = root.element("hashTree"); // 1级 测试计划
        Element hashTreeL2 = hashTreeL1.element("hashTree"); // 2级，线程组
        Element hashTreeL3 = hashTreeL2.element("hashTree"); // 3级

        Element httpSampler = hashTreeL3.addElement("HTTPSamplerProxy");
        httpSampler=JmeterSamplerUtil.add(httpSampler,testcase);

        Element hashTree = hashTreeL3.addElement("hashTree");
        hashTree = addHashTree(hashTree,testcase);

        //断言和后置处理器后民间再添加

        return root;
    }

    // 新增断言和后置处理器
    private static Element addHashTree(Element hashTree, JmxBean testcase){
        if(testcase!=null && testcase.getSaveName() != null && !testcase.getSaveName().equalsIgnoreCase("")){
            JmeterProcessorUtil.add(hashTree,testcase);
        }
        if (testcase!=null && testcase.getAssertMsg()!=null){
            JmeterAssertionUtil.add(hashTree,testcase.getAssertMsg());
        }

        return hashTree;
    }

    public static void main(String[] args){
        List<ArgsBean> list = new ArrayList<ArgsBean>();
        ArgsBean a = new ArgsBean();
        a.setName("arg1");
        a.setValue("value1");
        list.add(a);

        JmxBean testcase = new JmxBean();
        testcase.setCaseName("test001");
        testcase.setMethod("Post");
        testcase.setParams(list);
        testcase.setType("json");
        testcase.setPath("/test.do");
        testcase.setAssertMsg("true");
        testcase.setSaveName("test1");
        testcase.setSaveExpression("$.data.list");

        JmeterUtil.getJmeterDoc(testcase);
    }
}
