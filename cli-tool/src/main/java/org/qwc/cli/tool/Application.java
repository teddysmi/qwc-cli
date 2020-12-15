package org.qwc.cli.tool;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.shell.jline.PromptProvider;

@EnableJpaRepositories
@EnableScheduling
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		System.out.println("Hello World");

		List<String> lst = new ArrayList<>();
		lst.sort(List.de);

		String x = "hello";
		x.subString(0, 5);

		System.out.println("Hello World");
		SpringApplication.run(Application.class, args);
	}

	public Object hello(){

	}

	@Bean
	public PromptProvider myPromptProvider() {
		return () -> new AttributedString("cli-tool:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
	}
}

public class x extends Application{

	public Object hello(){

	}

}
