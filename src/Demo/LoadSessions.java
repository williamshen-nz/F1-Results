package Demo;

import Formula1.Model.Drivers;
import Formula1.Model.Session;
import Formula1.Model.Sessions;

import static Demo.LoadSession.SessionType.*;

public class LoadSessions {
    public static Sessions load(Drivers drivers, String location) throws Exception {
        Sessions sessions = new Sessions();
        System.out.print("Loading fp1 ");
        Session fp1 = LoadSession.load(drivers, Practice, location + "fp1.txt", location);
        System.out.print("fp2 ");
        Session fp2 = LoadSession.load(drivers, Practice, location + "fp2.txt", location);
        System.out.print("fp3 ");
        Session fp3 = LoadSession.load(drivers, Practice, location + "fp3.txt", location);
        System.out.print("qualifying ");
        Session qualifying = LoadSession.load(drivers, Qualifying, location + "qualifying.txt", location);
        System.out.print("race ");
        Session race = LoadSession.load(drivers, Race, location + "race.txt", location);
        sessions.setPractice1(fp1);
        sessions.setPractice2(fp2);
        sessions.setPractice3(fp3);
        sessions.setQualifying(qualifying);
        sessions.setRace(race);
        System.out.println("DONE");
        return sessions;
    }
}
