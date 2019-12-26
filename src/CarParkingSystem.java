import java.awt.*;
import java.util.concurrent.Semaphore;

class CarParkingSystem extends Thread {

    private static CarParkingSystem carParkingSystem;
    private SemaphoreGui semaphoreGui = new SemaphoreGui();
    private GateGui gateGui = new GateGui();
    private ParkingSpacesGui parkingSpacesGui = new ParkingSpacesGui();
    private Semaphore parkingSemaphore = new Semaphore(6);
    private int carNumber = 0;

    //Singleton
    public static CarParkingSystem getSystem() {
        return carParkingSystem;
    }

    public static void startSystem() {
        carParkingSystem = new CarParkingSystem();
    }

    public void enterCode(String code) {
        new Thread() {
            public void run() {

                String codeNumber = "0000";

                try {
                    Integer.parseInt(code);
                } catch (NumberFormatException e) {
                    Log.print("Invalid code: not an integer");
                    return;
                }
                if (code.equals(codeNumber)) {
                    Log.print("Correct Code!");
                    carEntry();
                } else {
                    Log.print("Wrong code");
                }
            }
        }.start();
    }

    public void carEntry() {
        new Thread() {
            public void run() {
                try {
                    parkingSemaphore.acquire();
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
        }.start();
    }

    public void carExit() {

        new Thread() {
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
        }.start();
    }

    public void changeGatePosition() {
        gateGui.changeGateState();
    }

    public void reset() {

    }

    public void shutDown() {

    }
}


   /* private static final Semaphore SEMAPHORE = new Semaphore(5);
    public static class Car implements Runnable {

        private int carNumber;
        private ParkingSemaphore parkingSemaphore;
        private Gate gate;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {

            try {
                SEMAPHORE.acquire();
                int parkingNumber = -1;

                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++) {
                        if (!PARKING_PLACES[i]) {
                            PARKING_PLACES[i] = true;
                            parkingNumber = i;
                            System.out.printf("Car #%d Parked on %d place.\n", carNumber, i);
                            break;
                        }
                    }
                }
                Thread.sleep(5000);

                synchronized (PARKING_PLACES) {
                    //Free space for car
                    PARKING_PLACES[parkingNumber] = false;
                }

                SEMAPHORE.release();
                System.out.printf("Car #%d leave the parking.\n", carNumber);

            } catch (InterruptedException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }*/