package Formula1.Analysis;

import Formula1.Model.GridPosition;
import Formula1.Model.Race;
import Formula1.Model.Result;

public class RaceStatistics {
    public static GridPosition getPolePosition(Race race) throws ResultNotFoundException {
        for (GridPosition gp : race.getSessions().getRace().getStartingGrid().getGridPositions()) {
            if (gp.getPosition() == 1)
                return gp;
        }
        throw new ResultNotFoundException("Pole position could not be found for " + race.getName() + "!");
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
