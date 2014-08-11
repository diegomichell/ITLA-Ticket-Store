/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.itlastore.model.entity.Ruta;
import org.itlastore.model.entity.controller.RutaJpaController;
import org.itlastore.util.Util;

/**
 * FXML Controller class
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class EstadisticasViewController implements Initializable {

    @FXML
    private PieChart grafico;
    @FXML
    private ComboBox<Ruta> box;
    @FXML
    private TextField totalVendidoField;
    @FXML
    private TextField totalFacturadoField;

    private Scene homeScene;
    private Stage stage;

    private ObservableList<Ruta> rutas;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        configurarBox();
        configurarChart();
    }

    private void configurarBox()
    {
        RutaJpaController controller = new RutaJpaController();
        rutas = FXCollections.observableArrayList(controller.findAll());
        box.setItems(rutas);

        box.valueProperty().addListener((ObservableValue<? extends Ruta> observable, Ruta oldValue, Ruta newValue) ->
        {
            totalVendidoField.setText(String.valueOf(newValue.getTickets().size()));
            totalFacturadoField.setText(String.format("$RD %s", newValue.getTickets().size() * newValue.getPrecio()));
        });
    }

    public Scene getHomeScene()
    {
        return homeScene;
    }

    public void setHomeScene(Scene homeScene)
    {
        this.homeScene = homeScene;
    }

    private void configurarChart()
    {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        int total = 0;
        //Total de tickets vendidos
        total = rutas.stream().map((r) -> r.getTickets().size()).reduce(total, Integer::sum);

        for (Ruta r : rutas)
        {
            double porcentaje = (r.getTickets().size() / (double) total) * 100;
            data.add(new PieChart.Data(String.format("%s (%s%s)", r.getDireccion(),Util.Redondear(porcentaje),"%"), r.getTickets().size()));
        }

        grafico.setData(data);

    }

    @FXML
    private void volverHomeScreen(ActionEvent event)
    {
        stage.setScene(homeScene);
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
