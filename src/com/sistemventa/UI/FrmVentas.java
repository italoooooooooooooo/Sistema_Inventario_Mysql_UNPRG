package com.sistemventa.UI;

import com.sisventas.model.beans.Cliente;
import com.sisventas.model.beans.Empresa;
import com.sisventas.model.beans.ItemPedido;
import com.sisventas.model.beans.Pedido;
import com.sisventas.model.beans.Producto;
import com.sisventas.model.logic.ClienteLogic;
import com.sisventas.model.logic.EmpresaLogic;
import com.sisventas.model.logic.PedidoLogic;
import com.sisventas.model.logic.ProductoLogic;
import com.sisventas.utils.GeneradorFacturaPDF;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmVentas extends javax.swing.JDialog {

    private List<ItemPedido> lstPedido = new ArrayList();

    public FrmVentas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Venta - SISTEMA DE VENTAS");
        ConfigurarComboCliente();
        CargarComboBoxCliente();
        ConfigurarComboProducto();
        CargarComboBoxProducto();
        CargarTablaVentas();
        CargarFechaActual();
        txtfecha.setEditable(false);
        txt_subtotal.setEditable(false);
        txt_totalpago.setEditable(false);
        txt_cambio.setEditable(false);
        txt_Precio.setEditable(false);
        txt_Stock.setEditable(false);
        cmb_cliente.setSelectedItem(null);
        cmb_cliente.setEditable(false);
        cmb_producto.setSelectedItem(null);
        txt_descuento.setText(String.valueOf(0.0));
    }

    private DefaultComboBoxModel modeloComboBoxCliente;
    private DefaultComboBoxModel modeloComboBoxProducto;
    private DefaultTableModel modeloTablaVentas;

    private void CargarComboBoxCliente() {
        try {
               this.modeloComboBoxCliente = ClienteLogic.getComboModelCliente();
        cmb_cliente.setModel(modeloComboBoxCliente);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"No se pudo cargar los clientes");
        }
     
    }

    private void CargarComboBoxProducto() {
        try {
            this.modeloComboBoxProducto = ProductoLogic.getComboModelProducto();
            cmb_producto.setModel(modeloComboBoxProducto);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }

    private void CargarTablaVentas() {
        this.modeloTablaVentas = new DefaultTableModel();
        modeloTablaVentas.addColumn("Producto");
        modeloTablaVentas.addColumn("Precio");
        modeloTablaVentas.addColumn("Cantidad");
        modeloTablaVentas.addColumn("Importe");
        tbl_pedidos.setModel(modeloTablaVentas);
    }

    private void CargarFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtfecha.setText(fechaActual.format(formato));
    }

    private void CalcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : lstPedido) {
            subtotal += item.getCantidad() * item.getPrecioVenta();
        }
        txt_subtotal.setText(String.valueOf(subtotal));
    }

    private void CalcularTotalPagar() {
        double subtotal = 0;
        for (ItemPedido item : lstPedido) {
            subtotal += item.getCantidad() * item.getPrecioVenta();
        }
        double descuento = 0;
        if (!txt_descuento.getText().trim().isEmpty()) {
            descuento = Double.parseDouble(txt_descuento.getText());
        }
        double total = subtotal - descuento;
        txt_subtotal.setText(String.valueOf(subtotal));
        txt_totalpago.setText(String.valueOf(total));
    }

    private void CalcularCambio() {
        double total = 0;
        double efectivo = 0;
        if (!txt_totalpago.getText().trim().isEmpty()) {
            total = Double.parseDouble(txt_totalpago.getText());
        }
        if (!txt_efectivo.getText().trim().isEmpty()) {
            efectivo = Double.parseDouble(txt_efectivo.getText());
        }
        double cambio = efectivo - total;
        txt_cambio.setText(String.valueOf(cambio));
    }

    private void ConfigurarComboCliente() {
        cmb_cliente.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value == null) {
                    setText("Seleccione el cliente:");
                } else {
                    setText(value.toString());
                }

                return this;
            }
        });
    }
    
     private void ConfigurarComboProducto() {
        cmb_producto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value == null) {
                    setText("Seleccione el producto:");
                } else {
                    setText(value.toString());
                }

                return this;
            }
        });
    }
     
     private int buscarItemPedido(int idProducto){
         for (int i = 0; i < lstPedido.size(); i++) {
            if(lstPedido.get(i).getProducto().getIdProducto()== idProducto){
                return i;
            } 
         }  
         return -1;
     }
     
     private int obtenerCantidaAgregada(int idproducto){
         int cantidadAgregada = 0;
         for (ItemPedido item : lstPedido) {
             if (item.getProducto().getIdProducto()==idproducto) {
                 cantidadAgregada+= item.getCantidad();
             }
         }
         return cantidadAgregada;
     }
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmb_cliente = new javax.swing.JComboBox<>();
        txt_buscar = new javax.swing.JTextField();
        btn_buscarProducto = new javax.swing.JButton();
        cmb_producto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_añadir = new javax.swing.JButton();
        txt_cantidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_Precio = new javax.swing.JTextField();
        txt_Stock = new javax.swing.JTextField();
        btn_buscarCliente = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_descuento = new javax.swing.JTextField();
        txt_subtotal = new javax.swing.JTextField();
        txt_totalpago = new javax.swing.JTextField();
        txt_cambio = new javax.swing.JTextField();
        txt_efectivo = new javax.swing.JTextField();
        btn_cambio = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btn_imprimirFactura = new javax.swing.JButton();
        txtfecha = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_pedidos = new javax.swing.JTable();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FACTURA");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cliente");

        cmb_cliente.setBackground(new java.awt.Color(255, 255, 255));
        cmb_cliente.setEditable(true);
        cmb_cliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmb_cliente.setForeground(new java.awt.Color(0, 0, 0));
        cmb_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione cliente:", "Item 2", "Item 3", "Item 4" }));
        cmb_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_clienteActionPerformed(evt);
            }
        });

        txt_buscar.setBackground(new java.awt.Color(255, 255, 255));
        txt_buscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_buscar.setForeground(new java.awt.Color(0, 0, 0));
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
        });

        btn_buscarProducto.setBackground(new java.awt.Color(51, 51, 51));
        btn_buscarProducto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_buscarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscarProducto.setText("Buscar Producto");
        btn_buscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarProductoActionPerformed(evt);
            }
        });

        cmb_producto.setBackground(new java.awt.Color(255, 255, 255));
        cmb_producto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmb_producto.setForeground(new java.awt.Color(0, 0, 0));
        cmb_producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione producto:", "Item 2", "Item 3", "Item 4" }));
        cmb_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_productoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Producto");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cantidad");

        btn_añadir.setBackground(new java.awt.Color(51, 51, 51));
        btn_añadir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_añadir.setForeground(new java.awt.Color(255, 255, 255));
        btn_añadir.setText("Añadir Producto");
        btn_añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_añadirActionPerformed(evt);
            }
        });

        txt_cantidad.setBackground(new java.awt.Color(255, 255, 255));
        txt_cantidad.setForeground(new java.awt.Color(0, 0, 0));
        txt_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Stock");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Precio");

        txt_Precio.setBackground(new java.awt.Color(255, 255, 255));
        txt_Precio.setForeground(new java.awt.Color(0, 0, 0));
        txt_Precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_PrecioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PrecioKeyTyped(evt);
            }
        });

        txt_Stock.setBackground(new java.awt.Color(255, 255, 255));
        txt_Stock.setForeground(new java.awt.Color(0, 0, 0));
        txt_Stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_StockKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_StockKeyTyped(evt);
            }
        });

        btn_buscarCliente.setBackground(new java.awt.Color(51, 51, 51));
        btn_buscarCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_buscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscarCliente.setText("Buscar Cliente");
        btn_buscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_buscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_buscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_añadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(cmb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btn_buscarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscarCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel11)
                        .addComponent(txt_Stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_añadir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Subtotal:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Descuento:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total a pagar:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Efectivo:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cambio:");

        txt_descuento.setBackground(new java.awt.Color(255, 255, 255));
        txt_descuento.setForeground(new java.awt.Color(0, 0, 0));
        txt_descuento.setText("0.0");
        txt_descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descuentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_descuentoKeyTyped(evt);
            }
        });

        txt_subtotal.setEditable(false);
        txt_subtotal.setBackground(new java.awt.Color(255, 255, 255));
        txt_subtotal.setForeground(new java.awt.Color(0, 0, 0));

        txt_totalpago.setEditable(false);
        txt_totalpago.setBackground(new java.awt.Color(255, 255, 255));
        txt_totalpago.setForeground(new java.awt.Color(0, 0, 0));

        txt_cambio.setEditable(false);
        txt_cambio.setBackground(new java.awt.Color(255, 255, 255));
        txt_cambio.setForeground(new java.awt.Color(0, 0, 0));
        txt_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cambioActionPerformed(evt);
            }
        });

        txt_efectivo.setBackground(new java.awt.Color(255, 255, 255));
        txt_efectivo.setForeground(new java.awt.Color(0, 0, 0));
        txt_efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_efectivoActionPerformed(evt);
            }
        });
        txt_efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_efectivoKeyTyped(evt);
            }
        });

        btn_cambio.setBackground(new java.awt.Color(51, 51, 51));
        btn_cambio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_cambio.setForeground(new java.awt.Color(255, 255, 255));
        btn_cambio.setText("Calcular cambio");
        btn_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))))
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_descuento, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(txt_subtotal)
                    .addComponent(txt_totalpago)
                    .addComponent(txt_efectivo)
                    .addComponent(txt_cambio))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cambio)
                .addGap(75, 75, 75))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_totalpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha");

        btn_imprimirFactura.setBackground(new java.awt.Color(255, 255, 255));
        btn_imprimirFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/001-imprimir.png"))); // NOI18N
        btn_imprimirFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirFacturaActionPerformed(evt);
            }
        });

        txtfecha.setBackground(new java.awt.Color(255, 255, 255));
        txtfecha.setForeground(new java.awt.Color(0, 0, 0));
        txtfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jScrollPane3.setBackground(new java.awt.Color(60, 63, 65));

        tbl_pedidos.setBackground(new java.awt.Color(255, 255, 255));
        tbl_pedidos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_pedidos.setForeground(new java.awt.Color(0, 0, 0));
        tbl_pedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbl_pedidos);

        jScrollPane3.setViewportView(jScrollPane2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(125, 125, 125)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btn_imprimirFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_imprimirFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarProductoActionPerformed
        String texto = txt_buscar.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el producto a buscar");
            return;
        }

        boolean encontrado = false;

        for (int i = 0; i < cmb_producto.getItemCount(); i++) {
            Object item = cmb_producto.getItemAt(i);
            Producto producto = (Producto) item;

            if (producto.getNombre().toLowerCase().contains(texto)) {
                cmb_producto.setSelectedIndex(i);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado");
        }
    }//GEN-LAST:event_btn_buscarProductoActionPerformed

    private void cmb_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_productoActionPerformed
       Producto producto = (Producto) cmb_producto.getSelectedItem();
        if (producto != null) {
           int cantidadAgregada = obtenerCantidaAgregada(producto.getIdProducto());
           int stockDisponible = producto.getStock() - cantidadAgregada;
           
           txt_Stock.setText(String.valueOf(stockDisponible));
           txt_Precio.setText(String.valueOf(producto.getPrecio()));
        }else{
            txt_Stock.setText("");
            txt_Precio.setText("");
        }
    }//GEN-LAST:event_cmb_productoActionPerformed

    private void btn_añadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_añadirActionPerformed
        try {
            Producto producto = (Producto) cmb_producto.getSelectedItem();
            if (producto == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto");
                return;
            }
            if (txt_cantidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad");
                return;
            }
            int cantidad = Integer.parseInt(txt_cantidad.getText());
            double precio = Double.parseDouble(txt_Precio.getText());

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0");
                return;
            }
            
            int posicion = buscarItemPedido(producto.getIdProducto());
            if(posicion != -1){
                ItemPedido itemExistente =  lstPedido.get(posicion);
                int nuevaCantidad = itemExistente.getCantidad() + cantidad;
                
                if (nuevaCantidad > producto.getStock()) {
                    JOptionPane.showMessageDialog(this, "Stock insuficiente");
                    return;
                }
                itemExistente.setCantidad(nuevaCantidad);
                
                double nuevoImporte = nuevaCantidad * itemExistente.getPrecioVenta();
                modeloTablaVentas.setValueAt(nuevaCantidad, posicion, 2 );
                modeloTablaVentas.setValueAt(nuevoImporte, posicion, 3);
            }else {
 
            if (cantidad > producto.getStock()) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente");
                return;
            }
            double importe = cantidad * precio;
            ItemPedido itemPedido = new ItemPedido(cantidad, precio, producto);
            lstPedido.add(itemPedido);

            modeloTablaVentas.addRow(new Object[]{
                producto.getNombre(),
                precio,
                cantidad,
                importe
            });
          }
            CalcularSubtotal();
            CalcularTotalPagar();
            limpiarDetalleProducto();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btn_añadirActionPerformed

    private void btn_imprimirFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirFacturaActionPerformed
        try {
            if (cmb_cliente.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente");
                return;
            }

            if (lstPedido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Agregue al menos un producto");
                return;
            }

            CalcularTotalPagar();

            CalcularCambio();

            if (txt_efectivo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el efectivo");
                return;
            }

            Empresa empresa = EmpresaLogic.obtenerEmpresa(1);
            Cliente cliente = (Cliente) cmb_cliente.getSelectedItem();

            double subtotal = Double.parseDouble(txt_subtotal.getText());
            double descuento = txt_descuento.getText().trim().isEmpty() ? 0 : Double.parseDouble(txt_descuento.getText());
            double total = Double.parseDouble(txt_totalpago.getText());
            double efectivo = txt_efectivo.getText().trim().isEmpty() ? 0 : Double.parseDouble(txt_efectivo.getText());
            double cambio = txt_cambio.getText().trim().isEmpty() ? 0 : Double.parseDouble(txt_cambio.getText());

            if (efectivo < total) {
                JOptionPane.showMessageDialog(this, "El efectivo es insuficiente");
                return;
            }

            if (cambio < 0) {
                JOptionPane.showMessageDialog(this, "El cambio no puede ser negativo");
                return;
            }

            Date fecha = new Date();

            Pedido pedido = new Pedido(0, fecha, cliente, lstPedido, total, subtotal, descuento, efectivo, cambio);
            boolean guardado = PedidoLogic.insertarPedido(pedido);
            if (guardado) {
                
                GeneradorFacturaPDF.GenerarFactura(empresa, pedido);
                JOptionPane.showMessageDialog(this, "Venta registrada correctamente");
                limpiarVenta();
                CargarComboBoxProducto();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar la venta");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btn_imprimirFacturaActionPerformed

    private void txt_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cambioActionPerformed
    }//GEN-LAST:event_txt_cambioActionPerformed

    private void txt_efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_efectivoActionPerformed
    }//GEN-LAST:event_txt_efectivoActionPerformed

    private void cmb_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_clienteActionPerformed
    }//GEN-LAST:event_cmb_clienteActionPerformed

    private void btn_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambioActionPerformed
        try {
            CalcularTotalPagar();
            if (txt_efectivo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el efectivo");
                return;
            }
            double total = Double.parseDouble(txt_totalpago.getText());
            double efectivo = Double.parseDouble(txt_efectivo.getText());

            if (total > efectivo) {
                JOptionPane.showMessageDialog(this, "El efectivo es insufiente");
                txt_efectivo.requestFocus();
                return;
            }

            CalcularCambio();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btn_cambioActionPerformed

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed

    }//GEN-LAST:event_txt_buscarKeyPressed

    private void txt_cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyPressed
        int c = evt.getKeyCode();
        if (java.awt.event.KeyEvent.VK_ENTER == c) {
            btn_añadir.requestFocus();
        }
    }//GEN-LAST:event_txt_cantidadKeyPressed

    private void txt_cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_cantidadKeyTyped

    private void txt_efectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_efectivoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        if (c == '.' && txt_efectivo.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_efectivoKeyTyped

    private void txt_descuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descuentoKeyPressed
        int c = evt.getKeyCode();
        if (java.awt.event.KeyEvent.VK_ENTER == c) {
            CalcularTotalPagar();
            txt_efectivo.requestFocus();
        }
    }//GEN-LAST:event_txt_descuentoKeyPressed

    private void txt_descuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descuentoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        if (c == '.' && txt_descuento.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_descuentoKeyTyped

    private void txt_PrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PrecioKeyPressed
    }//GEN-LAST:event_txt_PrecioKeyPressed

    private void txt_PrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PrecioKeyTyped
    }//GEN-LAST:event_txt_PrecioKeyTyped

    private void txt_StockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_StockKeyPressed
    }//GEN-LAST:event_txt_StockKeyPressed

    private void txt_StockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_StockKeyTyped
    }//GEN-LAST:event_txt_StockKeyTyped

    private void btn_buscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarClienteActionPerformed
        String texto = txt_buscar.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el cliente a buscar");
            return;
        }

        boolean encontrado = false;

        for (int i = 0; i < cmb_cliente.getItemCount(); i++) {
            Object item = cmb_cliente.getItemAt(i);
            Cliente cliente = (Cliente) item;

            if (cliente.toString().toLowerCase().contains(texto)) {
                cmb_cliente.setSelectedIndex(i);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado");
        }
    }//GEN-LAST:event_btn_buscarClienteActionPerformed

    private void txtfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaActionPerformed
    }//GEN-LAST:event_txtfechaActionPerformed


    private void limpiarDetalleProducto() {
        txt_cantidad.setText("");
        txt_Precio.setText("");
        txt_Stock.setText("");
        cmb_producto.setSelectedItem(null);
        txt_cantidad.requestFocus();
    }

    private void limpiarVenta() {
        lstPedido.clear();
        modeloTablaVentas.setRowCount(0);
        cmb_cliente.setSelectedItem(null);
        cmb_producto.setSelectedItem(null);
        txt_buscar.setText("");
        txt_cantidad.setText("");
        txt_Precio.setText("");
        txt_Stock.setText("");
        txt_subtotal.setText("");
        txt_totalpago.setText("");
        txt_efectivo.setText("");
        txt_cambio.setText("");
        txt_descuento.setText("0.0");
        txt_buscar.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_añadir;
    private javax.swing.JButton btn_buscarCliente;
    private javax.swing.JButton btn_buscarProducto;
    private javax.swing.JButton btn_cambio;
    private javax.swing.JButton btn_imprimirFactura;
    private javax.swing.JComboBox<String> cmb_cliente;
    private javax.swing.JComboBox<String> cmb_producto;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_pedidos;
    private javax.swing.JTextField txt_Precio;
    private javax.swing.JTextField txt_Stock;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_cambio;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_descuento;
    private javax.swing.JTextField txt_efectivo;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_totalpago;
    private javax.swing.JTextField txtfecha;
    // End of variables declaration//GEN-END:variables
}
