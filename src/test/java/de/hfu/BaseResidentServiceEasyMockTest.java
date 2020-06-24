package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*; 
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class BaseResidentServiceEasyMockTest {
	private List<Resident> residentsList;
	private BaseResidentService baseResidentService;
	private ResidentRepository residentRep;
	
	@Before
	public void setupTest() {
		Resident resident1= new Resident("Philipp", "Oeschger", "Bregstraße", "Furtwangen", null);
		Resident resident2= new Resident("Max", "Mustermann", "Beispielstraße", "Bielefeld", null);
		Resident resident3= new Resident("Test", "Test", "Test", "Test", null);
		Resident resident4= new Resident("Donald", "Trump", "Pennsylvania Avenuet", "Washington, D.C.", null);
		
		residentsList= Arrays.asList(resident1, resident2, resident3, resident4);
		
		residentRep= createMock(ResidentRepository.class);
		baseResidentService= new BaseResidentService();
		
	}
	
	@Test
	public void test() throws ResidentServiceException{
		Resident residentfilter= new Resident("Philipp", "Oeschger", "Bregstraße", "Furtwangen", null);
		
		expect(residentRep.getResidents()).andReturn(residentsList).times(3);
		replay(residentRep);
		
		baseResidentService.setResidentRepository(residentRep);
		
		String expectedName= "Philipp";
		String expectedFamilyName= "Oeschger";
		String expectedCity= "Furtwangen";
		
		assertThat(baseResidentService.getUniqueResident(residentfilter).getGivenName(), equalTo(expectedName));
		assertThat(baseResidentService.getUniqueResident(residentfilter).getFamilyName(), equalTo(expectedFamilyName));
		assertThat(baseResidentService.getUniqueResident(residentfilter).getCity(), equalTo(expectedCity));
	}
}
