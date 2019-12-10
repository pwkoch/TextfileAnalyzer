package analysis;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

import util.MeasuredFileInputStream;

public class CountTokenOccurrenceAnalyzer extends AbstractFileAnalyzer {

	public static final String MESSAGE_FAIL_ERROR_DURING_ANALYSIS = "An error occurred during file analysis.";
	Map<String, Integer> map;
	
	public CountTokenOccurrenceAnalyzer(FileAnalysisListener listener) {
		super(listener);
	}

	@Override
	protected boolean isEligible() {
		return true;
	}

	@Override
	protected void analyzeFile() {
		
//		map = Collections.synchronizedMap(new HashMap<String, Integer>());
		map = new HashMap<String, Integer>();
		
        new Thread(new Runnable() { 
            public void run() 
            { 
            	// perform any operation 
            	System.out.println("Performing operation in Asynchronous Task"); 

            	//reads the file  
				try {
					@SuppressWarnings("resource")
					
					int percentage = 0;
					long position = 0;
					long size = (int) file.length();
					MeasuredFileInputStream fis = new MeasuredFileInputStream(file);	
					
					Scanner in = new Scanner(fis);
				    while (in.hasNext()) {
				        String s = in.next(); //get the next token in the file
				        map.compute(s, (k, v) -> (v == null) ? 1 : v + 1);

				        long newPosition = fis.getFilePosition();
				        if (newPosition != position) {
				        	position = newPosition;
//				        	System.out.println(position);
				        	
				        	int newPercentage = (int) (position * 100 / size);
				        	if (newPercentage > percentage) {
				        		percentage = newPercentage;
				        		System.out.println(percentage + " %");
				        	}
				        }
				        
//				        System.out.println(s);
				    }
				} catch (Exception e) {
					listener.onFileAnalysisFailed(MESSAGE_FAIL_ERROR_DURING_ANALYSIS);
					e.printStackTrace();
				}
            	
                // check if listener is registered. 
                if (listener != null) { 
  
                    // invoke the callback method of class A 
                    listener.onFileAnalysisComplete(map);
                } 
            } 
        }).start(); 
		
	}

}
