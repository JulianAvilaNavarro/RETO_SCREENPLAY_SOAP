package co.com.sofka.models;

public class LanguajeModel {
    private String code;
    private String outcome;
    private String incorrectOutcome;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getIncorrectOutcome() {
        return incorrectOutcome;
    }

    public void setIncorrectOutcome(String incorrectOutcome) {
        this.incorrectOutcome = incorrectOutcome;
    }
}
