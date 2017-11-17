package cinac;


public class MovieTicket extends Product{
	private String dateTime;
	private String movieName;
	private Address address; //of the movie theater
	private String screenNumber;
	
		
	public MovieTicket(String productCode, String productType, String dateTime, String movieName,
			Address address, String screenNumber, double unitPrice) {
		super(productCode, productType, unitPrice, movieName);
		this.dateTime = dateTime;
		this.movieName = movieName;
		this.address = address;
		this.screenNumber = screenNumber;	
	}
	
	public double computeTax() {
		return this.getProductPrice() * 0.06;
	}
		
	//Getter/Setter for dateTime
	public String getDateTime() {
		return this.dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	//Getter/Setter for movieName
	public String getName() {
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
	public String getScreenNumber() {
		return this.screenNumber;
	}
	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}
		

		
}