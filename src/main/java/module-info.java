module it.unicam.cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens it.unicam.cs.pa2122.TomaMatteo116781 to javafx.fxml;
    exports it.unicam.cs.pa2122.TomaMatteo116781;
    exports it.unicam.cs.pa2122.TomaMatteo116781.view;

    opens it.unicam.cs.pa2122.TomaMatteo116781.view to javafx.fxml;
}