import java.io.IOException;

public class MovieTheater {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the cinema");
		CustomerCreator cc = new CustomerCreator();
		System.out.println(cc.createCustomerList());
		cc.createPersonList();
	}

}
