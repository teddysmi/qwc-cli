package org.qwc.cli.tool.scheduled;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.qwc.cli.tool.service.FebService;
import org.qwc.cli.tool.service.RecipientService;
import org.qwc.cli.tool.service.RecipientService.Recipient;
import org.qwc.cli.tool.service.SIService;
import org.qwc.cli.tool.service.UserService;
import org.qwc.cli.tool.service.UserService.User;
import org.qwc.cli.tool.util.Pop3MailUtil;
import org.qwc.cli.tool.util.SmtpMailUtil;
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

	@Autowired
	private RecipientService recipientService;

	// @Scheduled(cron = "0 0 9 * * *")
	@Scheduled(fixedDelay = 30000, initialDelay = 30000)
	public void scheduledDailyFetchMailTask() {

		Calendar c1 = Calendar.getInstance();

		if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			System.out.println("Not today");
			// return;
		}

		System.out.println("Start scheduled task");

		User user = userService.getUser();

		if (user == null) {
			LOGGER.error("No user credentials");
			return;
		}

		Recipient recipient = recipientService.getRecipient();

		if (recipient == null) {
			LOGGER.error("No recipient email");
			return;
		}

		Map<String, Double> dailyPPUData = new HashMap<>();
		Map<String, Double> output = new HashMap<>();
		String filePath = Pop3MailUtil.fetch(HOST, MAIL_STORE_TYPE, user.getEmail(), user.getPassword());
		System.out.println(filePath);
		if (filePath == null) {

		} else {
			dailyPPUData = TxtFileParser.parse(filePath);

			for (Entry<String, Double> map : dailyPPUData.entrySet()) {
				output.put(map.getKey(), map.getValue());
			}

			for (String msisdn : dailyPPUData.keySet()) {
				if (febService.findByMsisdn(msisdn)) {
					output.remove(msisdn);
				}
				if (siService.findByMsisdn(msisdn)) {
					output.remove(msisdn);
				}
			}
		}

		SmtpMailUtil.sendMail(user.getEmail(), user.getPassword(), recipient.getEmail(), output);

		System.out.println("End scheduled task");
	}

}
