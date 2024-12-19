module xyz.thefiredman.employee {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens xyz.thefiredman.employee to javafx.fxml;
    exports xyz.thefiredman.employee;
}
