package Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employee implements Serializable {
	private String name;
	private String id;
	private int departmentIndex;
	private int roleIndex;

	public enum TimeMethods {
		�����, �����, ����, �����
	}

	public enum WageSystem {
		����, ����, ������
	};

	private WageSystem wageSystem;
	private double salary;
	private TimeMethods preferedTimeMethod;
	private int preferedTimeDifference;
	private TimeMethods preferedCompanyTimeMethod;
	private int preferedCompanyTimeDifference;

	public Employee(String name, String id, WageSystem wageSystem, double salary, TimeMethods preferedTimeMethod,
			int preferedTimeDifference, TimeMethods preferedCompanyTimeMethod, int preferedComanyTimeDifference) {
		this.name = name;
		this.id = id;
		this.wageSystem = wageSystem;
		this.salary = salary;
		this.preferedTimeMethod = preferedTimeMethod;
		this.preferedTimeDifference = preferedTimeDifference;
		this.preferedCompanyTimeMethod = preferedCompanyTimeMethod;
		this.preferedCompanyTimeDifference = preferedComanyTimeDifference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDepartmentIndex() {
		return departmentIndex;
	}

	public void setDepartmentIndex(int departmentIndex) {
		this.departmentIndex = departmentIndex;
	}

	public int getRoleIndex() {
		return roleIndex;
	}

	public void setRoleIndex(int roleIndex) {
		this.roleIndex = roleIndex;
	}

	public WageSystem getWageSystem() {
		return wageSystem;
	}

	public void setWageSystem(WageSystem wageSystem) {
		this.wageSystem = wageSystem;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public TimeMethods getPreferedTimeMethod() {
		return preferedTimeMethod;
	}

	public void setPreferedTimeMethod(TimeMethods preferedTimeMethod) {
		this.preferedTimeMethod = preferedTimeMethod;
	}

	public int getPreferedTimeDifference() {
		return preferedTimeDifference;
	}

	public void setPreferedTimeDifference(int preferedTimeDifference) {
		this.preferedTimeDifference = preferedTimeDifference;
	}

	public TimeMethods getPreferedCompanyTimeMethod() {
		return preferedCompanyTimeMethod;
	}

	public void setPreferedCompanyTimeMethod(TimeMethods preferedCompanyTimeMethod) {
		this.preferedCompanyTimeMethod = preferedCompanyTimeMethod;
	}

	public int getPreferedComanyTimeDifference() {
		return preferedCompanyTimeDifference;
	}

	public void setPreferedComanyTimeDifference(int preferedComanyTimeDifference) {
		this.preferedCompanyTimeDifference = preferedComanyTimeDifference;
	}
//	public int employeeProfit () {
//		int profitPerHour = 0;
//		if (preferedCompanyTimeMethod == TimeMethods.����)
//			return 0;
//	}

	@Override
	public String toString() {
		return "��: " + name + "\t����� ����: " + id + "\t���� ���: " + wageSystem + "\t���: " + (int)salary
				+ "\t���� ����� ������: " + preferedTimeMethod + "\t���� ����: " + preferedTimeDifference;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		return other.getId().equals(getId());
	}

}
