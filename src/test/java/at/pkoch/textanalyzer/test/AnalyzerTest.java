package at.pkoch.textanalyzer.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import at.pkoch.textanalyzer.analysis.AnalysisResultType;
import at.pkoch.textanalyzer.analysis.analyzer.AbstractFileAnalyzer;
import at.pkoch.textanalyzer.analysis.analyzer.CountTokenOccurrenceAnalyzer;

/**
 * AnalyzerTest
 * 
 * JUnit test class to run tests of CountTokenOccurrenceAnalyzer class.
 * @author Patrick
 */
class AnalyzerTest {

	/**
	 * INTERFACE CLASS FOR TESTS
	 */
	private class CallbackHandler implements AbstractFileAnalyzer.IFileAnalysisListener {

		public String analysisFailedMessage = null;
		public int processUpdatePercantage = 0;
		public Object resultObject = null;
		public AnalysisResultType resultType = null;
		
		@Override
		public void onFileAnalysisFailed(String message) {
			analysisFailedMessage = message;
		}

		@Override
		public void onProcessUpdate(int percentage) {
			processUpdatePercantage = percentage;
		}

		@Override
		public void onFileAnalysisComplete(Object result, AnalysisResultType analysisResultType) {
			resultObject = result;
			resultType = analysisResultType;
		}
		
	}
	
	
	/**
	 * MEMBER VARIABLES FOR TEST
	 */
	private CallbackHandler callbackHandler;
	private CountTokenOccurrenceAnalyzer analyzer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Set Up Before Class - @BeforeAll");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Tear Down After Class - @AfterAll");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Set Up @BeforeEach");
		this.analyzer = new CountTokenOccurrenceAnalyzer();
		this.callbackHandler = new CallbackHandler();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Tear Down @AfterEach");
	}

	/**
	 * EvaluationResultTypeTest
	 * 
	 * Tests for correct result type of Analyzer.
	 */
	@Test
	void EvaluationResultTypeTest() {
		assertEquals(AnalysisResultType.MAP_KEY_STRING_VALUE_INTEGER, analyzer.getResultType());
	}
	
	/**
	 * DescriptionTest
	 * 
	 * Tests for correct description of Analyzer.
	 */
	@Test
	void DescriptionTest() {
		assertEquals("Count Tokens", analyzer.toString());
	}
	
	/**
	 * NoFileToAnalyzeTest
	 * 
	 * Tests for correct handling of missing file reference.
	 */
	@Test
	void NoFileToAnalyzeTest() {
		analyzer.processFile(null, callbackHandler);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals("An error occurred during file analysis.", callbackHandler.analysisFailedMessage);
		
//		File file = new File(
//				getClass().getClassLoader().getResource("test").getFile()
//			);
	}

	/**
	 * ProgressTest
	 * 
	 * Tests for correct behavior of progress bar.
	 */
	@Test
	void ProgressTest() {
		File file = new File(getClass().getClassLoader().getResource("test").getFile());
		analyzer.processFile(file, callbackHandler);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNotEquals(0, callbackHandler.processUpdatePercantage);
	}
	
	/**
	 * ResultTest
	 * 
	 * Tests for correct behavior of analysis result
	 */
	@Test
	void ResultTest() {
		File file = new File(getClass().getClassLoader().getResource("test").getFile());
		analyzer.processFile(file, callbackHandler);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(AnalysisResultType.MAP_KEY_STRING_VALUE_INTEGER, callbackHandler.resultType);
		
		@SuppressWarnings("unchecked")
		Map<String,Integer> map = (Map<String,Integer>)callbackHandler.resultObject;
		assertEquals(7,map.keySet().size());
		assertEquals(1,map.get("1:1"));
		assertEquals(2,map.get("Adam"));
		assertEquals(2,map.get("Seth"));
		assertEquals(1,map.get("Enos"));
		assertEquals(1,map.get("1:2"));
		assertEquals(1,map.get("Cainan"));
		assertEquals(1,map.get("Iared"));
	}
	
	
	
}
