package xyz.floatinglife.util;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 2019年8月7日17:17:29
 *
 * 功能：
 * 1.初始化JFrame文本内容
 * 2.将JFrame文本内容持久化到文件中
 *
 * @author flashylife
 */
public class TextAreaOperationsUtils {
    /**
     * 初始化和持久化的文件编码
     */
    private String charsetName = "UTF-8";
    /**
     * 路径
     */
    private String path = this.getClass().getClassLoader().getResource("").getFile().toString();

    /**
     * 初始化
     */
    public String init(String filePath) {
        String text = "";
        // 失败提示
        String fail = "init failed";

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(new File(path + filePath)), charsetName))) {
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fail;
    }

    /**
     * 持久化
     */
    public String persistence(String fileText, String filePath) {
        // 失败提示
        String fail = "persistence failed";
        // 成功提示
        String success = "persistence successful";

        if (StringUtils.isEmpty(fileText)) {
            return fail;
        }
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(new File(path + filePath)), charsetName))) {
            String[] strA = fileText.split("\\n");
            if (strA.length == 0) {
                return fail;
            }
            for (int i = 0; i < strA.length; i++) {
                pw.write(strA[i]);
                pw.write("\n");
            }
            return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fail;
    }

    /**
     * 获取输出路径
     *
     * @param text
     * @param str
     * @return
     */
    public String getPath(String text, String str) {
        // 失败提示
        String fail = "getPath fail";

        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(str)) {
            return fail;
        }
        // 长度
        int size = 4;
        // 忽略分割符
        String neglectSeparator = "#";
        // 文本分割符
        String textSeparator = "=";
        String[] strArray = text.split("\\n");
        if (strArray.length != size) {
            return fail;
        }
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].startsWith(neglectSeparator)) {
                continue;
            }
            String[] pathArray = strArray[i].split(textSeparator);
            if (pathArray.length == 2 && str.equals(pathArray[0])) {
                return pathArray[1];
            }
        }
        return fail;
    }

    /**
     * 静态内部类实现单例
     *
     * @return
     */
    public static TextAreaOperationsUtils getInstance() {
        return Instance.util;
    }

    private static class Instance {
        private static final TextAreaOperationsUtils util = new TextAreaOperationsUtils();
    }
}
