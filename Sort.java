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
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String lastName1 = list.get(j).getProfile().getLastName();
                String lastName2 = list.get(j + 1).getProfile().getLastName();
                if (lastName1.compareTo(lastName2) > 0) {
                    Provider temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
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
