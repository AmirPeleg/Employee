package Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;


import Listener.Listener;
import Model.Company;
import Model.Department;
import Model.Employee.TimeMethods;
import Model.Employee.WageSystem;
import View.View;

@SuppressWarnings("serial")
public class Controller implements Listener, Serializable {
	private Company company;
	private View view;
	
	public Controller(Company m, View v) {
		company = m;
		view = v;
		view.registerListener(this);
	}
	
	public void addDepartment(String name,boolean ischangeble,
			boolean isSynchronizing) {
		try {
			company.addDepartment(name, ischangeble, isSynchronizing);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			company.errorMessage(e.getMessage());
		}
	}
	public void addRole( String name,int departmentIndex,  boolean ischangeble) {
		try {
			company.addRole(name,departmentIndex, ischangeble);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			company.errorMessage(e.getMessage());
		}
	}
	public void addEmployee(String name, String id, WageSystem wageSystem, int salary,int sales, TimeMethods preferedTimeMethod,
			int preferedTimeDifference, int departmentIndex, int roleIndex) {
		try {
			company.addEmployee(name, id, wageSystem, salary, sales, preferedTimeMethod,preferedTimeDifference, departmentIndex, roleIndex);
		}
		catch (Exception e) {
			company.errorMessage(e.getMessage());
		}
		
	}
	public String showAllDepartments() {
		return company.showDepartments();
	}
	public Vector<String> getDepartmentsName(){
		return company.getDepartmentsName();
	}
	public String showRoles() {
		return company.showRoles();
	}
	public String showEmployees() {
		return company.showEmployees();
		
	}
	public Vector<String> showRolesOfDepartment(int depIndex){
		return company.showRolesOfDepartment(depIndex);
	}
	public boolean checkIfDepartmentExistForAddRole() throws Exception {
		try {
			company.checkIfDepartmentExistForAddRole();
			return true;
		}
		catch (Exception e) {
			company.errorMessage(e.getMessage());
			return false;
		}
	}
	public boolean checkIfDepartmentExistForAddEmployee() throws Exception {
		try {
			company.checkIfDepartmentExistForAddEmployee();
			return true;
		}
		catch (Exception e) {
			company.errorMessage(e.getMessage());
			return false;
		}
	}
	public boolean checkIfRoleExistForAddEmployee() {
		try {
			company.checkIfRoleExistForAddEmployee();
			return true;
		}
		catch (Exception e) {
			company.errorMessage(e.getMessage());
			return false;
		}
	}
	public String setRolesOfDepartment(int index, TimeMethods timeMethod, int timeDifference) {
		return company.setRolesOfDepartment(index, timeMethod, timeDifference);
	}
	public String setSpecificRole(int departmentIndex, int roleIndex ,TimeMethods timeMethod, int timeDifference) {
		return company.setSpecificRole(departmentIndex, roleIndex, timeMethod, timeDifference);
	}
	public void addHadrdCoded() throws Exception {
		company.addHardCoded();
	}
	public double getSalaryPerHour(WageSystem salaryType , double salary, double sales) {
		return company.getSalaryPerHour(salaryType, salary, sales);
	}
	public boolean isDepartmentChangeble(int index) {
		return company.isDepartmentChangeble(index);
	}
	public Vector<Department> getDepartments(){
		return company.getDepartments();
	}
	
	@Override
	public void saveFile() throws IOException {
		company.saveFile(company);
	}

	public Company loadFile() throws ClassNotFoundException, IOException, Exception {
		Company newFile;
		try {
			newFile = company.loadFile();
			if (newFile != null) {
				company = newFile;
				return company;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			view.joptionMessage(e.getMessage());
		}
		return null;
	}

	public String printEfficiency() {
		return company.printEfficiency();
	}

	@Override
	public void addEmployee(String name, String id, WageSystem wageSystem, double salary, double sales,
			TimeMethods preferedTimeMethod, int preferedTimeDifference, int departmentIndex, int roleIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSalaryPerHour(WageSystem salaryType, int salary, int sales) {
		// TODO Auto-generated method stub
		return 0;
	}


}
