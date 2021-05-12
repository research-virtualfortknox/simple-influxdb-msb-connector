package de.fhg.ipa.null70.simple_influxdb_msb_connector.model;

import java.util.Date;

/**
 * @author Arthur Grigorjan (Fraunhofer IPA)
 */
public class StoreDataSimple {

    private String database = "";
    private String measurement = "";
    private Date timestamp= new Date(0);
    private String fieldName;
    private String fieldValue;
    private Boolean sendConfirmation = true;
    private Boolean parseValuesToFloat = true;

    public StoreDataSimple() {
    }

    public StoreDataSimple(String database, String measurement, Date timestamp, String fieldName, String fieldValue, Boolean sendConfirmation, Boolean parseValuesToFloat) {
        this.database = database;
        this.measurement = measurement;
        this.timestamp = timestamp;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.sendConfirmation = sendConfirmation;
        this.parseValuesToFloat = parseValuesToFloat;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Boolean getSendConfirmation() {
        return sendConfirmation;
    }

    public void setSendConfirmation(Boolean sendConfirmation) {
        this.sendConfirmation = sendConfirmation;
    }

    public Boolean getParseValuesToFloat() {
        return parseValuesToFloat;
    }

    public void setParseValuesToFloat(Boolean parseValuesToFloat) {
        this.parseValuesToFloat = parseValuesToFloat;
    }
}
