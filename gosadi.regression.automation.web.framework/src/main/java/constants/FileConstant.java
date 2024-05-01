package constants;

import utilities.CommonUtils;

import java.io.File;
import java.nio.file.Paths;

public class FileConstant {
    private static final String environmentPropertyFilePath
            = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "Environment.properties").toString();

    public static String getEnvironmentKey(String environmentKey) {
        if (new File(environmentPropertyFilePath).exists()) {
            return CommonUtils.readPropertyFile(environmentPropertyFilePath)
                    .get(environmentKey)
                    .toString();
        }
        throw new RuntimeException("Environment File not exist at" + environmentPropertyFilePath);
    }

}
