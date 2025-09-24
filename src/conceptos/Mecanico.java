package conceptos;

import java.util.ArrayList;

public class Mecanico {
    String id;
    String nombre;
    String puesto;
    ArrayList<Servicio> serviciosValidados = new ArrayList<>();

    public Mecanico(String id, String nombre, String puesto) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public ArrayList<Servicio> getServiciosValidados() {
        return serviciosValidados;
    }

    public void setServiciosValidados(ArrayList<Servicio> serviciosValidados) {
        this.serviciosValidados = serviciosValidados;
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", puesto='" + puesto + '\'' +
                ", serviciosValidados=" + serviciosValidados +
                '}';
    }
}
