package almacen2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Almacen {
  
  // Atributos
  private ArrayList<Articulo> mercancia;
  
  // Constructor
  protected Almacen() {
    this.mercancia = new ArrayList<>();
  }
  
  /**
   * agregaremos un artículo en el almacén
   * @param n es el nombre del artículo
   * @param d es la descripción del artículo
   * @param p es el precio del artículo
   * @param u son las unidades del artículo
   */
  public void agregarArticulo(String n , String d,double pc,double pv, int u) {
    Articulo articulo = new Articulo(n,d,pc,pv,u);
    this.mercancia.add(articulo);
  }
  
  /**
   * con este método creamos un artículo con el mismo código del artículo en el
   * almacén para así ahorrarnos el bucle
   * @param codigoArticulo
   * @return
   */
  public boolean eliminarArticulo(int codigoArticulo) {
    Articulo eliminar = new Articulo(codigoArticulo);
    if (!this.mercancia.remove(eliminar)) {
      System.err.println("No existe ningún artículo con ese código.");
      return false;
    }
    return true;
  }

  /**
   * Lo mismo que el anterior, nos ahorraremos el bucle para buscar el artículo
   * @param codigoArticulo
   */
  public String saberArticulo(int codigoArticulo) {
    Articulo saberArticulo = new Articulo(codigoArticulo);
      // Para obtener el artículo que queremos, basta con usar indexOf el cual busca en nuestro
      // ArrayList y usando el método get lo extraeremos de este
      return this.mercancia.get(this.mercancia.indexOf(saberArticulo)).toString();

  }
  
  /**
   * Llamaremos al metodo entranUnidades de la clase Artículo usando el mismo proceso que el método anterior
   * @param codigoArticulo
   */
  public void incrementarUnidades(int codigoArticulo, int unidades) {
    Articulo saberArticulo = new Articulo(codigoArticulo);
      // puse esta linea para que me comprobase el "try" antes de escribirme que introduzca el número
      // de unidades
      Articulo articuloElegido = this.mercancia.get(this.mercancia.indexOf(saberArticulo));
      articuloElegido.entranUnidades(unidades);
  }
  
  /**
   * Igual que el anterior solo que decrementandolo
   * @param codigoArticulo
   * @throws NegativeException
   */
  public void decrementarUnidades(int codigoArticulo, int unidades) {
    Articulo saberArticulo = new Articulo(codigoArticulo);
    Articulo articuloElegido = this.mercancia.get(this.mercancia.indexOf(saberArticulo));
    articuloElegido.salenUnidades(unidades);
  }
  
  
  // Generar documento xml
  
  /**
   * Crea el documento XML y guarda los datos del almacen
   * @param fileName
   * @throws AlmacenXMLException
   * @throws IOException
   */
  public void saveXML(String fileName) throws AlmacenXMLException, IOException {
    try {   
      Document xml = createDocumentXML();
      saveRootXML(xml);
      for (Articulo articulo : this.mercancia) {
        saveArticuloXML(articulo, xml);
      }
      saveFileXML(xml, fileName);
      
    } catch (ParserConfigurationException | TransformerException e) {
      throw new AlmacenXMLException("Error al generar XML: " + e.getMessage());
    }
  }

  
  /**
   * Crea el documento XML
   * @return
   * @throws ParserConfigurationException
   */
  private Document createDocumentXML() throws ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.newDocument();
    return document;
  }

  
  /**
   * Guarda la raiz del documento XML
   * @param xml
   */
  private void saveRootXML(Document xml) {
    Element root = xml.createElement("almacen");
    xml.appendChild(root);
  }

  
  /**
   * Guarda los articulos en xml
   * @param articulo
   * @param xml
   */
  private void saveArticuloXML(Articulo articulo, Document xml) {
    Element root = xml.getDocumentElement();

    Element articuloElement = xml.createElement("articulo");
    root.appendChild(articuloElement);

    saveFieldArticuloXML("nombre", articulo.getArticulo(), articuloElement);
    saveFieldArticuloXML("descripcion", articulo.getDescripcion(), articuloElement);
    saveFieldArticuloXML("precio_compra", articulo.getPrecioCompra() + "", articuloElement);
    saveFieldArticuloXML("precio_venta", articulo.getPrecioVenta() + "", articuloElement);
    saveFieldArticuloXML("unidades", articulo.getUnidades() + "", articuloElement);
  }

  /**
   * guarda cada campo de los articulos en formato xml
   * @param attr
   * @param value
   * @param articuloElement
   */
  private void saveFieldArticuloXML(String attr, String value, Element articuloElement) {
    Document xml = articuloElement.getOwnerDocument();
    Element attrElement = xml.createElement(attr);
    attrElement.appendChild(xml.createTextNode(value));
    articuloElement.appendChild(attrElement);
  }

  
  /**
   * guarda el archivo XML en memoria
   * @param xml
   * @param fileName
   * @throws IOException
   * @throws TransformerException
   */
  private void saveFileXML(Document xml, String fileName) throws IOException, TransformerException {
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    DOMSource xmlSource = new DOMSource(xml);
    StreamResult output = new StreamResult(new FileWriter(fileName));
    transformer.transform(xmlSource, output);
  }
  
  
  // Importar documento XML
  
  /**
   * importa el almacen a XML
   * @param fileName
   * @return
   * @throws IOException
   * @throws AlmacenXMLException
   */
  static public Almacen loadXML(String fileName) throws IOException, AlmacenXMLException {
    var almacen = new Almacen();
    try { 
      Document xml = newDocumentXML(fileName);
      NodeList articulos = xml.getElementsByTagName("almacen");
      for (int i = 0; i < articulos.getLength(); i++) {
        Articulo articulo = newArticuloXML(articulos.item(i));
        almacen.mercancia.add(articulo);
      }
    } catch (ParserConfigurationException | SAXException e) {
      throw new AlmacenXMLException("Error al cargar XML: " + e.getMessage());
    } 
    return almacen;
  }

  
  /**
   * recoge el archivo en memoria y lo normaliza
   * @param fileName
   * @return
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  private static Document newDocumentXML(String fileName) throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(new File(fileName));
    document.getDocumentElement().normalize();
    return document;
  }

  
  /**
   * Guarda por nodos el XML contenido en el archivo para poder incluirlos en el almacen
   * @param itemArticulo
   * @return
   */
  private static Articulo newArticuloXML(Node itemArticulo) {
    String nombre = loadFieldarticuloXML("nombre", itemArticulo);
    String descripcion = loadFieldarticuloXML("descripcion", itemArticulo);
    double precioCompra = Double.parseDouble(loadFieldarticuloXML("precio_compra", itemArticulo));
    double precioVenta = Double.parseDouble(loadFieldarticuloXML("precio_venta", itemArticulo));
    int unidades = Integer.parseInt(loadFieldarticuloXML("unidades", itemArticulo));
    return newArticulo(nombre, descripcion, precioCompra, precioVenta, unidades);
  }

  
  /**
   * saca los campos de cada articulo para incluirlo en el almacen
   * @param field
   * @param itemarticulo
   * @return
   */
  private static String loadFieldarticuloXML(String field, Node itemarticulo) {
    Element elementArticulos = (Element) itemarticulo;
    String textField = elementArticulos.getElementsByTagName(field).item(0).getTextContent();
    return textField;
  }

  
  /**
   * crea cada articulo para incluirlo en el almacen a partir del archivo
   * @param nombre
   * @param descripcion
   * @param precioCompra
   * @param precioVenta
   * @param unidades
   * @return
   */
  private static Articulo newArticulo(String nombre, String descripcion, double precioCompra, double precioVenta,
      int unidades) {
    Articulo articulo;
    articulo = new Articulo(nombre, descripcion, precioCompra, precioVenta, unidades);
    return articulo;
  }
  
  // Exportar CSV
  
  /**
   * Guarda el almacen como fichero CSV.
   */
  public void saveCSV(String fileName) throws IOException {
    var file = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.CREATE);
    saveHeadCSV(file);
    for (Articulo articulo: this.mercancia) {
      saveArticuloCSV(articulo, file);
    }
    file.close();
  }

  
  /**
   * guarda la cabecera del CSV
   * @param file
   * @throws IOException
   */
  private void saveHeadCSV(BufferedWriter file) throws IOException {
    file.write("Nombre,Descripcion,Precio_compra,Precio_venta,Unidades");
    file.newLine();
  }

  /**
   * genera cada campo del articulo en el archivo CSV
   * @param articulo
   * @param file
   * @throws IOException
   */
  private void saveArticuloCSV(Articulo articulo, BufferedWriter file) throws IOException {
    file.write("\"" + articulo.getArticulo() + "\",");
    file.write("\"" + articulo.getDescripcion() + "\",");
    file.write("\"" + articulo.getPrecioCompra() + "\",");
    file.write("\"" + articulo.getPrecioVenta() + "\",");
    file.write("\"" + articulo.getUnidades() + "\""); 
    file.newLine();
  }

  /**
   * Recupera el almacen de un fichero CSV y la devuelve como Almacen.
   * @throws IOException 
   * @throws AlmacenCSVException 
   */
  static public Almacen loadCSV(String fileName) throws IOException, AlmacenCSVException {
    var almacen = new Almacen();
    var file = Files.newBufferedReader(Paths.get(fileName));
    validateHeadCSV(file);

    String line;
    while ((line = file.readLine()) != null) {
      Articulo articulo = newArticuloCSV(line);
      almacen.mercancia.add(articulo);
    }
    file.close();

    return almacen;
  }

  
  /**
   * comprueba que está correcta la cabecera del almacen
   * @param file
   * @throws IOException
   * @throws AlmacenCSVException
   */
  private static void validateHeadCSV(BufferedReader file) throws IOException, AlmacenCSVException {
    String head = file.readLine().trim();
    if (! head.equalsIgnoreCase("Nombre,Descripcion,Precio_compra,Precio_venta,Unidades")) {
      throw new AlmacenCSVException("Cabecera errónea en el CSV.");
    }
  }

  
  /**
   * crea el articulo desde el formato CSV
   * @param line
   * @return
   * @throws AlmacenCSVException
   */
  private static Articulo newArticuloCSV(String line) throws AlmacenCSVException{
    validarArticuloCSV(line);
    String[] fieldsarticulo = line.split("\","); 

    String nombre = fieldsarticulo[0].replace("\"", "");
    String descripcion = fieldsarticulo[1].replace("\"", "");
    double precioCompra = Double.parseDouble(fieldsarticulo[2].replace("\"", ""));
    double precioVenta = Double.parseDouble(fieldsarticulo[3].replace("\"", ""));
    int unidades = Integer.parseInt(fieldsarticulo[4].replace("\"", ""));
    Articulo articulo = newArticulo(nombre, descripcion, precioCompra, precioVenta, unidades);
    return articulo;
  }

  
  /**
   * Extrae cada campo del articulo desde el CSV
   * @param line
   * @throws AlmacenCSVException
   */
  private static void validarArticuloCSV(String line) throws AlmacenCSVException {
    int numFieldsLine = line.split("\",").length;   // solo las comas de separación de campos
    int numFieldsArticulo = "Nombre,Descripcion,Precio_compra,Precio_venta,Unidades".split(",").length;

    if (numFieldsLine != numFieldsArticulo) {
      throw new AlmacenCSVException(line + ": no es un formato válido para convertirlo en Articulo.");
    }
  }
  
  @Override
  public String toString() {
    return "Almacen mercancia=" + mercancia;
  }
  
  
}
