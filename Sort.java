public class Sort {
    private static void swapAppointments(List<Appointment> appointments, int i, int j) {
        Appointment temp = appointments.get(i);
        appointments.set(i, appointments.get(j));
        appointments.set(j, temp);
    }

    public static void sortByAppointment(List<Appointment> list, char key) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareByAppointment(list.get(j+1)) > 0) {
                    swapAppointments(list, j, j + 1);
                }
            }
        }
    }
    public static void sortByProvider(List<Provider> list) {

    }

    public static void sortByPatient(List<Appointment> list)
    {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareByPatient(list.get(j+1)) > 0) {
                    swapAppointments(list, j, j + 1);
                }
            }
        }
    }

    public static void sortByLocation(List<Appointment> list)
    {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareByLocation(list.get(j+1)) > 0) {
                    swapAppointments(list, j, j + 1);
                }
            }
        }
    }

    public static void sortByProfile(List<Appointment> list)
    {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getProfile().compareTo(list.get(j + 1).getProfile()) < 0) {
                    swapAppointments(list, j, j + 1);
                }
            }
        }
    }
}
