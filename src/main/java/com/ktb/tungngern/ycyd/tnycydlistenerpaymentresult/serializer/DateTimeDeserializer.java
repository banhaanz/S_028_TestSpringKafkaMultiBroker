package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateTimeDeserializer extends StdDeserializer<LocalDateTime>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8787461034023217923L;

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	public DateTimeDeserializer() {
		this(null);
	}
	
	protected DateTimeDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String date = null;
		LocalDateTime localDateTime =null;
		try {
			date = p.getText();
			if(date != null && !"".equalsIgnoreCase(date.trim())) {
				localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
			}
		}catch (DateTimeParseException e) {
			log.error("Cannot convert string to date : {}",e.getMessage());
		}
		return localDateTime;
	}

}
