/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.itlastore.model.entity.controller;

import org.itlastore.model.entity.Ticket;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class TicketJpaController extends AbstractController<Ticket> {

    public TicketJpaController()
    {
        super(Ticket.class);
    }
    
}
