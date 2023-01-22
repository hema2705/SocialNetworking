package StepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.fasterxml.jackson.databind.JsonNode;  


public class FilterOptionsDefinations {


	String usersbaseuri = "https://jsonplaceholder.typicode.com/users";
	String postsbaseuri = "https://jsonplaceholder.typicode.com/posts";
	String commentsbaseuri = "https://jsonplaceholder.typicode.com/comments";
	Header contettype = new Header("Content-type", "application/json; charset=UTF-8");

	Scenario thisscenario;
	RequestSpecification reqspec;
	Response response;


	InputStream actualresponsebodycomments;

	RequestSpecification users_reqspec;

	RequestSpecification posts_reqspec;
	Response users_response;
	Response posts_response;
	private RequestSpecification post_comments_reqspec;
	private Response post_comments_response;
	private RequestSpecification comments_post_reqspec;
	private Response comments_post_response;
	private String _exptestdata;


	@Before
	public void beforeScenario(Scenario s) throws IOException{
		thisscenario =s;

	}	


	@Given("I am a user")
	public void i_am_a_user() {

		//thisscenario.log(" url of the posts is :" +postsbaseuri);
	}



	@When("I want to read the comments under postid <postid>")
	public void i_want_to_read_the_comments_under_postid_postid(io.cucumber.datatable.DataTable dataTable) {

		reqspec = given().header(contettype).pathParam("postid", dataTable.asList().get(1)).pathParam("comments", "comments");
		response = 	reqspec.get(postsbaseuri+"/{postid}/{comments}");

	}


	public  String readFileAsString(String file)throws Exception
	{
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	@Then("validate the response")
	public void validate_the_response() throws Exception {

		String file = "src/test/resources/TestData/Comments/CommentUnderPostID1.json";
		String expjson = readFileAsString(file);

		ObjectMapper mapper = new ObjectMapper();  
		System.out.println(expjson);

		JsonNode node2 = mapper.readTree(response.asInputStream());  

		JsonNode node1 = mapper.readTree(expjson);  

		if(node1.equals(node2)) {  
			Assertions.assertEquals(node2, node1);
			thisscenario.log("the Comments for the post are verified");
		}
		else
		{
			Assertions.fail("Expected and Actual Responses are not equal");
		}

	}


	@Then("I validate that <commentscount> comments are present")
	public void i_validate_that_commentscount_comments_are_present(io.cucumber.datatable.DataTable dataTable) {

		List<Integer> commentlist= response.then().extract().jsonPath().getList("postId");

		if(commentlist.size() ==  Integer.valueOf(dataTable.asList().get(1)))
		{
			thisscenario.log("Comment size for the user is expected :"+commentlist.size());
		}

	}



	@When("I want to read the users userid <userid> filter posts")
	public void i_want_to_read_the_users_userid_userid_filter_posts(io.cucumber.datatable.DataTable dataTable) {
		// https://jsonplaceholder.typicode.com/users/1/posts
		 _exptestdata = "src/test/resources/TestData/Users/UserID_1_PostsDetails";

		users_reqspec = given().header(contettype).pathParam("userid", dataTable.asList().get(1)).pathParam("posts", "posts");
		users_response = 	users_reqspec.get(usersbaseuri+"/{userid}/{posts}");
		setNodeResponse1(users_response);
	}



	@When("I want to read the posts filter by user <userid>")
	public void i_want_to_read_the_posts_filter_by_user_userid(io.cucumber.datatable.DataTable dataTable) {
		// https://jsonplaceholder.typicode.com/posts?userId=1
		posts_reqspec = given().header(contettype).queryParam("userId", dataTable.asList().get(1));
		posts_response = posts_reqspec.get(postsbaseuri);
		setNodeResponse2(posts_response);

	}


	@Then("validate that total posts are <postscount>")
	public void validate_that_total_posts_are_postscount(io.cucumber.datatable.DataTable dataTable) throws Exception {
		int postscount2= getNodeResponse1().then().extract().jsonPath().getList("postId").size();

		int postscount=getNodeResponse2().then().extract().jsonPath().getList("userId").size();

		if((postscount ==  Integer.valueOf(dataTable.asList().get(1))) &&  (postscount2 ==  Integer.valueOf(dataTable.asList().get(1))))
		{
			thisscenario.log("Comment size for the user is expected :"+postscount2);
		}

	}


	@Then("validate the responses and both end points")
	public void validate_the_responses_and_both_end_points() throws Exception {

		String expjson = readFileAsString(_exptestdata);

		ObjectMapper mapper = new ObjectMapper();  

		JsonNode usersnode = mapper.readTree(getNodeResponse1().asInputStream());  
		JsonNode postsnode = mapper.readTree(getNodeResponse2().asInputStream());  

		JsonNode expectednode = mapper.readTree(expjson);  

		Assertions.assertEquals(postsnode, usersnode);
		thisscenario.log("the posts by the user is verified");
		Assertions.assertEquals(postsnode, expectednode);
		thisscenario.log("the posts by the user is verified");

	}



	@When("I want to read the comments with <postid> filter posts")
	public void i_want_to_read_the_comments_with_postid_filter_posts(io.cucumber.datatable.DataTable dataTable) {

		// https://jsonplaceholder.typicode.com/posts/1/comments
		_exptestdata = "src/test/resources/TestData/Comments/CommentUnderPostID3.json";
		post_comments_reqspec = given().header(contettype).pathParam("postId", dataTable.asList().get(1)).pathParam("comments", "comments");
		post_comments_response = 	post_comments_reqspec.get(postsbaseuri+"/{postId}/{comments}");
		setNodeResponse1(post_comments_response);

	}


	@When("I want to read the comments filter by postid <postid>")
	public void i_want_to_read_the_comments_filter_by_postid_postid(io.cucumber.datatable.DataTable dataTable) {

		//	https://jsonplaceholder.typicode.com/comments?postId=1
		comments_post_reqspec = given().header(contettype).queryParam("postId", dataTable.asList().get(1));
		comments_post_response = comments_post_reqspec.get(commentsbaseuri);
		setNodeResponse2(comments_post_response);
	}



	Response nodeReponse1;
	Response nodeReponse2;


	private void setNodeResponse1(Response r1)
	{
		nodeReponse1 = r1;
	}

	private void setNodeResponse2(Response r2)
	{
		nodeReponse2 = r2;
	}

	private Response getNodeResponse1()
	{
		return nodeReponse1;

	}

	private Response getNodeResponse2()
	{
		return nodeReponse2;

	}
}
