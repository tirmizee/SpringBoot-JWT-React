import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class GsonVsJackson {

	public static void main(String[] args) throws JsonProcessingException {
//		Gson gson = new Gson();
//		
//		long st = System.nanoTime();
//		Address address = new Address();
//		address.setCity("ssssssss");
//		address.setStreet("ddddddddd");
//		System.out.println(gson.toJson(address));
//		System.out.println(System.nanoTime() - st);
		
		
//		ObjectMapper mapper = new ObjectMapper();
//		long sts = System.nanoTime();
//		Address aaaa = new Address();
//		aaaa.setCity("ssssssss");
//		aaaa.setStreet("ddddddddd");
//		System.out.println(mapper.writeValueAsString(aaaa));
//		System.out.println(System.nanoTime() - sts);
		
		SimpleDateFormat DATE_FORMAT_DDMMYYY_HHMM = new SimpleDateFormat("ddMMyyyy HH:mm",new Locale("TH", "th"));
				System.out.println(DATE_FORMAT_DDMMYYY_HHMM.format(new Date()));
		
	}
	
	public static class Address {
		
		private String street;
		private String city;
		private int zipcode;
		
		public Address(String street, String city, int zipcode) {
			super();
			this.street = street;
			this.city = city;
			this.zipcode = zipcode;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public int getZipcode() {
			return zipcode;
		}
		public void setZipcode(int zipcode) {
			this.zipcode = zipcode;
		}
		
		@Override
		public String toString(){
			return getStreet() + ", "+getCity()+", "+getZipcode();
		}
	}

}
