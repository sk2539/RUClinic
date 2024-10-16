package util;

import src.*;

import java.text.DecimalFormat;

public class ListMethods<E> extends List{
    Sort sort = new Sort();
    int NOT_FOUND = -1;
    public ListMethods()
    {
        super();
    }

    public int timeslotTakenByPatient(List<E> objects, Profile profile, Date date, Timeslot timeslot) {
        for (int i = 0; i<objects.size(); i++) {
            E obj = objects.get(i);
            if(obj instanceof Appointment)
            {
                if (((Appointment) obj).getProfile().equals(profile) && ((Appointment) obj).getTimeslot().equals(timeslot) && ((Appointment) obj).getDate().equals(date)) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }
    public int dateExists (List <E> objects, Date date)
    {
        for(int i = 0; i < objects.size(); i++)
        {
            E obj = objects.get(i);
            if(obj instanceof Appointment)
            {
                if(((Appointment) obj).getDate().equals(date))
                    return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Prints all appointments ordered by date, time, and provider.
     */
    public void printByAppointment(List <E> objects) {
        System.out.println();
        System.out.println("** Appointments ordered by date/time/provider.");
        sort.sortByAppointment(objects);
        printAppointments(objects);
        System.out.println("** end of list **");
    }

    /**
     * Prints all appointments in the list.
     */
    private void printAppointments(List <E> objects) {
        for (int i = 0; i < objects.size(); i++) {
            E obj = objects.get(i);
            if (obj instanceof Appointment) {
                System.out.println(formatAppointment((Appointment) obj));
            }
        }
    }

    /**
     * Formats an appointment for display.
     * @param app The appointment to format.
     * @return A formatted string representing the appointment.
     */
    private String formatAppointment(Appointment app) {
        return String.format("%s %s %s %s %s %s",
                app.getDate(),
                app.getTimeslot().toString(),
                app.getProfile().getProfile().getFirstName(),
                app.getProfile().getProfile().getLastName(),
                app.getProfile().getProfile().getDob(),
                app.getProvider().toString().toUpperCase());
    }

    private String formatImagingAppointments(Imaging imaging) {
        return String.format("%s %s %s %s %s %s",
                imaging.getDate(),
                imaging.getTimeslot().toString(),
                imaging.getProfile().getProfile().getFirstName(),
                imaging.getProfile().getProfile().getLastName(),
                imaging.getProfile().getProfile().getDob(),
                imaging.getProvider().toString().toUpperCase());
    }

    public void printOfficeAppointments(List <E> objects) {
        System.out.println();
        System.out.println("** util.List of office appointments ordered by county/date/time.");
        sort.sortByLocation(objects);
        printOfficeAppts(objects);
        System.out.println("** end of list **");
    }

    private void printOfficeAppts(List <E> objects) {
        for (int i =0; i < objects.size(); i++) {
            E obj = objects.get(i);
            if (obj instanceof Appointment) {
                System.out.println(formatAppointment((Appointment) obj));
            }
        }
    }

    public void printImagingAppointments(List <E> objects) {
        System.out.println();
        System.out.println("** util.List of radiology appointments ordered by county/date/time.");
        sort.sortByLocation(objects);
        printImagingAppts(objects);

    }

    private void printImagingAppts(List <E> objects) {
        for (int i = 0; i<objects.size(); i++) {
            E obj = objects.get(i);
            if (obj instanceof Imaging) {
                System.out.println(formatImagingAppointments((Imaging) obj));
            }
        }
    }

    /**
     * Prints all appointments ordered by patient, date, and time.
     */
    public void printByPatient(List <E> objects) {
        System.out.println();
        System.out.println("** Appointments ordered by patient/date/time **");
        sort.sortByPatient(objects);
        printAppointments(objects);
        System.out.println("** end of list **");
    }

    /**
     * Prints all appointments ordered by location, date, and time.
     */
    public void printByLocation(List <E> objects) {
        System.out.println();
        System.out.println("** Appointments ordered by county/date/time.");
        sort.sortByLocation(objects);
        printAppointments(objects);
        System.out.println("** end of list **");
    }

    /**
     * Prints all charges for appointments, ordered by patient.
     */
    public void printAllCharge(List <E> objects) {
        if (objects.size() == 0) {
            System.out.println("There are no appointments in the system.");
            return;
        }
        System.out.println("** Billing statement ordered by patient **");
        sort.sortByProfile(objects);
        DecimalFormat formatDec = new DecimalFormat("$#,##0.00");
        int counter = 1;
        Profile currentProfile = null;
        int currentCharge = 0;

        for (int i = 0; i < objects.size(); i++) {
            E obj = objects.get(i);
            int charge=0;
            Profile profile=null;
            if (obj instanceof Appointment) {
                profile = ((Appointment) obj).getProfile().getProfile();
                if (((Appointment) obj).getProvider() instanceof Doctor) {
                    charge = ((Doctor)((Appointment) obj).getProvider()).getSpecialty().getCharge();
                }
                else if (((Appointment) obj).getProvider() instanceof Technician) {
                    charge = ((Technician) ((Appointment) obj).getProvider()).rate();
                }
            }

            if (currentProfile == null || !currentProfile.equals(profile)) {
                if (currentProfile != null) {
                    System.out.printf("(%d) %s [amount due: %s]%n",
                            counter++,
                            currentProfile.toString(),
                            formatDec.format(currentCharge));
                }
                currentProfile = profile;
                currentCharge = charge;
            } else {
                currentCharge += charge;
            }
        }
        if (currentProfile != null) {
            System.out.printf("(%d) %s [amount due: %s]%n",
                    counter,
                    currentProfile.toString(),
                    formatDec.format(currentCharge));
        }
        System.out.println("** end of list **");
    }

    public void printProviderCharges(List <E> objects) {
        System.out.println("** Credit amount ordered by provider. **");
        sort.sortByProvider(objects);

    }

    // REMEMBER TO DO PS COMMAND - ask dhyana about this

    public int getDoctorFromNPI(List<E> objects, String npi) {
        for (int i = 0; i<objects.size(); i++) {
            E obj = objects.get(i);
            if(objects.get(i) instanceof Doctor) {
                if (((Doctor) objects.get(i)).getNPI().equals(npi)) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }
    public int getTechnicianFromRate(List<E> objects, int rate) {
        for (int i = 0; i < size(); i++) {
            if(objects.get(i) instanceof Technician) {
                if (((Technician) objects.get(i)).rate()==(rate)) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }
    public int identifyAppointment(List<E> objects, Profile profile, Date date, Timeslot timeslot) {
        for (int i = 0; i<objects.size(); i++) {
            if (objects.get(i) instanceof Appointment) {
                if (((Appointment) objects.get(i)).getProfile().getProfile().equals(profile)) {
                    if (((Appointment) objects.get(i)).getDate().equals(date)) {
                        if (((Appointment) objects.get(i)).getTimeslot().equals(timeslot)) {
                            return i;
                        }
                    }
                }
            }
        }
        return NOT_FOUND;
    }

    public int identifyImagingAppt(List<E> objects, Technician tech, Date date, Timeslot timeslot) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Imaging) {
                Imaging imaging = (Imaging) objects.get(i);
                if (imaging.getProvider().equals(tech) &&
                        imaging.getDate().equals(date) &&
                        imaging.getTimeslot().equals(timeslot)) {
                    return i; // src.Technician is NOT available (appointment found)
                }
            }
        }
        return NOT_FOUND; // src.Technician is available (no conflicting appointment found)
    }

    public boolean isRoomFree(List<E> objects, Technician tech, Date date, Timeslot timeslot, Radiology room) {
        Location location = tech.getLocation();
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Imaging) {
                Imaging imaging = (Imaging) objects.get(i);
                if (imaging.getProvider().getLocation().equals(location) &&
                        imaging.getDate().equals(date) &&
                        imaging.getTimeslot().equals(timeslot) &&
                        imaging.getRoom().equals(room)) {
                    return false; // Room is NOT free
                }
            }
        }
        return true; // Room is free
    }

    public int timeslotTaken(List<E> objects, Provider provider, Timeslot timeslot, Date date) {
        for (int i = 0; i < objects.size(); i++) {
            if(objects.get(i) instanceof Appointment) {
                if (((Appointment) objects.get(i)).getProvider().equals(provider) && ((Appointment) objects.get(i)).getDate().equals(date) && ((Appointment) objects.get(i)).getTimeslot().equals(timeslot)) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }
}
