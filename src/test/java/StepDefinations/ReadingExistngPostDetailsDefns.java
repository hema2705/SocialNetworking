package StepDefinations;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;

import TestDataUtility.CommentsData;

public class ReadingExistngPostDetailsDefns {

	String posts_endpoint;
	RequestSpecification reqspec;
	Response response;
	Scenario thisscenario;
	final int initialpostssize=100;
	final int uniqueusers = 10;
	int expectstatuscode;
	int postid;
	CommentsData commentsdata;
	@Before
	public void beforeScenario(Scenario s) throws IOException{
		thisscenario =s;

	}	


	@Given("I am an user of the social network")
	public void i_am_an_user_of_the_social_network() {

		posts_endpoint = "https://jsonplaceholder.typicode.com/posts";	
		thisscenario.log("End point url of the posts is :" +posts_endpoint);

	}

	@When("I can access the posts")
	public void i_can_access_the_posts() {

		reqspec = given().when();	

	}

	@Then("I want to read the posts")
	public void i_want_to_read_the_posts() {
		response = 	reqspec.get(posts_endpoint);

	}


	@Then("I want to save the posts")
	public void i_want_to_save_the_posts() {
		List<String> uid = response.jsonPath().getList("userId");
		List<String> title = response.jsonPath().getList("title");
		List<String> body = response.jsonPath().getList("body");
		List<String> postid = response.jsonPath().getList("id");
		Assertions.assertEquals(initialpostssize, postid.size());
		thisscenario.log("Total number of existing posts :" +postid.size() );

		List<String> totalusres = uid.stream()
				.distinct()
				.collect(Collectors.toList());		
		Assertions.assertEquals(uniqueusers, totalusres.size());

		thisscenario.log("Total number of unique users are  :" +totalusres.size() );


	}


	@Then("I want to get the {string} {int}")
	public void i_want_to_get_the(String comments, Integer postid) {
		response = 	reqspec.get(posts_endpoint+"/"+postid+"/"+comments);
		this.postid = postid;
	}


	@Then("I want to verify comments")
	public void i_want_to_verify_comments() {

		int actstatuscode = response.statusCode();
		Assertions.assertEquals(actstatuscode, actstatuscode);
		List<Integer> postid = response.jsonPath().getList("postId");
		List<Integer> id = response.jsonPath().getList("id");
		List<Integer> name = response.jsonPath().getList("name");
		List<Integer> email = response.jsonPath().getList("email");
		List<Integer> body = response.jsonPath().getList("body");

		int num_postid = (int) postid.stream().count();

		Assertions.assertEquals(num_postid, 5);
		Integer n = this.postid;
		boolean  ispostidequal = postid.stream().allMatch(n::equals);
		
		if(ispostidequal)
		{
			thisscenario.log("the fetched comments postid is :"+ n );
			thisscenario.log("the fetched comments size is :"+ id.size() );
			thisscenario.log("the status code is :"+ actstatuscode );

		}

	}



	
	
	@Then("verify the details of comments made by userid {int} with values {int} with {string} {string} {string}")
	public void verify_the_details_of_comments_made_by_userid_with_values_with(Integer id, Integer postid, String name, String email, String body) {
		
		response= given().pathParam("postId",postid).and().pathParam("comments", "comments").and().queryParam("id", id).when().get(posts_endpoint+"/{postId}/{comments}");
	    
		response.then().
		assertThat().
		body("[0].postId", equalTo(1)).
		body("[0].id", equalTo(id)).
		body("[0].name", equalTo("quo vero reiciendis velit similique earum")).
		body("[0].email", equalTo("Jayne_Kuhic@sydney.com")).
		body("[0].body", equalTo("est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et"));
		
		
		
	}
	
	
	
	
	@Then("I want to get the posts of user id {int}")
	public void i_want_to_get_the_posts_of_user_id(Integer int1) {
		// given().queryParam("userId", int1).when().get(posts_endpoint).jsonPath().getList("[0]").get(0);
		 
		// .stream().forEach(n->System.out.println(n));

	}
	


}
