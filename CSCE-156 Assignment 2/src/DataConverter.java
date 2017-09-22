import java.io.IOException;
import java.util.List;

public class DataConverter {
	
	public static void main(String[] args) throws IOException {
		
		// Creating FlatFileReader object
		FlatFileReader ffr = new FlatFileReader();
		
		// Storing the lists that were created with FlatFileReader
		List<Person> per = ffr.createPersonList();
		List<Customer> c = ffr.createCustomerList();
		List<Product> pro = ffr.createProductList();

		// Writing to .xml
		XMLWriter xml = new XMLWriter();
		xml.xmlConverterCustomer(c);
		xml.xmlConverterPerson(per);
		xml.xmlConvertProducts(pro);
		
		// Writing to .json
		JsonWriter json = new JsonWriter();
		json.jsonConverterCustomer(c);
		json.jsonConverterPerson(per);
		json.jsonConverterProduct(pro);
		
	}

}
