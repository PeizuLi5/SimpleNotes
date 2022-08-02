package Applications;

public class Note {
	private int Nid;
	private String title;
	private String content;
	
	public Note(int Nid, String title, String content) {
		this.Nid = Nid;
		this.title = title;
		this.content = content;
	}
	
	public int getNid() {
		return this.Nid;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getContent() {
		return this.content;
	}
}
