package com.dzsiros.springexercise;

import com.dzsiros.springexercise.model.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringExerciseApplication {

	private static final String SINGLETON_MESSAGE_BEAN = "singletonMessage";
	private static final String PROTOTYPE_MESSAGE_BEAN = "prototypeScopeMessage";

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

		demonstrateBeanScopes(context);
	}

	private static void demonstrateBeanScopes(AnnotationConfigApplicationContext context) {
		Message singletonMessage = (Message) context.getBean(SINGLETON_MESSAGE_BEAN);
		Message prototypeMessage = (Message) context.getBean(PROTOTYPE_MESSAGE_BEAN);

		System.out.println("------");

		System.out.println("Set singletonMessage's text");
		singletonMessage.setMessageText("singleton message test");

		System.out.println("Get the object again from the context and print its text");
		Message newSingletonMessage = (Message) context.getBean(SINGLETON_MESSAGE_BEAN);
		System.out.println(newSingletonMessage.getMessageText());

		System.out.println("------");

		System.out.println("Set prototypeMessage's text");
		prototypeMessage.setMessageText("singleton message test");

		System.out.println("Get the object again from the context and print its text");
		Message newPrototypeMessage = (Message) context.getBean(PROTOTYPE_MESSAGE_BEAN);
		System.out.println(newPrototypeMessage.getMessageText());
	}

}
