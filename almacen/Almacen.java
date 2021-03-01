package almacen;

import java.util.ArrayList;
import java.util.Scanner;

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
  public void agregarArticulo(String n , String d,double p,int u) {
    Articulo cosa = new Articulo(n,d,p,u);
    this.mercancia.add(cosa);
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
  public void saberArticulo(int codigoArticulo) {
    Articulo saberArticulo = new Articulo(codigoArticulo);
    try {
      // Para obtener el artículo que queremos, basta con usar indexOf el cual busca en nuestro
      // ArrayList y usando el método get lo extraeremos de este
      System.out.println(this.mercancia.get(this.mercancia.indexOf(saberArticulo)).toString());
    }catch (IndexOutOfBoundsException e) {
      System.out.println("El Código del artículo no está en el almacén");
    }

  }
  
  /**
   * Llamaremos al metodo entranUnidades de la clase Artículo usando el mismo proceso que el método anterior
   * @param codigoArticulo
   */
  public void incrementarUnidades(int codigoArticulo) {
    Articulo saberArticulo = new Articulo(codigoArticulo);
    Scanner s = new Scanner(System.in);
    try {
      // puse esta linea para que me comprobase el "try" antes de escribirme que introduzca el número
      // de unidades
      this.mercancia.get(this.mercancia.indexOf(saberArticulo));
      System.out.println("Introduce cuantas unidades aumentarás.");
      System.out.println(this.mercancia.get(this.mercancia.indexOf(saberArticulo)).entranUnidades(s.nextInt()));
    }catch (IndexOutOfBoundsException e) {
      System.out.println("El Código del artículo no está en el almacén");
    }
  }
  
  /**
   * Igual que el anterior solo que decrementandolo
   * @param codigoArticulo
   * @throws NegativeException
   */
  public void decrementarUnidades(int codigoArticulo) throws NegativeException {
    Articulo saberArticulo = new Articulo(codigoArticulo);
    Scanner s = new Scanner(System.in);
    try {
      this.mercancia.get(this.mercancia.indexOf(saberArticulo));
      System.out.println("Introduce cuantas unidades decrementarás.");
      this.mercancia.get(this.mercancia.indexOf(saberArticulo)).salenUnidades(s.nextInt());
    }catch (IndexOutOfBoundsException e) {
      System.out.println("El Código del artículo no está en el almacén");
    }
  }
  
  @Override
  public String toString() {
    return "Almacen mercancia=" + mercancia;
  }
  
  
}
