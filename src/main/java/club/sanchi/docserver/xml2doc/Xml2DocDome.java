package club.sanchi.docserver.xml2doc;

import club.sanchi.docserver.util.FileUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangpeng on 2019/1/8 15:16
 */
public class Xml2DocDome {
    public static void main(String[] args) throws IOException, TemplateException {
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        dataMap.put("title", "测试转换word文档");
//        dataMap.put("head", "头部信息");
//        dataMap.put("content", "主要内容主要内容主要内容");
//        dataMap.put("tableDescription", "表格描述内容");
//        List<Student> list = new ArrayList<>();
//
//        Student student = new Student("张三", "男",1, FileUtil.localImage2Base64("src/main/resources/img/1.jpg"), 100, 80);
//        list.add(student);
//        Student student1 = new Student("李四", "男",2, FileUtil.localImage2Base64("src/main/resources/img/2.jpg"), 100, 80);
//        list.add(student1);
//
//        dataMap.put("list", list);
//        String templateFilePath = "src/main/resources/templates/20190108.xml";
//        String targetFilePath = "src/main/resources/out/20190108.doc";
//        FileUtil.xml2Doc(dataMap,templateFilePath,"/", targetFilePath, "/");

//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("title", "测试");
//        List<Row> r1list = new ArrayList<>();
//        Row row = new Row();
//        row.setC1("1");
//        row.setC2("张三");
//        row.setC3("男");
//        row.setC4("111");
//
//        r1list.add(row);
//
//        Row row1 = new Row();
//        row1.setC1("2");
//        row1.setC2("李四");
//        row1.setC3("女");
//        row1.setC4("222");
//        r1list.add(row1);
//
//        dataMap.put("r1list", r1list);
//        FileUtil.exportExcel(dataMap,
//                "src/main/resources/templates/20190108.xlsx",
//                "src/main/resources/out/20190108.xlsx", "/");

        String smartCardNumber = "哈哈哈哈哈";
        System.out.println("".trim().isEmpty());
        if (null == smartCardNumber || smartCardNumber.trim().isEmpty()) {
            System.out.println("1");
        } else if (smartCardNumber.length() < 5
                || smartCardNumber.length() > 20
                || !smartCardNumber.matches("^[a-z0-9A-Z]+$")) {
            System.out.println("2");
        } else {
            System.out.println("3");
        }
//        System.out.println(
//
//                "=============>"+
//
//                new SimpleDateFormat("yyyy").format(new Timestamp(Long.valueOf("1546272000000"))));
//
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(2019, 0, 1, 0, 0, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        System.out.println(calendar.getTime().getTime());

//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Timestamp(Long.valueOf("1546272000000")));//设置起时间
//        cal.add(Calendar.YEAR, 1);
//        Date endTime = cal.getTime();
//        System.out.println(endTime.getTime());
    }
}
