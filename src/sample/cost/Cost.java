package sample.cost;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.repotr.Report;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static sample.cost.Cost_buy.display;

/**
 * Cost class for go to the category of costs
 */
public class Cost {
    /**
     * initialize category costa
     */
    private String namecost;

    /**
     * initialize idUser
     */
    private int id;

    /**
     * server socket
     */
    private Socket socket;

    /**
     * This method used to go to the category of Car
     * @param actionEvent pressCar button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressCar(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "машина";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на ваш автомобиль",id, idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Transport
     * @param actionEvent pressTransport button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressTransport(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "транспорт";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на транспорт",id, idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Beautiful
     * @param actionEvent pressBeautiful button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressBeautiful(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "косметика/гигиена/красота";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на себя(красота требует денег)",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Health
     * @param actionEvent pressHealth button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressHealth(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "здоровье";
        socket = new Socket("localhost", 3345);
        try (
         DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
         DataInputStream ois = new DataInputStream(socket.getInputStream())) {
         JSONObject resultJson = new JSONObject();
         resultJson.put("comand", "lookCost");
         resultJson.put("namecost", namecost);
         oos.writeUTF(resultJson.toString());
         oos.flush();
         String jsonAnsw = ois.readUTF();
         JSONParser parser = new JSONParser();
         Object obj = parser.parse(jsonAnsw);
         JSONObject jsonObject = (JSONObject) obj;
         int idCost = ((Long) jsonObject.get("id")).intValue();
         display("Не болейте!Расходы на здоровье",id,idCost);
         JSONObject resultJsonQ = new JSONObject();
         resultJsonQ.put("comand", "quit");
         oos.writeUTF(resultJsonQ.toString());
         oos.flush();
         Thread.sleep(10);
         socket.close();
    }}

    /**
     * This method used to go to the category of Corn
     * @param actionEvent pressCorn button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressCorn(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "досуг/развлечения";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на развлечения и досуг, кааайф",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Animal
     * @param actionEvent pressAnimal button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressAnimal(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "животные";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на любимых животных",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Eat
     * @param actionEvent pressEat button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressEat(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "продукты";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на продукты, кушать хочется всегда",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Flat
     * @param actionEvent pressFlat button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressFlat(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "дом/квартира";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на жильё",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Conect
     * @param actionEvent pressConect button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressConect(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "связь";
        socket = new Socket("localhost", 3345);
        try (
         DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
         DataInputStream ois = new DataInputStream(socket.getInputStream())) {
         JSONObject resultJson = new JSONObject();
         resultJson.put("comand", "lookCost");
         resultJson.put("namecost", namecost);
         oos.writeUTF(resultJson.toString());
         oos.flush();
         String jsonAnsw = ois.readUTF();
         JSONParser parser = new JSONParser();
         Object obj = parser.parse(jsonAnsw);
         JSONObject jsonObject = (JSONObject) obj;
         int idCost = ((Long) jsonObject.get("id")).intValue();
         display("Расходы на связь, без связи нынче никак",id,idCost);
         JSONObject resultJsonQ = new JSONObject();
         resultJsonQ.put("comand", "quit");
         oos.writeUTF(resultJsonQ.toString());
         oos.flush();
         Thread.sleep(10);
         socket.close();
    }}

    /**
     * This method used to go to the category of Repair
     * @param actionEvent pressRepair button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressRepair(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "ремонт";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на ремонт, иногда что-то ломается, бывает",id, idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Stady
     * @param actionEvent pressStady button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressStady(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "образование/обучение";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Ученье свет.Расходы на образование",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Clothes
     * @param actionEvent pressClothes button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressClothes(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "одежда/аксессуары";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на одежду, обувь, аксессуары",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Cafe
     * @param actionEvent pressCafe button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressCafe(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "кафе/рестораны";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на кафе, рестораны",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * This method used to go to the category of Present
     * @param actionEvent pressPresent button from fxml
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    @FXML
    public void pressPresent(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        namecost = "подарки";
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookCost");
        resultJson.put("namecost", namecost);
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONObject jsonObject = (JSONObject) obj;
        int idCost = ((Long) jsonObject.get("id")).intValue();
        display("Расходы на подарки.Праздники - это всегда радость",id,idCost);
        JSONObject resultJsonQ = new JSONObject();
        resultJsonQ.put("comand", "quit");
        oos.writeUTF(resultJsonQ.toString());
        oos.flush();
        Thread.sleep(10);
        socket.close();
    }}

    /**
     * Method that goes to buy reports
     * @param actionEvent pressReport button from fxml
     * @throws IOException signals that an I/O exception of some sort has occurred
     */
    @FXML
    public void pressReport(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Cost_buy.class.getClassLoader().getResource("sample/repotr/report.fxml"));
        Parent root = loader.load();
        Stage window = new Stage();
        window.setTitle("Отчёт по расходам");
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
        Report controllerReport = loader.getController();
        controllerReport.setId(id);
        window.show();
    }

    /**
     * This a constructor to initialize idUser
     * @param id from Controller
     */
    public void setId(int id){ this.id = id;}
}
