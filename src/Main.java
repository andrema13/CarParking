public class Main {
    public static void main(String[] args) {
        //From this moment is always available for everyone
        CarParkingSystem.startSystem();

        new ParkingPanelGui();
        //Call this when is about to close
        Log.save();
    }
}