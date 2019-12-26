import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //From this moment is always available for everyone
        String[] config = readConfig();
        CarParkingSystem.startSystem(Integer.parseInt(config[0]), config[1]);

        new ParkingPanelGui();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Log.saveToFile();
            }
        });
    }

    public static String[] readConfig() {
        try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {
            //We only need to read the first line so there's no need of a while loop
            return br.readLine().split(",");
        } catch (IOException e) {
            return new String[]{"6", "0000"}; //default
        }
    }
}