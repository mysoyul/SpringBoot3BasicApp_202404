package com.basic.myspringboot;

import com.basic.myspringboot.property.MybootProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.function.Consumer;

@SpringBootApplication
public class BasicSpringBootAppApplication {

	public static void main(String[] args) {

		//SpringApplication.run(BasicSpringBootAppApplication.class, args);
		SpringApplication springApplication = new SpringApplication(BasicSpringBootAppApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.SERVLET);
		springApplication.run(args);
	} //main

	@Bean
	public ApplicationRunner applicationRunner() {
		//1. Anonymous Inner class
		return new ApplicationRunner() {
			@Autowired
			private MybootProperty property;

			@Override
			public void run(ApplicationArguments args) throws Exception {
				System.out.println("1. Anonymous Inner class 형태");
				System.out.println("MybootProperty 객체의 환경변수 getName() "  + property.getFullName());
				System.out.println("MybootProperty 객체의 환경변수 getAge() "  + property.getAge());

				args.getOptionNames().forEach(new Consumer<String>() {
					@Override
					public void accept(String name) {
						System.out.println("argument name = " + name);
					}
				});
			}
		};
	}

	@Bean
	public ApplicationRunner applicationRunnerLambda() {
		//2. Lambda Expression (람다식)
		return args -> {
			System.out.println("3. Lambda Expression 형태");
			args.getOptionNames().forEach(name -> System.out.println("Option argument name = " + name));
		};
	}


}
