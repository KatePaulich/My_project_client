package sample;

import javafx.scene.control.Alert;

/**
 * My_exception class for call error message
 */
public class My_Exception {
    /**
     * This method causes a window with a specific error
     * @param content String
     */
    public static void exception(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
