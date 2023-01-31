package com.eny.sensordataconsumer.deneme.serdes;

import com.eny.sensordataconsumer.payload.request.SensorDataMessage;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class SensorDataSerde extends Serdes.WrapperSerde<SensorDataMessage> {
    public SensorDataSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(SensorDataMessage.class));
    }

    public static Serde<SensorDataMessage> serdes() {
        JsonSerializer<SensorDataMessage> serializer = new JsonSerializer<>();
        JsonDeserializer<SensorDataMessage> deserializer = new JsonDeserializer<>(SensorDataMessage.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
