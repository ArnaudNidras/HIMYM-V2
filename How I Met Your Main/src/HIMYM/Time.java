package HIMYM;

public class Time {
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private String timing;
	
	public Time(int year, int month, int day, int hour, int minute, int second, String timing){
		
		this.timing = timing;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		
	}
	
	public String getTiming(){
		
		return timing;
		
	}
	
	public int getYear(){
		
		return year;
		
	}
	
	public int getMonth(){
		
		return month;
		
	}
	
	public int getDay(){
		
		return day;
		
	}
	
	public int getHour(){
		
		return hour;
		
	}
	
	public int getMinute(){
		
		return minute;
		
	}
	
	public int getSecond(){
		
		return second;
		
	}

}
