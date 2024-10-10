package in.co.ad.springboot.ai.sample;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

import static in.co.ad.springboot.ai.sample.ClaudeRecords.*;

public class StringContentSerializer extends StdSerializer<StringContent> {

    public StringContentSerializer() {
        super(StringContent.class);
    }

    @Override
    public void serialize(StringContent value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.text());
    }
}