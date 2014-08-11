/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.model.entity.controller;

import java.io.Serializable;
import org.itlastore.model.entity.EstudianteEducacionPermanente;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class EstudianteEducacionPermanenteJpaController extends AbstractController<EstudianteEducacionPermanente> implements Serializable {

    public EstudianteEducacionPermanenteJpaController()
    {
        super(EstudianteEducacionPermanente.class);
    }

}
