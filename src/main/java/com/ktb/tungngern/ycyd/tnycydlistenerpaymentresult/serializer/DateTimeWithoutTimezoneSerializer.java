package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeWithoutTimezoneSerializer extends StdSerializer<LocalDateTime>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 990020870718689618L;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	
	public DateTimeWithoutTimezoneSerializer() {
		this(null);
	}

	protected DateTimeWithoutTimezoneSerializer(Class<LocalDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if(value != null) {
			gen.writeString(value.format(formatter));
		}else {
			gen.writeNull();
		}
	}

}
