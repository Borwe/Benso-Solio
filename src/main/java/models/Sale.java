package models;

import java.util.ArrayList;
import java.util.List;

import databases.DBAccess;

public class Sale {

	private int inventory_id;
	private int produce_id;
	private int customer_id;
	private int quantity;
	private int amount;
	private String mpesa_Code;
	private String date;
	
	public Sale(int inventory_id, int produce_id, int customer_id, int quantity,int amount, String mpesa_Code, String date) {
		super();
		this.inventory_id = inventory_id;
		this.produce_id = produce_id;
		this.customer_id = customer_id;
		this.quantity = quantity;
		this.amount=amount;
		this.mpesa_Code = mpesa_Code;
		this.date = date;
	}
	
	public static List<Sale> getSalesList(){
		List<Sale> salesList=new ArrayList<>();
		
		DBAccess.getQuery().query("select * from sales_inventory", row->{
			salesList.add(new Sale(row.getInt("inventory_id"), 
						row.getInt("produce_id"),
						row.getInt("customer_id"),
						row.getInt("quantity"),
						row.getInt("amount"),
						row.getString("mpesa_Code"),
						row.getString("date")));
		});
		
		return salesList;
	}
	
	public String getProduceName() {
		for(Produce prod:Produce.getProduceList()) {
			if(prod.getProduce_id()==produce_id) {
				return prod.getProduce_name();
			}
		}
		return null;
	}

	public int getInventory_id() {
		return inventory_id;
	}

	public int getProduce_id() {
		return produce_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getAmount() {
		return amount;
	}

	public String getMpesa_Code() {
		return mpesa_Code;
	}

	public String getDate() {
		return date;
	}
}
