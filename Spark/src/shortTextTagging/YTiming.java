package shortTextTagging;

public class YTiming {


	public  void getTime(long t){
		long millis = System.currentTimeMillis() - t;
		long second = (millis / 1000) % 60;
		long minute = (millis / (1000 * 60)) % 60;
		long hour = (millis / (1000 * 60 * 60)) % 24;
	
		String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, millis);
				System.out.println("time " + time);
		
	}
	
	
}
