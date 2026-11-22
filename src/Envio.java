public class Envio {
    private String guia;
    private String destinatario;
    private String ciudadDestino;
    private double pesoKg;

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public double getPesoKg() {
        return pesoKg;
    }


    // constructor
    public Envio(String guia,String destinatario, String ciudadDestino, double pesoKg ){
        this.guia = guia;
        this.destinatario = destinatario;
        this.ciudadDestino = ciudadDestino;
        this.pesoKg = pesoKg;
    }

    public void setPesoKg(double pesoKg){
        if(pesoKg >0){this.pesoKg = pesoKg;}}


    public int kilosFacturables(){
        int entero = (int) this.pesoKg;
        if(this.pesoKg > entero){
            return entero +1;
        }
        return entero;    }

    public double calcularCosto(){
        return kilosFacturables() *2;
    }


    public String describir(){
        return "Guia: "+this.guia+" Destinatario: "+ this.destinatario+ " ciudad: "+ this.ciudadDestino + " peso: "+this.pesoKg;
    }

}
