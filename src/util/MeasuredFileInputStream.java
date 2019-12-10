package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author Patrick
 * Idea from https://stackoverflow.com/questions/2399943/how-to-obtain-position-in-file-byte-position-from-java-scanner/2820497#2820497
 */

public class MeasuredFileInputStream extends FileInputStream {

	public MeasuredFileInputStream(File file) throws FileNotFoundException {
		super(file);
	}

	private final int [] aiPos = new int [1];
   
	@Override
	public int read() throws IOException {
		aiPos[0]++;
		return super.read();
	}
   
	@Override
	public int read( byte [] b ) throws IOException {
		int iN = super.read( b );
		aiPos[0] += iN;
		return iN;
	}
	
	@Override
	public int read( byte [] b, int off, int len ) throws IOException {
		int iN = super.read( b, off, len );
		aiPos[0] += iN;
		return iN;
	}
	
	/**
	 * getFilePosition
	 * 
	 * @return number of bytes already read in the stream.
	 */
	public int getFilePosition() {
		return aiPos[0];
	}
	
}
