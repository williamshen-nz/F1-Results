package Helpers;

import Formula1.Model.PracticeResult;
import Formula1.Model.QualifyingResult;
import Formula1.Model.RaceResult;
import Formula1.Model.Result;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class ResultDeserializer extends JsonDeserializer<Result> {
    // Inspiration from https://stackoverflow.com/a/32777371
    @Override
    public Result deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        Class<? extends Result> resultClass;
        // This is not very sound or complete, relies on fields defined within each type of Result
        if (root.toString().contains("gap"))
            resultClass = PracticeResult.class;
        else if (root.toString().contains("q1"))
            resultClass = QualifyingResult.class;
        else
            resultClass = RaceResult.class;
        return mapper.convertValue(root, resultClass);
    }
}
