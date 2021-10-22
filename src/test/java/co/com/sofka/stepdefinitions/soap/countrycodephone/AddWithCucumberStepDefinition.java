package co.com.sofka.stepdefinitions.soap.countrycodephone;

import co.com.sofka.stepdefinitions.soap.calculator.SetUp;
import co.com.sofka.tasks.countryinfo.DoPost;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.sofka.questions.ReturnStringValue.systemValue;
import static co.com.sofka.util.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

public class AddWithCucumberStepDefinition extends SetUp {
    private static final String ADD_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\countryinfo\\countryphonecode.xml";
    private static final String STRING_ISO = "[stringISO]";

    @Given("el usuario proporciona el codigo iso del pais que es {string}")
    public void elUsuarioProporcionaElCodigoIsoDelPaisQueEs(String stringISO) {
        setUp();
        bodyRequest = defineBodyRequest(stringISO);
    }

    @When("el usuario ejecuta la accion")
    public void elUsuarioEjecutaLaAccion() {
        actor.attemptsTo(
                DoPost.doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );
    }

    @Then("el usuario obtiene el codigo celular del pais debe ser {int}")
    public void elUsuarioObtieneElCodigoCelularDelPaisDebeSer(Integer int1) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El codigo de celular debe ser: ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString("<m:CountryIntPhoneCodeResult>"+ int1 +"</m:CountryIntPhoneCodeResult>")
                )
        );
    }

    @Given("el usuario escribe {string} como el codigo iso del pais que quiere identificar")
    public void elUsuarioEscribeComoElCodigoIsoDelPaisQueQuiereIdentificar(String stringISO) {
        setUp();
        bodyRequest = defineBodyRequest(stringISO);
    }
    @When("el usuario ejecuta la accion de identificar el indicativo del pais")
    public void elUsuarioEjecutaLaAccionDeIdentificarElIndicativoDelPais() {
            actor.attemptsTo(
                    DoPost.doPost().
                            usingThe(RESOURCE).
                            with(headers()).
                            and(bodyRequest)
            );
    }
    @Then("el usuario deberá obtener como respuesta {string}")
    public void elUsuarioDeberáObtenerComoRespuesta(String string) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El codigo de celular debe ser: ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString("<m:CountryIntPhoneCodeResult>Country not found in the database</m:CountryIntPhoneCodeResult>")
                )
        );
    }
    private String defineBodyRequest(String stringISO){
        return readFile(ADD_XML)
                .replace(STRING_ISO, stringISO);
    }
}
