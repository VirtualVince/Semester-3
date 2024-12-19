import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DirectDepositForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Direct Deposit Form");

        // Main layout (GridPane)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Form fields
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        TextField addressField = new TextField();
        addressField.setPromptText("Enter your address");

        TextField cityField = new TextField();
        cityField.setPromptText("City");

        TextField stateField = new TextField();
        stateField.setPromptText("State");

        TextField zipField = new TextField();
        zipField.setPromptText("Zip Code");

        TextField bankNameField = new TextField();
        bankNameField.setPromptText("Bank Name");

        TextField accountNumberField = new TextField();
        accountNumberField.setPromptText("Account Number");

        TextField routingNumberField = new TextField();
        routingNumberField.setPromptText("Routing Number");

        Slider depositAmountSlider = new Slider(1, 100, 50);
        depositAmountSlider.setShowTickLabels(true);
        depositAmountSlider.setShowTickMarks(true);

        RadioButton checkingButton = new RadioButton("Checking");
        RadioButton savingsButton = new RadioButton("Savings");
        ToggleGroup accountTypeGroup = new ToggleGroup();
        checkingButton.setToggleGroup(accountTypeGroup);
        savingsButton.setToggleGroup(accountTypeGroup);

        Button submitButton = new Button("Submit");

        // Layout
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(new Label("Address:"), 0, 1);
        grid.add(addressField, 1, 1);

        grid.add(new Label("City:"), 0, 2);
        grid.add(cityField, 1, 2);

        grid.add(new Label("State:"), 0, 3);
        grid.add(stateField, 1, 3);

        grid.add(new Label("Zip Code:"), 0, 4);
        grid.add(zipField, 1, 4);

        grid.add(new Label("Bank Name:"), 0, 5);
        grid.add(bankNameField, 1, 5);

        grid.add(new Label("Account Number:"), 0, 6);
        grid.add(accountNumberField, 1, 6);

        grid.add(new Label("Routing Number:"), 0, 7);
        grid.add(routingNumberField, 1, 7);

        grid.add(new Label("Deposit Amount (%):"), 0, 8);
        grid.add(depositAmountSlider, 1, 8);

        grid.add(new Label("Account Type:"), 0, 9);
        HBox accountTypeBox = new HBox(10, checkingButton, savingsButton);
        grid.add(accountTypeBox, 1, 9);

        grid.add(submitButton, 1, 10);

        // Validation and Event Handling
        submitButton.setOnAction(e -> validateAndSubmit(
            nameField, addressField, cityField, stateField, zipField,
            bankNameField, accountNumberField, routingNumberField,
            accountTypeGroup, depositAmountSlider
        ));

        // Scene setup
        Scene scene = new Scene(grid, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void validateAndSubmit(TextField nameField, TextField addressField,
                                   TextField cityField, TextField stateField,
                                   TextField zipField, TextField bankNameField,
                                   TextField accountNumberField, TextField routingNumberField,
                                   ToggleGroup accountTypeGroup, Slider depositAmountSlider) {

        // Name validation: only letters and spaces
        if (!nameField.getText().matches("[a-zA-Z ]+")) {
            showAlert("Invalid Input", "Name should contain only letters and spaces.");
            return;
        }

        // Routing number: must be exactly 9 digits
        if (!routingNumberField.getText().matches("\\d{9}")) {
            showAlert("Invalid Input", "Routing Number must be exactly 9 digits.");
            return;
        }

        // Account number: must contain only numbers, up to 17 digits
        if (!accountNumberField.getText().matches("\\d{1,17}")) {
            showAlert("Invalid Input", "Account Number must be up to 17 digits and numeric.");
            return;
        }

        // Zip code: either 5 or 9 digits (US format)
        if (!zipField.getText().matches("\\d{5}(-\\d{4})?")) {
            showAlert("Invalid Input", "ZIP Code must be 5 or 9 digits.");
            return;
        }

        // Deposit amount: validate slider value is between 1% and 100%
        if (depositAmountSlider.getValue() < 1 || depositAmountSlider.getValue() > 100) {
            showAlert("Invalid Input", "Deposit Amount must be between 1% and 100%.");
            return;
        }

        // Ensure an account type is selected
        if (accountTypeGroup.getSelectedToggle() == null) {
            showAlert("Invalid Input", "Please select an account type (Checking or Savings).");
            return;
        }

        // If all validations pass
        showAlert("Success", "Form submitted successfully!");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
