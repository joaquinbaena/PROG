package almacen2;

public class Articulo {
  
  // Atributos
  private int codigo;
  private String articulo;
  private String descripcion;
  private double precioCompra;
  private double precioVenta;
  private int unidades;

  static private int codigoIncremental = 0;


  /**
   * Constructor en el cual generamos un código a partir de un atributo de clase el cual empieza por el 0
   * aunque se le va a asignar a partir del 1
   * @param articulo será el nombre del artículo
   * @param descripcion la descripción del artículo
   * @param precio lo que cuesta el artículo, nunca menor que 0
   * @param unidades las unidades que tendremos, nunca menores de 0 y nunca mayores que el stock máximo
   */
  Articulo(String articulo, String descripcion, double precioCompra,double precioVenta, int unidades) {
    if (articulo.equals("")){
      throw new IllegalArgumentException();
    }
    this.codigo = ++codigoIncremental;
    this.articulo = articulo;
    this.descripcion = descripcion;
    this.setPrecioCompra(precioCompra);
    this.setPrecioVenta(precioVenta);
    this.setUnidades(unidades);
  }

  /**
   * Este constructor nos sirve para poder usar el método equals y así ahorrarnos bucles
   * @param codigoArticulo
   */
  Articulo(int codigoArticulo) {
    this.codigo = codigoArticulo;
  }

  int getCodigo() {
    return codigo;
  }


  String getArticulo() {
    return articulo;
  }


  String getDescripcion() {
    return descripcion;
  }


  double getPrecioCompra() {
    return precioCompra;
  }

  double getPrecioVenta() {
    return precioVenta;
  }
  
  int getUnidades() {
    return unidades;
  }


  void setArticulo(String articulo) {
    this.articulo = articulo;
  }

  
  void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * Como dije antes el percio nunca menor que 0
   * @param precio
   * @return
   */
  void setPrecioCompra(double precio) {
    if (precio > 0) {
      this.precioCompra = precio;
    }else {
      throw new IllegalArgumentException();
    }
  }
  
  void setPrecioVenta(double precio) {
    if (precio > 0) {
      this.precioVenta = precio;
    }else {
      throw new IllegalArgumentException();
    }
  }

/**
 * Las unidades nunca menores que 0 y como mucho iguales que el stock máximo
 * @param unidades
 * @return
 */
  void setUnidades(int unidades) {
    if (unidades > 0) {
      this.unidades = unidades;
    }else {
      throw new IllegalArgumentException();
    }
    
  }

  /**
   * he considerado usar una excepción marcada dado que no podemos tener valores negativos en el stock
   * @param unidades
   * @throws NegativeException
   */
  void salenUnidades(int unidades) {
      int aux = this.unidades - unidades;
      if (aux < 0) {
        throw new IllegalArgumentException();
      }
      this.unidades = aux;
  }
/**
 * igual que el anterior, no podremos tener unidades negativas (por si alguien da un número negativo)
 * y no se podrá exceder el stock máximo
 * @param unidades
 * @return
 */
  void entranUnidades(int unidades) {
    int aux = this.unidades + unidades;
    if (aux > 0) {
      this.unidades = aux;
    }
    throw new IllegalArgumentException();
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
        + ", precio de compra=" + precioCompra + ", precio de venta="+ precioVenta +", unidades=" + unidades;
  }


  
  
 }
