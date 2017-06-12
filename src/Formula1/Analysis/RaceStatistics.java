package Formula1.Analysis;

import Formula1.Model.Race;
import Formula1.Model.Result;

public class RaceStatistics {
    public static Result getPolePosition(Race race) throws ResultNotFoundException {
        Result pole = SessionStatistics.getResult(race.getSessions().getQualifying(), 1);
        if (pole == null)
            throw new ResultNotFoundException("Couldn't find the pole position in the qualifying session!");
        else
            return pole;
    }

    public static Result getWinner(Race race) throws ResultNotFoundException {
        Result winner = SessionStatistics.getResult(race.getSessions().getRace(), 1);
        if (winner == null)
            throw new ResultNotFoundException("Couldn't find winner of the race!");
        else
            return winner;
    }

    public static Result[] getPodium(Race race) throws ResultNotFoundException {
        Result[] podium = SessionStatistics.getPodium(race.getSessions().getRace());
        if (podium == null)
            throw new ResultNotFoundException("Couldn't find the podium winners for the race!");
        else
            return podium;
    }
}
