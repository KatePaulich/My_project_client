package sample.authorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import sample.Hash;
import sample.My_Exception;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Authorization class for register new users
 */
public class Authorization {
    /**
     * socket of server
     */
    private Socket socket;

    /**
     * getting value TextField from fxml
     */
    @FXML private TextField name;

    /**
     * getting value TextField from fxml
     */
    @FXML private TextField surname;

    /**
     * getting value TextField from fxml
     */
    @FXML private TextField patronymic;

    /**
     * getting value TextField from fxml
     */
    @FXML private TextField login;

    /**
     * getting value TextField from fxml
     */
    @FXML private TextField password;

    /**
     * getting value TextField from fxml
     */
    @FXML private Button registr;

    /**
     * This method for registering new users
     * @param actionEvent button processRegistr from fxml
     * @throws IOException signals that an I/O exception of some sort has occurred
     */
    @FXML
    public void processRegistr(ActionEvent actionEvent) throws IOException {
        socket = new Socket("localhost", 3345);
        try (
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream())) {
            if (name.getText().trim().equals("") || surname.getText().trim().equals("") || patronymic.getText().trim().equals("")
                    || login.getText().trim().equals("") || password.getText().trim().equals("")) {
                My_Exception.exception("Необходимо заполнить все поля!");
            } else {
                if (password.getText().length() < 8) {
                    My_Exception.exception("Пароль должен содержать не менее 8 символов!");
                } else {
                    String text = Hash.cryptWithMD5(password.getText());
                    JSONObject resultJson1 = new JSONObject();
                    resultJson1.put("comand", "selectUser");
                    resultJson1.put("login", login.getText());
                    resultJson1.put("pass", text);
                    oos.writeUTF(resultJson1.toString());
                    String jsonAnsw1 = ois.readUTF();
                    oos.flush();
                    if (jsonAnsw1.equals("user") ||
                            jsonAnsw1.equals("admin")) {
                        My_Exception.exception("Такой логин или пароль уже использованы");
                    } else {
                        String role = "user";
                        JSONObject resultJson = new JSONObject();
                        resultJson.put("comand", "registrUser");
                        resultJson.put("role", role);
                        resultJson.put("name", name.getText());
                        resultJson.put("surname", surname.getText());
                        resultJson.put("patronymic", patronymic.getText());
                        resultJson.put("login", login.getText());
                        resultJson.put("password", text);
                        oos.writeUTF(resultJson.toString());
                        oos.flush();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/authorization/registr.fxml"));
                        Stage window = new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(root, 600, 150);
                        window.setScene(scene);
                        window.show();
                        registr.getScene().getWindow().hide();
                        JSONObject resultJsonQ = new JSONObject();
                        resultJsonQ.put("comand", "quit");
                        oos.writeUTF(resultJsonQ.toString());
                        oos.flush();
                        socket.close();
                    }
                }
            }
        }
    }
}
