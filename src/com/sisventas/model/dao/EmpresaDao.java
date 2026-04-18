package com.sisventas.model.dao;

import com.sisventas.db.ConexionMySQL;
import com.sisventas.model.beans.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDao {
    
    public boolean insertar(Empresa empresa) throws Exception {
        String sql = "INSERT INTO empresas (id_empresa, ruc, nombre, direccion, telefono) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, empresa.getIdEmpresa());
            ps.setString(2, empresa.getRuc());
            ps.setString(3, empresa.getNombre());
            ps.setString(4, empresa.getDireccion());
            ps.setString(5, empresa.getTelefono());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean modificar(Empresa empresa) throws Exception {
        String sql = "UPDATE empresas "
                   + "SET ruc = ?, nombre = ?, direccion = ?, telefono = ? "
                   + "WHERE id_empresa = ?";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, empresa.getRuc());
            ps.setString(2, empresa.getNombre());
            ps.setString(3, empresa.getDireccion());
            ps.setString(4, empresa.getTelefono());
            ps.setInt(5, empresa.getIdEmpresa());

            return ps.executeUpdate() > 0;
        }
    }
    
    public boolean eliminar(int id) throws Exception {
        String sql = "DELETE FROM empresas WHERE id_empresa = ?";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
    
    public List<Empresa> listar() throws Exception {
        List<Empresa> lista = new ArrayList<>();

        String sql = "SELECT id_empresa, ruc, nombre, direccion, telefono "
                   + "FROM empresas "
                   + "ORDER BY id_empresa";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEmpresa(rs));
            }
        }

        return lista;
    }
    
    public Empresa obtener(int id) throws Exception {
        String sql = "SELECT id_empresa, ruc, nombre, direccion, telefono "
                   + "FROM empresas "
                   + "WHERE id_empresa = ?";

        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearEmpresa(rs);
                }
            }
        }

        return null;
    }
    
    private Empresa mapearEmpresa(ResultSet rs) throws Exception {
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(rs.getInt("id_empresa"));
        empresa.setRuc(rs.getString("ruc"));
        empresa.setNombre(rs.getString("nombre"));
        empresa.setDireccion(rs.getString("direccion"));
        empresa.setTelefono(rs.getString("telefono"));
        return empresa;
    }
}
 