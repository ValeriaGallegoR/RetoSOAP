package co.com.sofka.runner.soap.countryphonecode;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/countryinfo/countrycodephone.feature"},
        glue = {"co.com.sofka.stepdefinitions.soap.countrycodephone"}
)
public class AddWithCucumberTest {
}
