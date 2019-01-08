package club.sanchi.docserver.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
  * 功能： 1 、实现把指定文件夹下的所有文件压缩为指定文件夹下指定 zip 文件 2 、实现把指定文件夹下的 zip 文件解压到指定目录下
  *
  * @author ffshi
  *
  */
public class ZipUtils {

	private static Log logger = LogFactory.getLog(ZipUtils.class);

	/**
	     *    
	     * @param inputFileName 输入一个文件夹   
	     * @param zipFileName   输出一个压缩文件夹，打包后文件名字   
	     * @throws Exception   
	     */    
	    public static boolean zip(String inputFileName, String zipFileName) throws Exception {
			logger.info("需要压缩的文件路径" + inputFileName);
			zip(zipFileName, new File(inputFileName));
			logger.info("压缩后的文件" + zipFileName);
			return true;
	    }     
	    
	    private static void zip(String zipFileName, File inputFile) throws Exception {
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
	                zipFileName));     
	        zip(out, inputFile, "");
			out.close();
	    }     
	    
	    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
	        if (f.isDirectory()) {  //判断是否为目录     
	            File[] fl = f.listFiles();     
//	            out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));     
	            out.putNextEntry(new ZipEntry(base + "/"));     
	            base = base.length() == 0 ? "" : base + "/";     
	            for (int i = 0; i < fl.length; i++) {     
	                zip(out, fl[i], base + fl[i].getName());     
	            }     
	        } else {                //压缩目录中的所有文件
	            out.putNextEntry(new ZipEntry(base));
	            FileInputStream in = new FileInputStream(f);     
	            int b;
	            while ((b = in.read()) != -1) {     
	                out.write(b);     
	            }     
	            in.close();     
	        }
	    }

	/**
	 * 替换某个 item,
	 * @param zipInputStream zip文件的zip输入流
	 * @param zipOutputStream 输出的zip输出流
	 * @param itemName 要替换的 item 名称
	 * @param itemInputStream 要替换的 item 的内容输入流
	 */
	public static void replaceItem(ZipInputStream zipInputStream,
								   ZipOutputStream zipOutputStream,
								   String itemName,
								   InputStream itemInputStream
	){
		//
		if(null == zipInputStream){return;}
		if(null == zipOutputStream){return;}
		if(null == itemName){return;}
		if(null == itemInputStream){return;}
		//
		ZipEntry entryIn;
		try {
			while((entryIn = zipInputStream.getNextEntry())!=null)
			{
				String entryName =  entryIn.getName();
				ZipEntry entryOut = new ZipEntry(entryName);
				// 只使用 name
				zipOutputStream.putNextEntry(entryOut);
				// 缓冲区
				byte [] buf = new byte[8*1024];
				int len;

				if(entryName.equals(itemName)){
					// 使用替换流
					while((len = (itemInputStream.read(buf))) > 0) {
						zipOutputStream.write(buf, 0, len);
					}
				} else {
					// 输出普通Zip流
					while((len = (zipInputStream.read(buf))) > 0) {
						zipOutputStream.write(buf, 0, len);
					}
				}
				// 关闭此 entry
				zipOutputStream.closeEntry();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//e.printStackTrace();
			close(itemInputStream);
			close(zipInputStream);
			close(zipOutputStream);
		}
	}

	/**
	 * 包装输入流
	 */
	public static ZipInputStream wrapZipInputStream(InputStream inputStream){
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		return zipInputStream;
	}

	/**
	 * 包装输出流
	 */
	public static ZipOutputStream wrapZipOutputStream(OutputStream outputStream){
		ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
		return zipOutputStream;
	}
	private static void close(InputStream inputStream){
		if (null != inputStream){
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void close(OutputStream outputStream){
		if (null != outputStream){
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
