public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minute;

    public Timeslot(int hour, int minute) { // in military time
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public int compareTo(Timeslot slot) {
        if (this.hour > slot.hour) {
            return 1;
        }
        else if (this.hour < slot.hour) {
            return -1;
        }
        if (this.minute > slot.minute) {
            return 1;
        }
        else if (this.minute < slot.minute) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Timeslot) {
            Timeslot slot = (Timeslot) object;
            return this.hour == slot.hour && this.minute == slot.minute;
        }
        return false;
    }

    @Override
    public String toString() {
        String minuteString = String.format("%02d", this.minute);
        if (hour == 0) {
            return "12:" + minuteString + " AM";
        }
        else if (hour == 12) {
            return "12:" + minuteString + " PM";
        } else if (hour > 12) {
            return (hour - 12) + ":" + minuteString + " PM";
        } else {
            return hour + ":" + minuteString + " AM";
        }
    }
}