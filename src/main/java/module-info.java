module com.example.komanjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires java.desktop;

    opens com.example.komanjava to javafx.fxml, google.code.gson, com.google.gson.Gson;
    exports com.example.komanjava;
    exports GameManagement to com.google.gson;
    opens GameManagement to com.google.gson;
    exports SceneManagers to com.google.gson;
    opens SceneManagers to com.google.gson;
    exports Items to com.google.gson;
    opens Items to com.google.gson;
    exports Entities to com.google.gson;
    opens Entities to com.google.gson;
}