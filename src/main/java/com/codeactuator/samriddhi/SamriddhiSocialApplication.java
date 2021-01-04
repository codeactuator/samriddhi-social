package com.codeactuator.samriddhi;

import com.codeactuator.samriddhi.services.RelationService;
import com.codeactuator.samriddhi.util.RelationUtil;
import org.bouncycastle.util.test.TestRandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SamriddhiSocialApplication implements ApplicationRunner {


	public static void main(String[] args) {
		System.out.println("MAIN METHOD");
		SpringApplication.run(SamriddhiSocialApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("RUN METHOD!");
	}
}
