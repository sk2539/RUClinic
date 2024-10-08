public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minute;

    public Timeslot() {
    }

    public void setTimeslot(String slot) {
        switch (slot) {
            case "1" -> { this.hour = 9; this.minute = 0; }
            case "2" -> { this.hour = 9; this.minute = 30; }
            case "3" -> { this.hour = 10; this.minute = 0; }
            case "4" -> { this.hour = 10; this.minute = 30; }
            case "5" -> { this.hour = 11; this.minute = 0; }
            case "6" -> { this.hour = 11; this.minute = 30; }
            case "7" -> { this.hour = 14; this.minute = 0; }
            case "8" -> { this.hour = 14; this.minute = 30; }
            case "9" -> { this.hour = 15; this.minute = 0; }
            case "10" -> { this.hour = 15; this.minute = 30; }
            case "11" -> { this.hour = 16; this.minute = 0; }
            case "12" -> { this.hour = 16; this.minute = 30; }
            default -> throw new IllegalArgumentException("Invalid slot number");
        }
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
        if (this.hour <= 11) {
            return hour + ":" + minuteString + " AM";
        } else if (this.hour > 11) {
            return hour + ":" + minuteString + " PM";
        }
        return "";
    }
}