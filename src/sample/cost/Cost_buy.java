package sample.cost;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.buy.Buy;

import java.io.IOException;

/**
 * Cost_buy class for go to a certain category of buy
 */
public class Cost_buy {
        /**
         * This method used to go to the category of buy
         * @param title from Buy
         * @param idUser from Buy
         * @param idCost from Buy
         * @throws IOException signals that an I/O exception of some sort has occurred
         */
    public static void display(String title, int idUser, int idCost) throws IOException {
            FXMLLoader loader = new FXMLLoader(Cost_buy.class.getClassLoader().getResource("sample/buy/buy.fxml"));
            Parent root = loader.load();
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            Scene scene = new Scene(root, 600, 400);
            window.setScene(scene);
            Buy controllerBuy = loader.getController();
            controllerBuy.setId(idUser, idCost);
            window.show();
        }
}
