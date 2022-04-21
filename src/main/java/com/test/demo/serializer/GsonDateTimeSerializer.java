package com.test.demo.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Slf4j
public class GsonDateTimeSerializer implements JsonSerializer<LocalDateTime>{

	@Override
	public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
		try {
			return new JsonPrimitive(src.toString());
		}catch (Exception e) {
			log.error("Cannot convert date to string : {}",e.getMessage());
		}
		return null;
	}

}
