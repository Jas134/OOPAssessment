package model;

public class Species {
	protected int speciesID;
	protected String title;
	protected int categoryID;
	protected String created_date;
	protected String content;
	protected boolean isHidden;

	public Species() {}
	
	public Species(String title, int categoryID, String created_date, String content, boolean isHidden) {
		this.title = title;
		this.categoryID= categoryID;
		this.created_date= created_date;
		this.content = content;
		this.isHidden = isHidden;
	}
	
	public Species(int speciesID, String title, int categoryID, String created_date, String content, boolean isHidden) {
		this.speciesID= speciesID;
		this.title = title;
		this.categoryID= categoryID;
		this.created_date = created_date;
		this.content = content;
		this.isHidden = isHidden;
	}

	public int getSpeciesID() {
		return speciesID;
	}

	public void setSpeciesID(int speciesID) {
		this.speciesID = speciesID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

}
