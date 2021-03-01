package almacen;

public class Articulo {
  
  // Atributos
  private int codigo;
  private String articulo;
  private String descripcion;
  static private int codigoIncremental = 0;
  private double precio;
  private int unidades;
  private static final int STOCK_MAXIMO = 100;
 


  /**
   * Constructor en el cual generamos un código a partir de un atributo de clase el cual empieza por el 0
   * aunque se le va a asignar a partir del 1
   * @param articulo será el nombre del artículo
   * @param descripcion la descripción del artículo
   * @param precio lo que cuesta el artículo, nunca menor que 0
   * @param unidades las unidades que tendremos, nunca menores de 0 y nunca mayores que el stock máximo
   */
  public Articulo(String articulo, String descripcion, double precio, int unidades) {
    this.codigo = ++codigoIncremental;
    this.articulo = articulo;
    this.descripcion = descripcion;
    this.setPrecio(precio);
    this.setUnidades(unidades);
  }

  /**
   * Este constructor nos sirve para poder usar el método equals y así ahorrarnos bucles
   * @param codigoArticulo
   */
  public Articulo(int codigoArticulo) {
    this.codigo = codigoArticulo;
  }

  protected int getCodigo() {
    return codigo;
  }


  protected String getArticulo() {
    return articulo;
  }


  protected String getDescripcion() {
    return descripcion;
  }


  protected double getPrecio() {
    return precio;
  }

  
  protected int getUnidades() {
    return unidades;
  }


  protected void setArticulo(String articulo) {
    this.articulo = articulo;
  }

  
  protected void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * Como dije antes el percio nunca menor que 0
   * @param precio
   * @return
   */
  protected boolean setPrecio(double precio) {
    if (precio > 0) {
      this.precio = precio;
      return true;
    }
    return false;
  }

/**
 * Las unidades nunca menores que 0 y como mucho iguales que el stock máximo
 * @param unidades
 * @return
 */
  protected boolean setUnidades(int unidades) {
    if (unidades > 0) {
      if (unidades > STOCK_MAXIMO) {
        this.unidades = STOCK_MAXIMO;
        return true;
      }
      this.unidades = unidades;
      return true;
    }
    return false;
  }

  /**
   * he considerado usar una excepción marcada dado que no podemos tener valores negativos en el stock
   * @param unidades
   * @throws NegativeException
   */
  protected void salenUnidades(int unidades) throws NegativeException {
    try {
      int aux = this.unidades;
      aux -= unidades;
      if (aux < 0) {
        throw new NegativeException();
      }
      this.unidades = aux;
    } catch (NegativeException e){
      System.out.println("No puede quitar más unidades de las que tiene.");
      System.out.println("Tiene "+this.unidades+" y pretende quitar "+unidades+ ".");
    }
  }
/**
 * igual que el anterior, no podremos tener unidades negativas (por si alguien da un número negativo)
 * y no se podrá exceder el stock máximo
 * @param unidades
 * @return
 */
  protected boolean entranUnidades(int unidades) {
    int aux = this.unidades;
    aux += unidades;
    if (aux > 0) {
      if (aux > STOCK_MAXIMO) {
        this.unidades = STOCK_MAXIMO;
        return true;
      }
      this.unidades = aux;
      return true;
    }
    return false;
  }
  
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + codigo;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Articulo other = (Articulo) obj;
    if (codigo != other.codigo)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "\nArticulo codigo=" + codigo + ", articulo=" + articulo + ", descripcion=" + descripcion
        + ", precio=" + precio + ", unidades=" + unidades;
  }
  
  
 }
