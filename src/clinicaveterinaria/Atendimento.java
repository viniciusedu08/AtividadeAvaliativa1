public class Atendimento {
    private String codigo;
    private String animal;
    private String especie;
    private String tutor;
    private String data;
    private String horario;
    private Status status;
    private String observacoes;
    private Procedimento procedimento;
    private Sala sala;

    public Atendimento(String codigo, String animal, String especie,
            String tutor, String data, String horario,
            String observacoes, Procedimento procedimento) {
        this.codigo = codigo;
        this.animal = animal;
        this.especie = especie;
        this.tutor = tutor;
        this.data = data;
        this.horario = horario;
        this.observacoes = observacoes;
        this.procedimento = procedimento;
        this.status = Status.AGENDADO;
    }

    public String getCodigo() {
        return codigo;
    }

    public Status getStatus() {
        return status;
    }

    public Sala getSala() {
        return sala;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void exibir() {
        System.out.println("Código: " + codigo);
        System.out.println("Animal: " + animal);
        System.out.println("Espécie: " + especie);
        System.out.println("Tutor: " + tutor);
        System.out.println("Data: " + data);
        System.out.println("Horário: " + horario);
        System.out.println("Status: " + status);
        System.out.println("Observações: " + observacoes);
        System.out.println("Procedimento: " + procedimento);
        if (sala != null) {
            System.out.println("Sala: " + sala.getNumero());
            if (sala.getVeterinario() != null) {
                System.out.println("Veterinário: "
                        + sala.getVeterinario().getNome());
            }
        } else {
            System.out.println("Sala: Nenhuma");
        }
        System.out.println("-----------------------------");
    }
}
