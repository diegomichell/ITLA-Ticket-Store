/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.itlastore.ui.LoginViewController;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/LoginView.fxml"));
        Parent root = loader.load();
        stage.setHeight(600);
        stage.setWidth(800);
        Scene scene = new Scene(root);
        stage.setTitle("Bienvenido");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../ui/resources/itla.png")));
        stage.setScene(scene);
        
        LoginViewController controller = loader.getController();
        controller.setStage(stage);
        
        stage.show();
    }

    public static void main(String[] args)
    {
        //  new UsuarioJpaController().create(new Usuario("ivanevil", "1234", "Diego", "Perez", "/home/ivanevil/ITLA_Store_Img/perfil.jpg"));
        launch(args);
    }

}
