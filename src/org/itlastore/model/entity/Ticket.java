/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.itlastore.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
@Entity
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Ruta ruta;
    @ManyToOne()
    private Estudiante estudiante;
    @ManyToOne()
    private Empleado empleado;
    @ManyToOne()
    private EstudianteEducacionPermanente estudianteEducacionPermante;
    
    public Ruta getRuta()
    {
        return ruta;
    }

    public Estudiante getEstudiante()
    {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante)
    {
        this.estudiante = estudiante;
    }

    public Empleado getEmpleado()
    {
        return empleado;
    }

    public void setEmpleado(Empleado empleado)
    {
        this.empleado = empleado;
    }

    public EstudianteEducacionPermanente getEstudianteEducacionPermante()
    {
        return estudianteEducacionPermante;
    }

    public void setEstudianteEducacionPermante(EstudianteEducacionPermanente estudianteEducacionPermante)
    {
        this.estudianteEducacionPermante = estudianteEducacionPermante;
    }

    public void setRuta(Ruta ruta)
    {
        this.ruta = ruta;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket))
        {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "org.itlastore.model.entity.Boleto[ id=" + id + " ]";
    }
    
}
