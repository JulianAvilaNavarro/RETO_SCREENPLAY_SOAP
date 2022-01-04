package co.com.sofka.utils.service.soap;

public enum ResponseLanguaje {
    SEARCH_RESPONSE("<m:LanguageNameResult>%s</m:LanguageNameResult>");

    private final String value;

    ResponseLanguaje(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
