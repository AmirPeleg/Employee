package Listener;

import java.io.IOException;
import java.util.Vector;

import Model.Company;
import Model.Department;
import Model.Employee.TimeMethods;
import Model.Employee.WageSystem;

public interface Listener {
	public void addDepartment(String name, boolean ischangeble, boolean isSynchronizing);

	public void addRole(String name, int departmentIndex, boolean ischangeble);

	public void addEmployee(String name, String id, WageSystem wageSystem, double salary, double sales,
			TimeMethods preferedTimeMethod, int preferedTimeDifference, int departmentIndex, int roleIndex);

	public String showAllDepartments();

	public Vector<String> getDepartmentsName();

	public String showRoles();

	public Vector<String> showRolesOfDepartment(int depIndex);

	public String showEmployees();

	public boolean checkIfDepartmentExistForAddRole() throws Exception;

	public boolean checkIfDepartmentExistForAddEmployee() throws Exception;

	public boolean checkIfRoleExistForAddEmployee() throws Exception;

	public void addHadrdCoded() throws Exception;

	public int getSalaryPerHour(WageSystem salaryType, int salary, int sales);

	public boolean isDepartmentChangeble(int index);

	public Vector<Department> getDepartments();

	public String setRolesOfDepartment(int index, TimeMethods timeMethod, int timeDifference);

	public String setSpecificRole(int departmentIndex, int roleIndex, TimeMethods timeMethod, int timeDifference);

	Company loadFile() throws ClassNotFoundException, IOException, Exception;

	void saveFile() throws IOException;


	public String printEfficiency();
}
