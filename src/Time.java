public class Time {
	int hours;
	int minutes;
	int seconds;

	public Time(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		normalize();
	}

	private void normalize() {
		int newSeconds = this.seconds % 60;
		int secondsCarryOver = this.seconds - newSeconds;

		int newMinutes = (secondsCarryOver / 60 + this.minutes) % 60;
		int minuteCarryOver = this.minutes - newMinutes;

		int newHours = (minuteCarryOver / 60 + this.hours) % 24;

		this.seconds = newSeconds;
		this.minutes = newMinutes;
		this.hours = newHours;
	}

	public void scale(int seconds) {
		this.seconds += seconds;
		normalize();
	}

	public String timeString() {
		// This is how I would do it with built in functions:
		// return String.format("%02d:%02d:%02d", seconds, minutes, hours);
		// This is my implementation:
		return formatNum(hours) + ":" + formatNum(minutes) + ":" + formatNum(seconds);
	}

	private String formatNum(int num) {
		if (num < 10) {
			return "0" + num;
		} else {
			return Integer.toString(num);
		}
	}
}
