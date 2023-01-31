package com.eny.sensordataconsumer.deneme.serdes;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.eny.sensordataconsumer.deneme.mapper.JsonMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class JsonDeserializer<T> implements Deserializer<T> {

    private Class<T> destinationClass;

    public JsonDeserializer(Class<T> destinationClass) {
        this.destinationClass = destinationClass;
    }

    @Override
    public void configure(Map<String, ?> props, boolean isKey) {
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (bytes == null) return null;

        try {
            return JsonMapper.readFromJson(new String(bytes, StandardCharsets.UTF_8), destinationClass);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing message", e);
        }
    }
}
