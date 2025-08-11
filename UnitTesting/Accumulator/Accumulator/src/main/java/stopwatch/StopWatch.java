package stopwatch;

public class StopWatch {
    int minutes;
    int hours;
    int days;
    int workingHoursPerDay;

    public StopWatch(int workingHoursPerDay) {
        this.workingHoursPerDay = workingHoursPerDay;
        minutes = 0;
        hours = 0;
        days = 0;
    }

    public void record(int minutes) {
        if (minutes < 0) {
            return;
        }
        this.minutes += minutes;
        if (this.minutes >= 60) {
            this.minutes -= 60;
            hours++;
        }
        if (hours >= workingHoursPerDay) {
            hours -= workingHoursPerDay;
            days++;
        }

    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getDays() {
        return days;
    }
}
