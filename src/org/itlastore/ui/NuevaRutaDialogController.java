/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.itlastore.model.entity.Ruta;
import org.itlastore.model.entity.controller.RutaJpaController;

/**
 * FXML Controller class
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class NuevaRutaDialogController implements Initializable {

    @FXML
    private TextField rutaField;
    @FXML
    private Slider precioSlider;
    @FXML
    private Label message;
    private Stage stage;
    private Ruta ruta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void registrarRuta(ActionEvent event)
    {
        if (!rutaField.getText().isEmpty())
        {
            RutaJpaController controller = new RutaJpaController();
            ruta = new Ruta(rutaField.getText(), precioSlider.getValue());
            controller.create(ruta);

            stage.close();
        } else
        {
            message.setText("Campo vacio");
        }

    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public Ruta getRuta()
    {
        return ruta;
    }

    public void setRuta(Ruta ruta)
    {
        this.ruta = ruta;
    }
}
