/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.ui;

import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.NoResultException;
import org.itlastore.model.EM;
import org.itlastore.model.entity.Usuario;

/**
 * FXML Controller class
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class LoginViewController implements Initializable {

    @FXML
    private TextField nombreUsuarioField;
    @FXML
    private TextField passField;
    @FXML
    private Text message;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    @FXML
    private void login()
    {
        if (!(nombreUsuarioField.getText().isEmpty() || passField.getText().isEmpty()))
        {
            try
            {
                Usuario loggedUser = EM.getInstance().createEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :name AND u.password = :pass", Usuario.class)
                        .setParameter("name", nombreUsuarioField.getText()).setParameter("pass", passField.getText()).getSingleResult();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
                Parent main = loader.load();

                MainViewController controller = loader.getController();
                controller.setUsuario(loggedUser);
                controller.configurarPerfil();
                
                stage.setScene(new Scene(main));
                controller.setStage(stage);

            } catch (NoResultException nre)
            {
                message.setText("Usuario o Controseña incorrecta");
            } catch (IOException ex)
            {
                Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {
            message.setText("Tanto su Usuario como su Controseña son requeridos");
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

}
