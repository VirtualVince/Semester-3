package xyz.thefiredman.employee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xyz.thefiredman.employee.models.Department;
import xyz.thefiredman.employee.models.Employee;
import xyz.thefiredman.employee.models.Payroll;

public class EmployeeManager {
    private final Stage stage;
    private final EmployeeData data;

    private final Scene homeScene;

    public EmployeeManager(Stage stage) {
        this.stage = stage;
        this.data = new EmployeeData();

        // create home scene
        VBox div = new VBox();
        div.setSpacing(20);
        div.setAlignment(Pos.CENTER);

        Button editEmployeeButton = new Button("Employee Manager");
        Button payroll = new Button("View Payroll Report");
        Button quit = new Button("Quit");

        div.getChildren().addAll(
                editEmployeeButton,
                payroll,
                quit);

        this.homeScene = makeFormScene("Employee Registar", div, null);

        quit.setOnAction(e -> System.exit(0));
        editEmployeeButton
                .setOnAction(event -> stage.setScene(
                        modifyEmployeesScene()));

        editEmployeeButton
                .setOnAction(
                        event -> stage.setScene(modifyEmployeesScene()));
        payroll
                .setOnAction(
                        event -> stage.setScene(payrollInfo()));

        stage.setScene(this.homeScene);
        stage.show();
    }

    private Scene modifyEmployeesScene() {
        VBox container = new VBox();
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);

        for (Employee employee : this.data.getEmployees()) {
            HBox div = new HBox();
            div.setSpacing(20);
            div.setAlignment(Pos.CENTER_LEFT);

            Label id = new Label(String.format("%d", employee.getId()));
            id.getStyleClass().addAll("tex", "bold");

            Label name = new Label(employee.getName());
            name.getStyleClass().addAll("tex");

            Region spacer = new Region();
            Button remove = new Button("Remove");
            Button edit = new Button("Edit");

            remove.setOnAction(event -> {
                this.data.removeEmployee(employee);

                // reload current scene
                this.stage.setScene(this.modifyEmployeesScene());
            });

            edit.setOnAction(event -> {
                // open edit scene
                this.stage.setScene(editEmployeeScene(employee));
            });

            HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

            div.getChildren().addAll(id, name, spacer, remove, edit);
            container.getChildren().add(div);
        }

        if (this.data.getEmployees().size() == 0) {
            Label empty = new Label("There are no employees");
            empty.getStyleClass().addAll("tex");
            container.getChildren().add(empty);
        }

        Button addEmployee = new Button("Add");
        addEmployee.setOnAction(event -> stage.setScene(
                editEmployeeScene(null)));

        container.getChildren().add(addEmployee);

        return makeFormScene("Modify An Employee", container, (e) -> {
            this.stage.setScene(this.homeScene);
        });
    }

    private Scene payrollInfo() {
        VBox container = new VBox();
        container.setSpacing(30);
        container.setAlignment(Pos.TOP_CENTER);

        Label employees = new Label("Employees");
        employees.getStyleClass().addAll("tex-big", "bold");
        container.getChildren().add(employees);

        for (Employee employee : this.data.getEmployees()) {
            VBox div = new VBox();
            div.getStyleClass().add("card");
            div.setSpacing(10);
            div.setAlignment(Pos.TOP_LEFT);

            Label name = new Label(employee.getName());
            Label department = new Label(employee.getDepartment());
            name.getStyleClass().addAll("tex-big", "bold");
            department.getStyleClass().addAll("tex");
            department.setStyle("-fx-padding: 0 0 15 0;");

            Payroll payroll = employee.getPayroll();
            Label worked = new Label(
                    String.format("Work Hours: %.2f, Rate: %.2f", payroll.getWorkHours(), payroll.getHourlyRate()));
            worked.getStyleClass().add("tex");
            Label bonus = new Label(String.format("Bonus: %.2f", payroll.getBonus()));
            bonus.getStyleClass().add("tex");
            Label total = new Label(String.format("Total Earned: %.2f", payroll.calculatePayroll()));
            total.getStyleClass().addAll("tex", "bold", "success");
            Label taxed = new Label(String.format("Tax Deducted: %.2f", payroll.calculatePayroll() * Main.TAX));
            taxed.getStyleClass().add("tex");

            div.getChildren().addAll(name, department, worked, bonus, total, taxed);
            container.getChildren().add(div);
        }

        Label departments = new Label("Departments");
        departments.getStyleClass().addAll("tex-big", "bold");
        container.getChildren().add(departments);

        for (Department department : this.data.getDepartments().values()) {
            VBox div = new VBox();
            div.getStyleClass().add("card");
            div.setSpacing(10);
            div.setAlignment(Pos.TOP_LEFT);

            Label name = new Label(department.getName());
            name.getStyleClass().addAll("tex-big", "bold");

            double payroll = department.computeAveragePayroll(this.data.getEmployees());
            Label total = new Label(String.format("Total Earned: %.2f", payroll));
            total.getStyleClass().addAll("tex", "bold", "success");
            Label taxed = new Label(String.format("Tax Deducted: %.2f", payroll * Main.TAX));
            taxed.getStyleClass().add("tex");

            div.getChildren().addAll(name, total, taxed);
            container.getChildren().add(div);
        }

        if (this.data.getEmployees().isEmpty()) {
            Label empty = new Label("No employee payroll data available.");
            empty.getStyleClass().addAll("tex-big", "italic");
            empty.setStyle("-fx-text-fill: #808080;");
            container.getChildren().add(empty);
        }

        if (this.data.getDepartments().isEmpty()) {
            Label empty = new Label("No department data available.");
            empty.getStyleClass().addAll("tex-big", "italic");
            empty.setStyle("-fx-text-fill: #808080;");
            container.getChildren().add(empty);
        }

        return makeFormScene("Payroll Report Info", container, (e) -> {
            this.stage.setScene(this.homeScene);
        });
    }

    // creates a register employee form
    private Scene editEmployeeScene(Employee employee) {
        VBox div = new VBox();
        div.setSpacing(20);
        div.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name");
        nameLabel.getStyleClass().addAll("tex");

        TextField nameField = new TextField();
        if (employee != null) {
            nameField.setText(employee.getName());
        }

        Label phoneLabel = new Label("Phone Number");
        phoneLabel.getStyleClass().addAll("tex");

        TextField phoneField = new TextField();
        if (employee != null) {
            phoneField.setText(employee.getPhone());
        }

        Label emailLabel = new Label("Email");
        emailLabel.getStyleClass().addAll("tex");

        TextField emailField = new TextField();
        if (employee != null) {
            emailField.setText(employee.getEmail());
        }

        Label departmentLabel = new Label("Department");
        departmentLabel.getStyleClass().addAll("tex");
        TextField departmentField = new TextField();
        if (employee != null) {
            departmentField.setText(employee.getDepartment());
        }

        Label workLabel = new Label("Work Hours");
        workLabel.getStyleClass().addAll("tex");
        TextField workField = new TextField();
        if (employee != null) {
            workField.setText(String.format("%.2f", employee.getPayroll().getWorkHours()));
        }

        Label hourRateLabel = new Label("Hourly Rate");
        hourRateLabel.getStyleClass().addAll("tex");

        TextField hourRateField = new TextField();
        if (employee != null) {
            hourRateField.setText(String.format("%.2f", employee.getPayroll().getHourlyRate()));
        }

        Label bonusLabel = new Label("Bonus");
        bonusLabel.getStyleClass().addAll("tex");
        TextField bonusField = new TextField();
        if (employee != null) {
            bonusField.setText(String.format("%.2f", employee.getPayroll().getBonus()));
        }

        Label statusLabel = new Label("");
        Button submit = new Button("Submit");

        div.getChildren().addAll(
                nameLabel, nameField,
                phoneLabel, phoneField,
                emailLabel, emailField,
                departmentLabel, departmentField,
                workLabel, workField,
                hourRateLabel, hourRateField,
                bonusLabel, bonusField,
                statusLabel, submit);

        // automatically format phone input
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            String onlyDigits = newValue.replaceAll("\\D", "");
            int pos = phoneField.getCaretPosition();

            String formattedValue;
            if (onlyDigits.length() > 6) {
                formattedValue = String.format("(%s) %s-%s",
                        onlyDigits.substring(0, 3),
                        onlyDigits.substring(3, 6),
                        onlyDigits.substring(6, Math.min(10, onlyDigits.length())));
            } else if (onlyDigits.length() > 3) {
                formattedValue = String.format("(%s) %s",
                        onlyDigits.substring(0, 3),
                        onlyDigits.substring(3));
            } else {
                formattedValue = onlyDigits;
            }

            if (oldValue != null && formattedValue.length() > oldValue.length() && formattedValue.length() == 4
                    || formattedValue.length() == 8) {
                pos += 2;
            }

            phoneField.setText(formattedValue);
            phoneField.positionCaret(pos);
        });

        // only allow integers
        workField.textProperty().addListener((observable, oldValue, newValue) -> {
            workField.setText(newValue.replaceAll("\\D", ""));
        });

        hourRateField.textProperty().addListener((observable, oldValue, newValue) -> {
            hourRateField.setText(newValue.replaceAll("\\D", ""));
        });

        bonusField.textProperty().addListener((observable, oldValue, newValue) -> {
            bonusField.setText(newValue.replaceAll("\\D", ""));
        });

        submit.setOnAction(event -> {
            String name = nameField.getText();
            String phone = phoneField.getText().replaceAll("[-() ]", "");
            String email = emailField.getText();
            String department = departmentField.getText();
            String workHours = workField.getText();
            String hourlyRate = hourRateField.getText();
            String bonus = bonusField.getText();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || department.isEmpty() || workHours.isEmpty()
                    || hourlyRate.isEmpty() || bonus.isEmpty()) {
                statusLabel.setText("All fields must be filled");
                statusLabel.getStyleClass().clear();
                statusLabel.getStyleClass().add("error");
                statusLabel.getStyleClass().add("tex");
            } else {
                String emailRegex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";
                statusLabel.getStyleClass().clear();

                if (!email.matches(emailRegex)) {
                    statusLabel.setText("Invalid Email, try again");
                    statusLabel.getStyleClass().addAll("error", "tex");
                } else if (phone.length() != 10) {
                    statusLabel.setText("Invalid Phone Number, try again.");
                    statusLabel.getStyleClass().addAll("error", "tex");
                } else {
                    statusLabel.setText(String.format("Sucessfully addded: %s", name));
                    statusLabel.getStyleClass().addAll("success", "tex");

                    if (employee != null) {
                        // if this employee is being modified, edit it

                        // remove employee
                        this.data.getEmployees().remove(employee.getId());
                        this.data.removeDepartment(employee);

                        // create modified employee
                        Employee newEmployee = new Employee(employee.getId(),
                                name, phone, email,
                                department, Double.parseDouble(workHours), Double.parseDouble(hourlyRate),
                                Double.parseDouble(bonus));

                        // add it back where it used to be
                        this.data.getEmployees().add(employee.getId(), newEmployee);
                        this.data.addDepartment(employee);

                        this.stage.setScene(modifyEmployeesScene());
                    } else {
                        Employee newEmployee = new Employee(this.data.getEmployees().size(),
                                name, phone, email,
                                department, Double.parseDouble(workHours), Double.parseDouble(hourlyRate),
                                Double.parseDouble(bonus));

                        // add the new employee data
                        this.data.addDepartment(newEmployee);
                        this.data.getEmployees().add(newEmployee);
                    }

                    this.data.saveEmployees();
                }
            }
        });

        return makeFormScene("Register An Employee", div, (e) -> {
            this.stage.setScene(modifyEmployeesScene());
        });
    }

    // makes the basis for any content centered on the screen
    public Scene makeFormScene(String titleText, VBox content, EventHandler<ActionEvent> backEvent) {
        VBox body = new VBox();
        body.setAlignment(Pos.CENTER);
        body.setPadding(new Insets(25));

        VBox container = new VBox();
        container.getStyleClass().addAll("container");
        container.setAlignment(Pos.CENTER);

        {
            VBox div = new VBox();

            div.setPadding(new Insets(0, 0, 50, 0));
            div.setAlignment(Pos.CENTER);
            Label title = new Label(titleText);
            title.getStyleClass().addAll("title");

            if (backEvent == null) {
                div.getChildren().add(title);
            } else {
                Button back = new Button("Back");
                back.setOnAction(backEvent);

                HBox hdiv = new HBox();
                hdiv.setAlignment(Pos.CENTER);
                hdiv.setSpacing(25);
                hdiv.getChildren().addAll(title, back);
                div.getChildren().add(hdiv);
            }

            container.getChildren().add(div);
        }

        container.getChildren().add(content);

        // ensure content can be viewed even at limited heights
        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setMaxWidth(700);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setPannable(false);

        body.getChildren().add(scrollPane);

        Scene scene = new Scene(body, 1000, 1000);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Resources.css);
        return scene;
    }
}
