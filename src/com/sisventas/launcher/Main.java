package com.sisventas.launcher;

import com.sistemventa.UI.FrmLogin;
import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args){
     
        SwingUtilities.invokeLater(() -> {
            FrmLogin frm = new FrmLogin();
            frm.setVisible(true);
            
            System.out.println("INGRESAR CON: ");
            System.out.println("CORREO demo123@gmail.com o USERNAME Demo ");
            System.out.println("CONTRASEÑA demo123 ");
        });
    }
 
}
 