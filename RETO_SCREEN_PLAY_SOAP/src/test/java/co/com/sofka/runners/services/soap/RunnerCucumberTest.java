package co.com.sofka.runners.services.soap;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/services/soap/languaje/languajes.feature",
                "src/test/resources/features/services/soap/temperature/temperature.feature"},
        glue = {"co.com.sofka.stepdefinitions.services.soap"}
)


public class RunnerCucumberTest {
}
