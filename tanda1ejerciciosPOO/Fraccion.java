package tanda1ejerciciosPOO;

public class Fraccion {
  private int num;
  private int den;
  
  public Fraccion(int num, int den) {
    this.num = num;
    this.setDen(den);
  }

  /**
   * @return the num
   */
  public int getNum() {
    return num;
  }

  /**
   * @return the den
   */
  public int getDen() {
    return den;
  }

  /**
   * @param num the num to set
   */
  public void setNum(int num) {
    this.num = num;
  }

  /**
   * @param den the den to set
   */
  public void setDen(int den) {
    this.den = den;
    
    // si el denominador es 0 deberíamos lanzar una excepción, pero como aun no se han visto
    // vamos a mostrar un mensaje de error
    if (den == 0) {
      System.err.println("El estado del objeto es incorrecto al ser el denominador 0.");
    }
  }

  public double resultado() {
    return (double) this.num / this.den;
  }
  
  public Fraccion multiplicar(int n) {
    return new Fraccion(this.num * n, this.den).simplificar();
  }
  
  public Fraccion multiplicar(Fraccion f) {
    return new Fraccion(this.num * f.num, this.den * f.den).simplificar();
  }
  
  public Fraccion sumar(Fraccion f) {
    return new Fraccion(this.num * f.den + this.den * f.num, this.den * f.den).simplificar();
  }
  
  public Fraccion restar(Fraccion f) {
    return this.sumar(f.multiplicar(-1));
  }
  
  private Fraccion simplificar() {
    boolean negativo = false;
    if (this.num < 0) {
      this.num *= -1;
      negativo = true;
    }
    int aux1 = this.num;
    int aux2 = this.den;
    int aux3;
    while (aux1 % aux2 != 0) {
      aux3 = aux1 % aux2;
      aux1 = aux2;
      aux2 = aux3;
    }
    if (negativo) {
      this.num /= (aux2 * -1);
      this.den /= aux2;
      return this;
    } else {
    this.num /= aux2;
    this.den /= aux2;
    return this;
    }
  }
  
  
  @Override
  public String toString() {
    return num + "/" + den;
  }
  
  
  
}
