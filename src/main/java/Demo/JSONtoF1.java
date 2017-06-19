package Demo;

import Formula1.Model.Season;
import Helpers.JSON;

import java.io.FileReader;
import java.util.Scanner;

public class JSONtoF1 {
    public static void main(String[] args) throws Exception {
        // This is slower than just loading the data files into a Season object
        Scanner scanner = new Scanner(new FileReader("data/2017/results.json"));
        String json = scanner.nextLine();
        Season season2017 = JSON.parse(json, Season.class);
    }
}
