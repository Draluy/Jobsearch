package fr.raluy.jobsearchback.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

public class DateDeserializer extends StdDeserializer<LocalDate> {

    protected DateDeserializer() {
        super((Class)null);
    }

    protected DateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ArrayNode node = jsonParser.getCodec().readTree(jsonParser);
        Iterator<JsonNode> elements = node.elements();
        if (elements.hasNext()) {
            JsonNode year = elements.next();
            JsonNode month = elements.next();
            JsonNode day = elements.next();

            return LocalDate.of(year.asInt(), month.asInt(), day.asInt());
        }
        return null;
    }
}
