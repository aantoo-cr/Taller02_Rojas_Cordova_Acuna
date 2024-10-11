class Habitacion {

    private String estado; // "OA"(OcupadaAlimentación),"OS"(OcupadaAlimentación),"R"(Reservada),"D"(Disponible)
    private int noches;
    private boolean alimentacion;

    public Habitacion(int i) {
        this.estado = "D"; // Todas las habitaciones empiezan disponibles
    }

    public String getEstado() {
        return estado;
    }

    public void reservar(int noches, boolean alimentacion) {
        try {   //Manejo de excepcion con try-catch
            if (!estado.equals("D")) {
                throw new IllegalStateException("No se puede reservar esta habitación. No está disponible.");
            }
            this.noches = noches;
            this.alimentacion = alimentacion;
            this.estado = "R"; // Reserva la habitacion
            System.out.println("La habitación ha sido reservada exitosamente.");
        } catch (IllegalStateException e) {
            System.out.println("Error al intentar reservar: " + e.getMessage());
        }
    }

    public void ocupar() {
        if (!estado.equals("R")) {
            throw new IllegalStateException("No se puede ocupar esta habitación. No está reservada.");
        }
        this.estado = alimentacion ? "OA" : "OS"; // Cambia el estado a Ocupada
    }
    private int calcularMonto() {
        int tarifa = alimentacion ? 45000 : 30000;  //Si la alimentacion es TRUE precio = 45000, si es FALSE precio= 30000
        return tarifa * noches;
    }

    public String liberar() {
        if (!estado.equals("OA") && !estado.equals("OS")) { // Para verificar que la habitacion esté ocupada
            throw new IllegalStateException("Esta habitación no está ocupada.");
        }
        int total = calcularMonto();
        estado = "D"; // Cambia el estado a Disponible
        return generarBoleta(total);
    }

    private String generarBoleta(int total) {
        return "Boleta:\nNoches: " + noches + "\nCon alimentación: " + (alimentacion ? "Sí" : "No") + "\nMonto total: $" + total;
    }

    public void reset() {
        this.estado = "D";
        this.noches = 0;
        this.alimentacion = false;
    }


//metodos para el menu con las 2 clases creadas

}