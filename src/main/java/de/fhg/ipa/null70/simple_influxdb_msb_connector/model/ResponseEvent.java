package de.fhg.ipa.null70.simple_influxdb_msb_connector.model;

public class ResponseEvent {

    private Boolean success;

    private String[] errors = {};

    public ResponseEvent() {
    }

    public ResponseEvent(Boolean success, String[] errors) {
        this.success = success;
        this.errors = errors;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }
}
