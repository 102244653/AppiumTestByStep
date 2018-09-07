package AppTool.SelefTools;

import AppTool.Logger.TestLog;

import java.io.File;

/**
 * Created by Administrator on 2017/7/21.
 * 创建文件夹
 */
public class FileUtil {


    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路劲
     * @return ture  or  false
     */
    public  boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 判断文件是否存在，没有就新建
     *
     * @param filePath 文件路径
     */
    public static void creatFile(String filePath) {
        FileUtil fileUtil = new FileUtil();
        if(fileUtil.fileExists(filePath)==false) {
            File file = new File(filePath);
            file.mkdir();
            TestLog.info("FileUtil: creatFile","创建文件："+filePath);
        }
    }

    /**
     * 删除目录及目录下所有文件和文件夹
     */
    public  void deleteDirectory(String directoryPath) {
        File file = new File(directoryPath);
        if (file.isDirectory()) { //是文件夹
            File temp = null;
            String[] childsFile = file.list(); //获得该目录下的子文件及子文件夹
            for (String s : childsFile) {
                //检查folderPath是不是以"\"节尾
                if (directoryPath.endsWith(File.separator)) {
                    temp = new File(directoryPath + s);
                } else {
                    temp = new File(directoryPath + File.separator + s);
                }
                //delete file
                if (temp != null && temp.isFile()) {  //是文件
                    System.out.println(temp.delete());
                    TestLog.info("FileUtil: deleteDirectory","删除文件: " + temp.getAbsolutePath());
                } else if (temp != null && temp.isDirectory()) {  //是文件夹
                    deleteDirectory(temp.getAbsolutePath());
                    TestLog.info("FileUtil: deleteDirectory","删除文件夹: " + temp.getAbsolutePath());
                }
            }
            file.delete();
        } else if (file.isFile()) {
            file.delete();
        }
    }
}
