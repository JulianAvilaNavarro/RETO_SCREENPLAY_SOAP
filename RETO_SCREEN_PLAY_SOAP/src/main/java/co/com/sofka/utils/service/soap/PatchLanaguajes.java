package co.com.sofka.utils.service.soap;


public enum PatchLanaguajes {
    BUSCAR(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\services\\soap\\languaje\\languajes.xml");

    private final String value;

    PatchLanaguajes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
