package club.sanchi.docserver.util;

import club.sanchi.docserver.base.BizException;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.poi.ss.usermodel.Workbook;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * 文件工具
 * Created by wangpeng on 2018/12/5 19:33
 */
public class FileUtil {

    /**
     * 获得文件夹路径
     * @param filePath 文件完整路径
     * @param separator 文件夹与文件名之间的分隔符
     * @return
     */
    public static String getFolder(String filePath, String separator){
        AssertUtil.isEmpty(filePath,"缺少文件完整路径");
        AssertUtil.isEmpty(separator,"缺少文件夹与文件名之间的分隔符");
        return filePath.substring(0, filePath.lastIndexOf(separator));
    }
    /**
     * 获得文件名
     * @param filePath 文件完整路径
     * @param separator 文件夹与文件名之间的分隔符
     * @return
     */
    public static String getFileName(String filePath, String separator){
        AssertUtil.isEmpty(filePath,"缺少文件完整路径");
        AssertUtil.isEmpty(separator,"缺少文件夹与文件名之间的分隔符");
        return filePath.substring(filePath.lastIndexOf(separator) + 1);
    }

    /**
     * 创建文件目录
     * @param folderPath 文件目录地址
     * @throws BizException
     */
    public static void createFolder(String folderPath) throws BizException {
        AssertUtil.isEmpty(folderPath,"文件目录为空");
        File file = new File(folderPath);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 下载网络文件本地
     * @param src 网络文件url
     * @param fileSavePath 文件保存完整地址（文件目录+文件名）
     * @throws IOException
     */
    public static void downloadNetworkFiles(String src, String fileSavePath) throws IOException {
        AssertUtil.isEmpty(src,"缺少网络文件url");
        AssertUtil.isEmpty(fileSavePath,"缺少保存到本地文件的完整地址");
        URL url = new URL(src);
        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileSavePath));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = dataInputStream.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        fileOutputStream.write(output.toByteArray());
        dataInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 将网络图片转成base64格式
     * @param src
     * @return
     * @throws IOException
     */
    public static String networkImage2Base64(String src) throws IOException {
        AssertUtil.isEmpty(src,"缺少网络图片url");
        URL url = new URL(src);
        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = dataInputStream.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        dataInputStream.close();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(output.toByteArray());
        return base64
                .replace("\n", "\r\n")
                .replace("\r\r\n", "\r\n");
    }

    /**
     * 将本地图片转成base64格式
     * @param imgFilePath 本地图片地址
     * @return
     * @throws IOException
     */
    public static String localImage2Base64(String imgFilePath) throws IOException {
        AssertUtil.isEmpty(imgFilePath,"缺少本地图片地址");
        InputStream in = new FileInputStream(imgFilePath);
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        int len = -1;
        byte[] by = new byte[1024];
        while ((len = in.read(by)) != -1) {
            data.write(by, 0, len);
        }
        in.close();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(data.toByteArray());
        return base64
                .replace("\n", "\r\n")
                .replace("\r\r\n", "\r\n");
    }

    /**
     * 根据 word文档flt模板 生成 doc文件
     * @param dataMap 数据源
     * @param templateFilePath 模板文件的完整地址
     * @param templateSeparator 模板文件所在文件夹与文件之间的分隔符
     * @param targetFilePath 生成的目标文件的完整地址
     * @param targetSeparator 生成的目标文件所在文件夹与文件之间的分隔符
     * @throws IOException
     * @throws TemplateException
     * @throws BizException
     */
    public static void xml2Doc(Map<String,Object> dataMap,
                                  String templateFilePath, String templateSeparator,
                                  String targetFilePath, String targetSeparator)
            throws IOException, TemplateException, BizException {
        // 将模板文件路径拆分为文件夹路径和文件名称
        String templateDir = getFolder(templateFilePath, templateSeparator);
        String templateName = getFileName(templateFilePath, templateSeparator);

        // 将目标文件保存路径拆分为文件夹路径和文件名称
        String targetDir = getFolder(targetFilePath, targetSeparator);
        String targetName = getFileName(targetFilePath, targetSeparator);

        // 如果目标文件目录不存在，则需要创建
        createFolder(targetDir);

        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        // 加载模板数据
        configuration.setDirectoryForTemplateLoading(new File(templateDir));
        // 获取模板实例
        Template template = configuration.getTemplate(templateName);
        File outFile = new File(targetDir + File.separator + targetName);
        //将模板和数据模型合并生成文件
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        //生成文件
        template.process(dataMap, out);
        out.flush();
        out.close();
    }

    /**
     * 使用 easypoi 根据模板导出excel
     * @param dataMap 数据源
     * @param templatePath 模板路径，包括文件名
     * @param targetPath 生成的目标文件的完整地址，包括文件名
     * @param targetSeparator  生成的目标文件所在文件夹与文件之间的分隔符
     * @throws IOException
     */
    public static void exportExcel (Map<String, Object> dataMap,
                                    String  templatePath,
                                    String targetPath,
                                    String targetSeparator) throws IOException {
        String targetDir = getFolder(targetPath, targetSeparator);
        // 如果目标文件目录不存在，则需要创建
        createFolder(targetDir);

        TemplateExportParams params = new TemplateExportParams(templatePath);
        Workbook workbook = ExcelExportUtil.exportExcel(params, dataMap);
        FileOutputStream fos = new FileOutputStream(targetPath);
        if (null != workbook) {
            workbook.write(fos);
        }
        if (null != fos) {
            fos.close();
        }
    }

    /**
     * 输出文件流
     * @param response
     * @param path 文件完整地址
     * @return
     * @throws IOException
     */
    public static HttpServletResponse outPutStream(HttpServletResponse response, String path)
            throws IOException {
        // path是指欲下载的文件的路径。
        File file = new File(path);
        String fileName = file.getName();
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        return response;
    }
}