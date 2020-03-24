package com.dzsiros.springexercise;

import com.dzsiros.springexercise.model.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringExerciseApplication {

	private static final String SINGLETON_MESSAGE_BEAN = "singletonMessage";
	private static final String PROTOTYPE_MESSAGE_BEAN = "prototypeScopeMessage";
	private static final Logger log = LoggerFactory.getLogger(SpringExerciseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringExerciseApplication.class, args);

		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(SpringExerciseApplication.class);

		Environment environment = context.getEnvironment();
		// print active profiles
		for (String profileName : environment.getActiveProfiles()) {
			log.info("Currently active profile - " + profileName);
		}

		String customProperty = environment.getProperty("foo.custom.property");
		String otherCustomProperty = environment.getProperty("foo.custom.other.property");

		log.info(customProperty);
		log.info(otherCustomProperty);

		demonstrateBeanScopes(context);
	}

	private static void demonstrateBeanScopes(AnnotationConfigApplicationContext context) {
		MessageModel singletonMessageModel = (MessageModel) context.getBean(SINGLETON_MESSAGE_BEAN);
		MessageModel prototypeMessageModel = (MessageModel) context.getBean(PROTOTYPE_MESSAGE_BEAN);

		log.info("------");

		log.info("Set singletonMessage's text");
		singletonMessageModel.setMessageText("singleton message test");

		log.info("Get the object again from the context and print its text");
		MessageModel newSingletonMessageModel = (MessageModel) context.getBean(SINGLETON_MESSAGE_BEAN);
		log.info(newSingletonMessageModel.getMessageText());

		log.info("------");

		log.info("Set prototypeMessage's text");
		prototypeMessageModel.setMessageText("singleton message test");

		log.info("Get the object again from the context and print its text");
		MessageModel newPrototypeMessageModel = (MessageModel) context.getBean(PROTOTYPE_MESSAGE_BEAN);
		log.info(newPrototypeMessageModel.getMessageText());
	}

}
