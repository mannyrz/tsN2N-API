package com.n2n.tsFrame;

import org.junit.Test;
import org.junit.runner.RunWith;


//Add imports for RA
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Before;
 
import org.junit.Test;
import org.junit.Ignore;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TsFrameApplicationTests {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	//String newLine = System.getProperty("line.separator");
	//String repository = "NEONScience/CI-Parameter-Repo";
	String responseString;
	static String host;

	//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	
	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	@Before
	public void setUp() {
		// 
		//host = System.getProperty("host");
		host = "localhost:8080/RESTfulExample/rest/hello/dummy";

    	System.out.println("Host is set to : " + host);
    	String testUrl = "http://" + host + ":";
    	System.out.println("Full URL = " + testUrl);
    	RestAssured.baseURI = testUrl;
	}
	
	@Test
	public void tsTimeReturned() throws Exception  {
		
		String dateWithCustomFormat = getJodaTime("START");
		System.out.println("Check Time Format A1 > " + dateWithCustomFormat);
		
	    //System.exit(0);
		
		//LOGGER.info("REQUEST");
		//Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
		Response response = RestAssured.
				given()
				//.pathParam("dummy", "Me")
				//.pathParam("dummy", dateWithCustomFormat)
				//.log().all()
				.when()
				.get("/");
			//LOGGER.info("RESPONSE");
			response.then() 
				//.log().status() 
				//.log().headers()
				.log().ifError()
				//.log().everything() //added by me.
				.statusCode(200);
		 //responseString = response.asString();
		 //System.out.println("Looking for: " + responseString);
	}
	
	// time manipulator
	String getJodaTime( String useTime ) {

		//(Need to match)     "2012-10-01T09:45:00.000-00:00";
		//                     2016-07-12T  11:00:00.000
		
        //Set date, but push it back to meet system requirements
		
		String dateStr;
		
		DateTime dt = DateTime.now();
		DateTime dtMYear = dt.minusYears(1);
		DateTime dtPMonths = dtMYear.plusMonths(2);
		
		//Set up END 10 minutes greater then start
		if ( useTime == "START" ) {
			dateStr = dtPMonths.toString(); 
			
		}
		else {
			DateTime dtEnd = dtPMonths.plusMinutes(30);
			dateStr = dtEnd.toString(); 
		}	

		String customFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		DateTimeFormatter dtf = ISODateTimeFormat.dateTime();
		org.joda.time.LocalDateTime parsedDate = dtf.parseLocalDateTime(dateStr);
		String dateWithCustomFormat = parsedDate.toString(DateTimeFormat.forPattern(customFormat));
		
		//System.out.println("Check RETURN Time Format > " + dateWithCustomFormat);

		return (dateWithCustomFormat);
	
		}		
}
