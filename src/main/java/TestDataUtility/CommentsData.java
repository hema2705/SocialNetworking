package TestDataUtility;

public class CommentsData {

	int postId;
	int id ;
	String name;
	String email;
	String body;



	public CommentsData(int postid , int id , String name,String email,String body) {

		setBody(body);
		setEmail(email);
		setId(id);
		setName(name);
		setPostId(postid);


	}

	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}



}
