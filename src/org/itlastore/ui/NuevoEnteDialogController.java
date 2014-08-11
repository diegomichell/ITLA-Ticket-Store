/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javax.persistence.NoResultException;
import org.controlsfx.dialog.Dialogs;
import org.itlastore.model.EM;
import org.itlastore.model.entity.Empleado;
import org.itlastore.model.entity.Estudiante;
import org.itlastore.model.entity.EstudianteEducacionPermanente;
import org.itlastore.model.entity.controller.EmpleadoJpaController;
import org.itlastore.model.entity.controller.EstudianteEducacionPermanenteJpaController;
import org.itlastore.model.entity.controller.EstudianteJpaController;
import org.itlastore.util.Util;

/**
 * FXML Controller class
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class NuevoEnteDialogController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField codigoField;
    @FXML
    private ComboBox<String> box;
    private Stage stage;
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
        configureBox();
    }

    @FXML
    private void registrar(ActionEvent event)
    {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String codigoMatricula = codigoField.getText();

        if (box.getValue() != null)
        {
            switch (box.getValue())
            {
                case "Estudiante":
                {
                    if (nombre.isEmpty())
                    {
                        message.setText("Debe ingresar su nombre");
                    } else if (apellido.isEmpty())
                    {
                        message.setText("Debe ingresar su apellido");
                    } else if (codigoMatricula.isEmpty())
                    {
                        message.setText("Debe ingresar su " + (box.getValue().equals("Estudiante") ? "matricula" : "codigo"));
                    } else if ((!codigoMatricula.matches(Util.MATRICULA_REGEX) && box.getValue().equals("Estudiante")))
                    {
                        message.setText("Matricula no valida");
                    } else
                    {
                        try
                        {
                            Estudiante checkerEstudiante = EM.getInstance().createEntityManager().createQuery("SELECT e FROM Estudiante e WHERE e.codigo = :codigo", Estudiante.class).setParameter("codigo", codigoMatricula).getSingleResult();

                            if (!codigoMatricula.equals(checkerEstudiante.getCodigo()))
                            {
                                new EstudianteJpaController().create(new Estudiante(nombre, apellido, codigoMatricula));
                                stage.close();
                            } else
                            {
                                Dialogs.create().title("Error").masthead("No se puede registrar").message(String.format("El estudiante con matricula \"%s\" ya existe.", codigoMatricula)).showError();
                            }
                        } catch (NoResultException nre)
                        {
                            new EstudianteJpaController().create(new Estudiante(nombre, apellido, codigoMatricula));
                            stage.close();
                        }
                    }
                }
                break;
                case "Empleado":
                {
                    if (nombre.isEmpty())
                    {
                        message.setText("Debe ingresar su nombre");
                    } else if (apellido.isEmpty())
                    {
                        message.setText("Debe ingresar su apellido");
                    } else if (codigoMatricula.isEmpty())
                    {
                        message.setText("Debe ingresar su " + (box.getValue().equals("Estudiante") ? "matricula" : "codigo"));
                    } else if ((!codigoMatricula.matches(Util.CODIGO_REGEX) && !box.getValue().equals("Estudiante")))
                    {
                        message.setText("Cedula no valida");
                    } else
                    {

                        try
                        {

                            Empleado checkerEmpleado = EM.getInstance().createEntityManager().createQuery("SELECT e FROM Empleado e WHERE e.codigo = :codigo", Empleado.class).setParameter("codigo", codigoMatricula).getSingleResult();

                            if (!codigoMatricula.equals(checkerEmpleado.getCodigo()))
                            {
                                new EmpleadoJpaController().create(new Empleado(nombre, apellido, codigoMatricula));
                                stage.close();
                            } else
                            {
                                Dialogs.create().title("Error").masthead("No se puede registrar").message(String.format("El empleado con cedula \"%s\" ya existe.", codigoMatricula)).showError();
                            }
                        } catch (NoResultException nre)
                        {
                            new EmpleadoJpaController().create(new Empleado(nombre, apellido, codigoMatricula));
                            stage.close();
                        }
                    }
                }
                break;
                case "Educacion Permanente":
                {
                    if (nombre.isEmpty())
                    {
                        message.setText("Debe ingresar su nombre");
                    } else if (apellido.isEmpty())
                    {
                        message.setText("Debe ingresar su apellido");
                    } else if (codigoMatricula.isEmpty())
                    {
                        message.setText("Debe ingresar su " + (box.getValue().equals("Estudiante") ? "matricula" : "codigo"));
                    } else if ((!codigoMatricula.matches(Util.CODIGO_REGEX) && !box.getValue().equals("Estudiante")))
                    {
                        message.setText("Cedula no valida");
                    } else
                    {
                        try
                        {

                            EstudianteEducacionPermanente checkerEstudiantePermanente = EM.getInstance().createEntityManager().createQuery("SELECT e FROM EstudianteEducacionPermanente e WHERE e.codigo = :codigo", EstudianteEducacionPermanente.class).setParameter("codigo", codigoMatricula).getSingleResult();

                            if (!codigoMatricula.equals(checkerEstudiantePermanente.getCodigo()))
                            {
                                new EstudianteEducacionPermanenteJpaController().create(new EstudianteEducacionPermanente(nombre, apellido, codigoMatricula));
                                stage.close();
                            } else
                            {
                                Dialogs.create().title("Error").masthead("No se puede registrar").message(String.format("El estudiante de educación permanente con cedula \"%s\" ya existe.", codigoMatricula)).showError();
                            }
                        } catch (NoResultException nre)
                        {
                            new EstudianteEducacionPermanenteJpaController().create(new EstudianteEducacionPermanente(nombre, apellido, codigoMatricula));
                            stage.close();
                        }
                    }
                }
                break;
            }
        } else
        {
            message.setText("Elija el ente que desea registrar");
        }
    }

    private void configureBox()
    {
        box.getItems().addAll("Estudiante", "Educacion Permanente", "Empleado");

        box.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            switch (newValue)
            {
                case "Estudiante":
                {
                    cmLabel.setText("Matricula:");
                    codigoField.setPromptText("####-####");
                    codigoField.setTooltip(new Tooltip("Introduzca su matricula"));
                }
                break;
                case "Educacion Permanente":
                {
                    cmLabel.setText("Codigo:");
                    codigoField.setPromptText("###-#######-#");
                    codigoField.setTooltip(new Tooltip("Introduzca su cedula de identidad"));
                }
                break;
                case "Empleado":
                {
                    cmLabel.setText("Codigo:");
                    codigoField.setPromptText("###-#######-#");
                    codigoField.setTooltip(new Tooltip("Introduzca su cedula de identidad"));
                }
            }
        });
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
