package Model;

import java.io.Serializable;
import java.util.Vector;

import Model.Employee.TimeMethods;

@SuppressWarnings("serial")
public class Department implements Changeble, Serializable  {
	private String name;
	private Vector <Role> roles = new Vector <Role>();
	private boolean ischangeble;
	private boolean isSynchronizing;
	
	public Department(String name, boolean ischangeble,
			boolean isSynchronizing) {
		this.name = name;
		this.ischangeble=ischangeble;
		this.isSynchronizing = isSynchronizing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<Role> getRoles() {
		return roles;
	}

	public void setRoles(Vector<Role> roles) {
		this.roles = roles;
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
	boolean getIsSynconized() {
		return isSynchronizing;
	}
	public void setSynchronizing(boolean isSynchronizing) {
		this.isSynchronizing = isSynchronizing;
	}
	public boolean addRole ( String name, boolean ischangeble) {
		return roles.add(new Role(name, ischangeble));
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Department))
			return false;
		Department other = (Department) obj;
		return other.getName().equals(getName());
	}
	public void setRole (int index, TimeMethods timeMethod, int timeDifference) {
			roles.get(index).setRole(timeMethod, timeDifference);
			
	}
	@Override
	public String toString() {
		String ischangebleHebrew="";
		String isSynchronizingHebrew="";
		if (ischangeble == true){
			ischangebleHebrew = "כן";
		}
		else {
			ischangebleHebrew = "לא";
		}
		if (isSynchronizing == true) {
			isSynchronizingHebrew = "כן";
		}
		else {
			isSynchronizingHebrew = "לא";
		}
		return "שם המחלקה: " + name + "\tהאם ניתן לשינוי? " + ischangebleHebrew + "\tהאם דורש סנכרון "
				+ isSynchronizingHebrew ;
	}

	
	
	
}
