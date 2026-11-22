import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contador = 1001;
        HashMap<String, Envio> enTransito = new HashMap<>();
        List<Envio> entregados = new ArrayList<>();
        int opcion;

        do {
            System.out.println("\n--- MENU SERVIENTREGA ---");
            System.out.println("1. Registrar envio");
            System.out.println("2. Consultar envio");
            System.out.println("3. Entregar envio");
            System.out.println("4. Listar entregados");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Tipo de servicio (1=Nacional, 2=Express, 3=Internacional): ");
                    int tipo = Integer.parseInt(sc.nextLine());

                    System.out.print("Nombre del destinatario: ");
                    String destinatario = sc.nextLine();

                    System.out.print("Ciudad de destino: ");
                    String ciudad = sc.nextLine();

                    System.out.print("Peso en kilos: ");
                    double peso = Double.parseDouble(sc.nextLine());
                    if (peso <= 0) {
                        System.out.println("Error: El peso debe ser mayor a cero.");
                        break;
                    }

                    Envio nuevoEnvio = null;
                    String guia = "";
                    if (tipo == 1 || tipo == 2) {
                        System.out.print("Entrega a domicilio? (S/N): ");
                        boolean domicilio = sc.nextLine().equalsIgnoreCase("S");

                        if (tipo == 1) {
                            guia = "NAC" + String.valueOf(contador);
                            nuevoEnvio = new EnvioNacional(guia, destinatario, ciudad, peso, domicilio);
                        } else {
                            guia = "EXP" + String.valueOf(contador);
                            System.out.print("Entrega en 24 horas? (S/N): ");
                            if (sc.nextLine().equalsIgnoreCase("S")) {
                                nuevoEnvio = new EnvioExpress(guia, destinatario, ciudad, peso, domicilio, 24);
                            } else {
                                nuevoEnvio = new EnvioExpress(guia, destinatario, ciudad, peso, domicilio);
                            }
                        }
                    } else if (tipo == 3) {
                        System.out.print("Valor de la compra en USD: ");
                        double valorFob = Double.parseDouble(sc.nextLine());
                        guia = "INT" + String.valueOf(contador);
                        EnvioInternacional inter = new EnvioInternacional(guia, destinatario, ciudad, peso, valorFob);

                        if (!inter.cumple4x4()) {
                            System.out.println("Envio rechazado: No cumple la regla 4x4.");
                            break;
                        }
                        nuevoEnvio = inter;
                    } else {
                        System.out.println("Tipo de servicio invalido.");
                        break;
                    }
                    enTransito.put(guia, nuevoEnvio);
                    contador++;
                    System.out.println("Envio registrado. Guia: " + guia + " | Costo: $" + nuevoEnvio.calcularCosto());
                    break;

                case 2:
                    System.out.print("Ingrese la guia a consultar: ");
                    String guiaConsulta = sc.nextLine();
                    if (enTransito.containsKey(guiaConsulta)) {
                        Envio e = enTransito.get(guiaConsulta);
                        System.out.println(e.describir() + " | Costo: $" + e.calcularCosto());
                    } else {
                        System.out.println("No existe un envio en transito con esa guia.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese la guia a entregar: ");
                    String guiaEntrega = sc.nextLine();
                    if (enTransito.containsKey(guiaEntrega)) {
                        Envio e = enTransito.remove(guiaEntrega);
                        entregados.add(e);
                        System.out.println("Envio " + guiaEntrega + " marcado como entregado.");
                    } else {
                        System.out.println("No existe un envio en transito con esa guia.");
                    }
                    break;

                case 4:
                    if (entregados.isEmpty()) {
                        System.out.println("Aun no hay envios entregados.");
                    } else {
                        double totalRecaudado = 0;
                        for (Envio e : entregados) {
                            double costo = e.calcularCosto();
                            System.out.println(e.describir() + " | Costo: $" + costo);
                            totalRecaudado += costo;
                        }
                        System.out.println("Total recaudado: $" + totalRecaudado);
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 5);
    }
}