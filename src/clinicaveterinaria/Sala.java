import java.util.ArrayList;

public class Sala {
    private int numero;
    private String bloco;
    private int capacidade;
    private String tipo;
    private Veterinario veterinario;
    private ArrayList<Atendimento> atendimentos;

    public Sala(int numero, String bloco, int capacidade, String tipo) {
        this.numero = numero;
        this.bloco = bloco;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.atendimentos = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public boolean adicionarAtendimento(Atendimento atendimento) {
        if (atendimentos.size() >= capacidade) {
            return false;
        }
        if (!atendimentos.isEmpty()) {
            String procedimentoAtual = atendimentos.get(0).getProcedimento().getNome();
            String procedimentoNovo = atendimento.getProcedimento().getNome();
            if (!procedimentoAtual.equalsIgnoreCase(procedimentoNovo)) {
                return false;
            }
        }
        atendimentos.add(atendimento);
        return true;
    }

    public void removerAtendimento(Atendimento atendimento) {
        atendimentos.remove(atendimento);
    }

    @Override
    public String toString() {
        String vet = veterinario == null
                ? "Nenhum"
                : veterinario.getNome();
        return "Sala " + numero +
                " | Bloco " + bloco +
                " | Capacidade: " + capacidade +
                " | Tipo: " + tipo +
                " | Veterinário: " + vet;
    }
}