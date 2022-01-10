package xyz.floatinglife.service;

import xyz.floatinglife.util.FileOperationsUtils;
import xyz.floatinglife.util.TextAreaOperationsUtils;

/**
 * 2019年8月7日16:13:14
 *
 * 功能：提取资源服务
 *
 * @author flashylife
 *
 */
public class FileCopyService {

	private static FileCopyService copyFileService;
	/** 输出文本 */
	private String outText;
	/** 输入文本 */
	private String inText;


	/**
	 * 懒汉模式单例
	 *
	 * @return
	 */
	public static FileCopyService getCopyFileService() {
		if (copyFileService == null) {
			copyFileService = new FileCopyService();
		}
		return copyFileService;
	}

	/**
	 * 执行方法
	 *
	 * @return
	 * 		成功或者失败提示
	 */
	public String execute() {
		String sourcePath = TextAreaOperationsUtils.getInstance().getPath(outText, "source_path");
		String desPath = TextAreaOperationsUtils.getInstance().getPath(outText, "des_path");

		String fromFile = "";
		String toFile = "";
		// 需要复制文件的个数
		int count1 = 0;
		// 实际复制文件个数
		int count2 = 0;
		String[] strA = this.inText.split("\\n");
		if (strA.length == 0) {
			return "请确认配置文件是否正确!";
		}
		for (int i = 0; i < strA.length; i++) {
			fromFile = sourcePath + strA[i];
			toFile = desPath + strA[i];
			count1++;
			try {
				FileOperationsUtils.copyOne(fromFile, toFile);
				count2++;
			} catch (Exception e) {
				e.printStackTrace();
				return "请检查路径是否正确:" + fromFile;
			}
		}
		return "需要复制文件个数为:" + count1 + ",实际复制文件个数为:" + count2;
	}

	public String getOutText() {
		return outText;
	}
	public void setOutText(String outText) {
		this.outText = outText;
	}
	public String getInText() {
		return inText;
	}
	public void setInText(String inText) {
		this.inText = inText;
	}
}