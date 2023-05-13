module com.svalero.globalfeedfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.svalero.globalfeedfx to javafx.fxml;
    exports com.svalero.globalfeedfx;
}