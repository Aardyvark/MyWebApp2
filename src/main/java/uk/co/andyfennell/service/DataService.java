package uk.co.andyfennell.service;

import uk.co.andyfennell.model.Country;

public interface DataService {
	
	public String getMessage();
	
	public String[] getCountries();
	
	public Country[] getCountryList();

}
