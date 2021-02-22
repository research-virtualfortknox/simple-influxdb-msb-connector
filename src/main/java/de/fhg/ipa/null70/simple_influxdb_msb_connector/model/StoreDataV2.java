package de.fhg.ipa.null70.simple_influxdb_msb_connector.model;

import java.util.Date;

/**
 * @author Arthur Grigorjan (Fraunhofer IPA)
 */
public class StoreDataV2 {

    private String database = "";
    private String measurement = "";
    private Date timestamp= new Date(0);
    private String[] fieldKeys = {};
    private String[] fieldValues = {};
    private String[] tagKeys = {};
    private String[] tagValues = {};
    private Boolean sendConfirmation = true;

    public StoreDataV2() {
    }

    public StoreDataV2(String database, String measurement, Date timestamp, String[] fieldKeys, String[] fieldValues, String[] tagKeys, String[] tagValues, Boolean sendConfirmation) {
        this.database = database;
        this.measurement = measurement;
        this.timestamp = timestamp;
        this.fieldKeys = fieldKeys;
        this.fieldValues = fieldValues;
        this.tagKeys = tagKeys;
        this.tagValues = tagValues;
        this.sendConfirmation = sendConfirmation;
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

    public String[] getFieldKeys() {
        return fieldKeys;
    }

    public void setFieldKeys(String[] fieldKeys) {
        this.fieldKeys = fieldKeys;
    }

    public String[] getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(String[] fieldValues) {
        this.fieldValues = fieldValues;
    }

    public String[] getTagKeys() {
        return tagKeys;
    }

    public void setTagKeys(String[] tagKeys) {
        this.tagKeys = tagKeys;
    }

    public String[] getTagValues() {
        return tagValues;
    }

    public void setTagValues(String[] tagValues) {
        this.tagValues = tagValues;
    }

    public Boolean getSendConfirmation() {
        return sendConfirmation;
    }

    public void setSendConfirmation(Boolean sendConfirmation) {
        this.sendConfirmation = sendConfirmation;
    }
}
