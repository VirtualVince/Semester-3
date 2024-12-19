// Shineng Zheng 101167765
// Vincente Sequeira: 101484793
// Shalev Haimovitz: 101482699
// Mackenzie Bishop 101508478

package xyz.thefiredman.employee;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    // application configuration settings
    public static final String DATA_DIR = "model_data";
    public static final String RESOURCES_DIR = "resources";
    public static final double TAX = 1.13;

    @Override
    public void start(Stage stage) throws IOException {
        new EmployeeManager(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
