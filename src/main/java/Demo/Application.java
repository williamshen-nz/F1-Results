package Demo;

import Formula1.Model.Season;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    static Season season2017;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        season2017 = LoadSeason.loadAndGetSeason("2017");
    }

    public static Object getSeason(String year) {
        year = year.trim();
        if (year.equals("2017") || year.equals("current"))
            return season2017;
        else
            return new CustomErrorMessage("No data exists for season: " + year);
    }
}
