package models;

public class Sale {

	private int inventory_id;
	private int produce_id;
	private int customer_id;
	private int quantity;
	private int amount;
	private String mpesa_Code;
	private String date;
	
	public Sale(int inventory_id, int produce_id, int customer_id, int quantity, String mpesa_Code, String date) {
		super();
		this.inventory_id = inventory_id;
		this.produce_id = produce_id;
		this.customer_id = customer_id;
		this.quantity = quantity;
		this.mpesa_Code = mpesa_Code;
		this.date = date;
	}
	
	
}
