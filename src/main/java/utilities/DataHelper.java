package utilities;

import java.util.Locale;

import com.github.javafaker.Faker;


public class DataHelper {
	private Locale local = new Locale("en");
	private Faker faker = new Faker(local);
	
	public static DataHelper getDataHelper() {
		return new DataHelper();
	}
	
	public String getFirstName() {
		return faker.address().firstName().replace("'", "");
	}
	
	public String getLastName() {
		return faker.address().lastName().replace("'", "");
	}
	
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getCityName() {
		return faker.address().city();
	}
	
	public String getState() {
		return faker.address().state();
	}
	
	public String getCountry() {
		return faker.address().country();
	}
	
	public String getPhoneNumber() {
		return faker.phoneNumber().cellPhone();
	}
	
	public String getPortalCode() {
		return faker.address().zipCode();
	}
	public String getAddress() {
		return faker.address().streetAddress();
	}

	public String getPassword() {
		return faker.internet().password(8, 12, false, false);
	}
	
	public String getPinNumber() {
		return String.valueOf(faker.number().numberBetween(100000, 999999));
	}
	
}
