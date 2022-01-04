package co.com.sofka.stepdefinitions.services.soap;

import co.com.sofka.ServiceSetupLanguaje;
import co.com.sofka.models.LanguajeModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static co.com.sofka.questions.ReturnSoapServicesResponse.returnSoapServicesResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.PatchLanaguajes.BUSCAR;
import static co.com.sofka.utils.service.soap.ResponseLanguaje.SEARCH_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class LanguajesStepDefinition extends ServiceSetupLanguaje {
    private static final Logger LOGGER = Logger.getLogger(LanguajesStepDefinition.class);
    private LanguajeModel languajeModel;
    //scenario 1
    @Given("que el usuario del buscador ha definido el codigo ISO {string}")
    public void queElUsuarioDelBuscadorHaDefinidoElCodigoISO(String code) {
        try {
            LOGGER.info("Se inicializa las configuraciones");
            super.setup();
            languajeModel = new LanguajeModel();
            languajeModel().setCode(code);
        }catch (Exception e){
            LOGGER.info("Error en la configuración general");
        }

    }

    @When("el usuario del buscador realiza la busqueda")
    public void elUsuarioDelBuscadorRealizaLaBusqueda() {
        try{
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequest())
            );
            LOGGER.info("Se carga el codigo del idioma");
        }catch (Exception e){
            LOGGER.info("Fallo en la carga del codigo");
        }

    }
    @Then("el usuario deberia obtener el resultado {string}")
    public void elUsuarioDeberiaObtenerElResultado(String outcome) {

        try{
            languajeModel.setOutcome(outcome);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda debe ser: ",
                            returnSoapServicesResponse(),
                            containsString(bodyResponse()))
            );
            LOGGER.info("Se comparan las respuestas");
        } catch (Exception e){
            LOGGER.info("Hubo fallos en el dato de salida");}

    }
    //scenario 2

    @Given("que el usuario del buscador ha definido el codigo ISO incorrecto {string}")
    public void queElUsuarioDelBuscadorHaDefinidoElCodigoISOIncorrecto(String code) {
        try {
            LOGGER.info("Se inicializa las configuraciones");
            super.setup();
            languajeModel = new LanguajeModel();
            languajeModel().setCode(code);
        }catch (Exception e){
            LOGGER.info("Error en la configuración general");
        }

    }


    @When("el usuario del buscador corre la busqueda")
    public void elUsuarioDelBuscadorCorreLaBusqueda() {
        try{
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(super.headers())
                            .andTheBodyRequest(bodyRequestError())
            );
            LOGGER.info("Se carga el codigo del idioma");
        }catch (Exception e){
            LOGGER.info("Fallo en la carga del codigo");
        }

    }
    @Then("el usuario deberia obtener el resultado incorrecto {string}")
    public void elUsuarioDeberiaObtenerElResultadoIncorrecto(String IncorrectOut) {
        try{
            languajeModel.setIncorrectOutcome(IncorrectOut);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la busqueda debe ser: ",
                            returnSoapServicesResponse(),
                            containsString(bodyResponseError()))
            );
            LOGGER.info("Se comparan las respuestas");
        } catch (Exception e){
            LOGGER.info("Hubo fallos en el dato de salida");}


    }
    private LanguajeModel languajeModel(){
        return languajeModel;
    }

    private String bodyRequest(){
        return String.format(readFile(BUSCAR.getValue()), languajeModel().getCode());
    }

    private String bodyResponse(){
        return String.format(SEARCH_RESPONSE.getValue(), languajeModel().getOutcome());
    }


    private String bodyRequestError(){
        return String.format(readFile(BUSCAR.getValue()), languajeModel().getCode());
    }

    private String bodyResponseError(){
        return String.format(SEARCH_RESPONSE.getValue(), languajeModel().getIncorrectOutcome());
    }


}
