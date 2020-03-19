package com.dzsiros.springexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExerciseApplication.class, args);

		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(SpringExerciseApplication.class);

		Environment environment = context.getEnvironment();
		// print active profiles
		for (String profileName : environment.getActiveProfiles()) {
			System.out.println("Currently active profile - " + profileName);
		}

		String customProperty = environment.getProperty("foo.custom.property");
		String otherCustomProperty = environment.getProperty("foo.custom.other.property");

		System.out.println(customProperty);
		System.out.println(otherCustomProperty);
	}

}
