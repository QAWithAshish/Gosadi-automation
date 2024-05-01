package utilities;

import logger.LOG;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class CommonUtils {

    public static Properties readPropertyFile(String filePath) {
        try (FileInputStream input = new FileInputStream(filePath)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            throw new RuntimeException("Exception occurred for filePath" + filePath + ":" + ex.getMessage());
        }
    }

    public static void holdForElement(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception ex) {

            LOG.INFO(ex.getMessage());
        }
    }

    public static String filePath(String fileName) {
        String filePath = Paths.get(System.getProperty("user.dir"), "src", "test", "resources" ,"mediaFile" + File.separator + fileName).toString();
        return filePath;
    }


}
