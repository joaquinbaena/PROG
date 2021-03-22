package almacen2;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.security.sasl.RealmCallback;
import proyecto.MenuSimple;

public class TestAlmacen {

  public static void main(String[] args) throws NegativeException {
    Almacen almacen = new Almacen();
    MenuSimple menu = new MenuSimple("Opciones para la gestión de su almacén",
        "Añadir artículo.","Eliminar articulo.", "Decrementar existencias.","Incrementar existencias",
        "Acceder al artículo por el código.","Listar el almacén.","Exportar XML" ,"Importar XML","Exportar CSV","Importar CSV" , "Salir del programa.");
    boolean salir = false;
    do {
      
      try {
        switch(menu.seleccionar()) {
          case 1:
            almacen.agregarArticulo(nombreArticulo(),descripcionArticulo(),precioCompraArticulo(),precioVentaArticulo()
                ,unidadesArticulo(""));
            System.out.println("");
            break;
          case 2:
            almacen.eliminarArticulo(seleccionarArticulo());
            System.out.println("");
            break;
          case 3:
            almacen.decrementarUnidades(seleccionarArticulo(),unidadesArticulo(" que desea decrementar"));
            System.out.println("");
            break;
          case 4:
            almacen.incrementarUnidades(seleccionarArticulo(),unidadesArticulo(" que desea aumentar"));
            System.out.println("");
            break;
          case 5:
            System.out.println(almacen.saberArticulo(seleccionarArticulo()));
            escribirString();
            break;
          case 6:
            System.out.println(almacen.toString());
            System.out.println("");
            break;
          case 7:
            System.out.println("Introduce el nombre del archivo XML que desea exportar");
            almacen.saveXML(escribirString());
            System.out.println("Se ha guardado con éxito");
            escribirString();
            break;
          case 8:
            System.out.println("Introduce el nombre del archivo que vas a importar");
            almacen = Almacen.loadXML(escribirString());
            System.out.println("Se la importado correctamente.");
            escribirString();
            break;
          case 9:
            System.out.println("Escribe el nombre del archivo CSV que desea exportar");
            almacen.saveCSV(escribirString());
            System.out.println("Se ha exportado correctamente");
            escribirString();
            break;
          case 10:
            System.out.println("Escribe el nombre del archivo CSV que desea importar");
            almacen = Almacen.loadCSV(escribirString());
            System.out.println("El archivo se ha importado correctamente");
            escribirString();
            break;
          case 11:
            salir = true;
            break;
          default:
            System.out.println("");
            System.out.println("Elige una opción correcta.");
            escribirString();
            break;
        }
      }catch (InputMismatchException e) {
        System.err.println("No puedes introducir otra cosa que no sean números o no está introduciendo"
            + " correctamente el precio de un artículo.");
        escribirString();
      }catch(IndexOutOfBoundsException iob) {
        System.err.println("Error: "+iob.getMessage());
      }catch(IOException ioe) {
        System.err.println("Error: "+ioe.getMessage());
      }catch(AlmacenXMLException axe) {
        System.err.println("Error: "+axe.getMessage());
      }catch (AlmacenCSVException ace) {
        System.err.println("Error: "+ace);
      }
    } while(!salir);
  }

  public static int seleccionarArticulo() {
    System.out.println("Seleccione el código del artículo.");
    int n = escribirInt();
    return n;
  }

  public static int unidadesArticulo(String mensaje) {
    System.out.println("Introduce las unidades"+mensaje+" del artículo.");
    int u = escribirInt();
    return u;
  }

  private static double precioVentaArticulo() {
    System.out.println("Introduce el precio de venta del Artículo.");
    double p = escribirDouble();
    return p;
  }
  
  private static double precioCompraArticulo() {
    System.out.println("Introduce el precio de compra del Artículo.");
    double p = escribirDouble();
    return p;
  }

  private static String descripcionArticulo() {
    System.out.println("Introduce la descripción del Artículo.");
    String d = escribirString();
    return d;
  }

  private static String nombreArticulo() {
    System.out.println("Introduce el nombre del artículo.");
    String n = escribirString();
    return n;
  }

  public static double escribirDouble() {
    Scanner s = new Scanner(System.in);
    double escribir = s.nextDouble();
    return escribir;
  }
  
  public static int escribirInt() {
    Scanner s = new Scanner(System.in);
    int escribir = s.nextInt();
    return escribir;
  }
  
  public static String escribirString() {
    Scanner s = new Scanner(System.in);
    String escribir = s.nextLine();
    return escribir;
  }
}
