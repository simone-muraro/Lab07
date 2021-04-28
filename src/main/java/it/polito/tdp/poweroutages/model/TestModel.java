package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		
		LocalDateTime o1= LocalDateTime.of(2019, 05, 20, 8, 30);	

		LocalDateTime o2=LocalDateTime.of(2019, 05, 20, 10, 05);
		PowerOutage p1=new PowerOutage(1, 10, 5,o1, o2);
		System.out.println(p1);
		//System.out.println(p1.calcolaOre());

	}

}
