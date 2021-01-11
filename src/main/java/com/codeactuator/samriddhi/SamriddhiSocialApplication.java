package com.codeactuator.samriddhi;

import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.services.MasterDataService;
import com.codeactuator.samriddhi.services.RelationService;
import com.codeactuator.samriddhi.util.RelationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SamriddhiSocialApplication implements CommandLineRunner {

	@Value("${data-init:false}")
	private boolean dataInit;

	@Autowired
	private MasterDataService masterDataService;

	@Autowired
	private RelationService relationService;

	private void init(){
		System.out.println("DataInit: " + dataInit);
		if(dataInit){
			System.out.println("Cleaning database...");
			//masterDataService.deletePersons();
			//masterDataService.deleteRelations();
			//masterDataService.deleteRelatives();

			System.out.println("Done!");

			System.out.println("Initializing Relations...");
			masterDataService.createRelations();

			System.out.println("Initializing Persons...");
			masterDataService.createPersons();

			System.out.println("Initializing Relatives...");
			masterDataService.createRelatives();
			System.out.println("Initialization Done!");
		}

		Map<Relation, String> relations = new HashMap();
		relationService.findAll().get()
				.forEach(relationDTO -> {
					//Setting Id as 0 is important here, we are keeping this relationship definition in a Hashmap
					//and when we will calculate a new relationship that would not have any id then how it will
					//find the newly created relationship in relations HashMap.
					Relation relation = relationDTO.marshall();
					relation.setId(0);
					relation.setName(null); //hascode and equal calculation does not includes id and name.
					relations.put(relation, relationDTO.getName());
				});


		RelationUtil relationUtil = RelationUtil.getInstance();
		relationUtil.setRelations(relations);
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
