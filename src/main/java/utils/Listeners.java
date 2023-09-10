package utils;

import base.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class Listeners implements ITestListener {
    BaseClass baseClass = new BaseClass();

    public void onTestFailure(ITestResult result) {
        if(result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);

            System.out.println(sw);
        }
        // ---------------- Screenshot ---------------- //

        File file = baseClass.getDriver().getScreenshotAs(OutputType.FILE);

        byte[] encoded = null;
        try {
            encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        String imagePath = "Screenshots" + File.separator + new ConfigLoader().initializeProperty().getProperty("Platform")
                + "_" + new ConfigLoader().initializeProperty().getProperty("Device") + File.separator + baseClass.dateTime() + File.separator
                + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";

        String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;

        try {
            FileUtils.copyFile(file, new File(completeImagePath));
            Reporter.log("This is the "+result.getName()+"'s failed screenshot");

            byte[] screenshotBytes = FileUtils.readFileToByteArray(new File(completeImagePath));
            String screenshotBase64 = Base64.encodeBase64String(screenshotBytes);

            Reporter.log("<a href='" + completeImagePath + "'> <img src='data:image/png;base64," + screenshotBase64 + "' height='250' width='250'/> </a>");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Extent_Report.getTest().fail("Test Failed",
                MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
    }

    @Override
    public void onTestStart(ITestResult result) {
        Extent_Report.startTest(result.getName(), result.getMethod().getDescription())
                .assignCategory(new ConfigLoader().initializeProperty().getProperty("Platform") + "_" + new ConfigLoader().initializeProperty().getProperty("Device"))
                .assignAuthor("Mustafizur Rahman");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Extent_Report.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Extent_Report.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) { }

    @Override
    public void onStart(ITestContext iTestContext) { }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Extent_Report.getReporter().flush();
    }
}
