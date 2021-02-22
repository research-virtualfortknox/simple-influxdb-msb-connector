package de.fhg.ipa.null70.simple_influxdb_msb_connector;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Arthur Grigorjan (Fraunhofer IPA)
 */
@Component
public class SimpleInfluxDbMsbConnector {

    private final static Logger LOG = LoggerFactory.getLogger(SimpleInfluxDbMsbConnector.class);

    @Value("${influxdb.server_url}")
    public String influxdbServer_url;

    @Value("${influxdb.user}")
    public String influxdbUser;

    @Value("${influxdb.password}")
    public String influxdbPassword;

    @Value("${influxdb.batchmode}")
    public Boolean influxdbBatchmode;

    @Value("${influxdb.useSourceTimestamp}")
    public Boolean influxdbUseSourceTimestamp;

    @Value("${msb.truststore}")
    public String msbTruststore;

    @Value("${msb.url}")
    public String msbUrl;

    @Value("${app.uuid}")
    public String appUuid;

    @Value("${app.name}")
    public String appName;

    @Value("${app.token}")
    public String appToken;

    @Value("${app.description}")
    public String appDescription;

    public InfluxDB influxDBClient = null;

    @PostConstruct
    public void init() {
        LOG.info("------------- CONFIG -------------------");
        LOG.info("influxdbServer_url = " + influxdbServer_url);
        LOG.info("influxdbUser = " + influxdbUser);
        LOG.info("influxdbPassword = " + influxdbPassword);
        LOG.info("influxdbBatchmode = " + influxdbBatchmode);
        LOG.info("influxdbUseSourceTimestamp = " + influxdbUseSourceTimestamp);
        LOG.info("******");
        LOG.info("msbTruststore = " + msbTruststore);
        LOG.info("msbUrl = " + msbUrl);
        LOG.info("appUuid = " + appUuid);
        LOG.info("appName = " + appName);
        LOG.info("appToken = " + appToken);
        LOG.info("appDescription = " + appDescription);
        LOG.info("----------------------------------------");


        // Init and start kafka producer
        influxDBClient = initInfluxDbClient(influxdbServer_url, influxdbUser, influxdbPassword);
    }

    private InfluxDB initInfluxDbClient(String serverURL, String username, String password) {
        LOG.trace("Creating InfluxDB-Client...");
        InfluxDB influxDBClient = InfluxDBFactory.connect(serverURL, username, password);
        if(influxdbBatchmode){
            influxDBClient.enableBatch(BatchOptions.DEFAULTS);
        }
        return influxDBClient;
    }

}
