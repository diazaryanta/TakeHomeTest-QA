package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportAPI {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/API_Report.html");

            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("Reqres API Automation Result");
            spark.config().setDocumentTitle("API Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Diaz");
            extent.setSystemInfo("Environment", "API Staging");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}