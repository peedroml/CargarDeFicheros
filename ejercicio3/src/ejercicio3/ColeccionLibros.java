package ejercicio3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class ColeccionLibros {
    private ArrayList<Libro> coleccion_libros;
    
    public void cargarLibro(String nombre){
        String linea,titulo,autor;
        String[] partes;
        int calificacion;
        int paginas;
        
        try{
            FileReader fr=new FileReader(nombre);
            BufferedReader br=new BufferedReader(fr);
            
            while((linea=br.readLine())!=null){
                partes=linea.split("@");
                
                titulo=partes[0];
                autor=partes[1];
                calificacion=Integer.parseInt(partes[2]);
                paginas=Integer.parseInt(partes[3]);
                
                this.añadirLibro(titulo, autor, calificacion, paginas);
            }
            
            br.close();
            fr.close();
        }catch (FileNotFoundException fnf){
            throw new LibroExcepcion("Fichero no encontrado");
        }catch (IOException ioe){
            throw new LibroExcepcion("Fallo al leer el archivo");
        }catch (NumberFormatException nfe){
            throw new LibroExcepcion("Datos numericos no validos");
        }catch (ArrayIndexOutOfBoundsException aio) {
            throw new LibroExcepcion("Falta de datos a la hora de crear libro");
        }
    }
    
    public void guardarLibro(String nombre){
        try{
            FileWriter fw=new FileWriter(nombre);
            PrintWriter pw=new PrintWriter(fw);
            
            for(Libro objeto : this.coleccion_libros){
                pw.println(objeto.getTitulo() + "@" +
                           objeto.getAutor() + "@" +
                           objeto.getCalificacion() + "@" + 
                           objeto.getPaginas());
            }
            
            pw.close();
            fw.close();
            
        }catch(IOException ioe){
            System.out.println("Fallo al guardar el libro");
        }
    }
    
    public ColeccionLibros(){
        coleccion_libros=new ArrayList<>();
    }
    
    
    //No lo pide el enunciado pero me gusta reutilizar y modularizar el codigo 
    private Libro buscarLibro(String titulo){
        Libro objeto_libro,res=null;
        int i;
        
        i=0;
        while(i<coleccion_libros.size() && res==null){
            objeto_libro=coleccion_libros.get(i);
            if(objeto_libro.getTitulo().equals(titulo)){
                res=objeto_libro;
            }
            i++;
        }
        
        return res;
    }
    
    public void añadirLibro(String titulo,String autor,int calificacion,int paginas){
        Libro nuevo_libro,buscado;
        
        buscado=buscarLibro(titulo);
        if(buscado==null){
            nuevo_libro=new Libro(titulo,autor,calificacion,paginas);
            coleccion_libros.add(nuevo_libro);
            System.out.println("Libro añadido con exito");
        }else{
            throw new LibroExcepcion("Ese libro ya existe");
        }
    }
    
    public String toString(){
        String res;
        
        if(coleccion_libros.size()>0){
            res="Libros disponibles en la coleccion\n";
            for(Libro objeto_libro:this.coleccion_libros){
                res+=objeto_libro.toString();
            }
        }else{
            res="No hay libros en la coleccion todavia";
        }
        
        return res;
        
    }
    
    public void borrarLibro(String titulo){
        Libro buscado;
        
        buscado=buscarLibro(titulo);
        if(buscado!=null){
            coleccion_libros.remove(buscado);
            System.out.println("Libro borrado con exito");
        }else{
            throw new LibroExcepcion("No existe ese libro");
        }
    }
       
    //Si no hay libros de dicho autor lanzar una excepcion con un mensaje adecuado
    public double mediaLibrosAutor(String autor){
        double suma=0,media;
        int contador=0;
        
        
        Iterator<Libro> iter=this.coleccion_libros.iterator();
        Libro objeto_libro;
        
        while(iter.hasNext()){
            objeto_libro=iter.next();
            if(objeto_libro.getAutor().equals(autor)){
                suma+=objeto_libro.getCalificacion();
                contador++;
            }
        }
        
        if(contador>0){
            media=suma/contador;
        }else{
            throw new LibroExcepcion("No hemos encontrado libro de este autor");
        }
        
        return media;
    } 
   
    public String libros100Paginas(){
        String res;
        
        res="Libros cortos";
        for(Libro objeto_libro:this.coleccion_libros){
            if(objeto_libro.getPaginas()<=100){
                res+=objeto_libro.toString();
            }
        }
        
        return res;
        
    }
    
    public Libro mayorCalificacion(){
        Libro res;
        
        if(coleccion_libros.size()>0){
            res=coleccion_libros.get(0);
            for(Libro objeto_libro:coleccion_libros){
                if(objeto_libro.getCalificacion()>res.getCalificacion()){
                    res=objeto_libro;
                }
            }
        }else{
            res=null;
        }
        
        return res;
    }
    
    
    public String libros3y5Estrellas(){
        String res;
        
        res="Libros mejores de la coleccion";
        
        Iterator<Libro> iter=this.coleccion_libros.iterator();
        Libro objeto_libro;
        
        while(iter.hasNext()){
            objeto_libro=iter.next();
            if(objeto_libro.getCalificacion()>=3 
               && objeto_libro.getCalificacion()<=5){
                res+=objeto_libro.toString();
            }
        }
        
        return res;
        
    }


    //Constructor de copias filtrado por autor
    //Este constructor es distinto a los que hemos visto hasta ahora
    //Mientras tenga parametros distintos se pueden hacer todos
    //los constructores que queramos
    public ColeccionLibros(ColeccionLibros cl,String autor){
        Libro copia;
        this.coleccion_libros=new ArrayList<>();
        
        Iterator<Libro> iter=cl.coleccion_libros.iterator();
        Libro objeto_libro;
        
        while(iter.hasNext()){
            objeto_libro=iter.next();
            if(objeto_libro.getAutor().equals(autor)){
                copia=new Libro(objeto_libro);
                this.coleccion_libros.add(copia);
            }
        }
    }
    
    public Libro mayorCalificacionAutor(String autor){
        Libro res=null,objeto_libro;
        int i;
        
        Iterator<Libro> iter=this.coleccion_libros.iterator();
        
        while(iter.hasNext() && res==null){
            objeto_libro=iter.next();
            if(objeto_libro.getAutor().equals(autor)){
                res=objeto_libro;
            }
        }
        
        if(res!=null){ //Recorremos el resto del arraylist
            while(iter.hasNext()){
                objeto_libro=iter.next();
                if(objeto_libro.getAutor().equals(autor) && res.getCalificacion()<objeto_libro.getCalificacion()){
                    res=objeto_libro;
                }
            }
        }
        
        return res;
    }
    
    
    public void borrarPeores(){
        Iterator<Libro> iter=this.coleccion_libros.iterator();
        Libro objeto_libro;
        
        while(iter.hasNext()){
            objeto_libro=iter.next();
            if(objeto_libro.getCalificacion()>=0 
               && objeto_libro.getCalificacion()<=2){
                iter.remove();
            }
        }
        
    }
    //=========================================================
    
    
    
    
    //Constructor de copias 3 y 5 estrellas y menos 100 paginas
    public ColeccionLibros(ColeccionLibros cl){
        Libro copia;
        this.coleccion_libros=new ArrayList<>();
        
        for(Libro objeto_libro:cl.coleccion_libros){
            if(objeto_libro.getCalificacion()>=3 
               && objeto_libro.getCalificacion()<=5
               && objeto_libro.getPaginas()<100){
                copia=new Libro(objeto_libro);
                this.coleccion_libros.add(copia);
            }
        }
    }
    
    
}
