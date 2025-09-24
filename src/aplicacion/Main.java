package aplicacion;

import conceptos.Cliente;
import conceptos.Servicio;
import conceptos.Mecanico;
import util.CargadorXML;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes  = CargadorXML.Cargar("Data/clientes.xml");
        ArrayList<Servicio> servicios = CargadorXML.CargarServicios("Data/servicios.xml");
        ArrayList<Mecanico> mecanicos = CargadorXML.CargarMecanicos("Data/mecanicos.xml", servicios);

        for (Cliente c : clientes)  System.out.println(c);
        for (Servicio s : servicios) System.out.println(s);
        for (Mecanico m : mecanicos) System.out.println(m);

        // ejemplo de guardado (opcional)
        // CargadorXML.GuardarClientes("Data/clientes.xml", clientes);
        // CargadorXML.GuardarServicios("Data/servicios.xml", servicios);
        // CargadorXML.GuardarMecanicos("Data/mecanicos.xml", mecanicos);
    }
}
