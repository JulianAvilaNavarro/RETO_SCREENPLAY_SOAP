package co.com.sofka.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.serenitybdd.screenplay.Question;
import java.nio.charset.StandardCharsets;

public class ReturnSoapServicesResponse implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        return new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);
    }

    public static ReturnSoapServicesResponse returnSoapServicesResponse(){
        return new ReturnSoapServicesResponse();
    }
}