public class Appointment implements Comparable <Appointment> {
        protected Date date;
        protected Timeslot timeslot;
        protected Person   patient;
        protected Person   provider;

        @Override
        public int compareTo(Appointment appt) {
            return 0;
        }

        @Override
        public boolean equals(Appointment appt) {
            return true;
        }

        @Override
        public String toString(Appointment appt) {

        }
}