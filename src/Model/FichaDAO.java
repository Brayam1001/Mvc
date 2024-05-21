
package Model;
import java.sql.*;
import java.util.*;
public class FichaDAO {
   
PreparedStatement pss;
ResultSet rss;
Connection conexion;
Connections conectar = Connections.getInstance();
  

public List ListarFi(){
    List<Ficha> datoss = new ArrayList<>();
    try {
        conexion = conectar.conectar();
        pss = conexion.prepareStatement("SELECT*FROM ficha");
        rss = pss.executeQuery();
        while (rss.next()) {            
            Ficha fi = new Ficha();
            fi.setIdficha(rss.getInt(1));
            fi.setNumeroficha(rss.getString(2));
            fi.setNombreficha(rss.getString(3));
            fi.setSede(rss.getString(4));
            fi.setCiudad(rss.getString(5));
            fi.setIdapre(rss.getInt(6));
            
            datoss.add(fi);
                
       }
    } catch (SQLException e) {
    }
    return datoss;
}
 public List<Object[]> Listartodo() {
        List<Object[]> datosCompletos = new ArrayList<>();

        try {
            conexion = conectar.conectar();
            pss = conexion.prepareStatement("SELECT f.numeroficha, f.nombreficha, a.name, f.sede FROM ficha f INNER JOIN aprendiz a ON f.id_aprendiz = a.id");
            rss = pss.executeQuery();

            while (rss.next()) {
                Ficha fi = new Ficha();
                Aprendiz ap = new Aprendiz();
                
                fi.setNumeroficha(rss.getString(1));
                fi.setNombreficha(rss.getString(2));
                ap.setNombre(rss.getString(3));
                fi.setSede(rss.getString(4));
              

                datosCompletos.add(new Object[]{fi, ap});
            }
        } catch (SQLException e) {
        } 

        return datosCompletos;
    }

        
        
        
    public int AgregarFi(Ficha fic){
    int rrr = 0;
    String sql = "INSERT INTO  ficha(numeroficha,nombreficha,sede,ciudad,id_aprendiz) VALUES (?,?,?,?,?)";
    try {
        conexion = conectar.conectar();
        pss = conexion.prepareStatement(sql);
        
        pss.setString(1,fic.getNumeroficha());
        pss.setString(2,fic.getNombreficha());
        pss.setString(3,fic.getSede());
        pss.setString(4,fic.getCiudad());
        pss.setInt(5, fic.getIdapre());
        
        rrr= pss.executeUpdate();
    }
         catch (Exception e) {
        }
        return rrr;
        
    }
    
 public int ActualizarFi (Ficha fic){
     
      int r = 0;
    String sql = "UPDATE ficha SET numeroficha=?,nombreficha=?,sede=?,ciudad=?,id_aprendiz=? WHERE id_ficha=?";
    
      try {
        conexion = conectar.conectar();
        pss = conexion.prepareStatement(sql);
        
        pss.setString(1,fic.getNumeroficha());
        pss.setString(2,fic.getNombreficha());
        pss.setString(3,fic.getSede());
        pss.setString(4,fic.getCiudad());
        pss.setInt(5, fic.getIdapre());
        pss.setInt(6, fic.getIdficha());
        
        
        r= pss.executeUpdate();
          if (r==1) {
              return 1;
          } else {
              return 0;
          }
        
        
    }
         catch (SQLException e) {
        }
        return r;
        
    }
     
  public int EliminarFi (int ida) {
    int r = 0;
    String sql = "DELETE FROM ficha WHERE id_ficha=" + ida;
    try {
        conexion = conectar.conectar();
        pss = conexion.prepareStatement(sql);
        r = pss.executeUpdate();
        
    } catch (SQLException e) {
    }
    return r;
} 
 
 
 
 
 
 
 
 
 
 }   
    
    
    
    
    

