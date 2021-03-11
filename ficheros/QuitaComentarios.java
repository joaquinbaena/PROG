package ficheros;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Este programa quitará los comentarios de los archivos .java
 * @author Joaquín Baena Salas
 *
 */
public class QuitaComentarios {

  public static void main(String[] args) {
    String lineaLectura = "";
    boolean comentarioBloque = false;
    if (args.length != 2) {
      System.out.println("Debe de introducir como argumentos el nombre del programa que deseas modoficar y el nombre"
          + " del programa ya modificado");
      System.exit(1);
    }
    try {
      // Abrimos como lectura el archivo introducido en el primer parametro
      var br = Files.newBufferedReader(Paths.get(args[0]));
      
      // Creamos como escritura el archivo intrudicido en el segundo parametro
      var bw = Files.newBufferedWriter(Paths.get(args[1]), StandardOpenOption.CREATE);
      
      while((lineaLectura = br.readLine()) != null) {
        
        // buscamos los comentarios de bloque
        if(lineaLectura.indexOf("/*") != -1) {
          comentarioBloque = true;  
        }
        
        // buscamos el final del comentario de bloque
        if(lineaLectura.indexOf("*/") != -1) {
          comentarioBloque = false;
          lineaLectura = lineaLectura.substring(lineaLectura.indexOf("/") + 1,lineaLectura.length());
        }
        
        // buscamos los comentarios simples
        if(lineaLectura.indexOf("//") != -1) {
          lineaLectura = lineaLectura.substring(0,lineaLectura.indexOf("/") - 1);
        }
        
        if(!comentarioBloque) {
          bw.write(lineaLectura);
          bw.newLine();
        }
      }
      br.close();
      bw.close();
    } catch (IOException e) {
      System.out.println("No se encontró archivo de lectura, o no se pudo crear archivo para escritura");
    }
    
    

  }

}
