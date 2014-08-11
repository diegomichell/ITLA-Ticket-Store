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
public class EstudianteEducacionPermanente extends Ente implements Serializable {

    @OneToMany(mappedBy = "estudianteEducacionPermante")
    private List<Ticket> tickets = new ArrayList<>();

    public EstudianteEducacionPermanente()
    {
    }

    public EstudianteEducacionPermanente(String nombre, String apellido, String codigo)
    {
        super(nombre, apellido, codigo);
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
        if (!(object instanceof EstudianteEducacionPermanente))
        {
            return false;
        }
        EstudianteEducacionPermanente other = (EstudianteEducacionPermanente) object;
        return (this.getId() != null || other.getId() == null) && (this.getId() == null || this.getId().equals(other.getId()));
    }

    @Override
    public String toString()
    {
        return "org.itlastore.model.entity.EstudianteEducacionPermanente[ id=" + getId() + " ]";
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
