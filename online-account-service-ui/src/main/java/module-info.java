module com.bankofjordan.training.onlineaccountserviceui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.bankofjordan.training.onlineaccountserviceui to javafx.fxml;
    exports com.bankofjordan.training.onlineaccountserviceui;
}