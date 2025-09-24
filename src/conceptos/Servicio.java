package conceptos;

import java.math.BigDecimal;

/**
 * Clase que representa un Servicio del taller mecánico
 * Contiene la información del servicio y su precio
 */
public class Servicio {
    
    private String id;
    private String nombre;
    private BigDecimal precio;
    
    /**
     * Constructor por defecto
     */
    public Servicio() {
    }
    
    /**
     * Constructor con todos los parámetros
     * @param id Identificador único del servicio
     * @param nombre Nombre del servicio
     * @param precio Precio del servicio
     */
    public Servicio(String id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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
    
    public BigDecimal getPrecio() {
        return precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return "Servicio{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
