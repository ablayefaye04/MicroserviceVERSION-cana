package uasz.sn.microservice_repartition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;*/
import uasz.sn.microservice_repartition.model.Enseignant;
import uasz.sn.microservice_repartition.model.Enseignement;
import uasz.sn.microservice_repartition.service.EnseignantService;
import uasz.sn.microservice_repartition.service.EnseignementService;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableScheduling
public class MicroserviceRepartitionApplication implements CommandLineRunner {
	@Autowired
	private EnseignementService enseignementService;
	@Autowired
	private EnseignantService enseignantService;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRepartitionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*enseignementService.saveAll();
		for(int i=0;i<5;i++){
			enseignantService.save(new Enseignant());
			//enseignementService.save(new Enseignement());
		}
*/
		Enseignant enseignant1 = new Enseignant();
		enseignant1.setNom("FAYE");
		enseignant1.setPrenom("coumba");

		Enseignant enseignant2 = new Enseignant();
		enseignant2.setNom("GUEYE");
		enseignant2.setPrenom("Baltazar");
		enseignantService.save(enseignant1);
		enseignantService.save(enseignant2);


		Enseignement enseignement1 = new Enseignement();
		enseignement1.setNom("MATH");
		enseignement1.setFormation("L2i");
		enseignement1.setNiveau(3);
		enseignement1.setSemestre(6);
		enseignementService.save(enseignement1);

		Enseignement enseignement2 = new Enseignement();
		enseignement2.setNom("PC");
		enseignement2.setFormation("Physique");
		enseignement2.setNiveau(3);
		enseignement2.setSemestre(4);
		enseignementService.save(enseignement2);



	}
}
