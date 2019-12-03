package com.djtest.apitest.util;

import com.djtest.apitest.model.JmxBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

public class SaveFileUtil {

    public static void save(String[] table_path,String[] sheets, String savepath) throws ParseException, DocumentException {
        List<JmxBean> cases = Table2JmxUtil.table2jmx(table_path,sheets);
        Document jmx_doc = JmeterUtil.getJmeterDoc(cases);

        if (jmx_doc != null) {
            File file = Paths.get(System.getProperty("user.dir"),savepath).toFile();

            String filePath = file.getAbsolutePath();
//            File file = new File(filePath);

            XMLWriter writer = null;
            try {
                writer = new XMLWriter(new FileOutputStream(filePath),
                        OutputFormat.createPrettyPrint());
                writer.write(jmx_doc);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ParseException, DocumentException {
        SaveFileUtil.save("\\demo\\api-testcase-demo.xls".split(";"),"Sheet1".split(";"),"\\demo\\jmeter.jmx");
    }
}
