/**
 * @author Veera Anandha Selvi Saravana Muthu
 *
 * Explanation: Executes the test
 */

/***************************************************/
package Runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
       
        features = "src/Features",
        glue= {"stepdefinitions"}
       
)

public class Task1_Runner {

}
