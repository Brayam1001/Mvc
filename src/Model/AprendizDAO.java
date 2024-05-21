
package Model;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class AprendizDAO {

PreparedStatement ps;
ResultSet rs;
Connection conexion;
Connections conectar = Connections.getInstance();

public List Listar(){
    List<Aprendiz> datos = new ArrayList<>();
    try {
        conexion = conectar.conectar();
        ps = conexion.prepareStatement("SELECT*FROM aprendiz");
        rs = ps.executeQuery();
        while (rs.next()) {            
            Aprendiz ap = new Aprendiz();
            ap.setId(rs.getInt(1));
            ap.setTipodoc(rs.getString(2));
            ap.setNumerodoc(rs.getString(3));
            ap.setNombre(rs.getString(4));
            ap.setFechanacimi(rs.getDate(5));
            ap.setGenero(rs.getString(6));
            ap.setCiudad(rs.getString(7));
            
            datos.add(ap);
                    
                    
                    
        }
    } catch (SQLException e) {
    }
    return datos;
}
public int Agregar(Aprendiz apre){
    int r = 0;
    String sql = "INSERT INTO  aprendiz(typedoc,docnumber,name,birthdate,gender,city) VALUES (?,?,?,?,?,?)";
    try {
        conexion = conectar.conectar();
        ps = conexion.prepareStatement(sql);
        ps.setString(1, getTipoDocIndex(apre.getTipodoc()));
        ps.setString(2, apre.getNumerodoc());
        ps.setString(3, apre.getNombre());
        java.util.Date fecha = apre.getFechanacimi();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formato = sdf.format(fecha);
        ps.setString(4, formato);
        ps.setString(5, apre.getGenero());
        ps.setString(6, apre.getCiudad());
        
        r = ps.executeUpdate();
        
    } catch (Exception e) {
    }
    return r;
}

public int Actualizar(Aprendiz apre){
    int r = 0;
    String sql = "UPDATE aprendiz SET typedoc=?,docnumber=?,name=?,birthdate=?,gender=?,city=? WHERE id=?";
    try {
        conexion = conectar.conectar();
        ps = conexion.prepareStatement(sql);
        ps.setString(1, getTipoDocIndex(apre.getTipodoc()));
        ps.setString(2, apre.getNumerodoc());
        ps.setString(3, apre.getNombre());
        java.util.Date fecha = apre.getFechanacimi();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formato = sdf.format(fecha);
        ps.setString(4, formato);
        ps.setString(5, apre.getGenero());
        ps.setString(6, apre.getCiudad());
        ps.setInt(7, apre.getId());
        
        r = ps.executeUpdate();
        
        if (r==1) {
            return 1;
        }else {
        return 0;
        }
        
    } catch (SQLException e) {
    }
    return r;
}


public int Eliminar (int ida) {
    int r = 0;
    String sql = "DELETE FROM aprendiz WHERE id=" + ida;
    try {
        conexion = conectar.conectar();
        ps = conexion.prepareStatement(sql);
        r = ps.executeUpdate();
        
    } catch (SQLException e) {
    }
    return r;
}


private String getTipoDocIndex(String tipodoc){
    return switch(tipodoc){
        case "Cedula Ciudadania" -> "CC";
        case "Cedula Extranjeria" -> "CE";
        case "Tarjeta de Identidad"-> "TI";
        case "Registro Civil"-> "RC";
        case "Pasaporte" -> "PS";
        default ->"";};
    
    
    
    
}
private String sede (String sede){
    return switch(sede){
        case "Cedula Ciudadania" -> "CC";
        case "Cedula Extranjeria" -> "CE";
        case "Tarjeta de Identidad"-> "TI";
        case "Registro Civil"-> "RC";
        case "Pasaporte" -> "PS";
        default ->"";};
    
    
    
    
}
    
}
