package aplicacion;

import conceptos.Cliente;
import conceptos.Mecanico;
import conceptos.Servicio;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1) Cargar todo desde XML
        TallerCrud.loadAll();

        // 2) Listar (para ver estado)
        System.out.println("Clientes:  " + TallerCrud.listarClientes().size());
        System.out.println("Servicios: " + TallerCrud.listarServicios().size());
        System.out.println("Mecánicos: " + TallerCrud.listarMecanicos().size());

        // 3) Crear servicio
        TallerCrud.crearServicio(new Servicio("000000123", "Alineación", new BigDecimal("18000")));

        // 4) Crear cliente
        TallerCrud.crearCliente(new Cliente("999999999", "Ana Solano", "XYZ987", "7000-0000", "ana@ejemplo.com"));

        // 5) Crear mecánico
        TallerCrud.crearMecanico(new Mecanico("000000888", "Pedro Núñez", "Técnico"));

        // 6) Asignar servicios al mecánico
        TallerCrud.setServiciosDeMecanico("000000888", List.of("000000123"));

        // 7) Actualizar cliente
        Cliente edit = new Cliente("999999999", "Ana S. Solano", "XYZ987", "7000-1111", "ana@ejemplo.com");
        TallerCrud.actualizarCliente(edit);

        // 8) Eliminar servicio (lo quita también de los mecánicos)
        // TallerCrud.eliminarServicio("000000123");

        // 9) Mostrar resultados finales
        System.out.println("== Estado final ==");
        TallerCrud.listarClientes().forEach(System.out::println);
        TallerCrud.listarServicios().forEach(System.out::println);
        TallerCrud.listarMecanicos().forEach(m -> {
            System.out.println(m);
            System.out.println("  Servicios: " + m.getServiciosValidados().stream().map(Servicio::getNombre).toList());
        });
    }
}
