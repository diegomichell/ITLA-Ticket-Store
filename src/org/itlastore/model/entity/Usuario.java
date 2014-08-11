/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30,unique = true)
    private String nombreUsuario;
    @Column(nullable = false, length = 64)
    private String password;
    @Column(nullable = false, length = 30)
    private String nombre;
    @Column(nullable = false, length = 30)
    private String apellido;
    private String imagenUsuario;

    public Usuario(String nombreUsuario, String password, String nombre, String apellido, String imagenUsuario)
    {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.imagenUsuario = imagenUsuario;
    }

    public Usuario()
    {

    }

    public String getNombreUsuario()
    {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario)
    {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String getImagenUsuario()
    {
        return imagenUsuario;
    }

    public void setImagenUsuario(String imagenUsuario)
    {
        this.imagenUsuario = imagenUsuario;
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
        if (!(object instanceof Usuario))
        {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "org.itlastore.model.entity.Usuario[ id=" + id + " ]";
    }

}
