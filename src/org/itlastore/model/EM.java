/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.model;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public class EM {

    private static EM em;

    private EM()
    {
    }

    public static EM getInstance()
    {
        if (em == null)
        {
            em = new EM();
        }

        return em;
    }

    public EntityManager createEntityManager()
    {
        return  Persistence.createEntityManagerFactory("ITLA_Ticket_StorePU").createEntityManager();
    }

}
