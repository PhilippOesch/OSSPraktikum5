package de.hfu.residents.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentService;

public class ResidentRepositoryStub implements ResidentRepository {
	List<Resident> residents;

	public ResidentRepositoryStub(){
//		Resident resident1= new Resident("Max", "Mustermann", "Beispielstraße", "Bielefeld", null);
//		Resident resident2= new Resident("Philipp", "Oeschger", "Bregstraße", "Furtwangen", null);
//		Resident resident3= new Resident("Test", "Test", "Test", "Test", null);
//		Resident resident4= new Resident("Donald", "Trump", "Pennsylvania Avenuet", "Washington, D.C.", null);
//		this.residents= Arrays.asList(resident1, resident2, resident3, resident4);
		
		this.residents= new ArrayList<Resident>();
	}
	
	@Override
	public List<Resident> getResidents() {
		return this.residents;
	}
	
	public void addToRep(Resident residents) {
		this.residents.add(residents);
	}

}
