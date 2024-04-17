package application;

public class Years {
	private int year;
	private MonthsAVL monthsAVL = new MonthsAVL();

	public Years() {
		super();
	}
	
	public Years(int year) {
		super();
		this.year = year;
	}

	public Years(int year, MonthsAVL monthsAVL) {
		super();
		this.year = year;
		this.monthsAVL = monthsAVL;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public MonthsAVL getMonthsAVL() {
		return monthsAVL;
	}

	public void setMonthsAVL(MonthsAVL monthList) {
		this.monthsAVL = monthList;
	}

	@Override
	public String toString() {
		return "Years [year=" + year + ", monthsAVL=" + monthsAVL + "]";
	}

	
	
	

}
