package co.com.sofka.utils.service.soap;


public enum PatchTemperature {
    CONVERTIR(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\services\\soap\\temperature\\temperature.xml");

    private final String value;

    PatchTemperature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
