package TestDataUtility;

public class PatchData {

	private int     userId;
	private String body;
	private String title;
	//	private int postid;
	private int id;
	private int height;
	public PatchData()
	{

	}

	
	public PatchData(Integer userid, String title, String body, Integer postid, Integer height) {
		setUserId(userid);
		setId(postid);
		setTitle(title);
		setBody(body);
		setheight(height);

	}

	public void setheight(Integer height) {

		this.height = height;
	}

	public int getheight() {

	return height;
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
