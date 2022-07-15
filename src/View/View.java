package View;

import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JOptionPane;

import Listener.Listener;
import Model.Company;
import Model.Department;
import Model.Employee;
import Model.Employee.TimeMethods;
import Model.Employee.WageSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

@SuppressWarnings("serial")
public class View implements Serializable {
	private Vector<Listener> allListeners = new Vector<Listener>();
	private Stage stage;

	public View(Stage primaryStage) {
		stage = primaryStage;
		stage.setTitle("תפריט ראשי");
		stage.setScene(createMainManuScene());
		stage.show();
	}

	public void registerListener(Listener listener) {
		allListeners.add(listener);
	}

	private Scene createMainManuScene() {
		stage.setTitle("מסך פתיחה");
		Scene currentScene;
		HBox options = new HBox();
		Button newSimulations = new Button("התחל");
		Button exit = new Button("צא מהתוכנה");
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		newSimulations.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					loadFile();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.setScene(creatOptionsManuScene());
			}
		});
		options.getChildren().addAll(newSimulations, exit);
		options.setAlignment(Pos.CENTER);

		newSimulations.setPrefHeight(150);
		newSimulations.setPrefWidth(150);
		newSimulations.setWrapText(true);
		newSimulations.setTextAlignment(TextAlignment.CENTER);
		exit.setPrefHeight(150);
		exit.setPrefWidth(150);
		newSimulations.setScaleX(1.2);
		newSimulations.setScaleY(1.2);
		exit.setScaleX(1.2);
		exit.setScaleY(1.2);
		options.setSpacing(50);

		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		currentScene = new Scene(options, 500, 500);
		return currentScene;

	}

	private Scene creatOptionsManuScene() {
		stage.setTitle("תפריט ראשי");
		Scene currentScene;
		Label label = new Label("תפריט ראשי");
		label.setScaleX(3);
		label.setScaleY(3);
		label.setTextFill(Color.DARKBLUE);
		Button addEmployee = new Button("הוסף עובד");
		addEmployee.setOnAction(e -> stage.setScene(createAddEmployeeScene()));
		Button addRole = new Button("הוסף תפקיד");
		Button addDepartment = new Button("הוסף מחלקה");
		addDepartment.setOnAction(e -> stage.setScene(createAddDepartmentScene()));
		Button showEmployees = new Button("הצג עובדים");
		showEmployees.setOnAction(e -> stage.setScene(createShowAllEmployees()));
		Button showRoles = new Button("הצג תפקידים");
		showRoles.setOnAction(e -> stage.setScene(createShowAllRoles()));
		Button ShowDepartment = new Button("הצג מחלקות");
		ShowDepartment.setOnAction(e -> stage.setScene(createShowAllDepartmetns()));
		Button setRoleMethod = new Button("ערוך שיטת עבודה של תפקיד");
		setRoleMethod.setOnAction(e -> stage.setScene(createChangeMethodOfRole()));
		Button setDepartmentMethod = new Button("ערוך שיטת עבודה של מחלקה");
		setDepartmentMethod.setOnAction(e -> stage.setScene(createChangeMethodOfDepartment()));
		Button showEfficiancy = new Button("הצג יעילות");
		showEfficiancy.setOnAction(e -> stage.setScene(createShowEfficiancy()));
		Button returnButton = new Button("חזור לתפריט ההתחלה");
		addRole.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					if (checkIfDepartmentExistForAddRole() == true)
						stage.setScene(creatAddRoleScene());
					else
						stage.setScene(createAddDepartmentScene());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		addEmployee.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				try {
					if (checkIfDepartmentExistForAddRole() == false)
						stage.setScene(createAddDepartmentScene());
					else if (checkIfRoleExistForAddEmployee() == false)
						stage.setScene(creatAddRoleScene());
					else
						stage.setScene(createAddEmployeeScene());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		returnButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					saveFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.setScene(createMainManuScene());
			}
		});

		HBox addButtons = new HBox(addDepartment, addRole, addEmployee);
		HBox showButtons = new HBox(ShowDepartment, showRoles, showEmployees);
		HBox changeButtons = new HBox(setDepartmentMethod, setRoleMethod);
		HBox returnOptions = new HBox(returnButton);
		VBox options = new VBox(addButtons, showButtons, changeButtons,showEfficiancy, returnOptions);

		addButtons.setAlignment(Pos.TOP_CENTER);
		showButtons.setAlignment(Pos.TOP_CENTER);
		changeButtons.setAlignment(Pos.TOP_CENTER);
		returnOptions.setAlignment(Pos.TOP_CENTER);
		options.setAlignment(Pos.CENTER);
		addButtons.setSpacing(30);
		showButtons.setSpacing(30);
		changeButtons.setSpacing(30);
		options.setSpacing(50);
		addEmployee.setScaleX(1.2);
		addEmployee.setScaleY(1.2);
		addRole.setScaleX(1.2);
		addRole.setScaleY(1.2);
		addDepartment.setScaleX(1.2);
		addDepartment.setScaleY(1.2);
		showEmployees.setScaleX(1.2);
		showEmployees.setScaleY(1.2);
		showRoles.setScaleX(1.2);
		showRoles.setScaleY(1.2);
		ShowDepartment.setScaleX(1.2);
		ShowDepartment.setScaleY(1.2);
		setDepartmentMethod.setScaleX(1.2);
		setDepartmentMethod.setScaleY(1.2);
		setRoleMethod.setScaleX(1.2);
		setRoleMethod.setScaleY(1.2);


		addEmployee.setPrefHeight(50);
		addEmployee.setPrefWidth(110);
		addRole.setPrefHeight(50);
		addRole.setPrefWidth(110);
		addDepartment.setPrefHeight(50);
		addDepartment.setPrefWidth(110);
		showEmployees.setPrefHeight(50);
		showEmployees.setPrefWidth(110);
		showRoles.setPrefHeight(50);
		showRoles.setPrefWidth(110);
		ShowDepartment.setPrefHeight(50);
		ShowDepartment.setPrefWidth(110);
		setDepartmentMethod.setPrefHeight(50);
		setDepartmentMethod.setPrefWidth(110);
		setDepartmentMethod.setFont(new Font(11));
		showEfficiancy.setFont(new Font(30));
		setDepartmentMethod.setWrapText(true);
		setDepartmentMethod.setTextAlignment(TextAlignment.CENTER);
		setRoleMethod.setFont(new Font(11));
		setRoleMethod.setWrapText(true);
		setRoleMethod.setTextAlignment(TextAlignment.CENTER);
		setRoleMethod.setPrefHeight(50);
		setRoleMethod.setPrefWidth(110);
		showEfficiancy.setPrefHeight(80);
		showEfficiancy.setPrefWidth(350);

		currentScene = new Scene(options, 500, 500);
		return currentScene;

	}

	private Scene createAddEmployeeScene() {

		Scene currentScene;
		Button backButton = new Button("חזור");
		Button addEmployee = new Button("הוסף תפקיד");
		Label EmployeeNameLable = new Label("שם:");
		TextField EmployeeNameField = new TextField();
		Label idLable = new Label("תעודת זהות: ");
		TextField idField = new TextField();
		Label employeePreferenceLable = new Label("העדפת העובד: ");
		Label chooseDep = new Label("בחר מחלקה:");
		Label chooseRole = new Label("בחר תפקיד:");
		Label salaryLabel = new Label("שכר: ");
		Label salesLabel = new Label("אחוז מכירות חודשי:");
		TextField salaryFiled = new TextField("0");
		TextField salesField = new TextField("0");
		Label preferationDifferenceLabel = new Label("מספר שעות הפרש: ");
		TextField preferationDifferenceField = new TextField("0");
		Label wagePreferationFiled = new Label("שיטת משכורת: ");
		salesField.setVisible(false);
		salesLabel.setVisible(false);
		preferationDifferenceLabel.setVisible(false);
		preferationDifferenceField.setVisible(false);
		ChoiceBox<TimeMethods> employeePreferenceChoice = new ChoiceBox<>();
		employeePreferenceChoice.getItems().setAll(TimeMethods.values());
		employeePreferenceChoice.setValue(TimeMethods.רגיל);
		ChoiceBox<Employee.WageSystem> employeeWageSystem = new ChoiceBox<>();
		employeeWageSystem.getItems().setAll(Employee.WageSystem.values());
		ChoiceBox<String> departmentsChoice = new ChoiceBox<String>();
		ChoiceBox<String> RolesChoices = new ChoiceBox<String>();
		departmentsChoice.getItems().setAll(getDepartmentsName());
		RolesChoices.getItems().setAll(showRolesOfDepartment(0));
		departmentsChoice.setValue(getDepartmentsName().get(0));
		RolesChoices.setValue(showRolesOfDepartment(0).get(0));
		employeeWageSystem.setValue(WageSystem.בסיס);
		employeePreferenceChoice.setOnAction(e -> {
			if (employeePreferenceChoice.getValue() == TimeMethods.מוקדם
					|| employeePreferenceChoice.getValue() == TimeMethods.מאוחר) {
				preferationDifferenceLabel.setVisible(true);
				preferationDifferenceField.setVisible(true);
			} else {
				preferationDifferenceLabel.setVisible(false);
				preferationDifferenceField.setVisible(false);
			}
		});
		employeeWageSystem.setOnAction(e -> {
			if (employeeWageSystem.getValue() == Employee.WageSystem.מכירות) {
				salesField.setVisible(true);
				salesLabel.setVisible(true);
			} else {
				salesField.setVisible(false);
				salesLabel.setVisible(false);
			}

		});
		departmentsChoice.setOnAction(e -> {
			RolesChoices.getItems()
					.setAll(showRolesOfDepartment(getDepartmentsName().indexOf(departmentsChoice.getValue())));
		});
		HBox employeeNameHbox = new HBox(EmployeeNameField, EmployeeNameLable);
		HBox employeeIDHbox = new HBox(idField, idLable);
		HBox emloyeePreferenceHbox = new HBox(preferationDifferenceField, preferationDifferenceLabel,
				employeePreferenceChoice, employeePreferenceLable);
		HBox salesAndPreferation = new HBox(salesField, salesLabel, employeeWageSystem, wagePreferationFiled);
		HBox employeeChoices = new HBox(RolesChoices, chooseRole, departmentsChoice, chooseDep);
		HBox salaryFileds = new HBox(salaryFiled, salaryLabel);
		HBox employeeButtons = new HBox(backButton, addEmployee);
		employeeNameHbox.setSpacing(30);
		employeeIDHbox.setSpacing(30);
		employeeChoices.setSpacing(30);
		salesAndPreferation.setSpacing(30);
		emloyeePreferenceHbox.setSpacing(30);
		employeeButtons.setSpacing(30);
		salaryFileds.setSpacing(30);
		salesField.setPrefWidth(50);
		preferationDifferenceField.setPrefWidth(40);
		employeeNameHbox.setAlignment(Pos.CENTER);
		employeeIDHbox.setAlignment(Pos.CENTER);
		employeeChoices.setAlignment(Pos.CENTER);
		salesAndPreferation.setAlignment(Pos.CENTER);
		emloyeePreferenceHbox.setAlignment(Pos.CENTER);
		employeeButtons.setAlignment(Pos.CENTER);
		salaryFileds.setAlignment(Pos.CENTER);
		backButton.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		addEmployee.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					if (EmployeeNameField.getText().isEmpty() || idField.getText().isEmpty()
							|| salaryFiled.getText().isEmpty() || salesField.getText().isEmpty()
							|| preferationDifferenceField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "כל השדות חייבים להיות מלאים!");
					} else {
						addEmployee(EmployeeNameField.getText(), idField.getText(), employeeWageSystem.getValue(),
								Integer.parseInt(salaryFiled.getText()), Integer.parseInt(salesField.getText()),
								employeePreferenceChoice.getValue(),
								Integer.parseInt(preferationDifferenceField.getText()),
								getDepartmentsName().indexOf(departmentsChoice.getValue()),
								showRolesOfDepartment(getDepartmentsName().indexOf(departmentsChoice.getValue()))
										.indexOf(RolesChoices.getValue()));
						stage.setScene(createAddEmployeeScene());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		VBox options = new VBox(employeeNameHbox, employeeIDHbox, emloyeePreferenceHbox, salesAndPreferation,
				employeeChoices, salaryFileds, employeeButtons);
		options.setSpacing(30);
		options.setAlignment(Pos.CENTER);
		currentScene = new Scene(options, 600, 500);
		return currentScene;
	}

	private Scene creatAddRoleScene() {
		Scene currentScene;
		Label roleNameLable = new Label("שם התפקיד:");
		TextField roleNameField = new TextField();
		RadioButton isRoleChangeble = new RadioButton("האם ניתן לשינוי: ");
		Button backButton = new Button("חזור");
		Button addRole = new Button("הוסף תפקיד");
		ChoiceBox<String> departmentChoices = new ChoiceBox<String>();
		backButton.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		departmentChoices.getItems().setAll(getDepartmentsName());
		HBox nameHBox = new HBox(roleNameField, roleNameLable);
		HBox ButtonsHBox = new HBox(backButton, addRole);
		VBox vbox = new VBox(nameHBox, isRoleChangeble, departmentChoices, ButtonsHBox);
		isRoleChangeble.setVisible(getDepartments().get(0).getIsChangebeble());
		departmentChoices.setValue(getDepartmentsName().get(0));
		nameHBox.setSpacing(20);
		ButtonsHBox.setSpacing(20);
		vbox.setSpacing(20);
		nameHBox.setAlignment(Pos.CENTER);
		ButtonsHBox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		isRoleChangeble.setAlignment(Pos.CENTER);
		departmentChoices.setOnAction(e -> {
			isRoleChangeble.setVisible(getDepartments().get(getDepartmentsName().indexOf(departmentChoices.getValue()))
					.getIsChangebeble());
			isRoleChangeble.setSelected(false);

		});

		addRole.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					if (roleNameField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "חובה למלא תפקיד!");
					} else {

					}
					addRole(roleNameField.getText(), getDepartmentsName().indexOf(departmentChoices.getValue()),
							isRoleChangeble.isSelected());
					stage.setScene(creatAddRoleScene());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		currentScene = new Scene(vbox, 500, 500);
		return currentScene;

	}

	private Scene createAddDepartmentScene() {
		Scene currentScene;
		Label departmentNameLable = new Label("שם:");
		TextField departmentNameField = new TextField();
		RadioButton isDepartmentChangeble = new RadioButton("האם ניתן לשינוי: ");
		isDepartmentChangeble.setSelected(true);
		RadioButton isDepartmentSynchronizable = new RadioButton("שעות עבודה אחידות: ");
		Button backButton = new Button("חזור");
		Button addDepartment = new Button("הוסף מחלקה");
		HBox departmentName = new HBox(departmentNameLable, departmentNameField);
		HBox deparmentCheckBoxes = new HBox(isDepartmentChangeble, isDepartmentSynchronizable);
		HBox departmentsButtons = new HBox(backButton, addDepartment);
		VBox departmentVBox = new VBox(departmentName, deparmentCheckBoxes, departmentsButtons);
		currentScene = new Scene(departmentVBox, 600, 500);
		departmentName.setSpacing(20);
		deparmentCheckBoxes.setSpacing(20);
		departmentsButtons.setSpacing(20);
		departmentVBox.setSpacing(20);
		departmentName.setAlignment(Pos.CENTER);
		deparmentCheckBoxes.setAlignment(Pos.CENTER);
		departmentsButtons.setAlignment(Pos.CENTER);
		departmentVBox.setAlignment(Pos.CENTER);
		backButton.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		addDepartment.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (departmentNameField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "חובה להזין שם מחלקה!");
				} else {
					addDepartment(departmentNameField.getText(), isDepartmentChangeble.isSelected(),
							isDepartmentSynchronizable.isSelected());
					stage.setScene(createAddDepartmentScene());
				}
			}
		});

		return currentScene;
	}

	private Scene createShowAllEmployees() {
		Scene currentScene;
		stage.setTitle("רשימת העובדים");
		TextArea printEmployees = new TextArea(showEmployees());
		Button back = new Button("חזור לתפריט");
		back.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		VBox vbox = new VBox(printEmployees, back);
		currentScene = new Scene(vbox, 600, 500);
		return currentScene;
	}

	private Scene createShowAllRoles() {
		Scene currentScene;
		stage.setTitle("רשימת התפקידים");
		TextArea printRoles = new TextArea(showRoles());
		Button back = new Button("חזור לתפריט");
		back.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		VBox vbox = new VBox(printRoles, back);
		currentScene = new Scene(vbox, 600, 500);
		return currentScene;

	}

	private Scene createShowAllDepartmetns() {

		Scene currentScene;
		stage.setTitle("רשימת המחלקות");
		TextArea printDepartments = new TextArea(showAllDepartments());
		Button back = new Button("חזור לתפריט");
		back.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		VBox vbox = new VBox(printDepartments, back);
		currentScene = new Scene(vbox, 500, 500);
		return currentScene;

	}

	private Scene createChangeMethodOfDepartment() {

		Scene currentScene;
		Button backButton = new Button("חזור");
		Button change = new Button("שנה");
		Label departmentName = new Label("שם מחלקה: ");
		backButton.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		ChoiceBox<String> departmentChoices = new ChoiceBox<String>();
		departmentChoices.getItems().setAll(getDepartmentsName());
		departmentChoices.setValue(getDepartmentsName().get(0));
		ChoiceBox<TimeMethods> departmentMethod = new ChoiceBox<>();
		departmentMethod.getItems().setAll(TimeMethods.values());
		departmentMethod.setValue(TimeMethods.רגיל);
		Label preferationLabel = new Label("שיטת עבודה: ");
		Label preferationDifferenceLabel = new Label("מספר שעות הפרש");
		TextField preferationDifferenceField = new TextField("0");
		preferationDifferenceLabel.setVisible(false);
		preferationDifferenceField.setVisible(false);
		HBox departmentSelection = new HBox(departmentChoices, departmentName);
		HBox changeHbox = new HBox(preferationDifferenceField, preferationDifferenceLabel, departmentMethod,
				preferationLabel);
		HBox buttons = new HBox(change, backButton);
		VBox allHboxes = new VBox(departmentSelection, changeHbox, buttons);
		allHboxes.setSpacing(20);
		departmentSelection.setSpacing(20);
		changeHbox.setSpacing(20);
		buttons.setSpacing(20);
		departmentName.setAlignment(Pos.CENTER);
		departmentSelection.setAlignment(Pos.CENTER);
		changeHbox.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		allHboxes.setAlignment(Pos.CENTER);
		departmentMethod.setOnAction(e -> {
			if (departmentMethod.getValue() == TimeMethods.מוקדם || departmentMethod.getValue() == TimeMethods.מאוחר) {
				preferationDifferenceLabel.setVisible(true);
				preferationDifferenceField.setVisible(true);
			} else {
				preferationDifferenceLabel.setVisible(false);
				preferationDifferenceField.setVisible(false);
				preferationDifferenceField.setText("0");
			}

		});
		change.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						setRolesOfDepartment(getDepartmentsName().indexOf(departmentChoices.getValue()),
								departmentMethod.getValue(), Integer.parseInt(preferationDifferenceField.getText())));
			}
		});
		currentScene = new Scene(allHboxes, 600, 500);
		return currentScene;

	}

	private Scene createChangeMethodOfRole() {

		Scene currentScene;
		Button backButton = new Button("חזור");
		Button change = new Button("שנה");
		Label departmentName = new Label("שם מחלקה: ");
		Label roleName = new Label("שם התפקיד: ");
		backButton.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		ChoiceBox<String> departmentChoices = new ChoiceBox<String>();
		departmentChoices.getItems().setAll(getDepartmentsName());
		departmentChoices.setValue(getDepartmentsName().get(0));
		ChoiceBox<TimeMethods> departmentMethod = new ChoiceBox<>();
		departmentMethod.getItems().setAll(TimeMethods.values());
		departmentMethod.setValue(TimeMethods.רגיל);
		ChoiceBox<String> RolesChoices = new ChoiceBox<String>();
		RolesChoices.getItems().setAll(showRolesOfDepartment(0));
		RolesChoices.setValue(showRolesOfDepartment(0).get(0));
		Label preferationLabel = new Label("שיטת עבודה: ");
		Label preferationDifferenceLabel = new Label("מספר שעות הפרש");
		TextField preferationDifferenceField = new TextField("0");
		preferationDifferenceLabel.setVisible(false);
		preferationDifferenceField.setVisible(false);
		HBox departmentSelection = new HBox(RolesChoices, roleName, departmentChoices, departmentName);
		HBox changeHbox = new HBox(preferationDifferenceField, preferationDifferenceLabel, departmentMethod,
				preferationLabel);
		HBox buttons = new HBox(change, backButton);
		VBox allHboxes = new VBox(departmentSelection, changeHbox, buttons);
		allHboxes.setSpacing(20);
		departmentSelection.setSpacing(20);
		changeHbox.setSpacing(20);
		buttons.setSpacing(20);
		departmentName.setAlignment(Pos.CENTER);
		departmentSelection.setAlignment(Pos.CENTER);
		changeHbox.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		allHboxes.setAlignment(Pos.CENTER);
		departmentMethod.setOnAction(e -> {
			if (departmentMethod.getValue() == TimeMethods.מוקדם || departmentMethod.getValue() == TimeMethods.מאוחר) {
				preferationDifferenceLabel.setVisible(true);
				preferationDifferenceField.setVisible(true);
			} else {
				preferationDifferenceLabel.setVisible(false);
				preferationDifferenceField.setVisible(false);
				preferationDifferenceField.setText("0");
			}

		});
		departmentChoices.setOnAction(e -> {
			RolesChoices.getItems()
					.setAll(showRolesOfDepartment(getDepartmentsName().indexOf(departmentChoices.getValue())));
		});
		change.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						setSpecificRole(getDepartmentsName().indexOf(departmentChoices.getValue()), showRolesOfDepartment(getDepartmentsName().indexOf(departmentChoices.getValue())).indexOf(RolesChoices.getValue()), departmentMethod.getValue(), Integer.parseInt(preferationDifferenceField.getText())));
			}
		});
		currentScene = new Scene(allHboxes, 600, 500);
		return currentScene;

	}

	private Scene createShowEfficiancy() {

		Scene currentScene;
		TextArea printEfficiancy = new TextArea(printEfficiency());
		Button back = new Button("חזור לתפריט");
		back.setOnAction(e -> stage.setScene(creatOptionsManuScene()));
		VBox vbox = new VBox(printEfficiancy, back);
		currentScene = new Scene(vbox, 500, 500);
		return currentScene;

	}

	public void addDepartment(String name, boolean ischangeble, boolean isSynchronizing) {
		for (Listener listener : allListeners) {
			listener.addDepartment(name, ischangeble, isSynchronizing);

		}
	}

	public String showAllDepartments() {
		for (Listener listener : allListeners) {
			return listener.showAllDepartments();
		}
		return null;
	}

	public Vector<String> getDepartmentsName() {
		for (Listener listener : allListeners) {
			return listener.getDepartmentsName();
		}
		return null;

	}

	public void addRole(String name, int departmentIndex, boolean ischangeble) {
		for (Listener listener : allListeners) {
			listener.addRole(name, departmentIndex, ischangeble);
		}
	}

	public String showRoles() {
		for (Listener listener : allListeners) {
			return listener.showRoles();
		}
		return null;
	}

	public String showEmployees() {
		for (Listener listener : allListeners) {
			return listener.showEmployees();
		}
		return null;
	}

	public Vector<String> showRolesOfDepartment(int depIndex) {
		for (Listener listener : allListeners) {
			return listener.showRolesOfDepartment(depIndex);
		}
		return null;
	}

	public boolean checkIfDepartmentExistForAddRole() throws Exception {
		for (Listener listener : allListeners)
			return listener.checkIfDepartmentExistForAddRole();
		return false;

	}

	public boolean checkIfDepartmentExistForAddEmployee() throws Exception {
		for (Listener listener : allListeners)
			return listener.checkIfDepartmentExistForAddEmployee();
		return false;
	}

	public boolean checkIfRoleExistForAddEmployee() throws Exception {
		for (Listener listener : allListeners)
			return listener.checkIfRoleExistForAddEmployee();
		return false;
	}

	public void addHardCoded() throws Exception {
		for (Listener listener : allListeners)
			listener.addHadrdCoded();
	}

	public void addEmployee(String name, String id, WageSystem wageSystem, int salary, int sales,
			TimeMethods preferedTimeMethod, int preferedTimeDifference, int departmentIndex, int roleIndex)
			throws Exception {
		for (Listener listener : allListeners)
			listener.addEmployee(name, id, wageSystem, salary, sales, preferedTimeMethod, preferedTimeDifference,
					departmentIndex, roleIndex);
	}

	public Vector<Department> getDepartments() {
		for (Listener listener : allListeners)
			return listener.getDepartments();
		return null;
	}

	public String setRolesOfDepartment(int index, TimeMethods timeMethod, int timeDifference) {
		for (Listener listener : allListeners)
			return listener.setRolesOfDepartment(index, timeMethod, timeDifference);
		return null;
	}

	public String setSpecificRole(int departmentIndex, int roleIndex, TimeMethods timeMethod, int timeDifference) {
		for (Listener listener : allListeners)
			return listener.setSpecificRole(departmentIndex, roleIndex, timeMethod, timeDifference);
		return null;
	}

	public void joptionMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}

	public void saveFile() throws IOException {
		for (Listener listener : allListeners) {
			listener.saveFile();
		}

	}

	public Company loadFile() throws ClassNotFoundException, IOException, Exception {
		for (Listener listener : allListeners) {
			return listener.loadFile();
		}
		return null;
	}

	public String printEfficiency() {
		for (Listener listener : allListeners) {
			return listener.printEfficiency();
		}
		return null;
	}

}