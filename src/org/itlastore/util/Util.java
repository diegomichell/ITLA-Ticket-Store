/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itlastore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 */
public final class Util {

    public static final double Redondear(double cantidad)
    {
        return Math.rint(cantidad * 100) / 100;
    }

    public static final String MATRICULA_REGEX = "^\\d{4}-\\d{4}$";
    public static final String CODIGO_REGEX = "^\\d{3}-\\d{7}-\\d{1}$";

    public static String saveImg(File file)
    {
        String saveDir = System.getProperty("user.home") + "/ITLA_Store_Img/" + file.getName();
        try
        {
            FileInputStream freader = new FileInputStream(file);
            FileOutputStream fwriter = new FileOutputStream(saveDir);
            int i = 0;

            while ((i = freader.read()) != -1)
            {
                fwriter.write(i);
            }

        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }

        return saveDir;
    }
}
