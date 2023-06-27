module com.tetris.jfx.tetris {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tetris.jfx.tetris to javafx.fxml;
    exports com.tetris.jfx.tetris;
//    exports ;
//    opens to;
}