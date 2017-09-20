
public class Product {

	private String productCode;
	private String productType;
	
	//Getter/Setter for productCode
	public String getProductCode() {
		return this.productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	//Getter/Setter for productType
	public String getProductType() {
		return this.productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}

	public class MovieTicket extends Product{
		private String dateTime;
		private String movieName;
		private Address address; //of the movie theater
		private int screenNumber;
		private double unitPrice;
		
		//Getter/Setter for dateTime
		public String getDateTime() {
			return this.dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		
		//Getter/Setter for movieName
		public String getMovieName() {
			return this.movieName;
		}
		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}
		
		//Getter/Setter for address
		public Address getAddress() {
			return this.address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		
		//Getter/Setter for screenNumber
		public int getScreenNumber() {
			return this.screenNumber;
		}
		public void setScreenNumber(int screenNumber) {
			this.screenNumber = screenNumber;
		}
		
		//Getter/Setter for unitPrice
		public double getUnitPrice() {
			return this.unitPrice;
		}
		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}
		
	}
	
	public class SeasonPass extends Product{
		private String startDate;
		private String endDate;
		
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
	
	public class ParkingPass extends Product{
		private double parkingFee;

		//Getter/Setter for parkingFee
		public double getParkingFee() {
			return this.parkingFee;
		}
		public void setParkingFee(double parkingFee) {
			this.parkingFee = parkingFee;
		}
		
	}
	
	public class Refreshments extends Product{
		private String name;
		private double cost;
		
		//Getter/Setter for name
		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		//Getter/Setter for cost
		public double getCost() {
			return this.cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
	}
	
}
