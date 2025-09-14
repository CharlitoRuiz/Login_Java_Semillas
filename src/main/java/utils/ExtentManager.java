package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/Reporte_" + timestamp + ".html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String nombre) {
        test = getInstance().createTest(nombre);
        return test;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static ExtentTest getTest() {
        return test;
    }
}
