package sample.buy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.My_Exception;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Buy class for add user buy, deleting, displaying and updating
 */
public class Buy {
    /**
     * userList of UserList
     */
    private static BuyList buyList = new BuyList();

    /**
     * idUser inisilize
     */
    private int idUser;

    /**
     * idCost inisilize
     */
    private int idCost;

    /**
     * Socket server
     */
    private Socket socket;

    /**
     * String to check the correctness of the input value
     */
    private String numAndSim ="^([0-9]+)\\." + "([0-9]+)$";

    /**
     * String to check the correctness of the input value
     */
    private String num = "[0-9]+";

    /**
     * String regex
     */
    private Pattern pattern1 = Pattern.compile(numAndSim);
    /**
     * String regex
     */
    private Pattern pattern2 = Pattern.compile(num);

    /**
     * getting value DatePicker from fxml
     */
    @FXML DatePicker date;

    /**
     * getting value TextField from fxml
     */
    @FXML TextField nameBuy;

    /**
     * getting value TextField from fxml
     */
    @FXML TextField priceBuy;

    /**
     * getting value TableView from fxml
     */
    @FXML TableView<BuyYou> tableBuy;

    /**
     * getting value TableColumn from fxml
     */
    @FXML TableColumn<BuyYou,String> name;

    /**
     * getting value TableColumn from fxml
     */
    @FXML TableColumn<BuyYou,String> price;

    /**
     * This method is used to add buy
     * @param actionEvent pressAddButton button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressAddButton(ActionEvent actionEvent) throws ParseException, IOException, InterruptedException {
      socket = new Socket("localhost", 3345);
      if (nameBuy.getText().trim().equals("") || priceBuy.getText().trim().equals("")||
       date.getValue() == null){
          My_Exception.exception("Необходимо заполнить все поля!");
      }else {
          String text = priceBuy.getText();
          Matcher regexp1 = pattern1.matcher(text);
          Matcher regexp2 = pattern2.matcher(text);
          if ( regexp1.matches() == true || regexp2.matches() == true) {
              try (
                  DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                  DataInputStream ois = new DataInputStream(socket.getInputStream())) {
                  name.setCellValueFactory(new PropertyValueFactory<BuyYou, String>("namebuy"));
                  price.setCellValueFactory(new PropertyValueFactory<BuyYou, String>("pricebuy"));
                  JSONObject resultJson = new JSONObject();
                  resultJson.put("comand", "addBuy");
                  resultJson.put("idUser", idUser);
                  resultJson.put("nameBuy", nameBuy.getText());
                  resultJson.put("priceBuy", priceBuy.getText());
                  resultJson.put("idCost", idCost);
                  resultJson.put("date", date.getValue().toString());
                  oos.writeUTF(resultJson.toString());
                  oos.flush();
                  Thread.sleep(200);
                  tableBuy.setItems(getBuy(idUser, idCost, date.getValue()));
                  nameBuy.clear();
                  priceBuy.clear();
                  JSONObject resultJsonQ = new JSONObject();
                  resultJsonQ.put("comand", "quit");
                  oos.writeUTF(resultJsonQ.toString());
                  oos.flush();
                  Thread.sleep(10);
                  socket.close();
              }
          }
          else {
              My_Exception.exception("Корректно введите стоимость товара или услуги!");
          }
      }
    }

    /**
     * This method is used to delete buy
     * @param actionEvent pressDeleteButton button from fxml
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressDeleteButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        socket = new Socket("localhost", 3345);
        try (
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream())) {
            ObservableList<BuyYou> buySelected, allBuy;
            allBuy = tableBuy.getItems();
            buySelected = tableBuy.getSelectionModel().getSelectedItems();
            int idBuy = allBuy.get(tableBuy.getSelectionModel().getSelectedIndex()).getId();
            JSONObject resultJson = new JSONObject();
            resultJson.put("comand", "deleteBuy");
            resultJson.put("idBuy", idBuy);
            oos.writeUTF(resultJson.toString());
            oos.flush();
            buySelected.forEach(allBuy::remove);
            JSONObject resultJsonQ = new JSONObject();
            resultJsonQ.put("comand", "quit");
            oos.writeUTF(resultJsonQ.toString());
            oos.flush();
            Thread.sleep(10);
            socket.close();
        }
    }

    /**
     * This method is used to display buy
     * @param actionEvent pressAddButton button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressDate(ActionEvent actionEvent) throws ParseException, IOException, InterruptedException {
        name.setCellValueFactory(new PropertyValueFactory<BuyYou, String>("namebuy"));
        price.setCellValueFactory(new PropertyValueFactory<BuyYou, String>("pricebuy"));
        tableBuy.setItems(getBuy(idUser,idCost,date.getValue()));
    }

    /**
     * This method is used to update buy
     */
    @FXML
    void initialize(){
        JSONObject resultJsons = new JSONObject();
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(e -> {
        try {
            socket = new Socket("localhost", 3345);
            }catch (IOException e1) {
                e1.printStackTrace();
                }
             try (
                 DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                 DataInputStream ois = new DataInputStream(socket.getInputStream())) {
                            e.getTableView().getItems().get(e.getTablePosition().getRow())
                                    .setNamebuy(e.getNewValue());
                     resultJsons.put("comand", "updateBuy");
                     resultJsons.put("idBuy", e.getTableView().getItems().get(e.getTablePosition().getRow()).getId());
                     resultJsons.put("nameBuy", e.getNewValue());
                     resultJsons.put("priceBuy", e.getTableView().getItems().get(e.getTablePosition().getRow()).getPricebuy());
                     oos.writeUTF(resultJsons.toString());
                     oos.flush();
                     JSONObject resultJsonQ = new JSONObject();
                     resultJsonQ.put("comand", "quit");
                     oos.writeUTF(resultJsonQ.toString());
                     oos.flush();
                     Thread.sleep(10);
                     socket.close();
                         } catch (IOException | InterruptedException e1) {
                            e1.printStackTrace();
                         }

        });
        JSONObject resultJson1 = new JSONObject();
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setOnEditCommit(t -> {
            try {
                socket = new Socket("localhost", 3345);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try (
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream())) {
                String newValue = t.getNewValue();
                Matcher regexp1 = pattern1.matcher(newValue);
                Matcher regexp2 = pattern2.matcher(newValue);
                if (regexp1.matches() == true || regexp2.matches() == true) {
                    t.getTableView().getItems().get(t.getTablePosition().getRow())
                            .setPricebuy(newValue);
                    resultJson1.put("comand", "updateBuy");
                    resultJson1.put("idBuy", t.getTableView().getItems().get(t.getTablePosition().getRow()).getId());
                    resultJson1.put("nameBuy", t.getTableView().getItems().get(t.getTablePosition().getRow()).getNamebuy());
                    resultJson1.put("priceBuy", newValue);
                    oos.writeUTF(resultJson1.toString());
                    oos.flush();
                    JSONObject resultJsonQ = new JSONObject();
                    resultJsonQ.put("comand", "quit");
                    oos.writeUTF(resultJsonQ.toString());
                    oos.flush();
                    Thread.sleep(10);
                    socket.close();
                } else {
                    My_Exception.exception("Корректно введите стоимость товара или услуги");
                    try {
                        tableBuy.setItems(getBuy(idUser, idCost, date.getValue()));
                    } catch (ParseException | IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This a constructor to initialize idUser and idCost
     * @param idUser from Cost_buy
     * @param idCost from Cost_buy
     */
    public void setId(int idUser, int idCost){
        this.idUser = idUser;
        this.idCost = idCost;
    }

    /**
     * Method to get data that will be displayed in the table
     * @param idUser initialize
     * @param idCost initialize
     * @param date from DatePicker from fxml
     * @return buyYous from ObservableList
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    private ObservableList<BuyYou> getBuy(int idUser, int idCost, LocalDate date) throws ParseException, IOException, InterruptedException {
        socket = new Socket("localhost", 3345);
        try (
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream())) {
            ObservableList<BuyYou> buyYous = FXCollections.observableArrayList();
            JSONObject resultJson = new JSONObject();
            resultJson.put("comand", "lookBuy");
            resultJson.put("idUser", idUser);
            resultJson.put("idCost", idCost);
            resultJson.put("date", date.toString());
            oos.writeUTF(resultJson.toString());
            oos.flush();
            Thread.sleep(30);
            String jsonAnsw = ois.readUTF();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonAnsw);
            JSONArray jsonArray = (JSONArray) obj;
            int kol = jsonArray.size();
            buyList.getBuyList().clear();
            for (int i = 0; i < kol; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                buyList.addBuyList(new BuyYou(
                        ((Long) jsonObject.get("id")).intValue(), ((Long) jsonObject.get("idUser")).intValue(),
                        (String) jsonObject.get("namebuy"), (String) jsonObject.get("pricebuy"),
                        ((Long) jsonObject.get("idcost")).intValue(),
                        LocalDate.parse((String) jsonObject.get("datebuy"))
                ));
            }
            for (BuyYou buyYou : buyList.getBuyList()) {
                buyYous.add(new BuyYou(buyYou.getId(), buyYou.getIduser(), buyYou.getNamebuy(), buyYou.getPricebuy(),
                        buyYou.getIdcost(), buyYou.getDatebuy()));
            }
            JSONObject resultJsonQ = new JSONObject();
            resultJsonQ.put("comand", "quit");
            oos.writeUTF(resultJsonQ.toString());
            oos.flush();
            Thread.sleep(10);
            socket.close();
            return buyYous;
        }
    }
}
