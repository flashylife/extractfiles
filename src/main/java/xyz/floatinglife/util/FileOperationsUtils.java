package xyz.floatinglife.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 2019年8月7日16:13:50
 * 
 * 文件操作工具类
 * 
 * @author flashylife
 *
 */
public class FileOperationsUtils {
	public static void copyOne(String fromFile,String toFile) throws IOException{
		FileInputStream ins = new FileInputStream(fromFile);
		
		createNewFile(toFile);

		FileOutputStream out = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int i = ins.read(b);
		while(i != -1){
			out.write(b,0,i);
			i=ins.read(b);
		}
		ins.close();
		out.close();
	}

	private static void createNewFile(String toFile) throws IOException {
		
		File file = new File(toFile);
		File pFile = file.getParentFile();
		if (pFile.exists()) {
			if (file.exists()) {
				file.delete();
				file.createNewFile();
			} else {
				file.createNewFile();
			}
		} else {
			pFile.mkdirs();
			file.createNewFile();
		}
	}
}
