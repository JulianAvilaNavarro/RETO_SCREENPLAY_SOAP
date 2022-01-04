package co.com.sofka.models;

public class TemperatureModel {

    private int temp;
    private String error;
    private int outcome;
    private String incorrectoutcome;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public String getIncorrectoutcome() {
        return incorrectoutcome;
    }

    public void setIncorrectoutcome(String incorrectoutcome) {
        this.incorrectoutcome = incorrectoutcome;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
