package de.hfu;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class ResidentServiceTest {
	private ResidentRepositoryStub residentRep;
	private BaseResidentService residentService;
	private Calendar date;
	
	@Before
	public void createQueue() {
		date= Calendar.getInstance();
		date.set(1990, 10, 10);
		Resident resident1= new Resident("Max", "Mustermann", "Beispielstraße", "Bielefeld", new Date(date.getTimeInMillis()));
		Resident resident2= new Resident("Philipp", "Oeschger", "Bregstraße", "Furtwangen", new Date(date.getTimeInMillis()));
		Resident resident3= new Resident("Test", "Test", "Test", "Test", new Date(date.getTimeInMillis()));
		Resident resident4= new Resident("Donald", "Trump", "Pennsylvania Avenuet", "Washington, D.C.", new Date(date.getTimeInMillis()));
		
		this.residentRep= new ResidentRepositoryStub();
		this.residentRep.addToRep(resident1);
		this.residentRep.addToRep(resident2);
		this.residentRep.addToRep(resident3);
		this.residentRep.addToRep(resident4);
		this.residentService= new BaseResidentService();
		this.residentService.setResidentRepository(this.residentRep);
	} 
	
	//Testen eines Filters mit Wildcards
	@Test
	public void test1(){
		Resident filterresident= new Resident("Philipp", null, "*", "*", new Date(date.getTimeInMillis()));
		Resident filterresident2= new Resident("Philipp", "Oeschger", "*", "Furtwangen", new Date(date.getTimeInMillis()));
		Resident filterresident3= new Resident("Philipp", "Oeschger", "Bregstraße", "*", new Date(date.getTimeInMillis()));
		String expectedFamilyName= "Oeschger";
		String expectedName2= "Philipp";
		String expectedCit3= "Furtwangen";
		
		assertEquals(expectedFamilyName, this.residentService.getFilteredResidentsList(filterresident).get(0).getFamilyName());
		assertEquals(expectedName2, this.residentService.getFilteredResidentsList(filterresident2).get(0).getGivenName());
		assertEquals(expectedCit3, this.residentService.getFilteredResidentsList(filterresident3).get(0).getCity());
	}
	
	//Testen eines Filters mit Wildcard und mehr als einem Ergebnis
	@Test
	public void test2(){
		Resident filterresident1= new Resident("*", "T*", "*", "*", new Date(date.getTimeInMillis()));
		Resident filterresident2= new Resident("*", "*", "*", "*", new Date(date.getTimeInMillis()));
		int expectedsize1= 2;
		int expectedsize2= 4;
		
		assertEquals(expectedsize1, this.residentService.getFilteredResidentsList(filterresident1).size());
		assertEquals(expectedsize2, this.residentService.getFilteredResidentsList(filterresident2).size());
	}
	
	//Testen eines nicht vorkommenden Filters
	@Test
	public void test3(){
		Resident filterresident= new Resident("Döner", "*", "*", "*", new Date(date.getTimeInMillis()));
		Resident filterresident2= new Resident("Philipp", "Oeschger", "Furtwangen", "Furtwangen", new Date(date.getTimeInMillis()));
		int expectedsize= 0;
		
		assertEquals(expectedsize, this.residentService.getFilteredResidentsList(filterresident).size());
		assertEquals(expectedsize, this.residentService.getFilteredResidentsList(filterresident2).size());
	}
	
	//Rückgabe eines Eindeutigen Ergebnisses
	@Test
	public void test4() throws ResidentServiceException{
		Resident filterresident= new Resident("Max", "Mustermann", "Beispielstraße", "Bielefeld", new Date(date.getTimeInMillis()));
		String expectedCity= "Bielefeld";
		
		assertEquals(expectedCity, this.residentService.getUniqueResident(filterresident).getCity());
	}
	
	//Nicht erlauben von Wildcards
	@Test(expected=ResidentServiceException.class, timeout=1000) 
	public void test5() throws ResidentServiceException{
		Resident filterresident= new Resident("Max", "Mustermann", "*", "Bielefeld", new Date(2000000));
		Resident filterresident2= new Resident("Max", "Mustermann", "*", "Bielefeld*", new Date(2000000));
		String expectedCity= "Bielefeld";
		
		this.residentService.getUniqueResident(filterresident);
		this.residentService.getUniqueResident(filterresident2);
	}
	
	//kein eindeutiges Ergebnis
	@Test(expected=ResidentServiceException.class, timeout=1000) 
	public void test6() throws ResidentServiceException {
		Resident filterresident= new Resident("Max", "Muster", "Beispielstraße", "Bielefeld", new Date(110000));
		
		this.residentService.getUniqueResident(filterresident);
	}
}
