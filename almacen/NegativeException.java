package almacen;

public class NegativeException extends Exception {
  
  private String codigoError;
  
  public NegativeException(){
    this.codigoError = "No puedes quitar más unidades de las que tienes."; 
  }

  
  
  @Override
  public String toString() {
    return this.codigoError;
  }
  
  
}
