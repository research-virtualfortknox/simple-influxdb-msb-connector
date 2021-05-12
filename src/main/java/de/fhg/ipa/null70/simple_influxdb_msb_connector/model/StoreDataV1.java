package de.fhg.ipa.null70.simple_influxdb_msb_connector.model;

import java.util.Date;

/**
 * @author Arthur Grigorjan (Fraunhofer IPA)
 */
public class StoreDataV1 {

    private String database = "";
    private String measurement = "";
    private Date timestamp= new Date(0);
    private Field[] fields = {};
    private Tag[] tags = {};
    private Boolean sendConfirmation = true;
    private Boolean parseValuesToFloat = true;

    public StoreDataV1() {
    }

    public StoreDataV1(String database, String measurement, Date timestamp, Field[] fields, Tag[] tags, Boolean sendConfirmation, Boolean parseValuesToFloat) {
        this.database = database;
        this.measurement = measurement;
        this.timestamp = timestamp;
        this.fields = fields;
        this.tags = tags;
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

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
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
