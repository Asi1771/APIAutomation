package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = "html:target/reports/cucumberReport.html",
        features = "src/test/resources",
        glue = "steps",
        tags = "@getInvoice",
        dryRun = false
)

public class CucumberRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return  super.scenarios();
    }
}
