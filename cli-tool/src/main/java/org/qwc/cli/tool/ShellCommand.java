package org.qwc.cli.tool;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.qwc.cli.tool.service.UserService;
import org.qwc.cli.tool.service.UserService.User;
import org.qwc.cli.tool.service.FebService;
import org.qwc.cli.tool.service.FebService.Feb;
import org.qwc.cli.tool.service.RecipientService;
import org.qwc.cli.tool.service.RecipientService.Recipient;
import org.qwc.cli.tool.service.SIService;
import org.qwc.cli.tool.service.SIService.SI;
import org.qwc.cli.tool.util.ExcelFileParser;
import org.qwc.cli.tool.util.SmtpMailUtil;
import org.qwc.cli.tool.util.DateUtil;
import org.qwc.cli.tool.util.TxtFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommand {

	private static Logger LOGGER = LoggerFactory.getLogger(ShellCommand.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RecipientService recipientService;

	@Autowired
	private FebService febService;

	@Autowired
	private SIService siService;

	@ShellMethod(value = "Say Hello", group = "Test Commands")
	public String hello() {
		return "Hello World\n";
	}

	/*
	 * Only one email to scan is supported!
	 */
	@ShellMethod(value = "Save email credentials", group = "Email Commands")
	public String sender(String email, String password) {

		if (!EmailValidator.getInstance().isValid(email)) {
			return "Error: Invalid email!";
		}

		if (StringUtils.isBlank(password)) {
			return "Error: Password cannot be empty!";
		}

		User user = new User(email, password);
		userService.createUser(user);

		return "Successfully saved email credentials!";
	}

	/*
	 * Only one email to scan is supported!
	 */
	@ShellMethod(value = "Save recipient email", group = "Email Commands")
	public String recipient(String email) {

		if (!EmailValidator.getInstance().isValid(email)) {
			return "Error: Invalid email!";
		}

		Recipient recipient = new Recipient(email);
		recipientService.createRecipient(recipient);

		return "Successfully saved recipient email!";
	}

	@ShellMethod(value = "[Testing] Parse text file", group = "Parser Commands")
	public String txt(String path) {

		System.out.println(path);

		File file = new File(path);
		if (file.exists()) {

			TxtFileParser.parse(path);

		} else {
			LOGGER.error("File does not exist");
			return "Invalid text file format";
		}

		return "Text file parser is OK!";
	}
	/*
	 * txt "C:/\Users/\ekxz900/\Desktop/\Daily_PPU_20201015 project.txt" txt
	 * "C:/Users/chown/Downloads/Parse/Daily_PPU_20201015 project.txt"
	 *
	 * save johndoe.dev99@gmail.com Password0@
	 *
	 * load-db
	 * "C:/Users/chown/IdeaProjects/parsetTest/src/main/resources/test2.xlsx"
	 */

	@ShellMethod(value = "Parse excel and load to DB", group = "Parser Commands")
	public String excel(String path) {

		System.out.println(path);

		File file = new File(path);
		if (file.exists()) {
			List<Feb> febList = ExcelFileParser.parseFeb(path);
			if (febList.isEmpty()) {
				LOGGER.error("could not load file");
				return "Could not load file";
			}
			febService.createFeb(febList);

			List<SI> siList = ExcelFileParser.parseSI(path);

			siService.createSI(siList);

		} else {
			LOGGER.error("File does not exist");
			return "Invalid file path";
		}

		return "Successfully loaded into database";
	}

	@ShellMethod(value = "[Testing] Send email", group = "Mail Commands")
	public String email() {

		User user = userService.getUser();

		if (user == null) {
			return "Error: Set email credentials first!";
		}

		Recipient recipient = recipientService.getRecipient();

		if (recipient == null) {
			return "Error: Set recipient email first!";
		}

		Map<String, Double> map = new HashMap<>();

		SmtpMailUtil.sendMail(user.getEmail(), user.getPassword(), recipient.getEmail(), map);

		map.put("12345678", 12151.2);

		SmtpMailUtil.sendMail(user.getEmail(), user.getPassword(), recipient.getEmail(), map);

		return "success";
	}

	@ShellMethod(value = "Get parameters", group = "Check Commands")
	public String get() {

		String settings = "";

		User user = userService.getUser();

		settings += "Sender: ";
		if (user != null) {
			settings += user.getEmail();
		}

		settings += "\nPassword: ";

		if (user != null) {
			settings += user.getPassword();
		}

		Recipient recipient = recipientService.getRecipient();

		settings += "\nRecipient: ";
		if (recipient != null) {
			settings += recipient.getEmail() + "\n";
		}

		return settings;
	}

	@ShellMethod(value = "Get current YYYYMMDD", group = "Check Commands")
	public String date() {

		return DateUtil.getCurrentDate();
	}

	@ShellMethod(value = "get Path", group = "Check Commands")
	public String path() {
		return new File("").getAbsolutePath();

	}
}
