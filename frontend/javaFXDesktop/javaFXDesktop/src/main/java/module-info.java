module com.klmsystems.javafxdesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.net.http;
    requires org.json;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires org.slf4j;
    
    requires okhttp3;  // Add this line for OkHttp support

    opens com.klmsystems.javafxdesktop to javafx.fxml;
    opens com.klmsystems.javafxdesktop.models to gson;

    exports com.klmsystems.javafxdesktop;
    exports com.klmsystems.javafxdesktop.services;
}
