package co.com.sofka.stepdefinitions.services.soap;
import co.com.sofka.models.TemperatureModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import co.com.sofka.ServiceSetupTemperature;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.service.soap.PatchTemperature.CONVERTIR;
import static co.com.sofka.utils.service.soap.ResponseTemperature.CONVERSION_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static co.com.sofka.questions.ReturnSoapServicesResponse.returnSoapServicesResponse;

public class TemperatureStepDefinition extends ServiceSetupTemperature {
    private static final Logger LOGGER = Logger.getLogger(TemperatureStepDefinition.class);
    private TemperatureModel temperatureModel;
    //scenario 1

    @Given("que el usuario del conversor ha definido la temperatura {int} grados celsius")
    public void queElUsuarioDelConversorHaDefinidoLaTemperaturaGradosCelsius(Integer temp) {
        try {
            LOGGER.info("Se inicializa las configuraciones");
            super.setup();
            temperatureModel = new TemperatureModel();
            temperatureModel.setTemp(temp);
        }catch (Exception e){
            LOGGER.info("Error en la configuración general");
        }
    }

    @When("el usuario del conversor ejecuta el calculo")
    public void elUsuarioDelConversorEjecutaElCalculo() {
        try{
        actor.attemptsTo(
                doPost()
                        .withTheResource(RESOURCE)
                        .andTheHeaders(super.headers())
                        .andTheBodyRequest(bodyRequest())
        );
            LOGGER.info("Se carga el numero a convertir");
        }catch (Exception e){
            LOGGER.info("Fallo en la carga del numero");
        }

    }
    @Then("el usuario deberia obtener el resultado {int}")
    public void elUsuarioDeberiaObtenerElResultado(Integer outcome) {
        try{
        temperatureModel.setOutcome(outcome);
        actor.should(
                seeThatResponse("El código de rspuesta HTTP debe ser: ",
                        response -> response.statusCode(HttpStatus.SC_OK)),
                seeThat("El resultado de la conversion debe ser: ",
                        returnSoapServicesResponse(),
                        containsString(bodyResponse()))
        );
        LOGGER.info("Se comparan las respuestas");
        } catch (Exception e){
            LOGGER.info("Hubo fallos en el dato de salida");}


    }
    //scenario 2

    @Given("que el usuario de la conversor ha definido la temperatura incorrecta {string} grados celcius")
    public void queElUsuarioDeLaConversorHaDefinidoLaTemperaturaIncorrectaGradosCelcius(String error) {
        try{
            LOGGER.info("Se inicializa las configuraciones");
        super.setup();
        temperatureModel=new TemperatureModel();
        temperatureModel.setError(error);}
        catch (Exception e){LOGGER.info("Error en la configuración general");}
    }

    @When("el usuario del conversor corre el calculo")
    public void elUsuarioDelConversorCorreElCalculo() {
        try{
            LOGGER.info("Se carga el numero a convertir");
        actor.attemptsTo(
                doPost()
                        .withTheResource(RESOURCE)
                        .andTheHeaders(super.headers())
                        .andTheBodyRequest(bodyRequestError())
        );}catch (Exception e){ LOGGER.info("Fallo en la carga del numero");}

    }
    @Then("el usuario deberia obtener el resultado error {string}")
    public void elUsuarioDeberiaObtenerElResultadoError(String Incorrect) {
        try{
        temperatureModel.setIncorrectoutcome(Incorrect);
        actor.should(
                seeThatResponse("El código de rspuesta HTTP debe ser: ",
                        response -> response.statusCode(HttpStatus.SC_OK)),
                seeThat("El resultado de la conversion debe ser: ",
                        returnSoapServicesResponse(),
                        containsString(bodyResponseError()))
        );}catch (Exception e){  LOGGER.info("Hubo fallos en el dato de salida");}

    }

    private TemperatureModel temperatureModel(){
        return temperatureModel;
    }

    private String bodyRequest(){
        return String.format(readFile(CONVERTIR.getValue()), temperatureModel().getTemp());
    }

    private String bodyResponse(){
        return String.format(CONVERSION_RESPONSE.getValue(), temperatureModel().getOutcome());
    }


    private String bodyRequestError(){
        return String.format(readFile(CONVERTIR.getValue()), temperatureModel().getError());
    }

    private String bodyResponseError(){
        return String.format(CONVERSION_RESPONSE.getValue(), temperatureModel().getIncorrectoutcome());
    }


}
