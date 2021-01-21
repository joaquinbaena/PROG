package tanda1ejerciciosPOO;

public class Tiempo {
  // Atributos
  private int horas;
  private int minutos;
  private int segundos;

  /**
   * Constructor
   * @param horas
   * @param minutos
   * @param segundos
   */
  Tiempo (int horas,int minutos,int segundos){
    int s = horas * 3600 + minutos * 60 + segundos;
    
    this.horas = s / 3600;
    this.minutos = (s % 3600) / 60;
    this.segundos = (s % 3600) % 60;
  }

  /**
   * @return the horas
   */
  public int getHoras() {
    return horas;
  }

  /**
   * @return the minutos
   */
  public int getMinutos() {
    return minutos;
  }

  /**
   * @return the segundos
   */
  public int getSegundos() {
    return segundos;
  }
  
  /**
   * sumando tiempo
   * @param sumando
   * @return
   */
  public Tiempo sumar(Tiempo sumando) {
    return new Tiempo(this.horas + sumando.horas, this.minutos + sumando.minutos,
        this.segundos + sumando.segundos);
  }
  
  /**
   * restando tiempo
   * @param restando
   * @return
   */
  public Tiempo restar(Tiempo restando) {
    return new Tiempo(this.horas - restando.horas, this.minutos - restando.minutos,
        this.segundos - restando.segundos);
  }

  /**
   * Creamos un objeto auxiliar para pasarlo por el constructor, y así normalizar el tiempo,
   * para así despues asignarselo al objeto al que le queremos sumar los segundos.
   * @param segundos
   */
  public void sumaSegundos(int segundos) {
    Tiempo aux = new Tiempo(this.horas, this.minutos, this.segundos+segundos);
        
    this.horas = aux.horas;
    this.minutos = aux.minutos;
    this.segundos = aux.segundos;
  }
  
  /**
   * Resta los segundos en el objeto que se usa el método
   * @param segundos
   */
  public void restaSegundos(int segundos) {
    this.sumaSegundos(-1 * segundos);
  }
  
  /**
   * Suma los minutos en el objeto que se usa el método
   * @param minutos
   */
  public void sumaMinutos(int minutos) {
    this.sumaSegundos(60 * minutos);
  }
  
  /**
   * Resta los minutos en el objeto en el que se usa el método
   * @param minutos
   */
  public void restaMinutos(int minutos) {
    this.sumaSegundos(-60 * minutos);
  }
  
  /**
   * Suma las horas y las cambia en el objeto que usa el método
   * @param horas
   */
  public void sumaHoras(int horas) {
    this.sumaSegundos(3600 * horas);
  }
  
  /**
   * Resta solamente las horas y las cambia en el objeto que utiliza el método
   * @param horas
   */
  public void restaHoras(int horas) {
    this.sumaSegundos(-3600 * horas);
  }
  
  /**
   * método toString
   */
  public String toString() {
    return this.horas + "h " + this.minutos + "m " + this.segundos + "s";
  }
  
}
