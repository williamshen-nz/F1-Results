package Formula1.Analysis;

import Formula1.Model.Driver;
import Formula1.Model.Race;
import Formula1.Model.RaceResult;
import Formula1.Model.Result;

public class RaceStatistics {
    public static Driver getPolePosition(Race race) throws ResultNotFoundException {
        // Go through each race result and check grid position
        for (Result result : race.getSessions().getRace().getResults()) {
            if (((RaceResult) result).getGridPosition() == 1) return result.getDriver();
        }
        throw new ResultNotFoundException("Pole position could not be found for " + race.getName() + "!");
    }

    public static Result getWinner(Race race) throws ResultNotFoundException {
        // Get the result whose position is 1st
        Result winner = SessionStatistics.getResult(race.getSessions().getRace(), 1);
        if (winner == null)
            throw new ResultNotFoundException("Couldn't find winner of the race!");
        else
            return winner;
    }

    public static Result[] getPodium(Race race) throws ResultNotFoundException {
        // Get the results whose positions are 1st, 2nd and 3rd
        Result[] podium = SessionStatistics.getPodium(race.getSessions().getRace());
        if (podium == null)
            throw new ResultNotFoundException("Couldn't find the podium winners for the race!");
        else
            return podium;
    }
}
