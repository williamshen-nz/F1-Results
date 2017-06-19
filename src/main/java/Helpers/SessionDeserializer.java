package Helpers;

import Formula1.Model.PracticeSession;
import Formula1.Model.QualifyingSession;
import Formula1.Model.RaceSession;
import Formula1.Model.Session;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class SessionDeserializer extends JsonDeserializer<Session> {
    // Inspiration from https://stackoverflow.com/a/32777371
    @Override
    public Session deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        Class<? extends Session> sessionClass;
        // This is not very sound or complete, relies on fields defined within the Results not the Session!
        if (root.toString().contains("gap"))
            sessionClass = PracticeSession.class;
        else if (root.toString().contains("q1"))
            sessionClass = QualifyingSession.class;
        else
            sessionClass = RaceSession.class;
        return mapper.convertValue(root, sessionClass);
    }
}
