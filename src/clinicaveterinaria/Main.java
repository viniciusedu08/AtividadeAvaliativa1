import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Veterinario> veterinarios = new ArrayList<>();
    static ArrayList<Sala> salas = new ArrayList<>();
    static ArrayList<Atendimento> atendimentos = new ArrayList<>();

    public static void main(String[] args) {
        criarDados();
        int opcao;
        do {
            System.out.println("\n===== CLÍNICA VETERINÁRIA =====");
            System.out.println("1 - Cadastrar atendimento");
            System.out.println("2 - Associar veterinário à sala");
            System.out.println("3 - Atribuir atendimento à sala");
            System.out.println("4 - Exibir atendimentos da sala");
            System.out.println("5 - Total de finalizados por sala");
            System.out.println("6 - Buscar atendimento por status");
            System.out.println("7 - Detalhes de atendimento");
            System.out.println("8 - Finalizar atendimento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(sc.nextLine());
            switch (opcao) {
                case 1:
                    cadastrarAtendimento();
                    break;
                case 2:
                    associarVeterinario();
                    break;
                case 3:
                    atribuirSala();
                    break;
                case 4:
                    listarSala();
                    break;
                case 5:
                    contarFinalizados();
                    break;
                case 6:
                    buscarStatus();
                    break;
                case 7:
                    detalhes();
                    break;
                case 8:
                    finalizar();
                    break;
                case 0:
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // Cria os 3 veterinários e 3 salas exigidos.
    static void criarDados() {
        veterinarios.add(
                new Veterinario(
                        "Ana",
                        "111",
                        "Clínica Geral",
                        "9999-1111"));
        veterinarios.add(
                new Veterinario(
                        "Bruno",
                        "222",
                        "Cirurgia",
                        "9999-2222"));
        veterinarios.add(
                new Veterinario(
                        "Carla",
                        "333",
                        "Dermatologia",
                        "9999-3333"));
        salas.add(new Sala(101, "A", 3, "Consulta"));
        salas.add(new Sala(102, "A", 2, "Cirurgia"));
        salas.add(new Sala(201, "B", 4, "Consulta"));
    }

    // 1 - Cadastrar atendimento
    static void cadastrarAtendimento() {
        System.out.println("\n--- CADASTRAR ATENDIMENTO ---");
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Nome do animal: ");
        String animal = sc.nextLine();
        System.out.print("Espécie: ");
        String especie = sc.nextLine();
        System.out.print("Nome do tutor: ");
        String tutor = sc.nextLine();
        System.out.print("Data: ");
        String data = sc.nextLine();
        System.out.print("Horário: ");
        String horario = sc.nextLine();
        System.out.print("Observações: ");
        String obs = sc.nextLine();
        System.out.print("Nome do procedimento: ");
        String nomeProc = sc.nextLine();
        System.out.print("Duração: ");
        int duracao = Integer.parseInt(sc.nextLine());
        System.out.print("Valor: ");
        double valor = Double.parseDouble(sc.nextLine());
        System.out.print("Complexidade: ");
        String complexidade = sc.nextLine();
        Procedimento procedimento = new Procedimento(
                nomeProc,
                duracao,
                valor,
                complexidade);
        Atendimento atendimento = new Atendimento(
                codigo,
                animal,
                especie,
                tutor,
                data,
                horario,
                obs,
                procedimento);
        atendimentos.add(atendimento);
        System.out.println("Atendimento cadastrado!");
    }

    // 2 - Associar veterinário à sala
    static void associarVeterinario() {
        System.out.println("\n--- ASSOCIAR VETERINÁRIO ---");
        System.out.println("Veterinários:");
        for (int i = 0; i < veterinarios.size(); i++) {
            System.out.println(
                    i + " - " + veterinarios.get(i));
        }
        System.out.print("Escolha: ");
        int v = Integer.parseInt(sc.nextLine());
        Veterinario veterinario = veterinarios.get(v);
        if (veterinario.getSala() != null) {
            System.out.println(
                    "Esse veterinário já possui uma sala.");
            return;
        }
        System.out.println("\nSalas:");
        for (int i = 0; i < salas.size(); i++) {
            System.out.println(
                    i + " - " + salas.get(i));
        }
        System.out.print("Escolha: ");
        int s = Integer.parseInt(sc.nextLine());
        Sala sala = salas.get(s);
        if (sala.getVeterinario() != null) {
            System.out.println(
                    "Essa sala já possui veterinário.");
            return;
        }
        sala.setVeterinario(veterinario);
        veterinario.setSala(sala);
        System.out.println(
                "Veterinário associado com sucesso!");
    }

    // 3 - Atribuir atendimento à sala
    static void atribuirSala() {
        System.out.println("\n--- ATRIBUIR ATENDIMENTO ---");
        System.out.print("Código do atendimento: ");
        String codigo = sc.nextLine();
        Atendimento atendimento = buscarCodigo(codigo);
        if (atendimento == null) {
            System.out.println("Atendimento não encontrado.");
            return;
        }
        if (atendimento.getStatus() != Status.AGENDADO) {
            System.out.println(
                    "O atendimento não está AGENDADO.");
            return;
        }
        for (int i = 0; i < salas.size(); i++) {
            System.out.println(
                    i + " - " + salas.get(i));
        }
        System.out.print("Escolha a sala: ");
        int s = Integer.parseInt(sc.nextLine());
        Sala sala = salas.get(s);
        if (sala.getVeterinario() == null) {
            System.out.println(
                    "A sala não possui veterinário.");
            return;
        }
        if (sala.adicionarAtendimento(atendimento)) {
            atendimento.setSala(sala);
            atendimento.setStatus(Status.EM_ANDAMENTO);
            System.out.println(
                    "Atendimento atribuído à sala!");
        } else {
            System.out.println(
                    "Não foi possível atribuir.");
            System.out.println(
                    "Verifique capacidade ou procedimento.");
        }
    }

    // 4 - Exibir atendimentos da sala
    static void listarSala() {
        System.out.print("Número da sala: ");
        int numero = Integer.parseInt(sc.nextLine());
        Sala sala = buscarSala(numero);
        if (sala == null) {
            System.out.println("Sala não encontrada.");
            return;
        }
        System.out.println("\n" + sala);
        for (Atendimento a : sala.getAtendimentos()) {
            a.exibir();
        }
        System.out.println(
                "Total: " + sala.getAtendimentos().size());
    }

    // 5 - Contar finalizados por sala
    static void contarFinalizados() {
        for (Sala sala : salas) {
            int total = 0;
            for (Atendimento a : atendimentos) {
                if (a.getStatus() == Status.FINALIZADO
                        && a.getSala() == sala) {
                    total++;
                }
            }
            System.out.println(
                    "Sala " + sala.getNumero()
                            + ": " + total
                            + " finalizado(s)");
        }
    }

    // 6 - Buscar por status
    static void buscarStatus() {
        System.out.println("\n1 - AGENDADO");
        System.out.println("2 - EM_ANDAMENTO");
        System.out.println("3 - FINALIZADO");
        System.out.print("Escolha: ");
        int op = Integer.parseInt(sc.nextLine());
        Status status;
        if (op == 1) {
            status = Status.AGENDADO;
        } else if (op == 2) {
            status = Status.EM_ANDAMENTO;
        } else if (op == 3) {
            status = Status.FINALIZADO;
        } else {
            System.out.println("Opção inválida.");
            return;
        }
        for (Atendimento a : atendimentos) {
            if (a.getStatus() == status) {
                a.exibir();
            }
        }
    }

    // 7 - Detalhes de atendimento
    static void detalhes() {
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        Atendimento a = buscarCodigo(codigo);
        if (a == null) {
            System.out.println("Não encontrado.");
        } else {
            a.exibir();
        }
    }

    // 8 - Finalizar atendimento
    static void finalizar() {
        System.out.print("Código do atendimento: ");
        String codigo = sc.nextLine();
        Atendimento a = buscarCodigo(codigo);
        if (a == null) {
            System.out.println("Não encontrado.");
            return;
        }
        if (a.getStatus() != Status.EM_ANDAMENTO) {
            System.out.println(
                    "O atendimento precisa estar EM_ANDAMENTO.");
            return;
        }
        a.setStatus(Status.FINALIZADO);
        if (a.getSala() != null) {
            a.getSala().removerAtendimento(a);
        }
        System.out.println(
                "Atendimento finalizado!");
    }

    static Atendimento buscarCodigo(String codigo) {
        for (Atendimento a : atendimentos) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }

    static Sala buscarSala(int numero) {
        for (Sala s : salas) {
            if (s.getNumero() == numero) {
                return s;
            }
        }
        return null;
    }
}