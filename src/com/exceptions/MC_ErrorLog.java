package com.exceptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class MC_ErrorLog {
	public MC_ErrorLog(String s, Exception e) {

		try {
			String dir = new File(".").getCanonicalPath();
			BufferedWriter writer = new BufferedWriter(new FileWriter(dir + "\\logs\\error_log.txt", true));

			LocalDateTime date = LocalDateTime.now();
			
			writer.append(s + " => " + e + " [ " + date + " ]\n\n");
			writer.close();
		} catch (IOException error) {
			// TODO Auto-generated catch block
			System.out.println(error);
			error.printStackTrace();
		}

	}

}
