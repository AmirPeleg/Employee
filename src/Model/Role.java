package Model;

import java.io.Serializable;
import java.util.Vector;

import Model.Employee.TimeMethods;

@SuppressWarnings("serial")
public class Role implements Serializable {
private String name;
private Vector<Employee> employees = new Vector<Employee>();
private boolean ischangeble;

public Role( String name, boolean ischangeble) {
	this.name = name;
	this.ischangeble = ischangeble;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Vector<Employee> getEmployees() {
	return employees;
}

public void setEmployee( Vector<Employee> employees) {
	this.employees = employees;
}
public void addEmployee(Employee employee) {
	employees.add(employee);
}

public boolean isIschangeble() {
	return ischangeble;
}

public void setIschangeble(boolean ischangeble) {
	this.ischangeble = ischangeble;
}
public boolean getIsChangebeble() {
	return ischangeble;
}
public boolean equals(Object obj) {
	if (!(obj instanceof Role))
		return false;
	Role other = (Role) obj;
	return other.getName().equals(getName());
}
public void setRole (TimeMethods timeMethod, int timeDifference) {
	for (int i=0; i<employees.size();i++) {
		employees.get(i).setPreferedCompanyTimeMethod(timeMethod);
		employees.get(i).setPreferedComanyTimeDifference(timeDifference);
	}
		
}
public String toString() {
	String ischangebleHebrew="";
	if (ischangeble == true){
		ischangebleHebrew = "כן";
	}
	else {
		ischangebleHebrew = "לא";
	}

	return "שם התפקיד: " + name + "\tהאם ניתן לשנות: " + ischangebleHebrew;
}



}
