package ejercicio3;

import java.util.Objects;

public class Libro {
    private String titulo,autor;
    private int calificacion,paginas;

    public Libro(String titulo, String autor, int calificacion, int paginas) {
        //El titulo y autor no pueden ser vacios
        //calificacion y paginas tienen sus propias restricciones
        if(!titulo.equals("") && !autor.equals("") && calificacion>0 && paginas>0){
            this.titulo = titulo;
            this.autor = autor;
            this.calificacion = calificacion;
            this.paginas = paginas;
        }else{
            throw new LibroExcepcion("Tipo de libro erroneo");
        }
            
    }
    
    public Libro(Libro l) {
        this.titulo = l.titulo;
        this.autor = l.autor;
        this.calificacion = l.calificacion;
        this.paginas = l.paginas;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCalificacion() {
        return this.calificacion;
    }

    public void setCalificacion(int calificacion) {
        if(calificacion>=0 && calificacion<=5){
            this.calificacion = calificacion;
        }else{
            throw new LibroExcepcion("Error al introducir la calificacion");
        }
    }

    public int getPaginas() {
        return this.paginas;
    }

    public void setPaginas(int paginas) {
        if(paginas>0){
            this.paginas = paginas;
        }else{
            throw new LibroExcepcion("Error al colocar las paginas del libro");
        }
    }

    public String toString(){
        String res;
        
        res="#####################\n"+
            "Titulo:"+this.titulo+"\n"+
            "Autor:"+this.autor+"\n"+
            "Paginas:"+this.paginas+"\n"+
            "Estrellas:"+this.calificacion+"\n"+    
            "#####################\n";    
        
        return res;
    }
}
