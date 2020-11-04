package org.qwc.cli.tool;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.qwc.cli.tool.scheduled.FetchMail;
import org.qwc.cli.tool.service.UserService;
import org.qwc.cli.tool.service.UserService.User;
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

	@ShellMethod(value = "Add two integers together.", group = "Mathematical Commands")
	public int add(int a, int b) {
		return a + b;
	}

	@ShellMethod(value = "Say Hello", group = "String Commands")
	public String hello() {
		return "Hello World\n";
	}

	/*
	 * Only one email to scan is supported!
	 */
	@ShellMethod(value = "Save email credentials", group = "Credential Commands")
	public String save(String email, String password) {

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

	@ShellMethod(value = "Parse text file", group = "Parser Commands")
	public String txt(String path) {

		System.out.println(path);

		File file = new File(path);
		if (file.exists()) {

			Map<String, Double> x = TxtFileParser.parse(path);

		} else {
			LOGGER.error("File does not exist");
			return "Invalid text file format";
		}

		return "Text file parser is OK!";
	}
	/*
	 * txt "C:/\Users/\ekxz900/\Desktop/\Daily_PPU_20201015 project.txt"
	 * 
	 * save johndoe.dev99@gmail.com Password0@
	 */

	@ShellMethod(value = "Parse excel and load to DB", group = "Parser Commands")
	public String loadDb(String path) {

		System.out.println(path);

		File file = new File(path);
		if (file.exists()) {

		}

		return "Successfully saved email credentials!";
	}

	@ShellMethod(value = "Get current YYYYMMDD", group = "Date Commands")
	public String date() {

		return DateUtil.getCurrentDate();
	}
}
