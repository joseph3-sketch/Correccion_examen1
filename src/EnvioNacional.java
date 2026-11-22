public class EnvioNacional extends Envio{
    private boolean entregaDomicilio;

    public boolean isEntregaDomicilio() {
        return entregaDomicilio;
    }

    public void setEntregaDomicilio(boolean entregaDomicilio) {
        this.entregaDomicilio = entregaDomicilio;
    }

    public EnvioNacional(String guia, String destinatario, String ciudadDestino, double pesoKg, boolean entregaDomicilio) {
        super(guia, destinatario, ciudadDestino, pesoKg);
        this.entregaDomicilio = entregaDomicilio;
    }
    @Override
    public double calcularCosto(){
        double valorBase = super.calcularCosto();
        if(entregaDomicilio){return  valorBase + 1.50;}
        else {return valorBase;}
    }

    @Override
    public String describir(){
        return super.describir() + "Entrega a domicilio? " + (entregaDomicilio ? "si" : "no");
    }


}