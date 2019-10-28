package sample.repotr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.My_Exception;
import sample.buy.BuyList;
import sample.buy.BuyYou;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;

/**
 * Report class for displaying the expense report for a specific period
 */
public class Report {
    /**
     * userList of UserList
     */
    private static BuyList buyList = new BuyList();

    /**
     * initialize id
     */
    private int id;
    /**
     * server socket
     */
    private Socket socket;
    /**
     * initialize summ buy
     */
    private static double summ;

    /**
     * getting value DatePicker from fxml
     */
    @FXML DatePicker dateStart;

    /**
     * getting value DatePicker from fxml
     */
    @FXML DatePicker dateEnd;

    /**
     * getting value TableView from fxml
     */
    @FXML TableView<BuyYou> tableReport;

    /**
     * getting value TableColumn from fxml
     */
    @FXML TableColumn<BuyYou,String> nameBuy;

    /**
     * getting value TableColumn from fxml
     */
    @FXML TableColumn<BuyYou,String> priceBuy;

    /**
     * getting value TextField from fxml
     */
    @FXML TextField totalPrice;

    /**
     * This method to view cost data
     * @param actionEvent pressLook button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    public void pressLook(ActionEvent actionEvent) throws ParseException, IOException, InterruptedException {
        if (dateStart.getValue() == null || dateEnd.getValue() == null){
            My_Exception.exception("Необходимо заполнить все поля!");
        } else if (dateStart.getValue().isAfter(dateEnd.getValue())){
            My_Exception.exception("Необходимо правильно выбрать даты расходов!");
        } else {
            nameBuy.setCellValueFactory(new PropertyValueFactory<BuyYou, String>("namebuy"));
            priceBuy.setCellValueFactory(new PropertyValueFactory<BuyYou, String>("pricebuy"));
            tableReport.setItems(getBuyReport(id, dateStart.getValue(),dateEnd.getValue()));
            //количество знаков в итоговой сумме
            DecimalFormatSymbols s = new DecimalFormatSymbols();
            s.setDecimalSeparator('.');
            DecimalFormat f = new DecimalFormat("#,##0.00", s);
            totalPrice.setText(f.format(summ));// где ты ее берешь?
        }
    }

    /**
     * This a constructor to initialize idUser
     * @param id from Cost
     */
    public void setId(int id){
        this.id = id;

    }

    /**
     * This method to display data in a table
     * @param idUser initialize
     * @param dateStart initialize
     * @param dateEnd initialize
     * @return buyYous from ObservableList
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    private ObservableList<BuyYou> getBuyReport(int idUser, LocalDate dateStart, LocalDate dateEnd) throws ParseException, IOException, InterruptedException {
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        ObservableList<BuyYou> buyYous = FXCollections.observableArrayList();
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookReport");
        resultJson.put("idUser", idUser);
        resultJson.put("dateStart", dateStart.toString());
        resultJson.put("dateEnd", dateEnd.toString());
        oos.writeUTF(resultJson.toString());
        oos.flush();
        Thread.sleep(50);
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONArray jsonArray = (JSONArray) obj;
        int kol = jsonArray.size();
        for (int i = 0; i < kol - 1; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                buyList.addBuyList(new BuyYou(
                        ((Long) jsonObject.get("id")).intValue(), ((Long) jsonObject.get("idUser")).intValue(),
                        (String) jsonObject.get("namebuy"), (String) jsonObject.get("pricebuy"),
                        ((Long) jsonObject.get("idcost")).intValue(),
                        LocalDate.parse((String) jsonObject.get("datebuy"))
                ));
            }
            JSONObject jsonObject = (JSONObject) jsonArray.get(jsonArray.size() - 1);
            summ = ((Double) jsonObject.get("summ")).intValue();
            for (BuyYou buyYou : buyList.getBuyList()) {
                buyYous.add(new BuyYou(buyYou.getId(), buyYou.getIduser(), buyYou.getNamebuy(), buyYou.getPricebuy(),
                        buyYou.getIdcost(), buyYou.getDatebuy()));
            }
            JSONObject resultJsonQ = new JSONObject();
            resultJsonQ.put("comand", "quit");
            oos.writeUTF(resultJsonQ.toString());
            oos.flush();
            Thread.sleep(50);
            socket.close();
            return buyYous;
        }
    }

    /**
     * This method for look diagram
     * @param actionEvent pressLookDiagram button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressLookDiagram(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        if (dateStart.getValue() == null || dateEnd.getValue() == null) {
            My_Exception.exception("Необходимо выбрать даты расходов!");
        } else {
            FXMLLoader loader = new FXMLLoader(Report.class.getClassLoader().getResource("sample/repotr/chart.fxml"));
            Parent root = loader.load();
            Stage window = new Stage();
            window.setTitle("Диаграмма ваших расходов");
            window.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root, 600, 400);
            window.setScene(scene);
            Chart controllerChart = loader.getController();
            //передача классу-контроллеру id пользователя и даты расходов
            controllerChart.setIdAndDate(id, dateStart.getValue(), dateEnd.getValue());
            window.show();
        }
    }
}
