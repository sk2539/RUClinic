import java.util.Scanner;

public class ClinicManager {
    List <Appointment> appts = new List <Appointment>();
    List <Provider> providers = new List <Provider>();
    // this class replaces Scheduler class from project1
    public void run() {
        System.out.println("Clinic Manager is running");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().trim();
        String [] splittedInput = input.split(",");
        System.out.println();
        while(true) {
            if(input.isEmpty() || splittedInput[0] == null) {
                input = in.nextLine();
                splittedInput = input.split(",");
            }
            if(splittedInput[0].equals("Q")) {
                System.out.println("Scheduler terminated.");
                return;
            }
            if (splittedInput.length == 7 || splittedInput.length==1) {
                if (splittedInput.length > 0 && !splittedInput[0].isEmpty()) {
                    String command = splittedInput[0];
                    switch (command) {
                        case "D":
                            scheduleDocAppt(splittedInput);
                            break;
                        case "T":
                            schedule(splittedInput);
                            break;
                        case "C":
                            cancel(splittedInput);
                            break;
                        case "R":
                            reschedule(splittedInput);
                            break;
                        case "PA":
                            if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByAppointment();
                            }
                            break;
                        case "PP":
                            if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByPatient();
                            }
                            break;
                        case "PL":
                            if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByLocation();
                            }
                            break;
                        case "PS":
                            if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printAllCharge();
                            }
                            break;
                        default:
                            if (command.length() > 0 && Character.isLowerCase(command.charAt(0))) {
                                System.out.println("Invalid command.");
                            } else if (appts.getSize() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                return;
                            }
                            break;
                    }
                }
            }
        }
    }

    public static void loadProviders() {
        String provider = "D  ANDREW  PATEL  01/21/1989  BRIDGEWATER  FAMILY  01\n" +
                "D  RACHAEL  LIM  11/30/1975  BRIDGEWATER  PEDIATRICIAN  23\n" +
                "D  MONICA  ZIMNES  03/11/1981  CLARK  FAMILY  11\n" +
                "D  JOHN  HARPER  03/01/1981  CLARK  FAMILY  32\n" +
                "D  TOM  KAUR  11/05/1972  PRINCETON  ALLERGIST  54\n" +
                "D  ERIC  TAYLOR  05/29/1969  PISCATAWAY  PEDIATRICIAN  91\n" +
                "D  BEN  RAMESH  04/08/1981  MORRISTOWN  ALLERGIST  39\n" +
                "D  JUSTIN  CERAVOLO  09/22/1980  EDISON  PEDIATRICIAN  09\n" +
                "D  GARY  JOHNSON  12/13/1977  EDISON  FAMILY  85\n" +
                "D  BEN  JERRY  12/28/1977  PISCATAWAY  FAMILY  77\n" +
                "T  GARY  JOHNSON  11/14/1987  PISCATAWAY  110\n" +
                "T  BEN  JERRY  9/28/1987  PISCATAWAY  150\n" +
                "T  FRANK  LIN  6/24/1999  PISCATAWAY  120\n" +
                "T  CHARLES  BROWN  6/24/1999  BRIDGEWATER  100\n" +
                "T  MONICA  FOX  10/10/1995  BRIDGEWATER  130\n" +
                "T  JENNY  PATEL  8/09/1991  BRIDGEWATER  125";
        String [] splittedProvider = provider.split("\n");
        for (int i = 0; i<splittedProvider.length; i++) {
            if (splittedProvider[i].charAt(0)=='D') {
                Doctor doc = new
            }
        }
    }

    public void scheduleDocAppt(String [] input) {
        Date date = stringToDate(input[1]);
        Timeslot slot = new Timeslot();
        slot.setTimeslot(input[2]);
        Profile profile = new Profile(input[3], input[4], stringToDate(input[5]));

    }

    public Date stringToDate(String date) {
        String [] dateString = date.split("/");
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
        Date dateObject = new Date(year, month, day);
        return dateObject;
    }
}
