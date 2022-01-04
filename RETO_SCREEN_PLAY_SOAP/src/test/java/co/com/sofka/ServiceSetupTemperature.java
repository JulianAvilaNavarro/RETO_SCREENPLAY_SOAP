package co.com.sofka;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.PropertyConfigurator;
import java.util.HashMap;

public class ServiceSetupTemperature {

    protected static final String URL_BASE = "https://www.w3schools.com";
    protected static final String RESOURCE = "/xml/tempconvert.asmx";
    protected final Actor actor = new Actor("Julian");


    protected void setup(){
        PropertyConfigurator.configure("C:\\Users\\Julian\\Desktop\\RETO_SCREEN_PLAY_SOAP\\src\\test\\resources\\log4j\\log4j.properties");
        actorCan();
    }

    private void actorCan(){
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected HashMap<String, Object> headers(){
        HashMap<String, Object> headersCollection = new HashMap<>();
        headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        headersCollection.put("SOAPAction", "https://www.w3schools.com/xml/CelsiusToFahrenheit");
        return headersCollection;
    }

}
