/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
@Entity
public class Estudiante extends Ente implements Serializable {

    @OneToMany(mappedBy = "estudiante")
    private List<Ticket> tickets = new ArrayList<>();

    /**
     * Constructor por defecto
     */
    public Estudiante()
    {

    }

    /**
     *
     * @param nombre
     * @param apellido
     * @param matricula
     */
    public Estudiante(String nombre, String apellido, String matricula)
    {
        super(nombre, apellido, matricula);
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante))
        {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "org.itlastore.model.entity.Estudiante[ id=" + getId() + " ]";
    }

    public List<Ticket> getTickets()
    {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }
}
