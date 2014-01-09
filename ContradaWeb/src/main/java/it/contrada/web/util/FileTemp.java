package it.contrada.web.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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

	public static void readFileTemp(HttpServletResponse response,
			String downloadFile) {
		response.setContentType("application/force-download");

		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ "nomeFile" + "\"");
		byte[] buf = new byte[1024];
		try {

			File file = new File(downloadFile);
			long length = file.length();
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(file));
			ServletOutputStream out = response.getOutputStream();
			response.setContentLength((int) length);
			while ((in != null) && ((length = in.read(buf)) != -1)) {
				out.write(buf, 0, (int) length);
			}
			in.close();
			out.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
