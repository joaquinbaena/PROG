package tanda1ejerciciosPOO;

public class TestTiempo {

  public static void main(String[] args) {
    Tiempo t1 = new Tiempo(10, 20, 30);
    Tiempo t2 = new Tiempo(5, 100, -5);
    
    // Probamos impresión
    System.out.println("Tiempo 1: " + t1);
    System.out.println("Tiempo 2: " + t2);
    
    // Probamos suma y resta
    System.out.println(t1 + " + " + t2 + " : " + t1.sumar(t2));
    System.out.println(t1 + " + " + t2 + " : " + t1.restar(t2));
    
    // Probamos suma y resta de segundos
    System.out.print(t1 + " + 22 segundos = ");
    t1.sumaSegundos(22);
    System.out.println(t1);
  }

}
