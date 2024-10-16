public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minute;

    public Timeslot() {
    }

    public boolean setTimeslot(String slot) {
        switch (slot) {
            case "1" -> { this.hour = 9; this.minute = 0; return true; }
            case "2" -> { this.hour = 9; this.minute = 30; return true; }
            case "3" -> { this.hour = 10; this.minute = 0; return true; }
            case "4" -> { this.hour = 10; this.minute = 30; return true; }
            case "5" -> { this.hour = 11; this.minute = 0; return true; }
            case "6" -> { this.hour = 11; this.minute = 30; return true; }
            case "7" -> { this.hour = 14; this.minute = 0; return true; }
            case "8" -> { this.hour = 14; this.minute = 30; return true; }
            case "9" -> { this.hour = 15; this.minute = 0; return true; }
            case "10" -> { this.hour = 15; this.minute = 30; return true; }
            case "11" -> { this.hour = 16; this.minute = 0; return true; }
            case "12" -> { this.hour = 16; this.minute = 30; return true; }
            default -> { return false; }
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
        String period = (this.hour < 12) ? "AM" : "PM";

        // Convert the hour to 12-hour format
        int displayHour = this.hour % 12;
        if (displayHour == 0) {
            displayHour = 12; // 0 hours should be displayed as 12 AM/PM
        }

        return displayHour + ":" + minuteString + " " + period;
    }
}