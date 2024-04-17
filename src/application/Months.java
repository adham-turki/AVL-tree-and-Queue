package application;

public class Months {
	private int month;
	private DaysAVL daysAVL = new DaysAVL();
	
	public Months() {
		super();
	}
	
	public Months(int month) {
		super();
		this.month = month;
	}

	public Months(int month, DaysAVL daysAVL) {
		super();
		this.month = month;
		this.daysAVL = daysAVL;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public DaysAVL getDaysAVL() {
		return daysAVL;
	}

	public void setDaysAVL(DaysAVL daysAVL) {
		this.daysAVL = daysAVL;
	}

	@Override
	public String toString() {
		return " [month=" + month + ", dayList=" + daysAVL + "]";
	}
	
	

}
