module com.atatree.todo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.atatree.todo to javafx.fxml;
    exports com.atatree.todo;
}