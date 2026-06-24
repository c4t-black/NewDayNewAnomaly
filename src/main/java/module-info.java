module com.mycompany.newdaynewanomaly {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.newdaynewanomaly to javafx.fxml;
    exports com.mycompany.newdaynewanomaly;
    exports com.mycompany.newdaynewanomaly.Controller;
    opens com.mycompany.newdaynewanomaly.Controller to javafx.fxml;
}
