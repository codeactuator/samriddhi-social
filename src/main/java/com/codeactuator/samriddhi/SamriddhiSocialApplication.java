package com.codeactuator.samriddhi;

import com.codeactuator.samriddhi.services.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SamriddhiSocialApplication implements CommandLineRunner {

	@Value("${data-init:false}")
	private boolean dataInit;

	@Autowired
	private MasterDataService masterDataService;

	private void init(){
		System.out.println("DataInit: " + dataInit);
		if(dataInit){
			System.out.println("Cleaning database...");
			masterDataService.deleteRelatives();
			masterDataService.deletePersons();
			masterDataService.deleteRelations();
			System.out.println("Done!");

			System.out.println("Initializing Relations...");
			masterDataService.createRelations();

			System.out.println("Initializing Persons...");
			masterDataService.createPersons();

			System.out.println("Initializing Relatives...");
			//masterDataService.createRelatives();
			System.out.println("Initialization Done!");

		}
	}


	public static void main(String[] args) {
		System.out.println("MAIN METHOD");
		SpringApplication.run(SamriddhiSocialApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		init();
	}
}
