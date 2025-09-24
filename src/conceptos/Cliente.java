package conceptos;

/**
 * Clase que representa un Cliente del taller mecánico
 * Contiene la información básica del cliente
 */
public class Cliente {
    
    private String id;
    private String nombre;
    private String placa;
    private String telefono;
    private String email;
    
    /**
     * Constructor por defecto
     */
    public Cliente() {
    }
    
    /**
     * Constructor con todos los parámetros
     * @param id Identificador único del cliente
     * @param nombre Nombre completo del cliente
     * @param placa Placa del vehículo
     * @param telefono Número de teléfono
     * @param email Dirección de correo electrónico
     */
    public Cliente(String id, String nombre, String placa, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.placa = placa;
        this.telefono = telefono;
        this.email = email;
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
    
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", placa='" + placa + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
