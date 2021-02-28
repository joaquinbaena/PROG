package almacen;

public class menuTest {

  public static void main(String[] args) {
    MenuSimple menu = new MenuSimple("Prueba","Pepito","opcion2");
    
    System.out.println("has seleccionado la opción "+menu.seleccionar());
    
  }

}
