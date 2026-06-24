module com.mycompany.newdaynewanomaly {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.newdaynewanomaly to javafx.fxml;
    exports com.mycompany.newdaynewanomaly;

    exports com.mycompany.newdaynewanomaly.Controller.Menu;
    opens com.mycompany.newdaynewanomaly.Controller.Menu to javafx.fxml;

    exports com.mycompany.newdaynewanomaly.Controller.Room.MainRoom;
    opens com.mycompany.newdaynewanomaly.Controller.Room.MainRoom to javafx.fxml;

    exports com.mycompany.newdaynewanomaly.Controller.Room.Computer;
    opens com.mycompany.newdaynewanomaly.Controller.Room.Computer to javafx.fxml;
}
