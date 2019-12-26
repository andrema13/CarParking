import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Log {
    private static ArrayList<String> log = new ArrayList<>();

    public static void print(String print) {
        String output = new Date().toString() + ": " + print;
        System.out.println(output);
        log.add(output);
    }

    public static void saveToFile() {
        //o windows nao deixava
        File file = new File(new Date().toString().replaceAll(":", "-") + ".txt");
        try (PrintWriter writer = new PrintWriter(file)) {
            StringBuilder sb = new StringBuilder();
            for (String s : log) {
                sb.append(s).append("\n");
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
