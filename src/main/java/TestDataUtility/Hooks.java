package TestDataUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	
	String posts_endpoint;
	
	

//	@Before
//	public void beforeScenario(Scenario s) throws IOException{
//
//		posts_endpoint = "https://jsonplaceholder.typicode.com/posts";
//			s.log("befor scenario");
//	}	



	@After
	public void afterScenario(){
		System.out.println("This will run after the Scenario");
	}

}
