package nimpash.cinema.objects.TypeSerializers;

import com.google.gson.*;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;

public class LocalDateTimeSerializer implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime>
{
    private static final DateTimeFormatter DATE_FORMAT = ISODateTimeFormat.dateTimeNoMillis();

    @Override
    public LocalDateTime deserialize(final JsonElement je, final Type type,
                                     final JsonDeserializationContext jdc) throws JsonParseException
    {
        final String dateAsString = je.getAsString() + "Z";

        if (dateAsString.length() == 0)
            return null;
        else
            return DATE_FORMAT.parseLocalDateTime(dateAsString);
    }

    @Override
    public JsonElement serialize(final LocalDateTime src, final Type typeOfSrc,
                                 final JsonSerializationContext context)
    {
        String retVal;

        if (src == null)
            retVal = "";
        else
            retVal = DATE_FORMAT.print(src);

        return new JsonPrimitive(retVal);
    }
}