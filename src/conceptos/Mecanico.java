package conceptos;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase que representa un Mecánico del taller
 * Contiene la información del mecánico y los servicios que puede realizar
 */
public class Mecanico {
    
    private String id;
    private String nombre;
    private String puesto;
    private List<Servicio> serviciosValidados;
    
    /**
     * Constructor por defecto
     * Inicializa la lista de servicios validados como ArrayList vacío
     */
    public Mecanico() {
        this.serviciosValidados = new ArrayList<>();
    }
    
    /**
     * Constructor con todos los parámetros
     * @param id Identificador único del mecánico
     * @param nombre Nombre completo del mecánico
     * @param puesto Puesto o cargo del mecánico
     * @param serviciosValidados Lista de servicios que el mecánico puede realizar
     */
    public Mecanico(String id, String nombre, String puesto, List<Servicio> serviciosValidados) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.serviciosValidados = serviciosValidados != null ? serviciosValidados : new ArrayList<>();
    }
    
    // Getters y Setters
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
    
    public List<Servicio> getServiciosValidados() {
        return serviciosValidados;
    }
    
    public void setServiciosValidados(List<Servicio> serviciosValidados) {
        this.serviciosValidados = serviciosValidados != null ? serviciosValidados : new ArrayList<>();
    }
    
    /**
     * Método para agregar un servicio a la lista de servicios validados
     * @param servicio El servicio a agregar
     */
    public void agregarServicio(Servicio servicio) {
        if (servicio != null && !this.serviciosValidados.contains(servicio)) {
            this.serviciosValidados.add(servicio);
        }
    }
    
    /**
     * Método para remover un servicio de la lista de servicios validados
     * @param servicio El servicio a remover
     */
    public void removerServicio(Servicio servicio) {
        this.serviciosValidados.remove(servicio);
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
