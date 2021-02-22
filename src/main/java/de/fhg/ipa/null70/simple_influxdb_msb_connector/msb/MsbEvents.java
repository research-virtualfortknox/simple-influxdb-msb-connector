package de.fhg.ipa.null70.simple_influxdb_msb_connector.msb;

import de.fhg.ipa.null70.simple_influxdb_msb_connector.model.ResponseEvent;
import de.fhg.ipa.vfk.msb.client.annotation.EventDeclaration;
import org.springframework.stereotype.Component;

/**
 * @author Arthur Grigorjan (Fraunhofer IPA)
 */
@Component
@de.fhg.ipa.vfk.msb.client.annotation.Events({
        @EventDeclaration(dataType = ResponseEvent.class, description = "This event will be thrown after the storeData-function was called and carries information about the success or errors of the function",
                name = MsbEvents.RESPONSE_EVENT, eventId = MsbEvents.RESPONSE_EVENT),
})
public class MsbEvents {

    public final static String RESPONSE_EVENT = "RESPONSE_EVENT";

}

