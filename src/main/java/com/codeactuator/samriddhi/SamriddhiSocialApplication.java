package com.codeactuator.samriddhi;

import com.codeactuator.samriddhi.constants.Relations;
import com.codeactuator.samriddhi.domain.Relation;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.services.RelationService;
import com.codeactuator.samriddhi.util.RelationUtil;
import org.bouncycastle.util.test.TestRandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SamriddhiSocialApplication implements ApplicationRunner {

	@Autowired
	private RelationService relationService;

	private void saveRelation(){
		List<RelationDTO> relationDTOS = Arrays.asList(
				new RelationDTO(0, 0, 1, true, false, true, Relations.ME.getRelation()),
				new RelationDTO(0, 1, 1, true, false, true, Relations.FATHER.getRelation()),
				new RelationDTO(0, 1, 0, false, false, true, Relations.MOTHER.getRelation()),

				new RelationDTO(1, 1, 1, true, false, false, Relations.UNCLE.getRelation()),
				new RelationDTO(1, 1, 0, false, false, false, Relations.AUNTY.getRelation()),

				new RelationDTO(0, 1, 1, true, true, true, Relations.FATHER_IN_LAW.getRelation()),
				new RelationDTO(0, 1, 0, false, true, true, Relations.MOTHER_IN_LAW.getRelation()),

				new RelationDTO(1, 1, 1, true, true, false, Relations.FATHER_IN_LAW_NON_SIBLING.getRelation()),
				new RelationDTO(1, 1, 0, false, true, false, Relations.MOTHER_IN_LAW_NON_SIBLING.getRelation()),

				new RelationDTO(1, 0, 1, true, false, true, Relations.BROTHER.getRelation()),
				new RelationDTO(1, 0, 1, true, false, false, Relations.BROTHER_NON_SIBLING.getRelation()),
				new RelationDTO(1, 0, 0, false, true, true, Relations.BROTHERS_WIFE.getRelation()),
				new RelationDTO(1, 0, 0, false, true, false, Relations.BROTHERS_WIFE_NON_SIBLING.getRelation()),

				new RelationDTO(1, 0, 0, true, false, true, Relations.SISTER.getRelation()),
				new RelationDTO(1, 0, 0, true, false, false, Relations.SISTER_NON_SIBLING.getRelation()),
				new RelationDTO(1, 0, 1, false, true, true, Relations.SISTERS_HUSBAND.getRelation()),
				new RelationDTO(1, 0, 1, false, true, false, Relations.SISTERS_HUSBAND_NON_SIBLING.getRelation()),

				new RelationDTO(0, 0, 0, false, true, true, Relations.WIFE.getRelation()),
				new RelationDTO(0, 0, 1, true, true, true, Relations.HUSBAND.getRelation()),

				new RelationDTO(1, 0, 1, true, true, true, Relations.BROTHER_IN_LAW.getRelation()),
				new RelationDTO(1, 0, 0, true, true, true, Relations.SISTER_IN_LAW.getRelation()),

				new RelationDTO(1, 0, 1, true, true, false, Relations.BROTHER_IN_LAW_NON_SIBLING.getRelation()),
				new RelationDTO(1, 0, 0, true, true, false, Relations.SISTER_IN_LAW_NON_SIBLING.getRelation()),

				new RelationDTO(0, -1, 1, true, false, true, Relations.SON.getRelation()),
				new RelationDTO(0, -1, 0, true, false, true, Relations.DOUGHTER.getRelation())
		);

		relationService.saveAll(relationDTOS)
				.ifPresent(relationDTOS1 -> {
					relationDTOS1.forEach(System.out::print);
				});
	}


	public static void main(String[] args) {
		System.out.println("MAIN METHOD");
		SpringApplication.run(SamriddhiSocialApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("RUN METHOD!");
		//saveRelation();
	}
}
