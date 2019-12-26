import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Log {
    private static ArrayList<String> log = new ArrayList<>();

    public static void print(String print) {
        String output = new Date().toString() + ": " + print;
        System.out.println(output);
        log.add(output);
    }

    public static void save() {
        //save to file
        File file = new File((new Date()).toString());
    }
}
