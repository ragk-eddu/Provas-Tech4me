import Classes.*;
import java.io.IOException;
import java.util.Scanner;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        // int qtdAeronaves = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);

        int atualArmazenamento = MAX_ELEMENTOS;
        int novoArmazenamento = 0;
        // Adicionados :::

        do {

            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaco de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("\nOpcao: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                System.out.println("\n");
                if (qtdCadastrados == atualArmazenamento) {
                    System.out.println("\nNao ha espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                } else {

                    // Cadastro :::

                    String nome, breve, cpf;
                    int matricula;

                    // String modelo, categoria;

                    System.out.println("\nPiloto " + (qtdCadastrados + 1) + ": ");

                    // ATRIBUTOS PILOTO
                    System.out.println("\nNome: ");
                    nome = (in.nextLine());

                    Boolean formatoCpf = false;

                    do {
                        System.out.println("\nCPF: [Formato: 12345678910]");
                        cpf = (in.nextLine());

                        if (!(cpf.length() == 11)) {
                            System.out.println(
                                    "\nERRO!\nCPF informado nao atende o Formato estabelecido!\nTente Novamente...");
                            formatoCpf = false;
                        } else {
                            formatoCpf = true;
                        }

                    } while (formatoCpf == false);

                    System.out.println("\nBreve: ");
                    breve = (in.nextLine());
                    System.out.println("\nMatricula: ");
                    matricula = (in.nextInt());
                    in.nextLine();

                    Piloto piloto = new Piloto(nome, cpf, breve, matricula);

                    pilotos[qtdCadastrados] = piloto;

                    System.out.println("\n");
                }

                // Cadastre seu piloto aqui

                System.out.println("\nPiloto cadastrado com sucesso.");

                // Qntd de Pilotos aumenta :::
                qtdCadastrados = +qtdCadastrados + 1;

                voltarMenu(in);

            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNao ha pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.println("\n> Piloto " + (i + 1) + "\n");
                    System.out.println("> Nome: " + pilotos[i].getNome());
                    System.out.println("> CPF: " + pilotos[i].getCpf());
                    System.out.println("> Matricula: " + ((Piloto) pilotos[i]).getMatricula());
                    System.out.println("> Breve: " + ((Piloto) pilotos[i]).getBreve());

                }

                System.out.println("\n");

                voltarMenu(in);
            } else if (opcao == 3) {

                String cpfBuscado = "12345678910";

                System.out.println("\nInsira CPF buscado:");
                cpfBuscado = in.nextLine();

                boolean encontrado = false;

                for (int i = 0; i < qtdCadastrados; i++) {
                    if (cpfBuscado.equals(pilotos[i].getCpf())) {

                        encontrado = true;

                        System.out.println("\n> Piloto " + (i + 1));
                        System.out.println("> Nome: " + pilotos[i].getNome());
                        System.out.println("> CPF: " + pilotos[i].getCpf());
                        System.out.println("> Matricula: " + ((Piloto) pilotos[i]).getMatricula());
                        System.out.println("> Breve: " + ((Piloto) pilotos[i]).getBreve());
                        System.out.println("\n");

                    } else {
                        encontrado = false;
                    }
                }

                if (encontrado == false) {
                    System.out.println("\nCPF inserido nao encontrado!");
                }

            } else if (opcao == 4) {
                System.out.println(
                        "\n> Quantidade Maxima Atual: " + (atualArmazenamento) + " pilotos"
                                + "\n> Insira novo armazenamento:");

                novoArmazenamento = in.nextInt();

                in.nextLine();

                if (novoArmazenamento < atualArmazenamento) {
                    do {

                        System.out.println("\nNovo Armazenamento deve ser maior que anterior.\nTente novamente...");
                        System.out.println(
                                "\nQuantidade Maxima Atual de " + atualArmazenamento + " pilotos"
                                        + "\n> Insira novo armazenamento:");

                        novoArmazenamento = in.nextInt();

                    } while (novoArmazenamento < atualArmazenamento);

                }

                Pessoa[] vetorAuxiliar = new Pessoa[(atualArmazenamento)];
                vetorAuxiliar = pilotos;

                pilotos = new Pessoa[(novoArmazenamento)];
                pilotos = vetorAuxiliar;

                // System.out.println("\n...");
                System.out.println("\n");
                System.out.println("> Quantidade Maxima Atualizada: " + novoArmazenamento);

            } else if (opcao != 0) {
                System.out.println("\nOpcao invalida!");
            }

            atualArmazenamento = novoArmazenamento;
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}