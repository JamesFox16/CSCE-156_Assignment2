
public class SeasonPass extends Product{
	private String passName;
	private String startDate;
	private String endDate;
	
	public SeasonPass(String productCode, String productType, String passName, String startDate, String endDate, double unitPrice) {
		super(productCode, productType, unitPrice);
		this.passName = passName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getPassName() {
		return this.getPassName();
	}
	
	//Getter/Setter for startDate
	public String getStartDate() {
		return this.startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	//Getter/Setter for endDate
	public String getEndDate() {
		return this.endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}