package models;

import java.util.ArrayList;
import java.util.List;

import databases.DBAccess;

public class Produce {
	private int produce_id;
	private String produce_name;
	private int category_id;
	private int quantity;
	private String date;
	private double price;
	
	private Produce(int produce_id, String produce_name, int category_id, int quantity,double price, String date) {
		super();
		this.produce_id = produce_id;
		this.produce_name = produce_name;
		this.category_id = category_id;
		this.quantity = quantity;
		this.date = date;
		this.price=price;
	}
	
	private Produce(String produce_name, int category_id, int quantity,double price, String date) {
		super();
		this.produce_name = produce_name;
		this.category_id = category_id;
		this.quantity = quantity;
		this.date = date;
		this.price=price;
		this.price=price;
	}
	
	public static Produce createProduce(String produce_name, int category_id, int quantity,double price, String date) {
		return new Produce(produce_name, category_id, quantity,price, date);
	}
	
	public void addToDB() {
		boolean currentTypExists[]=new boolean[1];
		currentTypExists[0]=false;
		int currentTypeID[]=new int[1];
		currentTypeID[0]=-1;
		
		DBAccess.getQuery().query("select * from produce", row->{
			if(row.getString("produce_name").equals(produce_name)) {
				currentTypExists[0]=true;
				currentTypeID[0]=row.getInt("produce_id");
			}
		});
		
		if(currentTypExists[0]==true) {
			//get quantity of current produce
			int count=DBAccess.getQuery().query("select * from produce"
					+ " where produce_id="+currentTypeID[0], row->{
						return row.getInt("quantity");
					});
			
			//add count
			count=quantity+count;
			quantity=count;
			
			//update table
			DBAccess.getQuery().update("update produce "
					+ "set quantity=? where produce_id=?", new Object[] {
							quantity,currentTypeID[0]
					});
		}else {
			//add produce if not already exists
			DBAccess.getQuery().update("insert into produce (produce_name,"
					+ "category_id,quantity,price,date) values(?,?,?,?,?)",
					new Object[] {
							produce_name,category_id,quantity,price,date
					});
		}
	}
	
	public static List<Produce> getProduceList(){
		List<Produce> produceList=new ArrayList<>();
		
		DBAccess.getQuery().query("select * from produce", row->{
			produceList.add(new Produce(row.getInt("produce_id")
					, row.getString("produce_name"),
					row.getInt("category_id"),
					row.getInt("quantity"),
					row.getInt("price"),
					row.getString("date")));
		});
		
		return produceList;
	}
	
	public String getCategoryName() {
		String category=DBAccess.getQuery().query("select * from"
				+ " produce_category where"
				+ " category_id="+category_id, row->{
					return row.getString("category_name");
				});
		
		return category;
	}
	
	public static Produce getProduceWithName(String produce_name) {
		List<Produce> list=getProduceList();
		
		Produce toReturn=null;
		for(Produce p: list) {
			if(p.getProduce_name().equals(produce_name.trim())) {
				toReturn=p;
			}
		}
		
		return toReturn;
	}
	
	public void deductProduceQuantity(int quantity) {
		this.quantity=this.quantity-quantity;
		
		DBAccess.getQuery().update("update produce "
				+ "set quantity=? where produce_id=?", new Object[] {
			this.quantity,this.produce_id
		});
	}
	
	public List<Employee> getEmployeesResponsible(){
		List<Employee> employees=new ArrayList<>();
		
		DBAccess.getQuery().query("select * from employees", row->{
			if(row.getInt("produce_id")==produce_id) {
				Employee employee=Employee.getFromDB(
						row.getInt("employee_id"));
				
				employees.add(employee);
			}
		});
		
		return employees;
	}

	public int getProduce_id() {
		return produce_id;
	}

	public String getProduce_name() {
		return produce_name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}
}
