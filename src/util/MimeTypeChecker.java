package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * MimeTypeChecker
 * 
 * Class for checking the type of a given file according to its MIME type.
 * Checked using the Java files.probeContentType functionality.
 * @author Patrick
 */
public class MimeTypeChecker {

	/*
	 * CONSTANTS
	 */
	public static final String MIME_TEXT = "text/plain";
	

	/*
	 * INTERFACE METHODS
	 */
	
	/**
	 * isTextFile
	 * 
	 * Checks if a given file is a valid text file based on a mime type check using the Java files.probeContentType functionality.
	 * @param File file to check file type for
	 * @return boolean verdict of check
	 */
	public static boolean isTextFile(File file) {
		if (file == null) {
			return false;
		}
		
		String mimeType = null;
		
		try {
			mimeType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mimeType != null && mimeType.equals(MIME_TEXT);
	}
	
}
