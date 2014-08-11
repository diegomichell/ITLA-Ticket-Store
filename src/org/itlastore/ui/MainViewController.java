/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.NoResultException;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog.Actions;
import org.controlsfx.dialog.Dialogs;
import org.itlastore.model.entity.Empleado;
import org.itlastore.model.entity.Estudiante;
import org.itlastore.model.entity.EstudianteEducacionPermanente;
import org.itlastore.model.entity.Ruta;
import org.itlastore.model.entity.Ticket;
import org.itlastore.model.entity.Usuario;
import org.itlastore.model.entity.controller.EmpleadoJpaController;
import org.itlastore.model.entity.controller.EstudianteEducacionPermanenteJpaController;
import org.itlastore.model.entity.controller.EstudianteJpaController;
import org.itlastore.model.entity.controller.RutaJpaController;
import org.itlastore.model.entity.controller.TicketJpaController;
import org.itlastore.util.Util;

/**
 * FXML Controller class
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class MainViewController implements Initializable {

    private Usuario usuario;
    private Stage stage;
    @FXML
    private Label nombreLabel;
    @FXML
    private ImageView avatar;

    /*
     CONTROLES DE FORMULARIO COMPRA DE BOLETO
     */
    @FXML
    private ComboBox<Ruta> box;
    @FXML
    private ToggleGroup tipoComprador;
    @FXML
    private TextField codigoMatriculaField;
    @FXML
    private Slider boletosSlider;

    private ObservableList<Ruta> rutas;

    @FXML
    private Label cmLabel;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        configurarRutasBox();
        configurarCompradorRadioGroup();
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    @FXML
    private void mostrarAcercaDialog(ActionEvent event)
    {
        Stage dstage = new Stage();
        try
        {
            dstage.setScene(new Scene(FXMLLoader.load(getClass().getResource("HelpDialog.fxml"))));
            dstage.setResizable(false);
            dstage.initModality(Modality.APPLICATION_MODAL);

            dstage.show();

        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salir(ActionEvent event)
    {
        Platform.exit();
    }

    @FXML
    private void openNuevoUsuarioDialog(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NuevoUsuarioDialog.fxml"));

            Parent p = loader.load();

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(p, 325, 399));

            NuevoUsuarioDialogController controller = loader.getController();
            controller.setStage(stage);

            stage.show();
        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void configurarPerfil() throws FileNotFoundException
    {
        nombreLabel.setText(usuario.getNombre() + " " + usuario.getApellido());
        avatar.setImage(new Image(new FileInputStream(usuario.getImagenUsuario())));
    }

    @FXML
    private void openEstadisticasDialog(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EstadisticasView.fxml"));
        try
        {
            Parent node = loader.load();
            Scene mainScene = stage.getScene();
            stage.setScene(new Scene(node));
            EstadisticasViewController controller = loader.getController();
            controller.setHomeScene(mainScene);
            controller.setStage(stage);

        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openNuevaRutaDialog(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NuevaRutaDialog.fxml"));
        try
        {
            Parent node = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(node, 300, 150));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            NuevaRutaDialogController controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();
            rutas.add(controller.getRuta());
        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void openNuevoEnteDialog(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NuevoEnteDialog.fxml"));
            Parent node = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(node));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            NuevoEnteDialogController controller = loader.getController();
            controller.setStage(stage);

            stage.show();
        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void configurarRutasBox()
    {
        rutas = FXCollections.observableArrayList(new RutaJpaController().findAll());
        box.setItems(rutas);
    }

    private void configurarCompradorRadioGroup()
    {
        tipoComprador.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) ->
        {
            if (newValue != null)
            {
                String tipo = ((RadioButton) newValue).getText();
                cmLabel.setText(tipo.equals("Estudiante") ? "Matricula" : "Codigo");
                codigoMatriculaField.setPromptText(tipo.equals("Estudiante") ? "####-####" : "###-#######-#");
                codigoMatriculaField.setTooltip(tipo.equals("Estudiante") ? new Tooltip("Introduzca su matricula") : new Tooltip("Introduzca su cedula de identidad"));
            }
        });
    }

    @FXML
    private void cerrarSesion(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/LoginView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            LoginViewController controller = loader.getController();
            controller.setStage(stage);
            
            stage.setScene(scene);

            
        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void comprar(ActionEvent event)
    {
        String tipo = ((RadioButton) tipoComprador.getSelectedToggle()).getText();

        if (codigoMatriculaField.getText().isEmpty())
        {
            message.setText(String.format("Debe digitar su %s", tipo.equals("Estudiante") ? "matricula" : "codigo"));
        } else if ((!codigoMatriculaField.getText().matches(Util.MATRICULA_REGEX) && tipo.equals("Estudiante")) || (!codigoMatriculaField.getText().matches(Util.CODIGO_REGEX) && !tipo.equals("Estudiante")))
        {
            message.setText("Entrada no valida");
        } else
        {
            if (box.getValue() != null)
            {
                switch (tipo)
                {
                    case "Estudiante":
                    {
                        EstudianteJpaController controller = new EstudianteJpaController();

                        try
                        {
                            Estudiante estudiante = controller.findByCodigo(codigoMatriculaField.getText());
                            TicketJpaController tc = new TicketJpaController();

                            int ticketsCount = 0;

                            while (ticketsCount < (int) boletosSlider.getValue())
                            {
                                Ticket ticket = new Ticket();
                                ticket.setRuta(box.getValue());
                                ticket.setEstudiante(estudiante);

                                //Se anade ticket a la ruta y al estudiante
                                box.getValue().getTickets().add(ticket);
                                estudiante.getTickets().add(ticket);
                                tc.create(ticket);
                                ticketsCount++;
                            }

                            codigoMatriculaField.clear();
                            message.setText("");

                            Dialogs.create().title(String.format("Se compro %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets")).masthead(String.format("Se compro %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets")).message(String.format("%s %s %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets", (ticketsCount == 1) ? "fue" : "fueron", (ticketsCount == 1) ? "vendido" : "vendidos")).showInformation();

                        } catch (NoResultException nre)
                        {
                            Action action = Dialogs.create().title("El estudiante no existe").masthead("El estudiante no esta registrado").message("Desea registrar el estudiante?").showConfirm();

                            if (action == Actions.YES)
                            {
                                openNuevoEnteDialog(null);
                            }

                        }
                    }
                    break;
                    case "Educación Permanente":
                    {
                        EstudianteEducacionPermanenteJpaController controller = new EstudianteEducacionPermanenteJpaController();

                        try
                        {
                            EstudianteEducacionPermanente estudiante = controller.findByCodigo(codigoMatriculaField.getText());
                            TicketJpaController tc = new TicketJpaController();

                            int ticketsCount = 0;

                            while (ticketsCount < (int) boletosSlider.getValue())
                            {
                                Ticket ticket = new Ticket();
                                ticket.setRuta(box.getValue());
                                ticket.setEstudianteEducacionPermante(estudiante);

                                //Se anade ticket a la ruta y al estudiante
                                box.getValue().getTickets().add(ticket);
                                estudiante.getTickets().add(ticket);
                                tc.create(ticket);
                                ticketsCount++;
                            }

                            codigoMatriculaField.clear();
                            message.setText("");

                            Dialogs.create().title(String.format("Se compro %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets")).masthead(String.format("Se compro %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets")).message(String.format("%s %s %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets", (ticketsCount == 1) ? "fue" : "fueron", (ticketsCount == 1) ? "vendido" : "vendidos")).showInformation();

                        } catch (NoResultException nre)
                        {
                            Action action = Dialogs.create().title("El estudiante de eduacion permante no existe").masthead("El estudiante de eduacion permante no esta registrado").message("Desea registrar el estudiante de eduacion permante?").showConfirm();

                            if (action == Actions.YES)
                            {
                                openNuevoEnteDialog(null);
                            }

                        }
                    }
                    break;
                    case "Empleado":
                    {
                        EmpleadoJpaController controller = new EmpleadoJpaController();

                        try
                        {
                            Empleado empleado = controller.findByCodigo(codigoMatriculaField.getText());
                            TicketJpaController tc = new TicketJpaController();

                            int ticketsCount = 0;

                            while (ticketsCount < (int) boletosSlider.getValue())
                            {
                                Ticket ticket = new Ticket();
                                ticket.setRuta(box.getValue());
                                ticket.setEmpleado(empleado);

                                //Se anade ticket a la ruta y al estudiante
                                box.getValue().getTickets().add(ticket);
                                empleado.getTickets().add(ticket);
                                tc.create(ticket);
                                ticketsCount++;
                            }

                            codigoMatriculaField.clear();
                            message.setText("");

                            Dialogs.create().title(String.format("Se compro %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets")).masthead(String.format("Se compro %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets")).message(String.format("%s %s %s %s", ticketsCount, (ticketsCount == 1) ? "ticket" : "tickets", (ticketsCount == 1) ? "fue" : "fueron", (ticketsCount == 1) ? "vendido" : "vendidos")).showInformation();

                        } catch (NoResultException nre)
                        {
                            Action action = Dialogs.create().title("El empleado no existe").masthead("El empleado no esta registrado").message("Desea registrar el empleado?").showConfirm();

                            if (action == Actions.YES)
                            {
                                openNuevoEnteDialog(null);
                            }

                        }
                    }
                    break;
                }
            } else
            {
                message.setText("Seleccione la ruta");
            }
        }
    }
}
