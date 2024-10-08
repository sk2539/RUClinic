import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
                            if (appts.size() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByAppointment();
                            }
                            break;
                        case "PP":
                            if (appts.size() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByPatient();
                            }
                            break;
                        case "PL":
                            if (appts.size() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printByLocation();
                            }
                            break;
                        case "PS":
                            if (appts.size() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                appts.printAllCharge();
                            }
                            break;
                        default:
                            if (command.length() > 0 && Character.isLowerCase(command.charAt(0))) {
                                System.out.println("Invalid command.");
                            } else if (appts.size() == 0) {
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

    public void loadProviders() {
        String file = "providers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] splittedLine = line.split(" ");
                if (splittedLine[0]=="D") {
                    Profile profile = new Profile (splittedLine[1], splittedLine[2], stringToDate(splittedLine[3]));
                    Specialty specialty = new Specialty(splittedLine[5]);
                    Doctor doctor = new Doctor(profile, setLocation(splittedLine[4]), specialty, splittedLine[6]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location setLocation(String input) {
        Location location;
        if (input.equals("BRIDGEWATER")) {
            return Location.Bridgewater;
        }
        else if (input.equals("CLARK")) {
            return Location.Clark;
        }
        else if (input.equals("PRINCETON")) {
            return Location.Princeton;
        }
        else if (input.equals("PISCATAWAY")) {
            return Location.Piscataway;
        }
        else if (input.equals("MORRISTOWN")) {
            return Location.Morristown;
        }
        else if (input.equals("EDISON")) {
            return Location.Edison;
        }
        return null;
    }

    public void scheduleDocAppt(String [] input) {
        Date date = stringToDate(input[1]);
        Timeslot slot = new Timeslot();
        slot.setTimeslot(input[2]);
        Profile profile = new Profile(input[3], input[4], stringToDate(input[5]));

    }

    // can i make this static?
    public Date stringToDate(String date) {
        String [] dateString = date.split("/");
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);
        Date dateObject = new Date(year, month, day);
        return dateObject;
    }
}
