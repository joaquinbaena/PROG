package tanda1ejerciciosPOO;

import java.util.Scanner;

public class TestFraccion {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    Fraccion f1 = new Fraccion(75,45);
    Fraccion f2 = new Fraccion(80,25);
    System.out.println("La primera fracción, es: "+ f1 +"\n"
        + "La segunda fracción es: "+ f2 + "\n"
        + "su suma sería:" + f1.sumar(f2) + "\n"
        + "su resta sería:" + f1.restar(f2) + "\n"
        + "y su multiplicación sería: " + f1.multiplicar(f2)+ "\n");
    
    System.out.println();
    System.out.print("Vamos a multiplicar la primera fracción por el número\n"
        + "que introduzcamos: ");
    System.out.println(f1.multiplicar(s.nextInt()));
    
    System.out.println();
    System.out.print("ahora modificaremos la fracción primera introduciendo \n"
    + "dos números: ");
    f1.setNum(s.nextInt());
    f1.setDen(s.nextInt());
    System.out.println("la primera fracción quedaría: "+ f1);
    
  }



}
