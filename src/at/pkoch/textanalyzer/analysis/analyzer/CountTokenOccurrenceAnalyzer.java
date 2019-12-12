package at.pkoch.textanalyzer.analysis.analyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import at.pkoch.textanalyzer.analysis.AnalysisResultType;
import at.pkoch.textanalyzer.util.MeasuredFileInputStream;

/**
 * CountTokenOccurrenceAnalyzer
 * 
 * Class that models models a token count analysis of a given text file.
 * The file is tokenized along white spaces, and the count of distinct tokens is recorded.
 * After successful analysis, the event callback returns an object of the type Map<String, Integer>, containing an unordered set of terms as keys and the frequency of those terms as values
 * @author Patrick
 */
public class CountTokenOccurrenceAnalyzer extends AbstractFileAnalyzer {

	/**
	 * isEligible
	 * 
	 * In this scenario, given text files are already checked by mime type and we accept them as eligible.
	 */
	@Override
	protected boolean isEligible() {
		return true;
	}

	/**
	 * analyzeFile
	 * splits the given 
	 * @return Map containing the analysis result, if successful; null otherwise
	 */
	@Override
	protected Object analyzeFile() {
		Map<String, Integer> map = new HashMap<String, Integer>();
	
		try {
			int percentage = 0;
			long position = 0;
			long size = (int) file.length();
			MeasuredFileInputStream fis = new MeasuredFileInputStream(file);	
			
			@SuppressWarnings("resource")
			Scanner in = new Scanner(fis);
		    while (in.hasNext()) {
		        String s = in.next(); //get the next token in the file
		        map.compute(s, (k, v) -> (v == null) ? 1 : v + 1);
	
		        long newPosition = fis.getFilePosition();
		        if (newPosition != position) {
		        	position = newPosition;
		        	int newPercentage = (int) (position * 100 / size);
		        	if (newPercentage > percentage) {
		        		Thread.sleep((long) 1);
		        		percentage = newPercentage;
		        		listener.onProcessUpdate(percentage);
		        	}
		        }
		    }
		} catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println(
              "Thread was interrupted, Failed to complete operation");
            return null;
		}		
		  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}
	
	/**
	 * toString
	 * 
	 * Returned string is displayed for analyzer selection.
	 */
	public String toString() {
		return "Count Tokens";
	}

	/**
	 * getResultType
	 * 
	 * Return the specification of the result type.
	 */
	@Override
	public AnalysisResultType getResultType() {
		return AnalysisResultType.MAP_KEY_STRING_VALUE_INTEGER;
	}
}
