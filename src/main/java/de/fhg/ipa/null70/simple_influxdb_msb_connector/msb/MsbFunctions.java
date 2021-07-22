package de.fhg.ipa.null70.simple_influxdb_msb_connector.msb;

import de.fhg.ipa.null70.simple_influxdb_msb_connector.SimpleInfluxDbMsbConnector;
import de.fhg.ipa.null70.simple_influxdb_msb_connector.model.*;
import de.fhg.ipa.vfk.msb.client.annotation.FunctionCall;
import de.fhg.ipa.vfk.msb.client.annotation.FunctionHandler;
import de.fhg.ipa.vfk.msb.client.annotation.FunctionParam;
import de.fhg.ipa.vfk.msb.client.api.MultipleResponseEvent;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Arthur Grigorjan (Fraunhofer IPA)
 */

@Component
@FunctionHandler(path = "/functionHandler")
public class MsbFunctions {

    @Autowired
    SimpleInfluxDbMsbConnector simpleInfluxDbMsbConnector;

    @Autowired
    MsbConnection msbConnection;

    @org.springframework.beans.factory.annotation.Value("${influxdb.useSourceTimestamp}")
    public Boolean influxdbUseSourceTimestamp;

    private final Logger LOG = LoggerFactory.getLogger(MsbFunctions.class);

    @FunctionCall(path = "/storeDataV1", name = "Store Data V1", description = "Inserts data into InfluxDb. Fields are objects with key and value.", responseEvents = {MsbEvents.RESPONSE_EVENT})
    public MultipleResponseEvent storeDataV1(@FunctionParam(name = "StoreDataV1") StoreDataV1 storeDataV1) {
        LOG.info("MSB called /storeDataV1 : " + storeDataV1);

        MultipleResponseEvent multipleResponseEvent = new MultipleResponseEvent();
        ResponseEvent responseEvent = new ResponseEvent();
        responseEvent.setSuccess(true); // will be set to false when an exception is catched..
        try{
            // Write points to InfluxDB.
            simpleInfluxDbMsbConnector.influxDBClient.setDatabase(storeDataV1.getDatabase());
            Point.Builder pb = Point.measurement(storeDataV1.getMeasurement());

            // Set the timestamp of this data point if 'influxdbUseSourceTimestamp' was set to false..
            if(influxdbUseSourceTimestamp){
                if(storeDataV1.getTimestamp() != null){
                    // Take timestamp from source
                    pb.time(storeDataV1.getTimestamp().getTime()
                        , TimeUnit.MILLISECONDS);
                }else{
                    LOG.warn("storeData.timestamp was null and therefore was not considered...");
                }
            }else{
                // Take current timestamp
                pb.time(
                        System.currentTimeMillis(),
                        TimeUnit.MILLISECONDS);
            }

            // Add all tags..
            for(int i = 0; i < storeDataV1.getTags().length; i++){
                pb.tag(storeDataV1.getTags()[i].getKey(), storeDataV1.getTags()[i].getValue());
            }

            // Add all fields..
            for(int i = 0; i < storeDataV1.getFields().length; i++){
                String key = storeDataV1.getFields()[i].getKey();
                String val = storeDataV1.getFields()[i].getValue();
                if(storeDataV1.getParseValuesToFloat()){
                    pb.addField(key, Float.parseFloat(val));
                }else{
                    pb.addField(key, val);
                }
            }

            // Write to InfluxDb..
            simpleInfluxDbMsbConnector.influxDBClient.write(pb.build());

        }catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            LOG.error(exceptionAsString);
            responseEvent.setSuccess(false);
            String[] errors = {exceptionAsString};
            responseEvent.setErrors(errors);
        }


        if(storeDataV1.getSendConfirmation()){
            multipleResponseEvent.addResponseEvent(MsbEvents.RESPONSE_EVENT, responseEvent);
        }

        return multipleResponseEvent;
    }

    @FunctionCall(path = "/storeDataV2", name = "Store Data V2", description = "Inserts data into InfluxDb. Fields are defined by two string[]. fieldKeys and fieldValues..", responseEvents = {MsbEvents.RESPONSE_EVENT})
    public MultipleResponseEvent storeDataV2(@FunctionParam(name = "StoreDataV2") StoreDataV2 storeDataV2) {
        StoreDataV1 storeDataV1 = new StoreDataV1();
        storeDataV1.setDatabase(storeDataV2.getDatabase());
        storeDataV1.setMeasurement(storeDataV2.getMeasurement());
        storeDataV1.setTimestamp(storeDataV2.getTimestamp());
        storeDataV1.setSendConfirmation(storeDataV2.getSendConfirmation());
        storeDataV1.setParseValuesToFloat(storeDataV2.getParseValuesToFloat());

        try{
            ArrayList<Field> fields = new ArrayList<>();
            for(int i = 0; i < storeDataV2.getFieldKeys().length; i++){
                fields.add(new Field(storeDataV2.getFieldKeys()[i], storeDataV2.getFieldValues()[i]));
            }
            storeDataV1.setFields((Field[]) fields.toArray());
        }catch(ArrayIndexOutOfBoundsException e){
            LOG.warn("Seems like storeDataV2.getFieldKeys and storeDataV2.getFieldValues have a different length.");
        }

        try{
            ArrayList<Tag> tags = new ArrayList<>();
            for(int i = 0; i < storeDataV2.getFieldKeys().length; i++){
                tags.add(new Tag(storeDataV2.getTagKeys()[i], storeDataV2.getTagValues()[i]));
            }
            storeDataV1.setFields((Field[]) tags.toArray());
        }catch(ArrayIndexOutOfBoundsException e){
            LOG.warn("Seems like storeDataV2.getTagKeys and storeDataV2.getTagValues have a different length.");
        }

        MultipleResponseEvent mre = storeDataV1(storeDataV1);
        return mre;
    }

    @FunctionCall(path = "/storeDataSimple", name = "Store Data Simple", description = "Inserts data into InfluxDb", responseEvents = {MsbEvents.RESPONSE_EVENT})
    public MultipleResponseEvent storeDataSimple(@FunctionParam(name = "StoreDataSimple") StoreDataSimple storeDataSimple) {
        StoreDataV1 storeDataV1 = new StoreDataV1();
        storeDataV1.setDatabase(storeDataSimple.getDatabase());
        storeDataV1.setMeasurement(storeDataSimple.getMeasurement());
        storeDataV1.setTimestamp(storeDataSimple.getTimestamp());
        storeDataV1.setSendConfirmation(storeDataSimple.getSendConfirmation());
        storeDataV1.setFields(new Field[]{new Field(storeDataSimple.getFieldName(), storeDataSimple.getFieldValue())});
        storeDataV1.setParseValuesToFloat(storeDataSimple.getParseValuesToFloat());

        MultipleResponseEvent mre = storeDataV1(storeDataV1);
        return mre;
    }


}
