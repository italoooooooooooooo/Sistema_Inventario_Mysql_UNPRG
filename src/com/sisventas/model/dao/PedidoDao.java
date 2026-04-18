package com.sisventas.model.dao;

import com.sisventas.db.ConexionMySQL;
import com.sisventas.model.beans.Categoria;
import com.sisventas.model.beans.Cliente;
import com.sisventas.model.beans.ItemPedido;
import com.sisventas.model.beans.Pedido;
import com.sisventas.model.beans.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

    public boolean insertar(Pedido pedido) throws Exception {
        String sqlPedidos = "INSERT INTO pedidos (fecha, id_cliente, subtotal, descuento, total, efectivo, cambio) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sqlDetalle = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_venta, importe) "
                + "VALUES (?, ?, ?, ?, ?)";

        String sqlStock = "UPDATE productos SET stock = stock - ? WHERE id_producto = ? AND stock >= ?";

        Connection cn = null;

        try {
            cn = ConexionMySQL.getConnection();
            cn.setAutoCommit(false);

            try (PreparedStatement psPedidos = cn.prepareStatement(sqlPedidos, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement psDetalle = cn.prepareStatement(sqlDetalle);
                 PreparedStatement psStock = cn.prepareStatement(sqlStock)) {

                psPedidos.setTimestamp(1, new Timestamp(pedido.getFecha().getTime()));
                psPedidos.setInt(2, pedido.getCliente().getIdCliente());
                psPedidos.setDouble(3, pedido.getSubtotal());
                psPedidos.setDouble(4, pedido.getDescuento());
                psPedidos.setDouble(5, pedido.getTotal());
                psPedidos.setDouble(6, pedido.getEfectivo());
                psPedidos.setDouble(7, pedido.getCambio());
                psPedidos.executeUpdate();
            try (ResultSet rsKeys = psPedidos.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                    int idGenerado = rsKeys.getInt(1);
                    pedido.setIdPedido(idGenerado);
                } else {
                    throw new Exception("No se pudo obtener el ID generado del pedido");
            }
        }    
                
                for (ItemPedido item : pedido.getItemPedido()) {
                    double importe = item.getCantidad() * item.getPrecioVenta();

                    psDetalle.setInt(1, pedido.getIdPedido());
                    psDetalle.setInt(2, item.getProducto().getIdProducto());
                    psDetalle.setInt(3, item.getCantidad());
                    psDetalle.setDouble(4, item.getPrecioVenta());
                    psDetalle.setDouble(5, importe);
                    psDetalle.executeUpdate();
                    
               

                    psStock.setInt(1, item.getCantidad());
                    psStock.setInt(2, item.getProducto().getIdProducto());
                    psStock.setInt(3, item.getCantidad());
                    
                    int filasAfectadas = psStock.executeUpdate();

                    if (filasAfectadas == 0) {
                    throw new Exception("Stock insuficiente para el producto: " 
                    + item.getProducto().getNombre());
                   }
                }

                cn.commit();
                return true;
            } catch (Exception ex) {
                if (cn != null) {
                    cn.rollback();
                }
                throw ex;
            }
        } finally {
            if (cn != null) {
                cn.setAutoCommit(true);
                cn.close();
            }
        }
    }

    public boolean eliminar(int id) throws Exception {
        String sqlDetalle = "SELECT id_producto, cantidad FROM detalle_pedido WHERE id_producto = ?";
        String sqlreponer =  "UPDATE productos SET stock = stock + ? WHERE id_producto = ?";
        String sqlBorrarDetalle = "DELETE FROM detalle_pedido WHERE id_pedido = ?";
        String sqlBorrarPedido = "DELETE FROM pedidos WHERE id_pedido = ?";

        Connection cn = null;

        try {
            cn = ConexionMySQL.getConnection();
            cn.setAutoCommit(false);

            try (PreparedStatement psDetalle =  cn.prepareStatement(sqlDetalle);
                 PreparedStatement psreponer = cn.prepareStatement(sqlreponer);
                 PreparedStatement psBorrarDetalle = cn.prepareStatement(sqlBorrarDetalle);
                 PreparedStatement psBorrarPedido = cn.prepareStatement(sqlBorrarPedido)) {

                psDetalle.setInt(1, id);
                try(ResultSet  rs = psDetalle.executeQuery()) {
                  while(rs.next()){
                        int idProdudto = rs.getInt("id_producto");
                        int cantidad = rs.getInt("cantidad");
                        
                        psreponer.setInt(1, cantidad);
                        psreponer.setInt(2, idProdudto);
                        psreponer.executeUpdate();
                   }  
                } 
                
                psBorrarDetalle.setInt(1, id);
                psBorrarDetalle.executeUpdate();

                psBorrarPedido.setInt(1, id);
                boolean eliminado = psBorrarPedido.executeUpdate() > 0;

                cn.commit();
                return eliminado;
               }              
            } catch (Exception ex) {
                if (cn != null) {
                    cn.rollback();
                }
                throw ex;
        } finally {
            if (cn != null) {
                cn.setAutoCommit(true);
                cn.close();
            }
        }
    }

    public List<Pedido> listar() throws Exception {
        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT p.id_pedido, p.fecha, p.subtotal, p.descuento, p.total, p.efectivo, p.cambio, "
                + "c.id_cliente, c.nombre, c.apellido, c.dni, c.direccion, c.telefono "
                + "FROM pedidos p "
                + "INNER JOIN clientes c ON p.id_cliente = c.id_cliente "
                + "ORDER BY p.id_pedido";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPedidoCabecera(rs));
            }
        }

        return lista;
    }

    public Pedido obtener(int id) throws Exception {
        String sqlPedido = "SELECT p.id_pedido, p.fecha, p.subtotal, p.descuento, p.total, p.efectivo, p.cambio, "
                + "c.id_cliente, c.nombre, c.apellido, c.dni, c.direccion, c.telefono "
                + "FROM pedidos p "
                + "INNER JOIN clientes c ON p.id_cliente = c.id_cliente "
                + "WHERE p.id_pedido = ?";

        String sqlDetalle = "SELECT d.id_producto, d.cantidad, d.precio_venta, "
                + "pr.nombre AS nombre_producto, pr.precio, pr.stock, "
                + "ca.id_categoria, ca.nombre AS nombre_categoria "
                + "FROM detalle_pedido d "
                + "INNER JOIN productos pr ON d.id_producto = pr.id_producto "
                + "INNER JOIN categorias ca ON pr.id_categoria = ca.id_categoria "
                + "WHERE d.id_pedido = ?";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement psPedido = cn.prepareStatement(sqlPedido);
             PreparedStatement psDetalle = cn.prepareStatement(sqlDetalle)) {

            psPedido.setInt(1, id);

            try (ResultSet rsPedido = psPedido.executeQuery()) {
                if (!rsPedido.next()) {
                    return null;
                }

                Pedido pedido = mapearPedidoCabecera(rsPedido);

                psDetalle.setInt(1, id);
                try (ResultSet rsDetalle = psDetalle.executeQuery()) {
                    List<ItemPedido> items = new ArrayList<>();

                    while (rsDetalle.next()) {
                        Categoria categoria = new Categoria();
                        categoria.setIdCategoria(rsDetalle.getInt("id_categoria"));
                        categoria.setNombre(rsDetalle.getString("nombre_categoria"));

                        Producto producto = new Producto();
                        producto.setIdProducto(rsDetalle.getInt("id_producto"));
                        producto.setNombre(rsDetalle.getString("nombre_producto"));
                        producto.setPrecio(rsDetalle.getDouble("precio"));
                        producto.setStock(rsDetalle.getInt("stock"));
                        producto.setCategoria(categoria);

                        ItemPedido item = new ItemPedido();
                        item.setCantidad(rsDetalle.getInt("cantidad"));
                        item.setPrecioVenta(rsDetalle.getDouble("precio_venta"));
                        item.setProducto(producto);

                        items.add(item);
                    }

                    pedido.setItemPedido(items);
                }

                return pedido;
            }
        }
    }

    private Pedido mapearPedidoCabecera(ResultSet rs) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellidos(rs.getString("apellido"));
        cliente.setDni(rs.getString("dni"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setTelefono(rs.getString("telefono"));

        Pedido pedido = new Pedido();
        pedido.setIdPedido(rs.getInt("id_pedido"));
        pedido.setFecha(rs.getTimestamp("fecha"));
        pedido.setCliente(cliente);
        pedido.setSubtotal(rs.getDouble("subtotal"));
        pedido.setDescuento(rs.getDouble("descuento"));
        pedido.setTotal(rs.getDouble("total"));
        pedido.setEfectivo(rs.getDouble("efectivo"));
        pedido.setCambio(rs.getDouble("cambio"));
        pedido.setItemPedido(new ArrayList<>());

        return pedido;
    }
}
