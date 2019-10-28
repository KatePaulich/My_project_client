package sample.repotr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;

/**
 * Chart class for  displays digram buy
 */
public class Chart {
    /**
     * initialize id
     */
    private int id;

    /**
     * initialize LocalDate
     */
    private LocalDate dateStart;

    /**
     * initialize LocalDate
     */
    private LocalDate dateEnd;

    /**
     * serer socket
     */
    private Socket socket;

    /**
     * getting value TextField from fxml
     */
    @FXML
    private PieChart pieChart;

    /**
     * Method to create and display a digram buy
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    public void initializeChar() throws ParseException, IOException, InterruptedException {
        socket = new Socket("localhost", 3345);
        try (
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream())) {
        ObservableList<PieChart.Data> generateData = FXCollections.observableArrayList();
        JSONObject resultJson = new JSONObject();
        resultJson.put("comand", "lookChart");
        resultJson.put("id", id);
        resultJson.put("dateStart", dateStart.toString());
        resultJson.put("dateEnd", dateEnd.toString());
        oos.writeUTF(resultJson.toString());
        oos.flush();
        String jsonAnsw = ois.readUTF();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonAnsw);
        JSONArray jsonArray = (JSONArray) obj;
        int kol = jsonArray.size();
        for (int i = 0; i < kol; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                int idCost = ((Long) jsonObject.get("idCost")).intValue();
                Double summ = (Double) jsonObject.get("summ");
                if (idCost == 1) {
                    generateData.add(new PieChart.Data("Транспорт", summ));
                }
                if (idCost == 2) {
                    generateData.add(new PieChart.Data("Машина", summ));
                }
                if (idCost == 3) {
                    generateData.add(new PieChart.Data("Здоровье", summ));
                }
                if (idCost == 4) {
                    generateData.add(new PieChart.Data("Продукты", summ));
                }
                if (idCost == 5) {
                    generateData.add(new PieChart.Data("Одежда/Аксессуары", summ));
                }
                if (idCost == 6) {
                    generateData.add(new PieChart.Data("Подарки", summ));
                }
                if (idCost == 7) {
                    generateData.add(new PieChart.Data("Животные", summ));
                }
                if (idCost == 8) {
                    generateData.add(new PieChart.Data("Образование/Обучение", summ));
                }
                if (idCost == 9) {
                    generateData.add(new PieChart.Data("Косметика/Гигиена", summ));
                }
                if (idCost == 10) {
                    generateData.add(new PieChart.Data("Кафе/Рестораны", summ));
                }
                if (idCost == 11) {
                    generateData.add(new PieChart.Data("Досуг/Развлечения", summ));
                }
                if (idCost == 12) {
                    generateData.add(new PieChart.Data("Дом/Квартира", summ));
                }
                if (idCost == 13) {
                    generateData.add(new PieChart.Data("Связь", summ));
                }
                if (idCost == 14) {
                    generateData.add(new PieChart.Data("Ремонт", summ));
                }
            }
            pieChart.setData(generateData);
            JSONObject resultJsonQ = new JSONObject();
            resultJsonQ.put("comand", "quit");
            oos.writeUTF(resultJsonQ.toString());
            oos.flush();
            Thread.sleep(10);
            socket.close();
        }
    }

    /**
     * This a constructor to initialize id, dateStart, dateEnd
     * @param id from Report
     * @param dateStart from Report
     * @param dateEnd from Report
     * @throws ParseException signals that an error has been reached unexpectedly while parsing json
     * @throws IOException signals that an I/O exception of some sort has occurred
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    public void setIdAndDate(int id, LocalDate dateStart,LocalDate dateEnd) throws ParseException, IOException, InterruptedException {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        initializeChar();
    }
}