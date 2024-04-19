package mr.mayatech.securedoc;

import mr.mayatech.securedoc.domain.RequestContext;
import mr.mayatech.securedoc.entity.RoleEntity;
import mr.mayatech.securedoc.enumeration.Authority;
import mr.mayatech.securedoc.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Collections;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
		return args -> {
			/*RequestContext.setUserId(0L);
			var userRole = new RoleEntity();
			userRole.setName(Authority.USER.name());
			userRole.setAuthorities(Collections.singletonList(Authority.USER));
			roleRepository.save(userRole);

			var adminRole = new RoleEntity();
			adminRole.setName(Authority.ADMIN.name());
			adminRole.setAuthorities(Collections.singletonList(Authority.ADMIN));
			roleRepository.save(userRole);

			RequestContext.start();*/
		};
	}

}

