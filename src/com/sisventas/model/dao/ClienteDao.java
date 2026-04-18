package com.sisventas.model.dao;

import com.sisventas.db.ConexionMySQL;
import com.sisventas.model.beans.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
  
  public boolean insertar(Cliente cliente)throws Exception{
      String sql = "INSERT INTO clientes(id_cliente, nombre, apellido, dni, direccion, telefono) "
              + "VALUES(?,?,?,?,?,?)";
      try (Connection cn = ConexionMySQL.getConnection();
           PreparedStatement ps = cn.prepareStatement(sql)) {
         ps.setInt(1, cliente.getIdCliente());
         ps.setString(2, cliente.getNombre());
         ps.setString(3, cliente.getApellidos());
         ps.setString(4, cliente.getDni());
         ps.setString(5, cliente.getDireccion());
         ps.setString(6, cliente.getTelefono());
          
         return ps.executeUpdate() > 0;
      } 
  }
  
  public boolean modificar(Cliente cliente) throws Exception{
      String sql = "UPDATE clientes "
                  + "SET nombre = ?, apellido = ? , dni = ? , direccion = ? , telefono = ? "
                  + "WHERE id_cliente  = ? ";
      try (Connection cn = ConexionMySQL.getConnection();
           PreparedStatement ps = cn.prepareStatement(sql)){
         ps.setString(1,cliente.getNombre());
         ps.setString(2,cliente.getApellidos());
         ps.setString(3,cliente.getDni());
         ps.setString(4,cliente.getDireccion());
         ps.setString(5,cliente.getTelefono());
         ps.setInt(6,cliente.getIdCliente());
         
         return ps.executeUpdate() > 0;
      } 
   }

  public boolean eliminar(int id) throws Exception{
     String  sql = "DELETE FROM clientes "
                 + "WHERE id_cliente = ?    ";
      try (Connection cn = ConexionMySQL.getConnection();
           PreparedStatement ps = cn.prepareStatement(sql)){
          
          ps.setInt(1, id);
           
          return ps.executeUpdate() >0;
      }
  }     
   
  public List<Cliente> listar() throws Exception{
      List<Cliente> lista = new ArrayList<>();
      String sql = "SELECT id_cliente , nombre, apellido, dni, direccion, telefono "
                  + "FROM clientes "
                  + "ORDER BY id_cliente";
      try(Connection cn = ConexionMySQL.getConnection();
          PreparedStatement ps = cn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery()){
        while(rs.next()){
          lista.add(mapearCliente(rs)); 
        }
      }
      return lista;
  }
  
  public Cliente obtener(int id) throws Exception{
     String sql = "SELECT id_cliente, nombre, apellido, dni, direccion, telefono "
                + "FROM clientes "
                + "WHERE id_cliente = ?";
      try (Connection cn = ConexionMySQL.getConnection();
           PreparedStatement ps = cn.prepareStatement(sql)) {
           ps.setInt(1, id);
           try(ResultSet rs = ps.executeQuery()){
               if (rs.next()) {
                 return mapearCliente(rs);
               }
          } 
      } 
      return null;
  } 
  
 private Cliente mapearCliente(ResultSet rs) throws Exception{
    Cliente cliente = new Cliente();
    cliente.setIdCliente(rs.getInt("id_cliente"));
    cliente.setNombre(rs.getString("nombre"));
    cliente.setApellidos(rs.getString("apellido"));
    cliente.setDni(rs.getString("dni"));
    cliente.setDireccion(rs.getString("direccion"));
    cliente.setTelefono(rs.getString("telefono"));
    return cliente;
 }
}