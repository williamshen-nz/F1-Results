package Formula1.Analysis;

import Formula1.Model.Result;
import Formula1.Model.Session;

public class SessionStatistics {
    public static Result getResult(Session session, int position) throws ResultNotFoundException {
        // Get the result with the given position in a session
        for (Result result : session.getResults())
            if (result.getPosition() == position) return result;
        throw new ResultNotFoundException("Result not found for position " + position + "!");
    }

    public static Result[] getPodium(Session session) throws ResultNotFoundException {
        // Get the podium results for a given session
        Result[] podium = new Result[3];
        for (Result result : session.getResults()) {
            if (result.getPosition() == 1) podium[0] = result;
            else if (result.getPosition() == 2) podium[1] = result;
            else if (result.getPosition() == 3) podium[2] = result;
        }
        // Check if we have successfully filled up the podium
        if (podium[0] != null && podium[1] != null && podium[2] != null)
            return podium;
        else
            throw new ResultNotFoundException("A complete podium could not be found!");
    }
}
