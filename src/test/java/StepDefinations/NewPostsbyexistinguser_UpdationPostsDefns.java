package StepDefinations;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import TestDataUtility.PostsData;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.empty;;


public class NewPostsbyexistinguser_UpdationPostsDefns {

	String posts_endpoint;
	RequestSpecification reqspec;
	Response resp ;
	ValidatableResponse validatepost ;
	int post_responsecode=201;
	int initialpostssize =100;
	int postid;

	Scenario thisscenario;
	private String expectedtitle;
	private String expectedbody;
	private Integer expectedid;
	private Integer expectedUserid;

	private String actualtitle;
	private String actualbody;
	private int actualid;
	PostsData resourcedata;
	private int actualuserid;
	
	@Before
	public void beforeScenario(Scenario s) throws IOException{
		thisscenario =s;

	}	


	@Given("I am an existing user of the social network")
	public void i_am_an_existing_user_of_the_social_network() {

		posts_endpoint = "https://jsonplaceholder.typicode.com/posts";
		thisscenario.log("Request to the end point:"+ posts_endpoint);

	}


	@Given("I want to post a new message with type header specification")
	public void i_want_to_post_a_new_message_with_type_header_specification() {

		Header contettype = new Header("Content-type", "application/json; charset=UTF-8");
		reqspec = given().header(contettype);
		thisscenario.log("Setting header:"+ contettype.toString());

	}

	@When("my user id {int} with title {string} posts {string}")
	public void my_user_id_with_title_posts(Integer userid, String title, String body) {
		expectedtitle = title;
		expectedbody = body;
		expectedUserid = userid;
		thisscenario.log("Creating a POST request with values :" +" body:"+body + " user ID:" +userid + " title:"+ title);

		PostsData newpost = new PostsData(userid,body,title);
		resp = reqspec.body(newpost).when().post(posts_endpoint);

		thisscenario.log("Creating a new resource post :"+ userid + ":"+title+":"+ body);
		this.postid= initialpostssize +1;
		expectedid= this.postid;
		
	}

	@Then("the response is posted")
	public void the_response_is_posted() {


		validatepost = resp.then();
		thisscenario.log("Retreived response is :"+ validatepost.log().body().extract().asString());
		validatepost.body("id",equalTo(expectedid));
		validatepost.body("userId",equalTo(expectedUserid));
		validatepost.body("title",equalTo(expectedtitle));
		validatepost.body("body",equalTo(expectedbody));

		thisscenario.log("validated the response and is :"+ validatepost.log().body().extract().asString());


	}

	@Then("I want to verify the log")
	public void i_want_to_verify_the_log() {
		int postid=	validatepost.statusCode(post_responsecode).and().extract().jsonPath().getInt("id");
		Assertions.assertEquals(this.postid,postid);
		thisscenario.log("New post successfully created and validated, with status code and the postid is :" +post_responsecode +","+ postid);


	}


	@Then("I want to verify the post deleted")
	public void i_want_to_verify_the_post_deleted() {
		int postid;
		try {
			
			postid = validatepost.statusCode(post_responsecode).and().extract().jsonPath().getInt("id");
			Assert.fail("The post is not deleted ");
		}
		catch (NullPointerException e) {
			thisscenario.log("the post is deleted sucessfully with postid :" + this.postid + "Response of the body is empty"+ validatepost.log().all());

		}
	}



	@When("my user id {int} with title {string} posts {string} to update where post id is {int}")
	public void my_user_id_with_title_posts_to_update_where_post_id_is(Integer userid, String title, String body, Integer postid) {

		PostsData updatepost = new PostsData(userid,body,title,postid);

		expectedbody = body;
		expectedUserid = userid;
		expectedid = postid;
		expectedtitle = title;

		thisscenario.log("Creating a PUT request with values :" +" body:"+body + " user ID:" +userid + " postID: "+ postid + " title:"+ title);
		resp = reqspec.pathParam("postId",postid).body(updatepost).when().put(posts_endpoint+"/{postId}");
		thisscenario.log("Updating an existing resource  resource post :"+ userid + ":"+title+":"+ body);
		this.postid = postid;
		post_responsecode = 200;
	}


	@When("delete where post id is {int}")
	public void delete_where_post_id_is(Integer postid) {

		resp = reqspec.pathParam("postId",postid).delete(posts_endpoint+"/{postId}");
		thisscenario.log("Deleting the an existing resource  resource post :"+postid );
		this.postid = postid;
		post_responsecode = 200;
	}


	@When("my user id {int} with title {string} posts {string} to patch where post id is {int}")
	public void my_user_id_with_title_posts_to_patch_where_post_id_is(Integer userid, String title, String body, Integer postid){

		System.out.println("PATCHIND:" + postid);
		PostsData patch = new PostsData(title);
		thisscenario.log("Creating a PATCH request with values :" +" body:"+body + " user ID:" +userid + " postID: "+ postid + " title:"+ title);

		resp = reqspec.pathParam("postId",postid).body(patch).when().patch(posts_endpoint+"/{postId}");
		thisscenario.log("Patching the an existing resource  :"+ userid + ":"+title+":"+ body);
		post_responsecode = 200;
		
		expectedUserid = userid;
		expectedtitle = title;
		expectedbody = body;
		expectedid = postid;
		this.postid = postid;

	}


	@Then("I want to verify the if patch is successful")
	public void i_want_to_verify_the_if_patch_is_successful() {

		actualtitle = validatepost.statusCode(post_responsecode).and().extract().jsonPath().getString("title");
		actualbody = validatepost.statusCode(post_responsecode).and().extract().jsonPath().getString("body");
		actualid = validatepost.statusCode(post_responsecode).and().extract().jsonPath().getInt("id");
		actualuserid = validatepost.statusCode(post_responsecode).and().extract().jsonPath().getInt("userId");

		Assertions.assertEquals(resourcedata.getUserId(), actualuserid);
		Assertions.assertEquals(resourcedata.getId(), actualid);
		Assertions.assertNotEquals(resourcedata.getTitle(), actualtitle);
		Assertions.assertNotEquals(resourcedata.getBody(), actualbody);

		thisscenario.log("Patching the an existing resource is sucessful at postid :"+actualid);


	}

	@Given("when I get the existing resource values with user id  with title where post id is {int}")
	public void when_i_get_the_existing_resource_values_with_user_id_with_title_where_post_id_is(Integer postid) {
		System.out.println("POSTID: get "+ postid);
		resourcedata = 	given().pathParam("postId",postid).when().get(posts_endpoint+"/{postId}").as(PostsData.class);

	}



}