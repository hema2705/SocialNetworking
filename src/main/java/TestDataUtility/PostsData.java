package TestDataUtility;

public class PostsData {

	private int     userId;
	private String body;
	private String title;
	//	private int postid;
	private int id;
	//private int height;
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

	public PostsData(Integer userid, String title, String body, Integer postid, Integer height) {
		setUserId(userid);
		setId(postid);
		setTitle(title);
		setBody(body);
		//setheight(height);

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}




}
