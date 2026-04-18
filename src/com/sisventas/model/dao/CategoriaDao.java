package com.sisventas.model.dao;

import com.sisventas.db.ConexionMySQL;
import com.sisventas.model.beans.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
       
    public boolean insertar(Categoria categoria) throws Exception{
       String sql = "INSERT INTO categorias(id_categoria, nombre)"+
                     "VALUES (?,?)";
        try(Connection cn = ConexionMySQL.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql)){
           ps.setInt(1, categoria.getIdCategoria());
           ps.setString(2, categoria.getNombre());
           return ps.executeUpdate()>0;
        }
    }
    
    public boolean modificar(Categoria categoria) throws Exception{
        String sql = "UPDATE categorias SET nombre = ? WHERE id_categorias = ?";
        try(Connection cn = ConexionMySQL.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getIdCategoria());
            
            return ps.executeUpdate()>0;    
        } 
    }
    
    public boolean eliminar(int id)throws Exception{
      String sql = "DELETE FROM categorias WHERE id_categorias = ?";
        try(Connection cn = ConexionMySQL.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1, id);
            return ps.executeUpdate()>0;
            
        } 
    }
   
    public List<Categoria> listar() throws Exception{
      List<Categoria> lista = new ArrayList<>();
      String sql =  "SELECT id_categoria, nombre FROM categorias ORDER BY id_categoria";
        try (Connection cn = ConexionMySQL.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) {                
               lista.add(mapearCategoria(rs));
            }
        } 
        return lista;
    }
    
    public Categoria obtener(int id) throws Exception{
        String sql = "SELECT id_categoria, nombre FROM categorias WHERE id_categoria = ?";
        try(Connection cn = ConexionMySQL.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
               if(rs.next()){
                   return mapearCategoria(rs);
               }
            } 
        } 
        return null;
    }
    
    private Categoria mapearCategoria(ResultSet rs)throws Exception{
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("id_categoria"));
        c.setNombre(rs.getString("nombre"));
        return c;
    }
}
