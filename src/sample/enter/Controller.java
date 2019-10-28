package sample.enter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.Hash;
import sample.My_Exception;
import sample.authorization.Authorization;
import sample.cost.Cost;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Controller class for enter user and admin or registration
 */
public class Controller {
    /**
     * Socket server
     */
    private  Socket socket;

    /**
     * getting value TextField from fxml
     */
    @FXML
    private TextField login;

    /**
     * getting value TextField from fxml
     */
    @FXML
    private TextField password;

    /**
     * getting value Button from fxml
     */
    @FXML
    private Button enter;

    /**
     * This method is used to go to the registration
     * @param actionEvent processAuthorization button from fxml
     * @throws IOException signals that an I/O exception of some sort has occurred
     */
    @FXML
    public void processAuthorization(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Authorization.class.getClassLoader().getResource("sample/authorization/authorization.fxml"));
        Parent root = loader.load();
        Stage window = new Stage();
        Scene scene = new Scene(root, 700, 500);
        window.setScene(scene);
        window.show();
    }

    /**
     * This method for enter user and admin
     * @param actionEvent processEnter burron from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void processEnter(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        String pass = Hash.cryptWithMD5(password.getText());
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "enterUser");
        resultJson.put("login", login.getText());
        resultJson.put("pass", pass);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        String role = (String) jsonObject.get("role");
        if ( role.equals("user")){
            Long idUserL = (Long) jsonObject.get("idUser");
            int idUser = idUserL.intValue();
            //забираем имя и отчество пользователя из БД
            String nameUser = (String) jsonObject.get("name");
            String patronymicUser = (String) jsonObject.get("patronymic");
            //переход к расходам пользователя
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/cost/cost.fxml"));
            Parent root = loader.load();
            Stage window = new Stage();
            window.setTitle("Здравствуйте, " + nameUser +" "+ patronymicUser);
            Scene scene = new Scene(root, 500, 500);
            window.setScene(scene);
            Cost controllerCost = loader.getController();
            //передача классу-контролеру id пользователя
            controllerCost.setId(idUser);
            window.show();
            enter.getScene().getWindow().hide();
        }
        if (role.equals("admin")){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/admin/admin.fxml"));
            Parent root = loader.load();
            Stage window = new Stage();
            window.setTitle("Окно администратора");
            Scene scene = new Scene(root, 600, 400);
            window.setScene(scene);
            window.show();
            enter.getScene().getWindow().hide();
        }
        if (role.equals("null")){
            My_Exception.exception("Неверно введены логин или пароль!");
        }
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
        }
    }
}
