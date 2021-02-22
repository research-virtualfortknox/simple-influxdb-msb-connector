package de.fhg.ipa.null70.simple_influxdb_msb_connector.model;

/**
 * @author Arthur Grigorjan (Fraunhofer IPA)
 */
public class Field {

    private String key = "";
    private String value = "";

    public Field() {
    }

    public Field(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
