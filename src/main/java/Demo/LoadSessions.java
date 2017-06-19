package Demo;

import Formula1.Model.Session;
import Formula1.Model.Sessions;

import static Demo.LoadSession.SessionType.*;

public class LoadSessions {
    public static Sessions load(String location) throws Exception {
        Sessions sessions = new Sessions();
        Session fp1 = LoadSession.load(Practice, location + "practice-1.txt", location);
        Session fp2 = LoadSession.load(Practice, location + "practice-2.txt", location);
        Session fp3 = LoadSession.load(Practice, location + "practice-3.txt", location);
        Session qualifying = LoadSession.load(Qualifying, location + "qualifying.txt", location);
        Session race = LoadSession.load(Race, location + "race-result.txt", location);
        sessions.setPractice1(fp1);
        sessions.setPractice2(fp2);
        sessions.setPractice3(fp3);
        sessions.setQualifying(qualifying);
        sessions.setRace(race);
        return sessions;
    }
}
