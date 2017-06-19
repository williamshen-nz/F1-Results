package Demo;

import Formula1.Model.Race;
import Formula1.Model.Season;
import Helpers.JSON;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class F1Controller {

    @RequestMapping(value = "f1/{year}", method = RequestMethod.GET)
    public Object getSeason(@PathVariable(value = "year") String year) {
        return Application.getSeason(year);
    }

    @RequestMapping(value = "f1/{year}/{round}", method = RequestMethod.GET)
    public Object getRace(@PathVariable(value = "year") String year,
                          @PathVariable(value = "round") String round,
                          @RequestParam(value = "sessions", required = false) String sessions) {
        // Try to get the corresponding race
        Object resultObj = Application.getSeason(year);
        if (resultObj instanceof CustomErrorMessage)
            return resultObj;

        // Get the race by the path variable
        if (NumberUtils.isNumber(round))
            resultObj = ((Season) resultObj).getRace(NumberUtils.createInteger(round));    // guaranteed
        else
            resultObj = ((Season) resultObj).getRace(round);   // unreliable

        // Make a copy of the race and filter by session if necessary
        Race race = JSON.parse(resultObj.toString(), Race.class);
        boolean fp1 = false;
        boolean fp2 = false;
        boolean fp3 = false;
        boolean qualifying = false;
        boolean raceSession = false;

        // By default, just get the race session
        if (sessions == null) {
            raceSession = true;
        } else {
            // Check which parameters have been specified
            sessions = sessions.replaceAll("\\s", "");  // remove whitespace from query parameter
            String[] sessionOptions = sessions.split(",");
            for (String session : sessionOptions) {
                session = session.toLowerCase();
                switch (session) {
                    case "all":
                        fp1 = true;
                        fp2 = true;
                        fp3 = true;
                        qualifying = true;
                        raceSession = true;
                        break;
                    case "fp1":
                        fp1 = true;
                        break;
                    case "fp2":
                        fp2 = true;
                        break;
                    case "fp3":
                        fp3 = true;
                        break;
                    case "qualifying":
                        qualifying = true;
                        break;
                    case "race":
                        raceSession = true;
                        break;
                }
            }
        }

        // Apply filters
        if (!fp1) race.getSessions().setPractice1(null);
        if (!fp2) race.getSessions().setPractice2(null);
        if (!fp3) race.getSessions().setPractice3(null);
        if (!qualifying) race.getSessions().setQualifying(null);
        if (!raceSession) race.getSessions().setRace(null);
        return race;
    }
}
