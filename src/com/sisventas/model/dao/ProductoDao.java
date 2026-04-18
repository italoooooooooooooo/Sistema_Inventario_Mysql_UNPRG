package com.sisventas.model.dao;

import com.sisventas.db.ConexionMySQL;
import com.sisventas.model.beans.Categoria;
import com.sisventas.model.beans.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    public boolean insertar(Producto producto) throws Exception {
        String sql = "INSERT INTO productos (id_producto, nombre, precio, stock, id_categoria) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getCategoria().getIdCategoria());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean modificar(Producto producto) throws Exception {
        String sql = "UPDATE productos "
                   + "SET nombre = ?, precio = ?, stock = ?, id_categoria = ? "
                   + "WHERE id_producto = ?";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setInt(4, producto.getCategoria().getIdCategoria());
            ps.setInt(5, producto.getIdProducto());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Producto> listar() throws Exception {
        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.stock, "
                   + "c.id_categoria, c.nombre AS nombre_categoria "
                   + "FROM productos p "
                   + "INNER JOIN categorias c ON p.id_categoria = c.id_categoria "
                   + "ORDER BY p.id_producto";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapeaProducto(rs));
            }
        }

        return lista;
    }

    public Producto obtener(int id) throws Exception {
        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.stock, "
                   + "c.id_categoria, c.nombre AS nombre_categoria "
                   + "FROM productos p "
                   + "INNER JOIN categorias c ON p.id_categoria = c.id_categoria "
                   + "WHERE p.id_producto = ?";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapeaProducto(rs);
                }
            }
        }

        return null;
    }

    private Producto mapeaProducto(ResultSet rs) throws Exception {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("id_categoria"));
        categoria.setNombre(rs.getString("nombre_categoria"));

        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("id_producto"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setStock(rs.getInt("stock"));
        producto.setCategoria(categoria);

        return producto;
    }
}
