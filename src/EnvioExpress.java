public class EnvioExpress extends EnvioNacional{
    private int horasEntrega;


    public int getHorasEntrega() {
        return horasEntrega;
    }

    public void setHorasEntrega(int horasEntrega) {
        this.horasEntrega = horasEntrega;
    }


    public EnvioExpress(String guia, String destinatario, String ciudadDestino, double pesoKg, boolean entregaDomicilio, int horasEntrega) {
        super(guia, destinatario, ciudadDestino, pesoKg, entregaDomicilio);
        this.horasEntrega = horasEntrega;
    }
    public EnvioExpress(String guia, String destinatario, String ciudadDestino, double pesoKg, boolean entregaDomicilio) {
        this(guia, destinatario, ciudadDestino, pesoKg, entregaDomicilio, 48);
    }

    @Override
    public double calcularCosto(){
        double costoNacional = super.calcularCosto();
        return costoNacional + (costoNacional * 0.40);


    }
    @Override
    public String describir(){
        return super.describir() + "Hora de entrega: ? " + this.horasEntrega;
    }
}