package ejercicio3;

import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        ColeccionLibros objeto_coleccion=new ColeccionLibros();
        ColeccionLibros copia_coleccion;
        Libro objeto_libro;
        String autor,titulo,descripcion,nombre;
        int opcion,estrellas,paginas;
      
        
        do{
            System.out.println("0.Salir");
            System.out.println("1.Añadir libro");
            System.out.println("2.Ver todos los libros");
            System.out.println("3.Borrar libro por titulo");
            System.out.println("4.Constructor de copias por autor");
            System.out.println("5.Media libros autor");
            System.out.println("6.Libros entre 3 y 5 estrellas");
            System.out.println("7.Libros con menos de 100 paginas");
            System.out.println("8.Constructor de copias por estrellas y paginas");
            System.out.println("9.Mayor calificacion");
            System.out.println("10.Mayor calificacion de un autor");
            System.out.println("11.Borrar los libros entre 0 y 2 estrellas");
            System.out.println("12.Cargar datos de un fichero");
            System.out.println("13.Escribir datos en un fichero");
            opcion=teclado.nextInt();
            
            switch(opcion){
                case 0:
                    System.out.println("Adios");
                    break;
                case 1:
                    System.out.println("Titulo:");
                    titulo=teclado.next();
                    System.out.println("Autor:");
                    autor=teclado.next();
                    System.out.println("Estrellas libro");
                    estrellas=teclado.nextInt();
                    System.out.println("Paginas");
                    paginas=teclado.nextInt();
                    objeto_coleccion.añadirLibro(titulo, autor, estrellas, paginas);
                    System.out.println("Cerveza añadida con exito");
                    break;
                case 2:
                    System.out.println(objeto_coleccion.toString());
                    break;
                case 3:
                    System.out.println("Dime el titulo:");
                    titulo=teclado.next();
                    objeto_coleccion.borrarLibro(titulo);
                    System.out.println("Libro borrado con exito");
                    break;
                case 4:
                    System.out.println("Dime el autor:");
                    autor=teclado.next();
                    copia_coleccion=new ColeccionLibros(objeto_coleccion,autor);
                    System.out.println(copia_coleccion.toString());
                    break;
                case 5:
                    System.out.println("Dime el autor:");
                    autor=teclado.next();
                    System.out.println("Media estrellas autor:"+objeto_coleccion.mediaLibrosAutor(autor));
                    break;
                case 6:
                    
                    System.out.println(objeto_coleccion.libros3y5Estrellas());
                    break;
                case 7:
                    
                    System.out.println(objeto_coleccion.libros100Paginas());
                    break;
                case 8:
                    copia_coleccion=new ColeccionLibros(objeto_coleccion);
                    System.out.println(copia_coleccion.toString());
                    break;
                case 9:
                    objeto_libro=objeto_coleccion.mayorCalificacion();
                    System.out.println(objeto_libro.toString());
                   
                    break;
                case 10:
                    System.out.println("Dime el autor:");
                    autor=teclado.next();
                    objeto_libro=objeto_coleccion.mayorCalificacionAutor(autor);
                    System.out.println(objeto_libro.toString());
                    
                    break;
                case 11:
                    objeto_coleccion.borrarPeores();
                    System.out.println("Borrados los libros entre 0 y 2 estrellas");
                    break;
                    
                case 12:
                    System.out.println("Escribe el nombre del fichero a cargar: ");
                    nombre=teclado.next();
                    
                    objeto_coleccion.cargarLibro(nombre);
                    
                    System.out.println("Libros cargados con exito");
                    break;
                    
                case 13:
                    System.out.println("Escribe el nombre del fichero a guardar: ");
                    nombre=teclado.next();
                    
                    objeto_coleccion.guardarLibro(nombre);
                    
                    System.out.println("Libros guardados con exito");
                    break;
                    
                default:
                    System.out.println("Opcion incorrecta");
            }
        }while(opcion!=0);
    }
    
}
