package model;

public class Categories {
	private int id;
	private String Name;
	
	public Categories() {}
	
	public Categories(int id, String name) {
		this.id = id;
		this.Name = name;
	}
	
	public Categories(int id) {
		this.id = id;
	}
	
	public Categories(String name) {
		this.Name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}