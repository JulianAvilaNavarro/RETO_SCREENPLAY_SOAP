package co.com.sofka.utils.service.soap;

public enum ResponseTemperature {
    CONVERSION_RESPONSE("<CelsiusToFahrenheitResult>%s</CelsiusToFahrenheitResult>");

    private final String value;

    ResponseTemperature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
