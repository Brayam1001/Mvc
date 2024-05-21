
package Model;

import java.util.Date;

public class Aprendiz {
  int id;
  String tipodoc;
  String numerodoc;
  String nombre;
  Date fechanacimi;
  String genero;
  String ciudad;

    public Aprendiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getNumerodoc() {
        return numerodoc;
    }

    public void setNumerodoc(String numerodoc) {
        this.numerodoc = numerodoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechanacimi() {
        return fechanacimi;
    }

    public void setFechanacimi(Date fechanacimi) {
        this.fechanacimi = fechanacimi;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
  
    
    
    
}
