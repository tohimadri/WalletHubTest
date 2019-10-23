package utility;

public class UtilityClass {

	public static long getRandomNumber() {
		return (long) Math.floor(Math.random() * 900000000L) + 10000000L;
	}
	
	public static String getReviewMessage() {
		String msg = "";
		for (int i=0; i<6; i++) {
			msg += "This is sample Automated review.";
		}
		return msg;
	}
}
