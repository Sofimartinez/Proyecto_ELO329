import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @throws Clase que configura los apoderados, asignatura, alumnos, profesores, cursos y la vista de login.
 * @author Sofía Martínez
 */
public class Colegio extends Pane {
    /**
     * Constructor de Colegio, inicializa un nuevo Colegio.
     * @param in Objeto Scanner que tiene la información del archivo de configuración.
     * @param auxAsignaturas Lista de Objetos Asignatura.
     * @param auxAlumnos Lista de Objetos Alumno.
     * @param auxProfesores Lista de Objetos Profesor.
     * @param auxApoderados Lista de Objetos Apoderado.
     * @param auxCursos Lista de Objetos Curso.
     */
    public Colegio(Scanner in, ArrayList<Asignatura> auxAsignaturas, ArrayList<Alumno> auxAlumnos, ArrayList<Profesor> auxProfesores, ArrayList<Apoderado> auxApoderados, ArrayList<Curso> auxCursos){
        int numCursos = in.nextInt();
        int numProfesores = in.nextInt();
        int numApoderados = in.nextInt();

        for(int i=0; i < numCursos; i++){
            int numAlumnos = in.nextInt();
            int numAsignaturas = in.nextInt();
            String grado = in.next();
            Curso curso = new Curso(grado, numAlumnos);
            for(int j=0; j < numAlumnos; j++){
                String apePaterno = in.next();
                String apeMaterno = in.next();
                String nombre1 = in.next();
                String nombre2 = in.next();
                String rut = in.next();
                Alumno alumno = new Alumno(nombre1,nombre2,apePaterno, apeMaterno,rut,curso);
                alumno.setNumLista(j+1);
                auxAlumnos.add(alumno);
                curso.addAlumno(alumno);
            }
            for(int z=0; z < numAsignaturas; z++){
                String sigla = in.next();
                String nombre = in.next();
                int anio = in.nextInt();
                Asignatura asignatura = new Asignatura(nombre,sigla,anio,grado);
                auxAsignaturas.add(asignatura);
                curso.addAsignatura(asignatura);
            }
            auxCursos.add(curso);
        }
        for(int i=0; i < numProfesores; i++){
            int numCursosImp = in.nextInt();
            String apePaterno = in.next();
            String apeMaterno = in.next();
            String nombre1 = in.next();
            String nombre2 = in.next();
            String rut = in.next();
            String correo = in.next();
            Profesor profesor = new Profesor(nombre1,nombre2,apePaterno,apeMaterno,rut,correo);
            auxProfesores.add(profesor);
            for(int j=0; j < numCursosImp; j++){
                String sigla = in.next();
                for(int z=0; z < auxAsignaturas.size(); z++) {
                    if(sigla.equals(auxAsignaturas.get(z).getSigla())){
                        profesor.addAsignatura(auxAsignaturas.get(z));
                    }
                }
            }
        }
        for(int i=0; i < numApoderados; i++){
            String nombre1 = in.next();
            String nombre2 = in.next();
            String apePaterno = in.next();
            String apeMaterno = in.next();
            String rut = in.next();
            String correo = in.next();
            String rutPupilo = in.next();
            Apoderado apoderado = new Apoderado(nombre1,nombre2,apePaterno,apeMaterno,rut,correo);
            auxApoderados.add(apoderado);
            for(int j=0; j < auxAsignaturas.size(); j++) {
                if(rutPupilo.equals(auxAlumnos.get(j).getRut())){
                    apoderado.addPupilo(auxAlumnos.get(j));
                    auxAlumnos.get(i).setApoderado(apoderado);
                }
            }
        }
        LoginView login = new LoginView(auxProfesores, auxCursos, auxApoderados);
        getChildren().addAll(login);
    }
}
