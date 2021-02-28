package almacen;

import java.util.Arrays;
import java.util.Scanner;

/**
 * En esta clase haremos un menú simple
 * @author juaki
 *
 */
public class MenuSimple {
  // Atributos
  public String titulo;
  private String[] opciones;

  
  
  // Constructor
  protected MenuSimple(String titulo, String ... opciones) {
    this.opciones = new String[opciones.length];
    this.titulo = titulo;
    for(int i = 0 ; i < this.opciones.length; i++) {
      this.opciones[i] = opciones[i];
    }
  }

  /**
   * 
   * @param n
   * @return
   */
public int seleccionar() {
  System.out.println(this.titulo);
  System.out.println("-----------------------");
  for (int i = 0 ; i < this.opciones.length ; i++) {
    System.out.println(i+1+"."+this.opciones[i]);
  }
  
  Scanner s = new Scanner(System.in);
  System.out.print("Selecciona una opción: ");
  int n = s.nextInt();
  s.close();
  return n;
}




  @Override
  public String toString() {
    return "MenuSimple [titulo=" + titulo + ", opciones=" + Arrays.toString(opciones) + "]";
  }
  
  
}
