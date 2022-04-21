package com.test.demo.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class JsonRedisSerializer implements RedisSerializer<Object> {

	private final ObjectMapper objectMapper;

	public JsonRedisSerializer() {
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		try {
			return objectMapper.writeValueAsBytes(t);
		} catch (JsonProcessingException e) {
			throw new SerializationException(e.getMessage(), e);
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if(bytes == null){
			return null;
		}
		try {
			return objectMapper.readValue(bytes, Object.class);
		} catch (Exception e) {
			throw new SerializationException(e.getMessage(), e);
		}
	}

}
