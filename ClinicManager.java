import java.io.File;
import java.util.Scanner;

public class ClinicManager {
    List <Appointment> appts = new List <Appointment>();
    List <Provider> providers = new List <Provider>();
    CircularLinkedList technicians = new CircularLinkedList();
    // this class replaces Scheduler class from project1
    public void run() {
        loadProviders();
        printProviders();
        System.out.println("Clinic Manager is running...");
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
//                        case "T":
//                            scheduleTechAppt(splittedInput);
//                            break;
//                        case "C":
//                            cancel(splittedInput);
//                            break;
//                        case "R":
//                            reschedule(splittedInput);
//                            break;
//                        case "PA":
//                            if (appts.size() == 0) {
//                                System.out.println("The schedule calendar is empty.");
//                            } else {
//                                appts.printByAppointment();
//                            }
//                            break;
//                        case "PP":
//                            if (appts.size() == 0) {
//                                System.out.println("The schedule calendar is empty.");
//                            } else {
//                                appts.printByPatient();
//                            }
//                            break;
//                        case "PL":
//                            if (appts.size() == 0) {
//                                System.out.println("The schedule calendar is empty.");
//                            } else {
//                                appts.printByLocation();
//                            }
//                            break;
//                        case "PS":
//                            if (appts.size() == 0) {
//                                System.out.println("The schedule calendar is empty.");
//                            } else {
//                                appts.printAllCharge();
//                            }
//                            break;
                        default:
                            if (!command.isEmpty() && Character.isLowerCase(command.charAt(0))) {
                                System.out.println("Invalid command!");
                            } else if (appts.size() == 0) {
                                System.out.println("The schedule calendar is empty.");
                            } else {
                                return;
                            }
                            break;
                    }
                    input = in.nextLine();
                    splittedInput = input.split(",");
                }
            }
            else {
                System.out.println("Missing data tokens.");
            }
        }
    }

    public void loadProviders() {
        String fileName = "providers.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }

        try {
            Scanner scanner = new Scanner(file);  // Using Scanner to read the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Split the line into individual fields
                String[] splittedLine = line.split("  ");
                // Use .equals() to compare strings, not ==
                if (splittedLine[0].equals("D")) {
                    Profile profile = new Profile(splittedLine[1], splittedLine[2], stringToDate(splittedLine[3]));
                    Specialty specialty = setSpecialty(splittedLine[5]);
                    Doctor doctor = new Doctor(profile, setLocation(splittedLine[4]), specialty, splittedLine[6]);
                    providers.add(doctor);
                } else if (splittedLine[0].equals("T")) {
                    Profile profile = new Profile(splittedLine[1], splittedLine[2], stringToDate(splittedLine[3]));
                    Location location = setLocation(splittedLine[4]);
                    int rate = Integer.parseInt(splittedLine[5]);
                    Technician technician = new Technician(profile, location, rate);
                    providers.add(technician);
                    technicians.addTechnician(technician);
                }
            }
            scanner.close();  // Don't forget to close the Scanner
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Providers loaded to the list.");
    }

    public void printProviders() {
        Sort.sortByProvider(providers); // check if this is the right syntax
        for (int i = 0; i<providers.size(); i++) {
            System.out.println(providers.get(i).toString());
        }

        technicians.display();
    }

    public Specialty setSpecialty(String input) {
        Specialty specialty;
        if (input.equals("FAMILY")) {
            return Specialty.Family;
        }
        else if (input.equals("PEDIATRICIAN")) {
            return Specialty.Pediatrician;
        }
        else if (input.equals("PRINCETON")) {
            return Specialty.Allergist;
        }
        return null;
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
        Timeslot slot = new Timeslot();
        slot.setTimeslot(input[2]);
        if (!slot.setTimeslot(input[2])) {
            System.out.println(input[2] + " is not a valid time slot.");
            return;
        }
        Date dob = stringToDate(input[5]);
        if (!dob.isValidDate()) { // FIX THIS - it could be null because of the checkDate method!!!
            System.out.println("Patient dob: " + input[5] + " is not a valid calendar date.");
            return;
        }
        Profile profile = new Profile(input[3], input[4], stringToDate(input[5]));
        int matchingDoctorIndex = providers.getDoctorFromNPI(input[6]);
        if (matchingDoctorIndex==-1) {
            System.out.println(input[6] + " - provider doesn't exist.");
            return;
        }
        Doctor doctor = (Doctor) providers.get(matchingDoctorIndex);

        // this might not work properly!!
        if (appts.timeslotTaken(doctor, slot) != -1) {
            System.out.println(appts.get(appts.timeslotTaken(doctor, slot)).getProfile().toString() + " has an existing appointment at the same timeslot.");
            return;
        }
        if (checkDate(input[1])) { //2/28/2025 9:00 AM John Doe 12/13/1989 [ANDREW PATEL 1/21/1989, BRIDGEWATER, Somerset 08807][FAMILY, #01] booked.
            System.out.println(doctor.toString());
        }
    }

    // given: Appointment date, timeslot, first name, last name, date of birth (date, timeslot and profile)
    public void cancel(String [] input) {
        if (input.length < 7) {
            System.out.println("Missing data tokens.");
            return;
        }
        Date date = stringToDate(input[1]);
        Timeslot slot = new Timeslot();
        if (!slot.setTimeslot(input[2])) {
            System.out.println(input[2] + " is not a valid time slot.");
            return;
        }
        Profile profile = new Profile(input[3], input[4], stringToDate(input[5]));
        int inptApp = appts.identifyAppointment(profile, date, slot);
        if (inptApp!=-1)
        {
            Appointment currApp = appts.get(inptApp);
            Appointment appointment = new Appointment(currApp.getDate(), currApp.getTimeslot(), currApp.getProfile(), currApp.getProvider());
            appts.remove(appointment);
            System.out.println(date.toString() + " " + slot.toString() + " " + profile.toString() + " has been canceled.");
            return;
        }
        System.out.println(date.toString() + " " + slot.toString() + " " + profile.toString() + " does not exist.");
    }

    // Reschedule an appointment given: Date, Timeslot1, First name, Last name, DOB, Timeslot2
    public void reschedule(String[] input) {
        if (input.length < 8) {
            System.out.println("Missing data tokens.");
            return;
        }
        Date date = stringToDate(input[1]);
        Timeslot timeslot1 = new Timeslot();
        timeslot1.setTimeslot(input[2]);
        Timeslot timeslot2 = new Timeslot();
        timeslot2.setTimeslot(input[6]);
        if (!timeslot2.setTimeslot(input[6])) {
            System.out.println(input[6] + " is not a valid timeslot.");
        }
        String firstName = input[3];
        String lastName = input[4];
        Date dob = stringToDate(input[5]);
        Profile profile = new Profile(firstName, lastName, dob);

        int apptIndex = appts.identifyAppointment(profile, date, timeslot1);

        if (apptIndex == -1) {
            System.out.println(input[1] + " " + timeslot1.toString() + " " + firstName + " " + lastName + " " + dob.toString() + " does not exist.");
            return;
        }

        Appointment appointment = appts.get(apptIndex);
        Provider provider = (Provider) appointment.getProvider();

        // [PATEL, BRIDGEWATER, Somerset 08807, FAMILY] is not available at slot 1.
        if (appts.timeslotTaken(provider, timeslot2) != -1) {
            System.out.println(provider.toString() + " is not available at slot " + input[2]);            return;
        }

        appts.get(apptIndex).setTimeslot(timeslot2);
        Appointment appointmentFinal = appts.get(apptIndex);

        System.out.println("Rescheduled to " + input[1] + " " + timeslot2.toString() + " " + firstName + " " + lastName + " " + dob.toString());
    }


public boolean checkDate(String input) {
        Date date = stringToDate(input);
        if (date==null) {
            return false;
        }
        else if(date.isBeforeToday() || date.isToday()) {
            System.out.println("Appointment date: " + input + " is today or a date before today.");
            return false;
        }
        else if (date.onWeekend()) {
            System.out.println("Appointment date: " + input + " is Saturday or Sunday.");
            return false;
        }
        else if(!date.isWithinSixMonths()) {
            System.out.println("Appointment date: " + input + " is not within six months.");
            return false;
        }
        else {
            return true;
        }
    }

    public Date stringToDate(String date) {
        String[] dateString = date.split("/");

        if (dateString.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Expected format: MM/DD/YYYY");
        }
        int month = Integer.parseInt(dateString[0]);
        int day = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);

        Date dateObject = new Date(year, month, day);

        if (!dateObject.isValidDate()) {
            System.out.println("Appointment date: " + date + " is not a valid calendar date.");
            return null;
        }
        else {
            return dateObject;
        }
    }
}