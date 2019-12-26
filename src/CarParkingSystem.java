import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class CarParkingSystem {

    private static CarParkingSystem carParkingSystem;
    private SemaphoreGui semaphoreGui = new SemaphoreGui();
    private GateGui gateGui = new GateGui();
    private ParkingSpacesGui parkingSpacesGui;
    private Semaphore parkingSemaphore;
    private int carNumber = 0;
    private ArrayList<Thread> runningThreads = new ArrayList<>();
    private int permits;
    private String password;

    public CarParkingSystem(int permits, String password) {
        this.permits = permits;
        this.password = password;
        parkingSemaphore = new Semaphore(permits);
        parkingSpacesGui = new ParkingSpacesGui(permits);
    }

    //Singleton
    public static CarParkingSystem getSystem() {
        return carParkingSystem;
    }

    public static void startSystem(int permits, String password) {
        carParkingSystem = new CarParkingSystem(permits, password);
    }

    public void enterCode(String code) {

        Thread thread = new Thread() {
            public void run() {
                try {
                    Integer.parseInt(code);
                } catch (NumberFormatException e) {
                    Log.print("Invalid code: not an integer");
                    return;
                }
                if (code.equals(password)) {
                    Log.print("Correct Code!");
                    carEntry();
                } else {
                    Log.print("Wrong code");
                }
            }
        };
        thread.start();
        runningThreads.add(thread);
    }

    public void carEntry() {
        Thread thread = new Thread() {
            public void run() {
                try {
                    parkingSemaphore.acquire();
                    if (this.isInterrupted()) return;

                    if (parkingSemaphore.availablePermits() == 0) {
                        semaphoreGui.changeSemaphoreColor(SemaphoreGui.SemaphoreColor.RED);
                        Log.print("Park is now FULL!!");
                    }
                    gateGui.openGate();
                    carNumber++;
                    Log.print("Car nº " + carNumber + " is parking");
                    Thread.sleep(2000);

                    for (int i = 0; i < parkingSpacesGui.getTextPanes().length; i++) {
                        if (parkingSpacesGui.getTextPanes()[i].getBackground().equals(Color.GREEN)) {
                            parkingSpacesGui.getTextPanes()[i].setBackground(Color.RED);
                            parkingSpacesGui.getTextPanes()[i].setText("FULL");
                            parkingSpacesGui.getTextPanes()[i].setForeground(Color.WHITE);
                            break;
                        }
                    }
                    gateGui.closeGate();
                } catch (InterruptedException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        };
        thread.start();
        runningThreads.add(thread);
    }

    public void carExit() {

        Thread thread = new Thread() {
            public void run() {
                gateGui.openGate();

                for (int i = parkingSpacesGui.getTextPanes().length - 1; i >= 0; i--) {
                    if (parkingSpacesGui.getTextPanes()[i].getBackground().equals(Color.RED)) {
                        Log.print("Car parked in nº " + (i + 1) + " position is leaving the park");
                        parkingSpacesGui.getTextPanes()[i].setBackground(Color.GREEN);
                        parkingSpacesGui.getTextPanes()[i].setText(String.valueOf(i + 1));
                        parkingSpacesGui.getTextPanes()[i].setForeground(Color.WHITE);
                        break;
                    }
                }
                gateGui.closeGate();
                semaphoreGui.changeSemaphoreColor(SemaphoreGui.SemaphoreColor.GREEN);
                parkingSemaphore.release();
            }
        };
        thread.start();
        runningThreads.add(thread);
    }

    public void changeGatePosition() {
        gateGui.changeGateState();
    }

    public void reset() {
        new Thread() {
            public void run() {
                Log.print("Resetting System...");
                parkingSemaphore.release(permits - parkingSemaphore.availablePermits());
                //kill all threads
                for (Thread thread : runningThreads) {
                    thread.stop();//TODO
                }
                //clear array
                runningThreads.clear();
                for (int i = 0; i < parkingSpacesGui.getTextPanes().length; i++) {
                    parkingSpacesGui.getTextPanes()[i].setBackground(Color.GREEN);
                    parkingSpacesGui.getTextPanes()[i].setText(String.valueOf(i + 1));
                    parkingSpacesGui.getTextPanes()[i].setForeground(Color.WHITE);
                }
                semaphoreGui.changeSemaphoreColor(SemaphoreGui.SemaphoreColor.GREEN);
                gateGui.closeGate();
                Log.print("Reset Complete!");
            }
        }.start();
    }

    public void shutDown() {

    }
}