package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import databases.DBAccess;

/**
 * @author msbhitsupport
 *
 */
public class Customer {
	
	private int customer_id;
	private String first_name;
	private String last_name;
	private String user_name;
	private String phone_no;
	private String password;
	
	
	
	private Customer(String first_name, String last_name, String user_name, String phone_no, String password) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.phone_no = phone_no;
		this.password = password;
	}
	
	private Customer(int customer_id,String first_name, String last_name, String user_name, String phone_no, String password) {
		super();
		this.customer_id=customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.phone_no = phone_no;
		this.password = password;
	}



	public static class Builder{
		private int customer_id;
		private String first_name;
		private String last_name;
		private String user_name;
		private String phone_no;
		private String password;
		
		public Builder() {
			customer_id=-1;
		}
		
		public Builder setCustomer_id(int customer_id) {
			this.customer_id = customer_id;
			return this;
		}
		
		public Builder setFirst_name(String first_name) {
			this.first_name = first_name;
			return this;
		}
		
		public Builder setLast_name(String last_name) {
			this.last_name = last_name;
			return this;
		}
		
		public Builder setUser_name(String user_name) {
			this.user_name = user_name;
			return this;
		}
		
		public Builder setPhone_no(String phone_no) {
			this.phone_no = phone_no;
			return this;
		}
		
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public Builder setAll(String first_name,String last_name,
				String user_name,String phone_no,String password) {
			
			setFirst_name(first_name);
			setLast_name(last_name);
			setUser_name(user_name);
			setPhone_no(phone_no);
			setPassword(password);
			
			return this;
		}
		
		public Builder setAllFromDB(int customer_id,String first_name,String last_name,
				String user_name,String phone_no,String password) {
			
			setCustomer_id(customer_id);
			setFirst_name(first_name);
			setLast_name(last_name);
			setUser_name(user_name);
			setPhone_no(phone_no);
			setPassword(password);
			
			return this;
		}
		
		public Customer build() {
			if(first_name==null || last_name==null || user_name==null
					|| phone_no==null || password==null) {
				throw new RuntimeException("Make sure all necessary "
						+ "fields have data before creating Customer");
			}else {
				return new Customer(first_name, last_name, user_name, phone_no, password);
			}
		}
		
		public Customer buildFromDB() {
			if(customer_id==-1 || first_name==null || last_name==null || user_name==null
					|| phone_no==null || password==null) {
				return null;
			}else {
				return new Customer(customer_id,first_name, last_name, user_name, phone_no, password);
			}
		}
	}
	
	public void addToDB() {
		DBAccess.getQuery().update("insert into customers"
				+ " (first_name,last_name,user_name,phone_no"
				+ ",password) values (?,?,?,?,?)" ,
				new Object[] {
						this.first_name,this.last_name,
						this.user_name,this.phone_no,
						this.password
				});
	}
	
	public void payGoodFor(Produce produce,int produce_quantity,
			String mpesa_code) {
		//get date
		Calendar today=Calendar.getInstance();
		String date="";
		
		date=today.get(Calendar.DAY_OF_MONTH)+"/"
				+(today.get(Calendar.MONTH)+1)
				+"/"+today.get(Calendar.YEAR);
		
		double price=produce_quantity*produce.getPrice();
		
		DBAccess.getQuery().update("insert into sales_inventory"
				+ " (produce_id,customer_id,quantity,"
				+ "amount,mpesa_code,date) values (?,?,?,?,?,?)", new Object[] {
						produce.getProduce_id(),customer_id,
						produce_quantity,price,mpesa_code,date
				});
		
		//deeduct Prouce quantity
		produce.deductProduceQuantity(produce_quantity);
	}
	
	public static Customer getFromDB(String user_name,String password) {
		final Builder builder=new Builder();
		DBAccess.getQuery().query("select * from customers", row->{
			if(user_name.equals(row.getString("user_name"))
					&&row.getString("password").equals(password)) {
				builder.setAllFromDB(row.getInt("customer_id"),
						row.getString("first_name"),
						row.getString("last_name"),
						row.getString("user_name"),
						row.getString("phone_no"),
						row.getString("password"));
			}
		});
		
		return builder.buildFromDB();
	}
	
	public List<Sale> getPreviousProduceList(){
		List<Sale> sales=new ArrayList<>();
		
		DBAccess.getQuery().query("select * from sales_inventory where"
				+ " customer_id="+customer_id, row->{
					sales.add(new Sale(row.getInt("inventory_id"),
							row.getInt("produce_id"),
							row.getInt("customer_id"),
							row.getInt("quantity"),
							row.getInt("amount"),
							row.getString("mpesa_Code"),
							row.getString("date")));
				});
		
		return sales;
	}
	
	public static String getCustomerNameByID(int id) {
		return DBAccess.getQuery().query("select * from customers"
				+ " where customer_id="+id, row->{
					return row.getString("first_name")+" "+row.getString("last_name");
				});
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", user_name=" + user_name + ", phone_no=" + phone_no + ", password=" + password + "]";
	}
}
