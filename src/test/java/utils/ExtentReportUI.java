package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUI {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/UI_Report.html");

            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("GitHub Gist Automation Result");
            spark.config().setDocumentTitle("QA Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Diaz");
            extent.setSystemInfo("Environment", "Production");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String screenshotName = testName + "_" + dateName + ".png";
        String destinationPath = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName;
        File finalDestination = new File(destinationPath);

        if (!finalDestination.getParentFile().exists()) {
            finalDestination.getParentFile().mkdirs();
        }

        try {
            FileHandler.copy(source, finalDestination);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan screenshot: " + e.getMessage());
        }

        return "screenshots/" + screenshotName;
    }
}