package testrunners;

import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;


@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
        jsonReport = "target/study-setup.json",
        overviewReport = true,
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewChartsReport = true,
        outputFolder = "./target",
        toPDF = true,
        screenShotLocation ="./target/screenshots")
@CucumberOptions(
        features="./src/main/resources/features/",
        glue={"test_PageObjectsModel","testPageObjects","testrunners","testHooks_Utility","test_StepsFolder"},
        plugin={ "pretty",
                "html:target/study-setup-html-report",
                "json:target/study-setup.json",
                "junit:target/testableResults.xml"},
        tags = {"~@ignore"}
        

)

public class TestRunner {

}