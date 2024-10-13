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
                        case "T":
                            scheduleImaging(splittedInput);
                            break;
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
                input = in.nextLine();
                splittedInput = input.split(",");
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

    public void scheduleImaging(String[] input)
    {
        List<Appointment> scheduledImgAppt = new List<Appointment>();
        //T,9/30/2024,1,John,Doe,12/13/1989,xray
        if(!checkApptDate(input[1])) {
            return;
        }
        Date apptDate = stringToDate(input[1]);
        Timeslot timeslot = new Timeslot();
        timeslot.setTimeslot(input[2]);
        if(!timeslot.setTimeslot(input[2])) {
            System.out.println(input[2] + " is not a valid time slot.");
            return;
        }
        Date dob = stringToDate(input[5]);
        if(!checkDOB(dob)){
            return;
        }
        Profile profile = new Profile(input[3], input[4], dob);
        Person patient = new Person(profile);
        int index = appts.identifyAppointment(profile, apptDate, timeslot);
        if (index != -1) {
            System.out.println(appts.get(index).patient.getProfile().toString() + " has an existing appointment at the same timeslot.");
            return;
        }
        if(!isValidImaging(input[6])) {
            System.out.println(input[6] + " - imaging service not provided");
            return;
        }
        Radiology room = setRadioRoom(input[6]);
        if(techAvailable(apptDate, timeslot) == null) {
            System.out.println("No technician available");
            return;
        }
        Person technician = techAvailable(apptDate, timeslot);
        Appointment newAppt = new Appointment(apptDate, timeslot, patient, technician);
        appts.add(newAppt);
        scheduledImgAppt.add(newAppt);
        System.out.println(newAppt.toString() + " created imging appt");
    }

    public void scheduleDocAppt(String [] input) {
        if (!checkApptDate(input[1])) {
            return;
        }
        Timeslot slot = new Timeslot();
        slot.setTimeslot(input[2]);
        if (!slot.setTimeslot(input[2])) {
            System.out.println(input[2] + " is not a valid time slot.");
            return;
        }
        Date dob = stringToDate(input[5]);
        if (!checkDOB(dob)) {
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
        //2/28/2025 9:00 AM John Doe 12/13/1989 [ANDREW PATEL 1/21/1989, BRIDGEWATER, Somerset 08807][FAMILY, #01] booked.
        System.out.println(doctor.toString());

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

    // Reschedule an appointment given: R, Date, Timeslot1, First name, Last name, DOB, Timeslot2
    public void reschedule(String[] input) {
        if (input.length < 7) {
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
            return;
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

        int apptIndex2 = appts.identifyAppointment(profile, date, timeslot2);
        if (apptIndex2 !=-1) {
            Appointment appointment = appts.get(apptIndex2);
            System.out.println(profile.toString() + " has an existing appointment at " + appointment.getDate().toString() + " " + timeslot2.toString());
            return;
        }

        Appointment appointment = appts.get(apptIndex);
        Provider provider = (Provider) appointment.getProvider();

        // [PATEL, BRIDGEWATER, Somerset 08807, FAMILY] is not available at slot 1.
        if (appts.timeslotTaken(provider, timeslot2) != -1) {
            System.out.println(provider.toString() + " is not available at slot " + input[2]);
            return;
        }

        Appointment newAppt = appts.get(apptIndex);
        newAppt.setTimeslot(timeslot2);

        System.out.println("Rescheduled to " + input[1] + " " + timeslot2.toString() + " " + firstName + " " + lastName + " " + dob.toString() + " " + newAppt.getProvider().toString());
    }

    public boolean checkDOB(Date dob)
    {
        if(!dob.isValidDate()) {
            System.out.println("Patient dob: " + dob.toString() + " is not a valid calendar date");
            return false;
        }
        else if(dob.isToday() || dob.isFutureDate()) {
            System.out.println("Patient dob: " + dob.toString() + " is today or a date after today.");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkApptDate(String input) {
        Date date = stringToDate(input);
        if (!date.isValidDate()) {
            System.out.println("Appointment date: " + input + " is not a valid calendar date");
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

        if (dateObject == null) {
            return null;
        }
        else {
            return dateObject;
        }
    }

    public boolean isValidImaging(String input) {
        input.toLowerCase();
        if(input.equals("xray") || input.equals("catscan") || input.equals("ultrasound"))
        {
            return true;
        }
        return false;
    }

    public Radiology setRadioRoom(String input) {
        input.toLowerCase();
        if(input.equals("xray")) {
            return Radiology.XRAY;
        }
        else if(input.equals("catscan")) {
            return Radiology.CATSCAN;
        }
        else if(input.equals("ultrasound")) {
            return Radiology.ULTRASOUND;
        }
        return null;
    }

    public Technician techAvailable(Date date, Timeslot timeslot) {
        //iterate through circular linked list and check if any provider is available
        //not available means that the technician, date, and timeslot are the same as an appt in the appts list
        CircularLinkedList.Node head = technicians.getHead();
        CircularLinkedList.Node curr = technicians.getHead();
        do{
            Technician currentTech = curr.technician;
            Date requestedDate;
            Timeslot requestedTimeslot;
            //the provider is not available
            if(appts.identifyImagingAppt(currentTech, date, timeslot) != -1) {
                curr = curr.next;
            }
            else {
                return currentTech;
            }
        }while(head != curr);

        return null;
    }
}