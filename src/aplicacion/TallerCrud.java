package aplicacion;

import conceptos.Cliente;
import conceptos.Mecanico;
import conceptos.Servicio;
import util.CargadorXML;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TallerCrud {

    private static final String XML_CLIENTES   = "Data/clientes.xml";
    private static final String XML_SERVICIOS  = "Data/servicios.xml";
    private static final String XML_MECANICOS  = "Data/mecanicos.xml";

    private static ArrayList<Cliente>  clientes  = new ArrayList<>();
    private static ArrayList<Servicio> servicios = new ArrayList<>();
    private static ArrayList<Mecanico> mecanicos = new ArrayList<>();

    // ====== CARGA / GUARDADO ======
    public static void loadAll() {
        clientes  = CargadorXML.Cargar(XML_CLIENTES);
        servicios = CargadorXML.CargarServicios(XML_SERVICIOS);
        mecanicos = CargadorXML.CargarMecanicos(XML_MECANICOS, servicios);
    }

    private static void saveClientes()  { CargadorXML.GuardarClientes(XML_CLIENTES, clientes); }
    private static void saveServicios() { CargadorXML.GuardarServicios(XML_SERVICIOS, servicios); }
    private static void saveMecanicos() { CargadorXML.GuardarMecanicos(XML_MECANICOS, mecanicos); }

    // ====== LISTAR ======
    public static List<Cliente>  listarClientes()  { return clientes; }
    public static List<Servicio> listarServicios() { return servicios; }
    public static List<Mecanico> listarMecanicos() { return mecanicos; }

    // ====== CLIENTE ======
    public static boolean crearCliente(Cliente c) {
        if (c == null || isBlank(c.getId()) || findCliente(c.getId()) != null) return false;
        clientes.add(c);
        saveClientes();
        return true;
    }

    public static boolean actualizarCliente(Cliente c) {
        if (c == null || isBlank(c.getId())) return false;
        Cliente cur = findCliente(c.getId());
        if (cur == null) return false;
        cur.setNombre(c.getNombre());
        cur.setPlaca(c.getPlaca());
        cur.setTelefono(c.getTelefono());
        cur.setEmail(c.getEmail());
        saveClientes();
        return true;
    }

    public static boolean eliminarCliente(String id) {
        Cliente cur = findCliente(id);
        if (cur == null) return false;
        clientes.remove(cur);
        saveClientes();
        return true;
    }

    // ====== SERVICIO ======
    public static boolean crearServicio(Servicio s) {
        if (s == null || isBlank(s.getId()) || findServicio(s.getId()) != null) return false;
        if (s.getPrecio() == null || s.getPrecio().compareTo(BigDecimal.ZERO) < 0) return false;
        servicios.add(s);
        saveServicios();
        return true;
    }

    public static boolean actualizarServicio(Servicio s) {
        if (s == null || isBlank(s.getId())) return false;
        Servicio cur = findServicio(s.getId());
        if (cur == null) return false;
        if (s.getPrecio() == null || s.getPrecio().compareTo(BigDecimal.ZERO) < 0) return false;
        cur.setNombre(s.getNombre());
        cur.setPrecio(s.getPrecio());
        saveServicios();
        return true;
    }

    public static boolean eliminarServicio(String id) {
        Servicio cur = findServicio(id);
        if (cur == null) return false;
        // quitarlo de los mecánicos también
        for (Mecanico m : mecanicos) {
            m.getServiciosValidados().removeIf(x -> x.getId().equals(id));
        }
        servicios.remove(cur);
        saveServicios();
        saveMecanicos();
        return true;
    }
    
    public static Servicio findServicioPorNombre(String nombre) {
    for (Servicio s : listarServicios()) {
        if (s.getNombre().equals(nombre)) return s;
    }
    return null;
}


    // ====== MECÁNICO ======
    public static boolean crearMecanico(Mecanico m) {
        if (m == null || isBlank(m.getId()) || findMecanico(m.getId()) != null) return false;
        if (isBlank(m.getNombre()) || isBlank(m.getPuesto())) return false;
        mecanicos.add(m);
        saveMecanicos();
        return true;
    }

    public static boolean actualizarMecanico(Mecanico m) {
        if (m == null || isBlank(m.getId())) return false;
        Mecanico cur = findMecanico(m.getId());
        if (cur == null) return false;
        cur.setNombre(m.getNombre());
        cur.setPuesto(m.getPuesto());
        saveMecanicos();
        return true;
    }

    public static boolean eliminarMecanico(String id) {
        Mecanico cur = findMecanico(id);
        if (cur == null) return false;
        mecanicos.remove(cur);
        saveMecanicos();
        return true;
    }

    // Asignar servicios por IDs (checkboxes en el futuro)
    public static boolean setServiciosDeMecanico(String mecanicoId, List<String> servicioIds) {
        Mecanico m = findMecanico(mecanicoId);
        if (m == null) return false;
        m.getServiciosValidados().clear();
        if (servicioIds != null) {
            for (String sid : servicioIds) {
                Servicio s = findServicio(sid);
                if (s != null) m.getServiciosValidados().add(s);
            }
        }
        saveMecanicos();
        return true;
    }

    // ====== Generación de IDs ======
    public static String generarIdCliente() {
        return generarIdUnico("C", clientes, Cliente::getId);
    }
    
    public static String generarIdServicio() {
        return generarIdUnico("S", servicios, Servicio::getId);
    }
    
    public static String generarIdMecanico() {
        return generarIdUnico("M", mecanicos, Mecanico::getId);
    }
    
    private static <T> String generarIdUnico(String prefijo, ArrayList<T> lista, java.util.function.Function<T, String> getId) {
        int contador = 1;
        String id;
        do {
            id = prefijo + String.format("%06d", contador);
            contador++;
            final String currentId = id; // Variable final para la lambda
            if (lista.stream().anyMatch(item -> getId.apply(item).equals(currentId))) {
                continue; // Si existe, continúa con el siguiente número
            }
            break; // Si no existe, usa este ID
        } while (true);
        return id;
    }
    
    // ====== Validación de IDs ======
    public static boolean validarIdCliente(String id) {
        return !isBlank(id) && findCliente(id) == null;
    }
    
    public static boolean validarIdServicio(String id) {
        return !isBlank(id) && findServicio(id) == null;
    }
    
    public static boolean validarIdMecanico(String id) {
        return !isBlank(id) && findMecanico(id) == null;
    }
    
    // ====== Búsqueda por ID ======
    public static Cliente buscarCliente(String id) {
        return findCliente(id);
    }
    
    public static Servicio buscarServicio(String id) {
        return findServicio(id);
    }
    
    public static Mecanico buscarMecanico(String id) {
        return findMecanico(id);
    }

    // ====== Helpers ======
    private static Cliente findCliente(String id) {
        for (Cliente c : clientes) if (c.getId().equals(id)) return c;
        return null;
    }
    private static Servicio findServicio(String id) {
        for (Servicio s : servicios) if (s.getId().equals(id)) return s;
        return null;
    }
    private static Mecanico findMecanico(String id) {
        for (Mecanico m : mecanicos) if (m.getId().equals(id)) return m;
        return null;
    }
    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
