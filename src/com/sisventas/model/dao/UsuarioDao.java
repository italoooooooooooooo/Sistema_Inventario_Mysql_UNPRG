package com.sisventas.model.dao;

import com.sisventas.db.ConexionMySQL;
import com.sisventas.model.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public boolean insertar(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuarios (id_usuario, username, password_texto, nombre, correo, rol) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConexionMySQL.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPasword());
            ps.setString(4, usuario.getNombre());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getRol());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean modificar(Usuario usuario) throws Exception {
        String sql = "UPDATE usuarios "
                + "SET username = ?, password_texto = ?, nombre = ?, correo = ?, rol = ? "
                + "WHERE id_usuario = ?";

        try (Connection cn = ConexionMySQL.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPasword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getRol());
            ps.setInt(6, usuario.getIdUsuario());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection cn = ConexionMySQL.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Usuario> listar() throws Exception {
        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT id_usuario, username, password_texto, nombre, correo, rol "
                + "FROM usuarios "
                + "ORDER BY id_usuario";

        try (Connection cn = ConexionMySQL.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }

        return lista;
    }

    public Usuario obtener(int id) throws Exception {
        String sql = "SELECT id_usuario, username, password_texto, nombre, correo, rol "
                + "FROM usuarios "
                + "WHERE id_usuario = ?";

        try (Connection cn = ConexionMySQL.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        return null;
    }

    public Usuario Autenticar(String correoOusername, String password) throws Exception {
        String sql = "SELECT id_usuario, username, password_texto, nombre, correo, rol "
                + "FROM usuarios "
                + "WHERE (LOWER(username) = LOWER(?) OR LOWER(correo) = LOWER(?)) "
                + "AND password_texto = ?";

        try (Connection cn = ConexionMySQL.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, correoOusername);
            ps.setString(2, correoOusername);
            ps.setString(3, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        return null;
    }

    private Usuario mapearUsuario(ResultSet rs) throws Exception {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setUsername(rs.getString("username"));
        u.setPasword(rs.getString("password_texto"));
        u.setNombre(rs.getString("nombre"));
        u.setCorreo(rs.getString("correo"));
        u.setRol(rs.getString("rol"));
        return u;
    }
}
