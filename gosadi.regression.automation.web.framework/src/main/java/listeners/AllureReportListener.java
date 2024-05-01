package listeners;

import browserFactory.DriverInit;
import io.qameta.allure.Attachment;
import logger.LOG;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.JiraPolicy;
import utilities.JiraServiceProvider;

import java.util.ArrayList;
import java.util.List;

import static logger.LOG.errorMsgDetails;

public class AllureReportListener implements ITestListener {

    public static boolean verifyElementsFail = false;
    public static List<String> failedElementList = new ArrayList<>();

    private static synchronized String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        verifyElementsFail = false;
        failedElementList.clear();
        LOG.INFO(" Starting Test Suite " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOG.INFO(" Finished Test Suite '" + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        verifyElementsFail = false;
        failedElementList.clear();
        LOG.INFO(getTestMethodName(iTestResult) + " Test is Starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

          LOG.INFO(getTestMethodName(iTestResult) + " Test is Succeed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOG.INFO("Testcase '" + iTestResult.getMethod().getMethodName() +
                "' failed, because of " + iTestResult.getThrowable());
        captureScreenshotOnFailure(DriverInit.getDriver());
        errorMsgDetails(5, iTestResult);
        JiraPolicy jiraPolicy = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
        boolean isTicketReady = jiraPolicy.logTicketReady();
        if (isTicketReady) {
            LOG.INFO("Ticket is ready for JIRA: " + isTicketReady);
            JiraServiceProvider jiraSp = new JiraServiceProvider("https://appinventivtech.atlassian.net/",
                    "ashish.bhalray8@appinventiv.com", "ATATT3xFfGF0zcfGYm-HdmMNUSQ2P12Gf0Isk0eBNttELvgZzS3KSkFvL_nO6Efhtxbo-I8jEWtXS3FXBR1rVaQTYwdpbh_YmmIvCW78KB-XKQbxc9onuHyKKUbiPaG2bfZelinlvr2RfltFSeoI2Q4mnr78atlU65aLeFLWvcPnOWQRoq787BI=A3C7C994");
            String issueDescription = iTestResult.getThrowable().getMessage() + "\n";

            issueDescription.concat(ExceptionUtils.getFullStackTrace(iTestResult.getThrowable()));

            String issueSummary = iTestResult.getMethod().getConstructorOrMethod().getMethod().getName()
                    + " Got failed due to ";

            jiraSp.createJiraTicket("Bug", issueSummary, issueDescription );
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] captureScreenshotOnFailure(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String skipReason = iTestResult.getThrowable().getMessage();
        LOG.INFO(getTestMethodName(iTestResult)  + " test is skipped. Reason: " + skipReason);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOG.INFO("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}
