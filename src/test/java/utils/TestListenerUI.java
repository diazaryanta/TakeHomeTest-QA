package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListenerUI implements ITestListener {

    private static ExtentReports extent = ExtentReportUI.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getDescription() != null ?
                result.getMethod().getDescription() : result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "UI Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "UI Test Failed: " + result.getThrowable());

        try {
            Object testClass = result.getInstance();
            org.openqa.selenium.WebDriver driver = ((ui.base.BaseUITest) testClass).getDriver();
            String screenshotPath = ExtentReportUI.captureScreenshot(driver, result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(screenshotPath, "Bukti Screenshot Gagal");
        } catch (Exception e) {
            System.out.println("Gagal mengambil driver untuk screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "UI Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}