package it.contrada.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTemp {

	public static File writeFile(String content, String nomeFile,
			String extension) throws IOException {
		FileWriter fw = null;
		try {

			// create a temporary file in that directory
			File tempFile = File.createTempFile(nomeFile, extension);

			// write to file
			fw = new FileWriter(tempFile);

			fw.write(content);
			return tempFile;
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

}
