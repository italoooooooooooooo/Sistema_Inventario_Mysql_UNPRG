package com.sistemventa.UI;

import com.sisventas.utils.ReporteCategoria;
import com.sisventas.utils.ReporteCliente;
import com.sisventas.utils.ReporteProducto;
import com.sisventas.utils.ReporteVentas;
import java.awt.Dimension;
import javax.swing.JOptionPane;

public class FrmPrincipal extends javax.swing.JFrame {

    public FrmPrincipal() {
        initComponents();
        this.setSize(new Dimension(1200, 700));
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setTitle("Principal - SISTEMA DE VENTAS");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuUsuario = new javax.swing.JMenu();
        mnuItemUsuario = new javax.swing.JMenuItem();
        MenuCategoria = new javax.swing.JMenu();
        mnuItemNuevaCategoria = new javax.swing.JMenuItem();
        MenuProducto = new javax.swing.JMenu();
        mnuItemNuevoProducto = new javax.swing.JMenuItem();
        MenuCliente = new javax.swing.JMenu();
        mnuItemNuevoCliente = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mnuItemNuevaVenta = new javax.swing.JMenuItem();
        MenuReportes = new javax.swing.JMenu();
        mnuItemReportProducto = new javax.swing.JMenuItem();
        mnuItemReportCateg = new javax.swing.JMenuItem();
        mnuItemReportCliente = new javax.swing.JMenuItem();
        mnuItemReportVenta = new javax.swing.JMenuItem();
        MenuConfi = new javax.swing.JMenu();
        mnuItemReportConfiguracion = new javax.swing.JMenuItem();
        MenuCerrarSesion = new javax.swing.JMenu();
        mnuItemCerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SISTEMA DE VENTAS!!");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/UNPRG (1).jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addGap(85, 85, 85)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel11)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(336, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        MenuUsuario.setBackground(new java.awt.Color(0, 0, 0));
        MenuUsuario.setForeground(new java.awt.Color(0, 0, 0));
        MenuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/trabajando (1).png"))); // NOI18N
        MenuUsuario.setText("Usuario");
        MenuUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuUsuario.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N

        mnuItemUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/trabajando (1).png"))); // NOI18N
        mnuItemUsuario.setText("Nuevo Usuario");
        mnuItemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemUsuarioActionPerformed(evt);
            }
        });
        MenuUsuario.add(mnuItemUsuario);

        jMenuBar1.add(MenuUsuario);

        MenuCategoria.setBackground(new java.awt.Color(0, 0, 0));
        MenuCategoria.setForeground(new java.awt.Color(0, 0, 0));
        MenuCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/004-inventario.png"))); // NOI18N
        MenuCategoria.setText("Categoria");
        MenuCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuCategoria.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N

        mnuItemNuevaCategoria.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemNuevaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/004-inventario.png"))); // NOI18N
        mnuItemNuevaCategoria.setText("Nueva Categoria");
        mnuItemNuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemNuevaCategoriaActionPerformed(evt);
            }
        });
        MenuCategoria.add(mnuItemNuevaCategoria);

        jMenuBar1.add(MenuCategoria);

        MenuProducto.setBackground(new java.awt.Color(0, 0, 0));
        MenuProducto.setForeground(new java.awt.Color(0, 0, 0));
        MenuProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/005-caja.png"))); // NOI18N
        MenuProducto.setText("Producto");
        MenuProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuProducto.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N

        mnuItemNuevoProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/005-caja.png"))); // NOI18N
        mnuItemNuevoProducto.setText(" Nuevo Producto");
        mnuItemNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemNuevoProductoActionPerformed(evt);
            }
        });
        MenuProducto.add(mnuItemNuevoProducto);

        jMenuBar1.add(MenuProducto);

        MenuCliente.setBackground(new java.awt.Color(0, 0, 0));
        MenuCliente.setForeground(new java.awt.Color(0, 0, 0));
        MenuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/006-hombre.png"))); // NOI18N
        MenuCliente.setText("Cliente");
        MenuCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuCliente.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N

        mnuItemNuevoCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/006-hombre.png"))); // NOI18N
        mnuItemNuevoCliente.setText("Nuevo Cliente");
        mnuItemNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemNuevoClienteActionPerformed(evt);
            }
        });
        MenuCliente.add(mnuItemNuevoCliente);

        jMenuBar1.add(MenuCliente);

        jMenu6.setBackground(new java.awt.Color(0, 0, 0));
        jMenu6.setForeground(new java.awt.Color(0, 0, 0));
        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/012-factura.png"))); // NOI18N
        jMenu6.setText("Venta");
        jMenu6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenu6.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N

        mnuItemNuevaVenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/014-verificar.png"))); // NOI18N
        mnuItemNuevaVenta.setText("Nueva Venta");
        mnuItemNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemNuevaVentaActionPerformed(evt);
            }
        });
        jMenu6.add(mnuItemNuevaVenta);

        jMenuBar1.add(jMenu6);

        MenuReportes.setBackground(new java.awt.Color(0, 0, 0));
        MenuReportes.setForeground(new java.awt.Color(0, 0, 0));
        MenuReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/016-inmigracion.png"))); // NOI18N
        MenuReportes.setText("Reportes");
        MenuReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuReportes.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N

        mnuItemReportProducto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemReportProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/005-caja.png"))); // NOI18N
        mnuItemReportProducto.setText("Reporte Productos ");
        mnuItemReportProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemReportProductoActionPerformed(evt);
            }
        });
        MenuReportes.add(mnuItemReportProducto);

        mnuItemReportCateg.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemReportCateg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/004-inventario.png"))); // NOI18N
        mnuItemReportCateg.setText("Reporte Categorias");
        mnuItemReportCateg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemReportCategActionPerformed(evt);
            }
        });
        MenuReportes.add(mnuItemReportCateg);

        mnuItemReportCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemReportCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/006-hombre.png"))); // NOI18N
        mnuItemReportCliente.setText("Reporte Clientes");
        mnuItemReportCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemReportClienteActionPerformed(evt);
            }
        });
        MenuReportes.add(mnuItemReportCliente);

        mnuItemReportVenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemReportVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/012-factura.png"))); // NOI18N
        mnuItemReportVenta.setText("Reporte Ventas");
        mnuItemReportVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemReportVentaActionPerformed(evt);
            }
        });
        MenuReportes.add(mnuItemReportVenta);

        jMenuBar1.add(MenuReportes);

        MenuConfi.setBackground(new java.awt.Color(0, 0, 0));
        MenuConfi.setForeground(new java.awt.Color(0, 0, 0));
        MenuConfi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/001-configuraciones.png"))); // NOI18N
        MenuConfi.setText("Configuración");
        MenuConfi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuConfi.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N

        mnuItemReportConfiguracion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemReportConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/001-configuraciones.png"))); // NOI18N
        mnuItemReportConfiguracion.setText("Configuración");
        mnuItemReportConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemReportConfiguracionActionPerformed(evt);
            }
        });
        MenuConfi.add(mnuItemReportConfiguracion);

        jMenuBar1.add(MenuConfi);

        MenuCerrarSesion.setBackground(new java.awt.Color(0, 0, 0));
        MenuCerrarSesion.setForeground(new java.awt.Color(0, 0, 0));
        MenuCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/017-cerrar-sesion.png"))); // NOI18N
        MenuCerrarSesion.setText("Salir");
        MenuCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        MenuCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCerrarSesionActionPerformed(evt);
            }
        });

        mnuItemCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mnuItemCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/017-cerrar-sesion.png"))); // NOI18N
        mnuItemCerrarSesion.setText("Salir");
        mnuItemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCerrarSesionActionPerformed(evt);
            }
        });
        MenuCerrarSesion.add(mnuItemCerrarSesion);

        jMenuBar1.add(MenuCerrarSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuItemNuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemNuevaCategoriaActionPerformed
        try {
            FrmRegistrarCategoria FrmCategoria = new FrmRegistrarCategoria(this, true);
            FrmCategoria.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mnuItemNuevaCategoriaActionPerformed

    private void mnuItemNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemNuevaVentaActionPerformed
        FrmVentas FrmVentas = new FrmVentas(this, true);
        FrmVentas.setVisible(true);
    }//GEN-LAST:event_mnuItemNuevaVentaActionPerformed

    private void mnuItemReportProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemReportProductoActionPerformed
        try {
            ReporteProducto.generarReporProductos();
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mnuItemReportProductoActionPerformed

    private void mnuItemReportCategActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemReportCategActionPerformed
        try {
            ReporteCategoria.generarReporteCategoria();
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mnuItemReportCategActionPerformed

    private void mnuItemReportClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemReportClienteActionPerformed
        try {
            ReporteCliente.generarReportesClientes();
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mnuItemReportClienteActionPerformed

    private void mnuItemReportVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemReportVentaActionPerformed
        try {
            ReporteVentas.generarReportePedido();
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mnuItemReportVentaActionPerformed

    private void mnuItemNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemNuevoProductoActionPerformed
        FrmRegistrarProducto frmRegProducto = new FrmRegistrarProducto(this, true);
        frmRegProducto.setVisible(true);
    }//GEN-LAST:event_mnuItemNuevoProductoActionPerformed

    private void mnuItemNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemNuevoClienteActionPerformed
        FrmRegistrarCliente frmRegCliente = new FrmRegistrarCliente(this, true);
        frmRegCliente.setVisible(true);
    }//GEN-LAST:event_mnuItemNuevoClienteActionPerformed

    private void mnuItemReportConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemReportConfiguracionActionPerformed
        FrmEmpresa frmConfiguracion = new FrmEmpresa(this, true);
        frmConfiguracion.setVisible(true);
    }//GEN-LAST:event_mnuItemReportConfiguracionActionPerformed

    private void MenuCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCerrarSesionActionPerformed

    }//GEN-LAST:event_MenuCerrarSesionActionPerformed

    private void mnuItemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCerrarSesionActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea cerrar sesión?",
                "Confirmar cierre de sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_mnuItemCerrarSesionActionPerformed

    private void mnuItemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemUsuarioActionPerformed
        try {
            FrmRegistrarUsuario frmUsuario = new FrmRegistrarUsuario(this, true);
            frmUsuario.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mnuItemUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuCategoria;
    private javax.swing.JMenu MenuCerrarSesion;
    private javax.swing.JMenu MenuCliente;
    private javax.swing.JMenu MenuConfi;
    private javax.swing.JMenu MenuProducto;
    private javax.swing.JMenu MenuReportes;
    private javax.swing.JMenu MenuUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JMenuItem mnuItemCerrarSesion;
    private javax.swing.JMenuItem mnuItemNuevaCategoria;
    private javax.swing.JMenuItem mnuItemNuevaVenta;
    private javax.swing.JMenuItem mnuItemNuevoCliente;
    private javax.swing.JMenuItem mnuItemNuevoProducto;
    private javax.swing.JMenuItem mnuItemReportCateg;
    private javax.swing.JMenuItem mnuItemReportCliente;
    private javax.swing.JMenuItem mnuItemReportConfiguracion;
    private javax.swing.JMenuItem mnuItemReportProducto;
    private javax.swing.JMenuItem mnuItemReportVenta;
    private javax.swing.JMenuItem mnuItemUsuario;
    // End of variables declaration//GEN-END:variables
}
