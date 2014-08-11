/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.ui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.persistence.NoResultException;
import org.itlastore.model.EM;
import org.itlastore.model.entity.Usuario;
import org.itlastore.model.entity.controller.UsuarioJpaController;
import org.itlastore.util.Util;

/**
 * FXML Controller class
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class NuevoUsuarioDialogController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField passField;
    private File imageFile;
    private Stage stage;
    @FXML
    private Label imagenLabel;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void openFileChooser(ActionEvent event)
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleccione su imagen de usuario");

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );

        imageFile = chooser.showOpenDialog(stage);

        if (imageFile != null)
        {
            imagenLabel.setText(imageFile.getName());
        }
    }

    @FXML
    private void registrar(ActionEvent event)
    {
        Usuario checkerUsuario;

        if (nombreField.getText().isEmpty())
        {
            message.setText("Introduzca su nombre");
        } else if (apellidoField.getText().isEmpty())
        {
            message.setText("Introduzca su nombre su apellido");
        } else if (usuarioField.getText().isEmpty())
        {
            message.setText("Introduzca su nombre de usuario");
        } else if (passField.getText().isEmpty())
        {
            message.setText("Debe introducir su contraseña");
        } else if (imageFile == null)
        {
            message.setText("Seleccione su imagen de usuario");
        } else
        {
            try
            {
                checkerUsuario = EM.getInstance().createEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :userName", Usuario.class).setParameter("userName", usuarioField.getText()).getSingleResult();

                if (!usuarioField.getText().equals(checkerUsuario.getNombreUsuario()))
                {
                    new UsuarioJpaController().create(new Usuario(usuarioField.getText(), passField.getText(), nombreField.getText(), apellidoField.getText(), Util.saveImg(imageFile)));
                    stage.close();
                } else
                {
                    message.setText(String.format("El nombre de usuario \"%s\" ya esta ocupado.", usuarioField.getText()));
                }

            } catch (NoResultException nre)
            {
                new UsuarioJpaController().create(new Usuario(usuarioField.getText(), passField.getText(), nombreField.getText(), apellidoField.getText(), Util.saveImg(imageFile)));
                stage.close();
            }
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
