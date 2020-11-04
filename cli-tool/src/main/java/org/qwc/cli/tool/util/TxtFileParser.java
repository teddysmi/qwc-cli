package org.qwc.cli.tool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TxtFileParser {

	public static Map<String, Double> parse(String filename) {

		Map<String, Double> map = new HashMap<>();
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filename));

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				if (line.isEmpty() || line.contains("MSISDN") || line.contains("USAGE") || line.startsWith("-")) {

					continue;
				}
				String[] subString = line.split("\t");

				map.put(subString[0], Double.valueOf(subString[1]));

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return map;
	}

}
