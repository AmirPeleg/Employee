package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JOptionPane;

import Model.Employee.TimeMethods;
import Model.Employee.WageSystem;

@SuppressWarnings("serial")
public class Company implements Serializable {
	Vector<Department> departments = new Vector<Department>();

	public void addDepartment(String name, boolean ischangeble, boolean isSynchronizing) throws Exception {
		Department department = new Department(name, ischangeble, isSynchronizing);
		if (departments.contains(department) == true)
			throw new Exception("����� ����� �� ���� ���");
		departments.add(department);
	}

	public void addRole(String name, int departmentIndex, boolean ischangeble) throws Exception {
		Role role = new Role(name, ischangeble);
		if (departments.get(departmentIndex).getRoles().contains(role) == true)
			throw new Exception("���� ����� ������ �� ���� ���");
		departments.get(departmentIndex).addRole(name, ischangeble);
	}

	public void addEmployee(String name, String id, WageSystem wageSystem, int salary, int sales,
			TimeMethods preferedTimeMethod, int preferedTimeDifference, int departmentIndex, int roleIndex)
			throws Exception {
		Employee employee = new Employee(name, id, wageSystem, getSalaryPerHour(wageSystem, salary, sales),
				preferedTimeMethod, preferedTimeDifference, TimeMethods.����, 0);

		if (checkIfEmployeeExist(employee) == true)
			throw new Exception("���� ���� �� ����� ����� ���");
		departments.get(departmentIndex).getRoles().get(roleIndex).addEmployee(employee);

	}

	public boolean checkIfEmployeeExist(Employee employee) {
		for (int i = 0; i < departments.size(); i++) {
			for (int j = 0; j < departments.get(i).getRoles().size(); j++) {
				for (int k = 0; k < departments.get(i).getRoles().get(j).getEmployees().size(); k++) {
					if (departments.get(i).getRoles().get(j).getEmployees().get(k).equals(employee))
						return true;
				}
			}
		}
		return false;
	}

	public String showDepartments() {

		String departmentsString = "";
		for (int i = 0; i < departments.size(); i++) {
			departmentsString = departmentsString + departments.get(i).toString() + "\n";
		}
		return departmentsString;

	}

	public String showRoles() {
		String rolesString = "";
		for (int i = 0; i < departments.size(); i++) {
			rolesString = rolesString + "�� ������: " + departments.get(i).getName() + "\n";
			for (int j = 0; j < departments.get(i).getRoles().size(); j++) {
				rolesString = rolesString + departments.get(i).getRoles().get(j).toString() + "\n";
			}
		}
		return rolesString;

	}

	public String showEmployees() {
		String employeesString = "";
		for (int i = 0; i < departments.size(); i++) {
			for (int j = 0; j < departments.get(i).getRoles().size(); j++) {
				for (int k = 0; k < departments.get(i).getRoles().get(j).getEmployees().size(); k++) {
					employeesString = employeesString + (i + 1) + ") "
							+ departments.get(i).getRoles().get(j).getEmployees().get(k).toString() + "\n";
				}
			}
		}
		return employeesString;
	}

	public Vector<String> showRolesOfDepartment(int depIndex) {
		Vector<String> names = new Vector<String>();
		for (int i = 0; i < departments.get(depIndex).getRoles().size(); i++) {
			names.add(departments.get(depIndex).getRoles().get(i).getName());
		}
		return names;
	}

	public Vector<String> getDepartmentsName() {
		Vector<String> names = new Vector<String>();
		for (int i = 0; i < departments.size(); i++)
			names.add(departments.get(i).getName());
		return names;
	}

	public void errorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}

	public void checkIfDepartmentExistForAddEmployee() throws Exception {
		if (departments.isEmpty() == true)
			throw new Exception("�� ����� ����� ���� �� ���� ������ ����");
	}

	public void checkIfDepartmentExistForAddRole() throws Exception {
		if (departments.isEmpty() == true)
			throw new Exception("�� ����� ����� ���� �� ���� ������ �����");
	}

	public void checkIfRoleExistForAddEmployee() throws Exception {
		for (int i = 0; i < departments.size(); i++)
			if (departments.get(i).getRoles().isEmpty() == true)
				throw new Exception(
						"������ " + departments.get(i).getName() + " ��� �����. �� ������ �� ����� ���� ����� ����");

	}

	public double getSalaryPerHour(WageSystem salaryType, double salary, double sales) {
		double newSalary = 0;
		if (salaryType == WageSystem.����)
			newSalary = salary / 160;
		else if (salaryType == WageSystem.����)
			newSalary = salary;
		else
			newSalary = salary * (sales / 100);

		return newSalary;

	}

	public Vector<Department> getDepartments() {
		return departments;
	}

	public boolean isDepartmentChangeble(int index) {
		return departments.get(index).getIsChangebeble();
	}

	public boolean isDepartmentOrItsRoleChangeble(Department department) {
		if (department.getIsChangebeble() == false)
			return false;
		for (int i = 0; i < department.getRoles().size(); i++) {
			if (department.getRoles().get(i).getIsChangebeble() == false)
				return false;
		}
		return true;

	}

	public Vector<String> getChangebleDepartments() {
		Vector<String> names = new Vector<String>();
		for (int i = 0; i < departments.size(); i++) {
			if (isDepartmentOrItsRoleChangeble(departments.get(i)) == true)
				names.add(departments.get(i).getName());

		}
		return names;
	}

	public boolean getIfHasInchangebleRoles(int index) {
		Department currentDepartment = departments.get(index);
		for (int i = 0; i < currentDepartment.getRoles().size(); i++) {
			if (currentDepartment.getRoles().get(i).getIsChangebeble() == false)
				return false;
		}
		return false;
	}

	public String setRolesOfDepartment(int index, TimeMethods timeMethod, int timeDifference) {
		Department currentDepartment = departments.get(index);
		String message = "";
		String inChangebles = "";
		if (departments.get(index).getIsChangebeble() == false) {
			message = "�� ���� ����� ����� ������ ������� ����� ����� ������";
			return message;
		} else {
			for (int i = 0; i < currentDepartment.getRoles().size(); i++) {
				if (currentDepartment.getRoles().get(i).getIsChangebeble() == false)
					inChangebles = inChangebles + currentDepartment.getRoles().get(i).getName() + "\n";
				else
					currentDepartment.setRole(i, timeMethod, timeDifference);
			}
		}
		if (inChangebles == "")
			message = "���� ����� �� ����� " + departments.get(index).getName() + " ����� ������";
		else
			message = message + "�������� ����� �� ���� ������ ������� ���� ������ ������: " + inChangebles + "\n";
		return message;
	}

	public String setSpecificRole(int departmentIndex, int roleIndex, TimeMethods timeMethod, int timeDifference) {
		String message = "";
		if (departments.get(departmentIndex).getIsChangebeble() == false)
			message = "�� ���� ����� ����� ������ ����� ������ ���� ����� ������";
		else if (departments.get(departmentIndex).getRoles().get(roleIndex).getIsChangebeble() == false)
			message = "������ ���� ���� ������";
		else if (departments.get(departmentIndex).getIsSynconized() == false) {
			departments.get(departmentIndex).setRole(roleIndex, timeMethod, timeDifference);
			message = "���� ������ �� ������ ����� ������";
		} else {
			message = "������ ������ ���� ���� ���� ������ �� �� �������� �� ����� ��.\n"
					+ setRolesOfDepartment(departmentIndex, timeMethod, timeDifference);
		}
		return message;
	}

	public Company loadFile() throws ClassNotFoundException, IOException, Exception {
		Company company;
		ObjectInputStream inFile;
		try {
			inFile = new ObjectInputStream(new FileInputStream("Company.dat"));
			company = (Company) inFile.readObject();
			inFile.close();
		} catch (IOException e) {
			addHardCoded();
			throw new Exception("�� ���� ���� ������!");
		}
		return company;
	}

	public void saveFile(Company company) {
		try {
			ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Company.dat"));
			outFile.writeObject(company);
			outFile.close();
		} catch (IOException e) {
		}
	}

	public double showEmployeesEfficiency(Employee employee) {
		if (employee.getPreferedCompanyTimeMethod() == TimeMethods.����)
			return 1;
		else if (employee.getPreferedCompanyTimeMethod() == TimeMethods.�����) {
			if (employee.getPreferedTimeMethod() == TimeMethods.�����)
				return 1.1;
			else
				return 1;
		}
		if (employee.getPreferedCompanyTimeMethod() == employee.getPreferedTimeMethod()) {
			if (employee.getPreferedTimeDifference() >= employee.getPreferedComanyTimeDifference())
				return 1.2;
			else
				return ((employee.getPreferedTimeDifference() * 1.2
						+ (employee.getPreferedComanyTimeDifference() - employee.getPreferedTimeDifference()) * 0.8)
						/ employee.getPreferedComanyTimeDifference());

		}
		return employee.getPreferedComanyTimeDifference() * 0.8;

	}

	public String printEfficiency() {
		String output = "";
		double departmentEfficiency = 0;
		double roleEfficiency = 0;
		double companyEfficiency = 0;

		for (int i = 0; i < departments.size(); i++) {
			for (int j = 0; j < departments.get(i).getRoles().size(); j++) {
				for (int k = 0; k < departments.get(i).getRoles().get(j).getEmployees().size(); k++) {
					output = output +"\t\t"+(k + 1) + ") "
							+ departments.get(i).getRoles().get(j).getEmployees().get(k).getName() + ": "
							+ showEmployeesEfficiency(departments.get(i).getRoles().get(j).getEmployees().get(k))+"\n";
					roleEfficiency = roleEfficiency + showEmployeesEfficiency(departments.get(i).getRoles().get(j).getEmployees().get(k));
				}
				output = "\t"+(j + 1)+ ") " +departments.get(i).getRoles().get(j).getName()+": "+roleEfficiency+"\n"+ output;	
				departmentEfficiency = departmentEfficiency+ roleEfficiency;
				roleEfficiency=0;
			}
			output = (i + 1) + ") "+departments.get(i).getName()+": "+departmentEfficiency+"\n"+ output;			
			companyEfficiency = companyEfficiency +departmentEfficiency;
			departmentEfficiency=0;
		}
		output= "������ ����� ������: "+ companyEfficiency+"\n\n"+output;
		return output;
	}

	public void addHardCoded() throws Exception {
		this.addDepartment("�����", true, false);
		this.addDepartment("��� ���", true, false);
		this.addRole("���� �����", 0, true);
		this.addRole("������", 0, true);
		this.addRole("����� ����� ����", 1, true);
		this.addRole("�����", 1, true);
		this.addEmployee("����", "123456789", WageSystem.����, 7000, 0, TimeMethods.�����, 3, 0, 0);
		this.addEmployee("����", "123456788", WageSystem.����, 50, 0, TimeMethods.�����, 2, 0, 1);
		this.addEmployee("����", "123456787", WageSystem.������, 4000, 120, TimeMethods.����, 0, 1, 0);
		this.addEmployee("����", "123456784", WageSystem.����, 8000, 0, TimeMethods.�����, 0, 1, 1);

	}
}
