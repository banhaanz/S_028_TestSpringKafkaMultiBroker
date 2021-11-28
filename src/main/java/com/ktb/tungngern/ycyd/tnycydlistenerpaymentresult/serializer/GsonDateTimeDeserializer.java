package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class GsonDateTimeDeserializer implements JsonDeserializer<LocalDateTime>{

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	@Override
	public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		String value = null;
		LocalDateTime localDateTime = null;
		try {
			value = json != null ?json.getAsString():null;
	        if(value != null && !"".equalsIgnoreCase(value.trim())) {
	        	value = json.getAsString();
	        	localDateTime = LocalDateTime.parse(value, dateTimeFormatter);
	        }
		}catch(DateTimeParseException e) {
			log.error("Cannot convert string to date : {}",e.getMessage());
		}
		return localDateTime;
	}

}
