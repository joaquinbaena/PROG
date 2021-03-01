package almacen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestAlmacen {

  public static void main(String[] args) throws NegativeException {
    Scanner s = new Scanner(System.in);
    Almacen almacen = new Almacen();
    MenuSimple menu = new MenuSimple("Opciones para la gestión de su almacén",
        "Añadir artículo.","Eliminar articulo.", "Decrementar existencias.","Incrementar existencias",
        "Acceder al artículo por el código.","Listar el almacén.", "Salir del programa.");
    boolean salir = false;
    do {
      
      try {
        switch(menu.seleccionar()) {
          case 1:
            almacen.agregarArticulo(nombreArticulo(s),descripcionArticulo(s),precioArticulo(s)
                ,unidadesArticulo(s));
            System.out.println("");
            break;
          case 2:
            almacen.eliminarArticulo(seleccionarArticulo(s));
            System.out.println("");
            break;
          case 3:
            almacen.decrementarUnidades(seleccionarArticulo(s));
            System.out.println("");
            break;
          case 4:
            almacen.incrementarUnidades(seleccionarArticulo(s));
            System.out.println("");
            break;
          case 5:
            almacen.saberArticulo(seleccionarArticulo(s));
            System.out.println("");
            break;
          case 6:
            System.out.println(almacen.toString());
            System.out.println("");
            break;
          case 7:
            salir = true;
            break;
          default:
            System.out.println("");
            System.out.println("Elige una opción correcta.");
            s.nextLine();
            break;
        }
      }catch (InputMismatchException e) {
        System.out.println("");
        System.out.println("No puedes introducir otra cosa que no sean números o no está introduciendo"
            + " correctamente el precio de un artículo.");
        s.nextLine();
      }
    } while(!salir);
  }

  public static int seleccionarArticulo(Scanner s) {
    System.out.println("Seleccione el código del artículo.");
    int n = s.nextInt();
    s.nextLine();
    return n;
  }

  public static int unidadesArticulo(Scanner s) {
    System.out.println("Introduce las unidades del artículo.");
    int u = s.nextInt();
    s.nextLine();
    return u;
  }

  private static double precioArticulo(Scanner s) {
    System.out.println("Introduce el precio del Artículo.");
    double p = s.nextDouble();
    return p;
  }

  private static String descripcionArticulo(Scanner s) {
    System.out.println("Introduce la descripción del Artículo.");
    String d = s.nextLine();
    return d;
  }

  private static String nombreArticulo(Scanner s) {
    System.out.println("Introduce el nombre del artículo.");
    String n = s.nextLine();
    return n;
  }

}
