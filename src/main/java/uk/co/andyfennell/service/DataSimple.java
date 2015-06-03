package uk.co.andyfennell.service;

import uk.co.andyfennell.model.Country;

public class DataSimple implements DataService {
	
	public String getMessage() {
		return "Hello there";
	}

	public String[] getCountries() {
		String[] countries = new String[10];
		
		countries[0] = "England";
		countries[1] = "Scotland";
		countries[2] = "Wales";
		
		return countries;
	}
	
	public Country[] getCountryList() {
		Country[] countryList = new Country[10];
		Country country = new Country();
		country.setCode("E");
		country.setName("England");
		countryList[0] = country;

		country = new Country();
		country.setCode("S");
		country.setName("Scotland");
		countryList[1] = country;
		
		country = new Country();
		country.setCode("W");
		country.setName("Wales");
		countryList[2] = country;
		
		return countryList;
	}
}
