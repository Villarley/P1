package aplicacion;

import conceptos.Cliente;
import conceptos.Mecanico;
import conceptos.Servicio;
import ventanas.Principal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        /*System.out.println("=== SISTEMA DE GESTIÓN DE TALLER MECÁNICO ===");
        
        // Cargar datos desde XML
        TallerCrud.loadAll();
        System.out.println("Datos cargados exitosamente.");
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1 -> gestionarClientes();
                case 2 -> gestionarServicios();
                case 3 -> gestionarMecanicos();
                case 4 -> mostrarResumen();
                case 5 -> continuar = false;
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
        
        System.out.println("¡Gracias por usar el sistema!");
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestionar Clientes");
        System.out.println("2. Gestionar Servicios");
        System.out.println("3. Gestionar Mecánicos");
        System.out.println("4. Mostrar Resumen");
        System.out.println("5. Salir");
        System.out.println("=====================");
    }
    
    private static void gestionarClientes() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Actualizar Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("6. Volver al menú principal");
            
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> listarClientes();
                case 3 -> buscarCliente();
                case 4 -> actualizarCliente();
                case 5 -> eliminarCliente();
                case 6 -> continuar = false;
                default -> System.out.println("Opción inválida.");
            }
        }
    }
    
    private static void gestionarServicios() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE SERVICIOS ===");
            System.out.println("1. Crear Servicio");
            System.out.println("2. Listar Servicios");
            System.out.println("3. Buscar Servicio");
            System.out.println("4. Actualizar Servicio");
            System.out.println("5. Eliminar Servicio");
            System.out.println("6. Volver al menú principal");
            
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1 -> crearServicio();
                case 2 -> listarServicios();
                case 3 -> buscarServicio();
                case 4 -> actualizarServicio();
                case 5 -> eliminarServicio();
                case 6 -> continuar = false;
                default -> System.out.println("Opción inválida.");
            }
        }
    }
    
    private static void gestionarMecanicos() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE MECÁNICOS ===");
            System.out.println("1. Crear Mecánico");
            System.out.println("2. Listar Mecánicos");
            System.out.println("3. Buscar Mecánico");
            System.out.println("4. Actualizar Mecánico");
            System.out.println("5. Asignar Servicios");
            System.out.println("6. Eliminar Mecánico");
            System.out.println("7. Volver al menú principal");
            
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1 -> crearMecanico();
                case 2 -> listarMecanicos();
                case 3 -> buscarMecanico();
                case 4 -> actualizarMecanico();
                case 5 -> asignarServicios();
                case 6 -> eliminarMecanico();
                case 7 -> continuar = false;
                default -> System.out.println("Opción inválida.");
            }
        }
    }
    
    // === MÉTODOS DE CLIENTES ===
    private static void crearCliente() {
        System.out.println("\n--- Crear Cliente ---");
        
        String id = TallerCrud.generarIdCliente();
        System.out.println("ID generado: " + id);
        
        String nombre = leerTexto("Nombre completo: ");
        String placa = leerTexto("Placa del vehículo: ");
        String telefono = leerTexto("Teléfono: ");
        String email = leerTexto("Email: ");
        
        Cliente cliente = new Cliente(id, nombre, placa, telefono, email);
        
        if (TallerCrud.crearCliente(cliente)) {
            System.out.println("✓ Cliente creado exitosamente con ID: " + id);
        } else {
            System.out.println("✗ Error al crear el cliente.");
        }
    }
    
    private static void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = TallerCrud.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            clientes.forEach(c -> System.out.println("ID: " + c.getId() + " | " + c.getNombre() + 
                " | Placa: " + c.getPlaca() + " | Tel: " + c.getTelefono()));
        }
    }
    
    private static void buscarCliente() {
        String id = leerTexto("Ingrese el ID del cliente: ");
        Cliente cliente = TallerCrud.buscarCliente(id);
        if (cliente != null) {
            System.out.println("\n--- Cliente Encontrado ---");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
    
    private static void actualizarCliente() {
        String id = leerTexto("Ingrese el ID del cliente a actualizar: ");
        Cliente cliente = TallerCrud.buscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        System.out.println("Cliente actual: " + cliente);
        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        
        String nombre = leerTextoOpcional("Nombre: ", cliente.getNombre());
        String placa = leerTextoOpcional("Placa: ", cliente.getPlaca());
        String telefono = leerTextoOpcional("Teléfono: ", cliente.getTelefono());
        String email = leerTextoOpcional("Email: ", cliente.getEmail());
        
        Cliente actualizado = new Cliente(id, nombre, placa, telefono, email);
        
        if (TallerCrud.actualizarCliente(actualizado)) {
            System.out.println("✓ Cliente actualizado exitosamente.");
        } else {
            System.out.println("✗ Error al actualizar el cliente.");
        }
    }
    
    private static void eliminarCliente() {
        String id = leerTexto("Ingrese el ID del cliente a eliminar: ");
        if (TallerCrud.eliminarCliente(id)) {
            System.out.println("✓ Cliente eliminado exitosamente.");
        } else {
            System.out.println("✗ Cliente no encontrado.");
        }
    }
    
    // === MÉTODOS DE SERVICIOS ===
    private static void crearServicio() {
        System.out.println("\n--- Crear Servicio ---");
        
        String id = TallerCrud.generarIdServicio();
        System.out.println("ID generado: " + id);
        
        String nombre = leerTexto("Nombre del servicio: ");
        BigDecimal precio = leerBigDecimal("Precio: ");
        
        Servicio servicio = new Servicio(id, nombre, precio);
        
        if (TallerCrud.crearServicio(servicio)) {
            System.out.println("✓ Servicio creado exitosamente con ID: " + id);
        } else {
            System.out.println("✗ Error al crear el servicio.");
        }
    }
    
    private static void listarServicios() {
        System.out.println("\n--- Lista de Servicios ---");
        List<Servicio> servicios = TallerCrud.listarServicios();
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            servicios.forEach(s -> System.out.println("ID: " + s.getId() + " | " + s.getNombre() + 
                " | Precio: ₡" + s.getPrecio()));
        }
    }
    
    private static void buscarServicio() {
        String id = leerTexto("Ingrese el ID del servicio: ");
        Servicio servicio = TallerCrud.buscarServicio(id);
        if (servicio != null) {
            System.out.println("\n--- Servicio Encontrado ---");
            System.out.println(servicio);
        } else {
            System.out.println("Servicio no encontrado.");
        }
    }
    
    private static void actualizarServicio() {
        String id = leerTexto("Ingrese el ID del servicio a actualizar: ");
        Servicio servicio = TallerCrud.buscarServicio(id);
        if (servicio == null) {
            System.out.println("Servicio no encontrado.");
            return;
        }
        
        System.out.println("Servicio actual: " + servicio);
        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        
        String nombre = leerTextoOpcional("Nombre: ", servicio.getNombre());
        BigDecimal precio = leerBigDecimalOpcional("Precio: ", servicio.getPrecio());
        
        Servicio actualizado = new Servicio(id, nombre, precio);
        
        if (TallerCrud.actualizarServicio(actualizado)) {
            System.out.println("✓ Servicio actualizado exitosamente.");
        } else {
            System.out.println("✗ Error al actualizar el servicio.");
        }
    }
    
    private static void eliminarServicio() {
        String id = leerTexto("Ingrese el ID del servicio a eliminar: ");
        if (TallerCrud.eliminarServicio(id)) {
            System.out.println("✓ Servicio eliminado exitosamente (también se quitó de los mecánicos).");
        } else {
            System.out.println("✗ Servicio no encontrado.");
        }
    }
    
    // === MÉTODOS DE MECÁNICOS ===
    private static void crearMecanico() {
        System.out.println("\n--- Crear Mecánico ---");
        
        String id = TallerCrud.generarIdMecanico();
        System.out.println("ID generado: " + id);
        
        String nombre = leerTexto("Nombre completo: ");
        String puesto = leerTexto("Puesto: ");
        
        Mecanico mecanico = new Mecanico(id, nombre, puesto);
        
        if (TallerCrud.crearMecanico(mecanico)) {
            System.out.println("✓ Mecánico creado exitosamente con ID: " + id);
        } else {
            System.out.println("✗ Error al crear el mecánico.");
        }
    }
    
    private static void listarMecanicos() {
        System.out.println("\n--- Lista de Mecánicos ---");
        List<Mecanico> mecanicos = TallerCrud.listarMecanicos();
        if (mecanicos.isEmpty()) {
            System.out.println("No hay mecánicos registrados.");
        } else {
            mecanicos.forEach(m -> {
                System.out.println("ID: " + m.getId() + " | " + m.getNombre() + 
                    " | Puesto: " + m.getPuesto());
                System.out.println("  Servicios: " + m.getServiciosValidados().stream()
                    .map(Servicio::getNombre).toList());
            });
        }
    }
    
    private static void buscarMecanico() {
        String id = leerTexto("Ingrese el ID del mecánico: ");
        Mecanico mecanico = TallerCrud.buscarMecanico(id);
        if (mecanico != null) {
            System.out.println("\n--- Mecánico Encontrado ---");
            System.out.println(mecanico);
            System.out.println("Servicios: " + mecanico.getServiciosValidados().stream()
                .map(Servicio::getNombre).toList());
        } else {
            System.out.println("Mecánico no encontrado.");
        }
    }
    
    private static void actualizarMecanico() {
        String id = leerTexto("Ingrese el ID del mecánico a actualizar: ");
        Mecanico mecanico = TallerCrud.buscarMecanico(id);
        if (mecanico == null) {
            System.out.println("Mecánico no encontrado.");
            return;
        }
        
        System.out.println("Mecánico actual: " + mecanico);
        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        
        String nombre = leerTextoOpcional("Nombre: ", mecanico.getNombre());
        String puesto = leerTextoOpcional("Puesto: ", mecanico.getPuesto());
        
        Mecanico actualizado = new Mecanico(id, nombre, puesto);
        actualizado.setServiciosValidados(mecanico.getServiciosValidados()); // Mantener servicios
        
        if (TallerCrud.actualizarMecanico(actualizado)) {
            System.out.println("✓ Mecánico actualizado exitosamente.");
        } else {
            System.out.println("✗ Error al actualizar el mecánico.");
        }
    }
    
    private static void asignarServicios() {
        String id = leerTexto("Ingrese el ID del mecánico: ");
        Mecanico mecanico = TallerCrud.buscarMecanico(id);
        if (mecanico == null) {
            System.out.println("Mecánico no encontrado.");
            return;
        }
        
        System.out.println("Mecánico: " + mecanico.getNombre());
        System.out.println("Servicios actuales: " + mecanico.getServiciosValidados().stream()
            .map(Servicio::getNombre).toList());
        
        // Mostrar servicios disponibles
        List<Servicio> servicios = TallerCrud.listarServicios();
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios disponibles.");
            return;
        }
        
        System.out.println("\nServicios disponibles:");
        servicios.forEach(s -> System.out.println("  " + s.getId() + " - " + s.getNombre()));
        
        String idsText = leerTexto("Ingrese los IDs de servicios separados por comas (ej: S000001,S000002): ");
        List<String> idsServicios = new ArrayList<>();
        
        if (!idsText.trim().isEmpty()) {
            String[] ids = idsText.split(",");
            for (String idServicio : ids) {
                idsServicios.add(idServicio.trim());
            }
        }
        
        if (TallerCrud.setServiciosDeMecanico(id, idsServicios)) {
            System.out.println("✓ Servicios asignados exitosamente.");
        } else {
            System.out.println("✗ Error al asignar servicios.");
        }
    }
    
    private static void eliminarMecanico() {
        String id = leerTexto("Ingrese el ID del mecánico a eliminar: ");
        if (TallerCrud.eliminarMecanico(id)) {
            System.out.println("✓ Mecánico eliminado exitosamente.");
        } else {
            System.out.println("✗ Mecánico no encontrado.");
        }
    }
    
    // === MÉTODOS AUXILIARES ===
    private static void mostrarResumen() {
        System.out.println("\n=== RESUMEN DEL SISTEMA ===");
        System.out.println("Clientes: " + TallerCrud.listarClientes().size());
        System.out.println("Servicios: " + TallerCrud.listarServicios().size());
        System.out.println("Mecánicos: " + TallerCrud.listarMecanicos().size());
        System.out.println("========================");
    }
    
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
    
    private static String leerTextoOpcional(String mensaje, String valorActual) {
        System.out.print(mensaje + "[" + valorActual + "]: ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? valorActual : input;
    }
    
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }
    
    private static BigDecimal leerBigDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine().trim();
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un precio válido.");
            }
        }
    }
    
    private static BigDecimal leerBigDecimalOpcional(String mensaje, BigDecimal valorActual) {
        while (true) {
            try {
                System.out.print(mensaje + "[" + valorActual + "]: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return valorActual;
                }
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un precio válido.");
            }
        }*/
        
        Principal principal = new Principal();
        principal.setVisible(true);
    }
}
