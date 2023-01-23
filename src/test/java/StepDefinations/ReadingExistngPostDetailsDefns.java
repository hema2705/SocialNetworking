package StepDefinations;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;

import TestDataUtility.CommentsData;
import TestDataUtility.PostsData;

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
	private RequestSpecification requestSpeification;

	Header contettype = new Header("Content-type", "application/json; charset=UTF-8");
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

		response = given().when().get(posts_endpoint);	

	}

	@Then("I want to read the posts")
	public void i_want_to_read_the_posts() {

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

		response= given().pathParam("postId",postid).and().pathParam("comments", postid).when().get(posts_endpoint+"/{postId}/{comments}");

		this.postid = postid;
	}



	@Then("verify the details of comments made by userid {int} with values {int}")
	public void verify_the_details_of_comments_made_by_userid_with_values(Integer id, Integer postid) {

		response= given().pathParam("postId",postid).and().pathParam("comments", "comments").and().queryParam("id", id).when().get(posts_endpoint+"/{postId}/{comments}");

		response.then().
		assertThat().
		body("[0].postId", equalTo(1)).
		body("[0].id", equalTo(id)).
		body("[0].name", equalTo("quo vero reiciendis velit similique earum")).
		body("[0].email", equalTo("Jayne_Kuhic@sydney.com")).
		body("[0].body", equalTo("est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et"));

	}




	@When("I want to delete the comments {int} and userid {int}")
	public void i_want_to_delete_the_comments_and_userid(Integer postid, Integer id) {
		requestSpeification =	given().pathParam("postId",postid).and().pathParam("comments", "comments").and().queryParam("id", id).when();
		thisscenario.log(given().pathParam("postId",postid).and().pathParam("comments", "comments").and().queryParam("id", id).when().get(posts_endpoint+"/{postId}/{comments}").then().log().body().extract().asString());

	}



	@Then("verify the details of comments made by userid {int} with values {int} deleted with empy respnse body")
	public void verify_the_details_of_comments_made_by_userid_with_values_deleted_with_empy_respnse_body(Integer id, Integer postid) {
		response= requestSpeification.delete(posts_endpoint+"/{postId}/{comments}");

		ValidatableResponse responseofoperation = 	response.then();
		responseofoperation.body("id",equalTo(null));
		responseofoperation.body("userId",equalTo(null));
		responseofoperation.body("title",equalTo(null));
		responseofoperation.body("body",equalTo(null));

		thisscenario.log("validated the response and is :"+ responseofoperation.log().body().extract().asString());

	}


	@Then("verify the details of comments name {string} email {string} and body {string} made by userid {int} with values {int} posted")
	public void verify_the_details_of_comments_name_email_and_body_made_by_userid_with_values_posted(String name, String email, String body, Integer id, Integer postid) {

		response= requestSpeification.post(posts_endpoint+"/{postId}/{comments}");


		thisscenario.log("Creating a Post comment request with values :" +" body:"+body + " user ID:" +id + " postID: "+ postid + " body:"+ body +"email:"+ email);
		ValidatableResponse responseofoperation = 	response.then();
		thisscenario.log(" the response and is :"+ responseofoperation.log().body().extract().asString());

		try {
			responseofoperation.body("name",equalTo(name));
			responseofoperation.body("email",equalTo(email));
			responseofoperation.body("body",equalTo(body));
			responseofoperation.body("id",equalTo(501));
			responseofoperation.body("postId",equalTo(postid));


		}
		catch (java.lang.AssertionError e) {
			Assertions.fail("the response is not as expected parameter values does not match");
		}



	}
	@When("I want to post the comments  name {string} email {string} and body {string} made by userid {int} with values {int} posted")
	public void i_want_to_post_the_comments_name_email_and_body_made_by_userid_with_values_posted(String name, String email, String body, Integer id, Integer postid) {

		CommentsData updatepost = new CommentsData(postid,name,email,body);
		requestSpeification = given().header(contettype).body(updatepost).pathParam("postId",postid).and().pathParam("comments", "comments").when();

		//requestSpeification = given().header(contettype).body(updatepost).pathParam("postId",postid).and().pathParam("comments", "comments").and().queryParam("id", id).when();
	}


	@When("I want to post the comments  name {string} email {string} and body {string} made by userid {int} with values {int} edited")
	public void i_want_to_post_the_comments_name_email_and_body_made_by_userid_with_values_edited(String name, String email, String body, Integer id, Integer postid) {
		try {
			response= requestSpeification.put(posts_endpoint+"/{postId}/{comments}");
		}
		catch(java.lang.NullPointerException e)
		{
			Assertions.fail("the response is null");

		}


		thisscenario.log("Creating a Post comment request with values :" +" body:"+body + " user ID:" +id + " postID: "+ postid + " body:"+ body +"email:"+ email);
		ValidatableResponse responseofoperation = 	response.then();
		thisscenario.log(" the response and is :"+ responseofoperation.log().body().extract().asString());

		try {
			responseofoperation.body("name",equalTo(name));
			responseofoperation.body("email",equalTo(email));
			responseofoperation.body("body",equalTo(body));
			responseofoperation.body("id",equalTo(501));
			responseofoperation.body("postId",equalTo(postid));


		}
		catch (java.lang.AssertionError e) {
			Assertions.fail("the response is not as expected paramenter values does not match");
		}


	}

	@Then("verify the details of comments name {string} email {string} and body {string} made by userid {int} with values {int} edited")
	public void verify_the_details_of_comments_name_email_and_body_made_by_userid_with_values_edited(String name, String email, String body, Integer id, Integer postid)  {
		CommentsData updatepost = new CommentsData(postid,name,email,body);

		requestSpeification = given().header(contettype).body(updatepost).pathParam("postId",postid).and().pathParam("comments", "comments").and().queryParam("id", id).when();

	}


}
