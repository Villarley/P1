package util;

import conceptos.Cliente;
import conceptos.Servicio;
import conceptos.Mecanico;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CargadorXML {

    private static String getValue(String tag, Element elemento) {
        NodeList nl = elemento.getElementsByTagName(tag);
        if (nl.getLength() == 0) return "";
        return nl.item(0).getTextContent().trim();
    }

    // ======== CLIENTES ========
    public static ArrayList<Cliente> Cargar(String archivo) { // clientes
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            File f = new File(archivo);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(f);
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("cliente");
            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    String id       = e.getAttribute("id").trim();
                    String nombre   = getValue("nombre", e);
                    String placa    = getValue("placa", e);
                    String telefono = getValue("telefono", e);
                    String email    = getValue("email", e);
                    clientes.add(new Cliente(id, nombre, placa, telefono, email));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    // Guardar clientes con mismo estilo (una sola clase util, DOM + Transformer)
    public static void GuardarClientes(String archivo, ArrayList<Cliente> clientes) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.newDocument();
            Element root = doc.createElement("clientes");
            doc.appendChild(root);

            for (Cliente c : clientes) {
                Element el = doc.createElement("cliente");
                el.setAttribute("id", c.getId());
                addChild(doc, el, "nombre", c.getNombre());
                addChild(doc, el, "placa", c.getPlaca());
                addChild(doc, el, "telefono", c.getTelefono());
                addChild(doc, el, "email", c.getEmail());
                root.appendChild(el);
            }
            XmlWrite(doc, new File(archivo));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ======== SERVICIOS ========
    public static ArrayList<Servicio> CargarServicios(String archivo) {
        ArrayList<Servicio> servicios = new ArrayList<>();
        try {
            File f = new File(archivo);
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(f);
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("servicio");
            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    String id     = e.getAttribute("id").trim();
                    String nombre = getValue("nombre", e);
                    String pTxt   = getValue("precio", e);
                    BigDecimal precio = pTxt.isEmpty() ? BigDecimal.ZERO : new BigDecimal(pTxt);
                    servicios.add(new Servicio(id, nombre, precio));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return servicios;
    }

    public static void GuardarServicios(String archivo, ArrayList<Servicio> servicios) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.newDocument();
            Element root = doc.createElement("servicios");
            doc.appendChild(root);

            for (Servicio s : servicios) {
                Element el = doc.createElement("servicio");
                el.setAttribute("id", s.getId());
                addChild(doc, el, "nombre", s.getNombre());
                addChild(doc, el, "precio", s.getPrecio() == null ? "0" : s.getPrecio().toPlainString());
                root.appendChild(el);
            }
            XmlWrite(doc, new File(archivo));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ======== MECÁNICOS ========
    public static ArrayList<Mecanico> CargarMecanicos(String archivo, ArrayList<Servicio> serviciosDisponibles) {
        ArrayList<Mecanico> mecanicos = new ArrayList<>();
        try {
            File f = new File(archivo);
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(f);
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("mecanico");
            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    String id     = e.getAttribute("id").trim();
                    String nombre = getValue("nombre", e);
                    String puesto = getValue("puesto", e);

                    Mecanico m = new Mecanico(id, nombre, puesto);

                    // <servicios><id>...</id></servicios>
                    NodeList serviciosNodes = e.getElementsByTagName("servicios");
                    if (serviciosNodes.getLength() > 0) {
                        Element se = (Element) serviciosNodes.item(0);
                        NodeList ids = se.getElementsByTagName("id");
                        for (int j = 0; j < ids.getLength(); j++) {
                            String srvId = ids.item(j).getTextContent().trim();
                            Servicio srv = findServicio(serviciosDisponibles, srvId);
                            if (srv != null) m.getServiciosValidados().add(srv);
                        }
                    }
                    mecanicos.add(m);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mecanicos;
    }

    public static void GuardarMecanicos(String archivo, ArrayList<Mecanico> mecanicos) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.newDocument();
            Element root = doc.createElement("mecanicos");
            doc.appendChild(root);

            for (Mecanico m : mecanicos) {
                Element el = doc.createElement("mecanico");
                el.setAttribute("id", m.getId());
                addChild(doc, el, "nombre", m.getNombre());
                addChild(doc, el, "puesto", m.getPuesto());

                Element serviciosEl = doc.createElement("servicios");
                for (Servicio s : m.getServiciosValidados()) {
                    Element idEl = doc.createElement("id");
                    idEl.setTextContent(s.getId());
                    serviciosEl.appendChild(idEl);
                }
                el.appendChild(serviciosEl);
                root.appendChild(el);
            }
            XmlWrite(doc, new File(archivo));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void XmlWrite(Document doc, File destino) throws Exception {
        javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "UTF-8");
        t.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        t.transform(new javax.xml.transform.dom.DOMSource(doc),
                    new javax.xml.transform.stream.StreamResult(destino));
    }

    private static void addChild(Document doc, Element parent, String tag, String text) {
        Element el = doc.createElement(tag);
        el.setTextContent(text == null ? "" : text);
        parent.appendChild(el);
    }

    private static Servicio findServicio(ArrayList<Servicio> servicios, String id) {
        for (Servicio s : servicios) if (s.getId().equals(id)) return s;
        return null;
    }
}
