package Demo;

import Formula1.Model.Driver;
import Formula1.Model.Drivers;
import Formula1.Model.Session;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadSession {
    public enum SessionType {
        Practice, Qualifying, Race;
    }

    public static Session load(Drivers drivers, SessionType type, String location) throws Exception {
        Session session = new Session();
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            if (str == null) {
                System.out.print("(none)... ");
                return session;
            }
            String[] curr = str.split(",");
            Driver match = drivers.getDriver(Integer.parseInt(curr[1]));  // get driver based on their racing number
            switch (type) {
                case Practice:
                    session.addResult(match, Integer.parseInt(curr[0]), Integer.parseInt(curr[5]), curr[3], curr[4]);
                    break;
                case Qualifying:
                    session.addResult(match, Integer.parseInt(curr[0]), Integer.parseInt(curr[6]), curr[3], curr[4], curr[5]);
                    break;
                case Race:
                    session.addResult(match, (curr[0].equals("NC") ? -1 : Integer.parseInt(curr[0])), Integer.parseInt(curr[3]),
                            curr[4], Integer.parseInt(curr[5]));
                    break;
            }
        } while ((str = in.readLine()) != null);
        in.close();
        System.out.print("... ");
        return session;
    }
}
