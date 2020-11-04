package org.qwc.cli.tool.scheduled;

import java.util.Map;

import org.qwc.cli.tool.service.UserService;
import org.qwc.cli.tool.service.UserService.User;
import org.qwc.cli.tool.util.MailUtil;
import org.qwc.cli.tool.util.TxtFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FetchMail {

	private static Logger LOGGER = LoggerFactory.getLogger(FetchMail.class);

	private static final String HOST = "pop.gmail.com";

	private static final String MAIL_STORE_TYPE = "pop3";

	@Autowired
	private UserService userService;

	@Scheduled(fixedDelay = 10000, initialDelay = 10000)
	public void scheduledDailyFetchMailTask() {
		System.out.println("Start");

		User user = userService.getUser();

		if (user == null) {
			LOGGER.error("No user credentials");
			return;
		}

		String filePath = MailUtil.fetch(HOST, MAIL_STORE_TYPE, user.getEmail(), user.getPassword());

		System.out.println(filePath);

		Map<String, Double> x = TxtFileParser.parse(filePath);
		x.entrySet().forEach(System.out::println);
		// System.out.println(x.size());

		System.out.println("End");
	}

}
