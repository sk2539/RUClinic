public class ListMethods<E> extends List{
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

    public static int getDoctorFromNPI(List<E> objects, String npi) {
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
    public static int identifyAppointment(List<E> objects, Profile profile, Date date, Timeslot timeslot) {
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

    public static int identifyImagingAppt(List<E> objects, Technician tech, Date date, Timeslot timeslot) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Imaging) {
                Imaging imaging = (Imaging) objects.get(i);
                if (imaging.getProvider().equals(tech) &&
                        imaging.getDate().equals(date) &&
                        imaging.getTimeslot().equals(timeslot)) {
                    return i; // Technician is NOT available (appointment found)
                }
            }
        }
        return NOT_FOUND; // Technician is available (no conflicting appointment found)
    }

    public static boolean isRoomFree(List<E> objects, Technician tech, Date date, Timeslot timeslot, Radiology room) {
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

    public static int timeslotTaken(List<E> objects, Provider provider, Timeslot timeslot, Date date) {
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
