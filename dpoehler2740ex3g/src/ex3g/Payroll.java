package ex3g;
import java.text.DecimalFormat;

public class Payroll {
	private int id;
	private String name;
	private double payRate;
	private double hours;


	public Payroll(int id, String name, double payRate, double hours) {
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;
	}


	public int getId() {
		return id;
	}
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}

	public String getName() {
		return name;
	}
	public boolean setName(String name) {
		if (name.isEmpty())	{	
			return false;
		}
		else {
			this.name = name;
			return true;
		}
	}

	public double getPayRate() {
		return payRate;
	}
	public boolean setPayRate(double payRate) {
		if (payRate >= 7.25 && payRate <=100) { 
			this.payRate = payRate;
			return true;
		}
		else {
			return false;
		}
	}

	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	

	public String toString() {
		DecimalFormat rateFmt = new DecimalFormat ("###.00");
		return id + " " + name + ", payRate=" + rateFmt.format(payRate);
	}
	
	public double calcGrossPay() {
		double gross;
		double overtimePay;
		
		// More than forty hours worked?
		if (hours > 40) {
		
			// Regular pay
			gross = 40 * payRate;
			// Overtime pay 1.5 times hourly rate
			overtimePay = (hours - 40) * (payRate * 1.5);
			// Total pay
			gross += overtimePay;			
		}
		else {
			// Regular pay
			gross = hours * payRate;
		}
		return gross;
	}
	
	public boolean addHours (double hours) {
		if (hours >= 0.1 && hours <= 20) {
			this.hours += hours;
			return true;
		}
		else {
			return false;
		}
	}
		
/*
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
 */

}
