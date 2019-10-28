package sample.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Admin class this is the administrator controller class
 * for displaying users of the application and removing them if necessary
 */
public class Admin {
    /**
     * userList of UserList
     */
    private static UserList userList = new UserList();
    /**
     * socket of server
     */
    private Socket socket;
    /**
     * getting value TableView from fxml
     */
    @FXML TableView<User> tableUser;

    /**
     * getting value TableColumn from fxml
     */
    @FXML TableColumn<User,String> nameUser;

    /**
     * getting value TableColumn from fxml
     */
    @FXML TableColumn<User,String> surnameUser;

    /**
     * getting value TableColumn from fxml
     */
    @FXML TableColumn<User,String> patronymicUser;

    /**
     * Method for filling table desired values
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    void initialize() throws ParseException, IOException, InterruptedException {
            nameUser.setCellValueFactory(new PropertyValueFactory<User, String>("nameuser"));
            surnameUser.setCellValueFactory(new PropertyValueFactory<User, String>("surnameuser"));
            patronymicUser.setCellValueFactory(new PropertyValueFactory<User, String>("patronymicuser"));
            tableUser.setItems(getUser());
    }

    /**
     * This method is used to display data in a table,
     * data we got from server
     * @return users from ObservableList
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException ignals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    private ObservableList<User> getUser() throws ParseException, IOException, InterruptedException {
        socket = new Socket("localhost", 3345);
        try (
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream())) {
            ObservableList<User> users = FXCollections.observableArrayList();
            JSONObject resultJson = new JSONObject();
            resultJson.put("comand", "lookUser");
            resultJson.put("role", "user");
            oos.writeUTF(resultJson.toString());
            oos.flush();
            Thread.sleep(100);
            String jsonAnsw = ois.readUTF();
            System.out.println(ois.readUTF());
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonAnsw);
            JSONArray jsonArray = (JSONArray) obj;
            int kol = jsonArray.size();
            for (int i = 0; i < kol; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                userList.addUserList(new User(
                        ((Long) jsonObject.get("id")).intValue(), (String) jsonObject.get("role"),
                        (String) jsonObject.get("nameuser"), (String) jsonObject.get("surnameuser"),
                        (String) jsonObject.get("patronymicuser")
                ));
            }
            for (User user : userList.getUserList()) {
                users.add(new User(user.getId(), user.getRole(), user.getNameuser(),
                        user.getSurnameuser(), user.getPatronymicuser()));
            }
            JSONObject resultJsonQ = new JSONObject();
            resultJsonQ.put("comand", "quit");
            oos.writeUTF(resultJsonQ.toString());
            oos.flush();
            Thread.sleep(10);
            socket.close();
            return users;
        }
    }

    /**
     * This method is used to delete users in the admin window
     * @param actionEvent button pressDeleteUser from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException ignals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressDeleteUser(ActionEvent actionEvent) throws ParseException, IOException, InterruptedException {
        socket= new Socket("localhost", 3345);
        try (
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream())) {
            ObservableList<User> userSelected, allUser;
            allUser = tableUser.getItems();
            userSelected = tableUser.getSelectionModel().getSelectedItems();
            int idUser = allUser.get(tableUser.getSelectionModel().getSelectedIndex()).getId();
            JSONObject resultJson = new JSONObject();
            resultJson.put("comand", "deleteUser");
            resultJson.put("idUser", idUser);
            oos.writeUTF(resultJson.toString());
            oos.flush();
            userSelected.forEach(allUser::remove);
            JSONObject resultJsonQ = new JSONObject();
            resultJsonQ.put("comand", "quit");
            oos.writeUTF(resultJsonQ.toString());
            oos.flush();
            Thread.sleep(30);
            socket.close();
        }
    }
}
