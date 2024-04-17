package application;

import java.util.Date;

public class Days {
	private double day;
	private double Israeli_Lines_MWs;
	private double Gaza_Power_Plant_MWs;
	private double Egyptian_Lines_MWs;
	private double Total_daily_Supply_available_in_MWs;
	private double Overall_demand_in_MWs;
	private double Power_Cuts_hours_day_400mg;
	private double Temp;
	private Date date;
	
    // Default constructor
	public Days() {
		super();
	}
	// Default constructor
		public Days(double day) {
			this.day = day;
		}

    // Default constructor with arguments
	public Days(double day, double israeli_Lines_MWs, double gaza_Power_Plant_MWs, double egyptian_Lines_MWs,
			double total_daily_Supply_available_in_MWs, double overall_demand_in_MWs, double power_Cuts_hours_day_400mg,
			double temp,Date date) {
		super();
		this.day = day;
		Israeli_Lines_MWs = israeli_Lines_MWs;
		Gaza_Power_Plant_MWs = gaza_Power_Plant_MWs;
		Egyptian_Lines_MWs = egyptian_Lines_MWs;
		Total_daily_Supply_available_in_MWs = total_daily_Supply_available_in_MWs;
		Overall_demand_in_MWs = overall_demand_in_MWs;
		Power_Cuts_hours_day_400mg = power_Cuts_hours_day_400mg;
		Temp = temp;
		this.date = date;
	}

    // Getter and setter methods for each attribute
	public double getDay() {
		return day;
	}


	public void setDay(double day) {
		this.day = day;
	}


	public double getIsraeli_Lines_MWs() {
		return Israeli_Lines_MWs;
	}


	public void setIsraeli_Lines_MWs(double israeli_Lines_MWs) {
		Israeli_Lines_MWs = israeli_Lines_MWs;
	}


	public double getGaza_Power_Plant_MWs() {
		return Gaza_Power_Plant_MWs;
	}


	public void setGaza_Power_Plant_MWs(double gaza_Power_Plant_MWs) {
		Gaza_Power_Plant_MWs = gaza_Power_Plant_MWs;
	}


	public double getEgyptian_Lines_MWs() {
		return Egyptian_Lines_MWs;
	}


	public void setEgyptian_Lines_MWs(double egyptian_Lines_MWs) {
		Egyptian_Lines_MWs = egyptian_Lines_MWs;
	}


	public double getTotal_daily_Supply_available_in_MWs() {
		return Total_daily_Supply_available_in_MWs;
	}


	public void setTotal_daily_Supply_available_in_MWs(double total_daily_Supply_available_in_MWs) {
		Total_daily_Supply_available_in_MWs = total_daily_Supply_available_in_MWs;
	}


	public double getOverall_demand_in_MWs() {
		return Overall_demand_in_MWs;
	}


	public void setOverall_demand_in_MWs(double overall_demand_in_MWs) {
		Overall_demand_in_MWs = overall_demand_in_MWs;
	}


	public double getPower_Cuts_hours_day_400mg() {
		return Power_Cuts_hours_day_400mg;
	}


	public void setPower_Cuts_hours_day_400mg(double power_Cuts_hours_day_400mg) {
		Power_Cuts_hours_day_400mg = power_Cuts_hours_day_400mg;
	}


	public double getTemp() {
		return Temp;
	}


	public void setTemp(double temp) {
		Temp = temp;
	}


	@Override
	public String toString() {
		return "Days [day=" + day + ", Israeli_Lines_MWs=" + Israeli_Lines_MWs + ", Gaza_Power_Plant_MWs="
				+ Gaza_Power_Plant_MWs + ", Egyptian_Lines_MWs=" + Egyptian_Lines_MWs
				+ ", Total_daily_Supply_available_in_MWs=" + Total_daily_Supply_available_in_MWs
				+ ", Overall_demand_in_MWs=" + Overall_demand_in_MWs + ", Power_Cuts_hours_day_400mg="
				+ Power_Cuts_hours_day_400mg + ", Temp=" + Temp + "]";
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

}
