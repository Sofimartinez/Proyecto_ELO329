public class Persona {
    private String nombre1;
    private String nombre2;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;

    public Persona(String n1, String n2, String ap, String am, String r){
        nombre1 = n1;
        nombre2 = n2;
        apellidoPaterno = ap;
        apellidoMaterno = am;
        rut = r;
    }
    public String getNombre1() {
        return nombre1;
    }
    public String getNombre2() {
        return nombre2;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public String getRut() {
        return rut;
    }
}
