package models;

import databases.DBAccess;

public class Employee {

	private int employee_id;
	private String first_name;
	private String last_name;
	private String national_id;
	private int produce_id;

	private Employee(int employee_id, String first_name, String last_name, String national_id, int produce_id) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.national_id = national_id;
		this.produce_id = produce_id;
	}

	private Employee(String first_name, String last_name, String national_id, int produce_id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.national_id = national_id;
		this.produce_id = produce_id;
	}

	public static Employee makeEmployee(String first_name, String last_name, String national_id, int produce_id) {
		return new Employee(first_name, last_name, national_id, produce_id);
	}
	
	public static Employee getFromDB(int employee_id) {
		Employee[] employee=new Employee[1];
		
		DBAccess.getQuery().query("select * from employees where"
				+ " employee_id="+employee_id, row->{
					employee[0]=new Employee(employee_id, 
							row.getString("first_name"),
							row.getString("last_name"),
							row.getString("national_id"),
							row.getInt("produce_id"));
				});
		
		return employee[0];
	}
	
	public void addEmployeeToDB() {
		DBAccess.getQuery().update("insert into employees (first_name,"
				+ "last_name,national_id,produce_id) values (?,?,?,?)"
					, new Object[] {
						first_name,last_name,national_id,produce_id
				});
	}
	
	public boolean checkIfEmployeeExist(String national_id) {
		//return true if employee already exists
		int employees[]=new int[1];
		DBAccess.getQuery().query("select * from employees where "
				+ "national_id="+national_id, row->{
					employees[0]=employees[0]+1;
				});
		
		if(employees[0]<=0) {
			return false;
		}else {
			return true;
		}
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getNational_id() {
		return national_id;
	}

	public int getProduce_id() {
		return produce_id;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", national_id=" + national_id + ", produce_id=" + produce_id + "]";
	}
}
