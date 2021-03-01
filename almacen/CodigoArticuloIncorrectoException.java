package almacen;

public class CodigoArticuloIncorrectoException extends RuntimeException {

  private String codigoError;
  public CodigoArticuloIncorrectoException() {
    this.codigoError = "No existe el articulo"; 
  }
}
