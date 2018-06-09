package models;

public class Categories {

	private final int category_id;
	private final String category_name;
	
	public Categories(int category_id,String category_name) {
		this.category_id=category_id;
		this.category_name=category_name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}
}
