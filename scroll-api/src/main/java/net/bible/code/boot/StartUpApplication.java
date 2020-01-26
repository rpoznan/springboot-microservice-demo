package net.bible.code.boot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"net.bible.code.scroll.controller",
		"net.bible.code.servicelayer.pojo",
		"net.bible.code.servicelayer.services",
		"net.bible.code.scroll.configuration"})
@EnableAutoConfiguration
@EnableJpaRepositories("net.bible.code")
public class StartUpApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<StartUpApplication> applicationClass = StartUpApplication.class;
}

