package TestDataUtility;

public class PostsData {
	
	private int     userId;
	private String body;
	private String title;
//	private int postid;
	private int id;
	public PostsData()
	{
		
	}
		
	// used for psting the POST request 
	
	public PostsData(int userId , String body , String title)
	{
		setBody(body);
		setTitle(title);
		setUserId(userId);
	}
	
	
	public PostsData(int userId , String body , String title , int postid)
	{
		setBody(body);
		setTitle(title);
		setUserId(userId);
		setId(postid);
	} 
	
		
	public PostsData(String body , String title)
	{
		setBody(body);
		setTitle(title);
		
	} 
	public PostsData(String body)
	{
		setBody(body);
		
	} 
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	

//	public int getPostid() {
//		return postid;
//	}
//
//
//
//	public void setPostid(int postid) {
//		this.postid = postid;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
