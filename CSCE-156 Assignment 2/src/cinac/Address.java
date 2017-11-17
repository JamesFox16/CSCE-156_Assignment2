package cinac;


public class Address {

		private String street;
		private String city;
		private String state;
		private String zip;
		private String country;
		
		public Address(String street, String city, String state, String zip, String country) {
			super();
			this.street = street;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.country = country;
		}
		//Getter/Setter for street
		public String getStreet() {
			return this.street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		
		//Getter/Setter for city
		public String getCity() {
			return this.city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
		//Getter/Setter for state
		public String getState() {
			return this.state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		//Getter/Setter for ZIP
		public String getZIP(){
			return this.zip;
		}
		public void setZIP(String zip) {
			this.zip = zip;
		}
		
		//Getter/Setter for country
		public String getCountry() {
			return this.country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		
		public String toString() {
			String toReturn;
			toReturn = (getStreet()+ "\n"+ getCity()+", "+ getState()+ " "+ getZIP()+ " "+ getCountry());
			
			return toReturn;
			
		}
}
