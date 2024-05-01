package logger;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;



public class LOG {

    private final static Logger logger = LoggerFactory.getLogger(LOG.class);

    public static void INFO(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void Reporter(String Message) {
        INFO(Message);
        Allure.step(Message);
    }

    // Allure annotation for attaching text
    @Attachment(value = "Error Details", type = "text/plain")
    public static String errorMsgDetails(int count, ITestResult iTestResult) {
        StringBuilder errorDetails = new StringBuilder();
        for (int i = 0; i < count; i++) {
            errorDetails.append("Error in::")
                    .append(iTestResult.getThrowable().getStackTrace()[i].getClassName())
                    .append("  And methodName: ")
                    .append(iTestResult.getThrowable().getStackTrace()[i].getMethodName())
                    .append("  And lineNumber: ")
                    .append(iTestResult.getThrowable().getStackTrace()[i].getLineNumber())
                    .append("\n");
        }
        return errorDetails.toString();
    }
}
