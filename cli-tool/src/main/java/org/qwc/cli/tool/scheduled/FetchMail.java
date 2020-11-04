package org.qwc.cli.tool.scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.qwc.cli.tool.service.FebService;
import org.qwc.cli.tool.service.SIService;
import org.qwc.cli.tool.service.UserService;
import org.qwc.cli.tool.service.UserService.User;
import org.qwc.cli.tool.util.Pop3MailUtil;
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

	@Autowired
	private FebService febService;

	@Autowired
	private SIService siService;

	//@Scheduled(fixedDelay = 10000, initialDelay = 10000)
	public void scheduledDailyFetchMailTask() {
		System.out.println("Start");

		User user = userService.getUser();

		if (user == null) {
			LOGGER.error("No user credentials");
			return;
		}

		String filePath = Pop3MailUtil.fetch(HOST, MAIL_STORE_TYPE, user.getEmail(), user.getPassword());

		System.out.println(filePath);

		Map<String, Double> dailyPPUData = TxtFileParser.parse(filePath);
		List<String> msisdnNotFound = new ArrayList<>();

		for(String msisdn : dailyPPUData.keySet()){
			if(!febService.findByMsisdn(msisdn)){
				msisdnNotFound.add(msisdn);
			}
		}
		msisdnNotFound.removeIf(msisdn -> siService.findByMsisdn(msisdn));
		// System.out.println(x.size());

		System.out.println("End");
	}

}
