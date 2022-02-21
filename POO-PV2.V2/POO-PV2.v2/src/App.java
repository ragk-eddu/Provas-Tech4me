import loja.*;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {

        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Produto> produtosInclusos = new ArrayList<>();
        ArrayList<Produto> produtosComprados = new ArrayList<>();
        ArrayList<Venda> vendas = new ArrayList<>();

        String dataInicioPrograma = getDataAtual();

        Scanner in = new Scanner(System.in);
        int opcao;

        do {

            // ArrayList<String> codigos = new ArrayList<>();

            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Vendas de produto");
            System.out.println("5 - Realizar venda");
            System.out.println("6 - Abastecer produto");

            System.out.println("0 - Sair");
            System.out.print("\nOpcao: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {

                System.out.println("\n************\nNOVO PRODUTO\n************\n");

                String codigo = "````````````````````````````````````````````````";
                int quantidade;
                double valor;
                String nome;

                boolean codigoValido = true;

                System.out.println("\nCODIGO:");
                codigo = in.nextLine();

                if (produtos.size() > 0) {
                    codigoValido = validarCodigo(produtos, codigo);
                }

                if (codigoValido == true) {

                    System.out.println("\nNOME:");
                    nome = in.nextLine();

                    System.out.println("\nVALOR:");
                    valor = in.nextDouble();

                    System.out.println("\nQUANTIDADE:");
                    quantidade = in.nextInt();

                    Produto produto = new Produto(codigo, nome, valor, quantidade);
                    Produto produtoSave = new Produto(codigo, nome, valor, quantidade);

                    produtos.add(produto);
                    produtosComprados.add(produto);
                    produtosInclusos.add(produtoSave);

                } else {
                    System.out.println("\nCODIGO INDISPONIVEL...\nTENTE NOVAMENTE");
                }

                voltarMenu(in);

            } else if (opcao == 2) {
                // COMPLETADO DE FORMA MAIS SIMPLES
                // TESTES REALIZADOS : FILTER

                String codigo = "~";

                System.out.println("\nINSIRA CODIGO DO PRODUTO:");
                codigo = in.nextLine();

                System.out.printf(
                        "\n************************************************************\n%40s\n************************************************************\n",
                        " CONSULTA DE PRODUTO ");

                ArrayList<Produto> produtoProcurado = new ArrayList<>();

                int index = getProdutoProcurado(produtos, codigo);

                if (index != -1) {

                    System.out.println("\n------------------------------------------------------------");

                    System.out.printf("%10s %15s %15s %15s", "CODIGO", "NOME", "VALOR", "QUANTIDADE");
                    System.out.println("\n------------------------------------------------------------");

                    produtoProcurado.add(produtos.get(index));

                    System.out.println(produtoProcurado.get(0).toString());

                    System.out.println("\n------------------------------------------------------------");

                }

                voltarMenu(in);

            } else if (opcao == 3) {

                if (produtos.size() == 0) {
                    System.out.println("\nNENHUM PRODUTO CADASTRADO!");
                } else {

                    System.out.printf(
                            "\n************************************************************\n%40s\n************************************************************\n",
                            "LISTAGEM DOS PRODUTOS");

                    System.out.println("\n------------------------------------------------------------");

                    System.out.printf("%10s %15s %15s %15s", "CODIGO", "NOME", "VALOR", "QUANTIDADE");
                    System.out.println("\n------------------------------------------------------------");

                    produtos.stream()
                            .forEach(System.out::println);

                    System.out.println("\n------------------------------------------------------------");

                    DoubleSummaryStatistics resumo = produtos.stream()
                            .collect(Collectors.summarizingDouble(Produto::getValor));

                    System.out.printf("VALOR MEDIO %47.2f", resumo.getAverage());
                    System.out.printf("\nVALOR MAX   %47.2f", resumo.getMax());
                    System.out.printf("\nVALOR MIN   %47.2f", resumo.getMin());

                    System.out.println("\n------------------------------------------------------------");

                }

                voltarMenu(in);

            } else if (opcao == 4) {

                if (vendas.size() == 0) {
                    System.out.println("\nNENHUMA VENDA REALIZADA!");
                } else {

                    System.out.println(
                            "\nRELATORIO DE VENDAS | PERIODO:\t\tINICIALIZACAO:     " + dataInicioPrograma + " \n\t\t\t\t        FINALIZACAO:       " + getDataAtual());

                    System.out.printf(
                            "\n*****************************************************************************\n%48s\n*****************************************************************************",
                            "VENDAS DE PRODUTOS");

                    System.out.println(
                            "\n-----------------------------------------------------------------------------");

                    System.out.printf("%5s %16s %10s %10s %16s %15s", "DATA", "HORA", "NOME", "QNTD", "VALOR UNICO",
                            "VALOR TOTAL");
                    System.out.println(
                            "\n-----------------------------------------------------------------------------");

                    vendas.stream()
                            .forEach(System.out::println);

                    System.out.println(
                            "\n-----------------------------------------------------------------------------");

                    System.out.printf("%5s %69.2f"," TOTAL", getVendasTotais(vendas));
                    

                    System.out.println(
                            "\n-----------------------------------------------------------------------------");

                    // DoubleSummaryStatistics resumo = vendas.stream()
                    //         .collect(Collectors.summarizingDouble(Venda::getQuantidade));

                    // System.out.printf("%5s %63.2f", " VALOR MEDIO", resumo.getAverage());

                    System.out.printf("%5s %63.2f", " VALOR MEDIO", getVendasTotais(vendas)/vendas.size());

                    System.out.println(
                            "\n-----------------------------------------------------------------------------");



                    // System.out.println("\n------------------------------------------------------------");

                }

                voltarMenu(in);

            } else if (opcao == 5) {

                Produto produtoVendido = new Produto();
                String codigo = "";
                int quantidade;
                int index;

                System.out.printf(
                        "\n************************************************************\n%40s\n************************************************************\n",
                        "   REALIZAR VENDA   ");

                System.out.println("\nCODIGO:");
                codigo = in.nextLine();

                index = getProdutoProcurado(produtos, codigo);

                if (index == -1) {
                    System.out.println("\nTENTE NOVAMENTE");
                } else {

                    System.out.println("\nQUANTIDADE:");
                    quantidade = in.nextInt();

                    if (quantidade <= produtos.get(index).getQuantidade()) {

                        produtoVendido = produtos.get(index);
                        Venda venda = new Venda(produtoVendido, quantidade);
                        vendas.add(venda);
                        System.out.println("\nVENDA FINALIZADA");
                        in.nextLine();

                    } else {
                        System.out.println("\nNAO HA SUFICIENTE DO PRODUTO EM ESTOQUE\n\nESTOQUE ATUAL:\n");
                        System.out.println("\n------------------------------------------------------------");

                        System.out.printf("%10s %15s %15s %15s", "CODIGO", "NOME", "VALOR", "QUANTIDADE");
                        System.out.println("\n------------------------------------------------------------");

                        System.out.println(produtos.get(index));

                        System.out.println("\n------------------------------------------------------------");
                        in.nextLine();
                    }
                }

                voltarMenu(in);

            }

            
            else if (opcao == 6) {

                String codigo;
                int quantidade;

                System.out.printf(
                        "\n************************************************************\n%43s\n************************************************************\n",
                        "   ABASTECIMENTO DE ESTOQUE   ");

                System.out.println("\nCODIGO:");
                codigo = in.nextLine();

                int index = getProdutoProcurado(produtos, codigo);

                if(index != -1){System.out.println("\nQUANTIDADE:");

                    quantidade = in.nextInt();
                    in.nextLine();

                    produtos.get(index).setQuantidade(quantidade + produtos.get(index).getQuantidade());

                    System.out.println("\nPRODUTO ABASTECIDO!");
                    
                }
                
                voltarMenu(in);
            }
            

            // TESTES
            else if (opcao == 21) {

                
                System.out.println("ARRAYLIST PRODUTOS \n" + produtos);
                System.out.println("ARRAYLIST PRODUTOS INCLUSOS\n" + produtosInclusos);

                System.out.println("ARRAYLIST VENDAS\n" + vendas);
                
                voltarMenu(in);

            }




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

    private static void rewindScanner(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }

    public static String getDataAtual() {

        String data;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  HH:mm");
        Date date = new Date(System.currentTimeMillis());
        data = formatter.format(date);
        return data;
    }

    public static boolean validarCodigo(ArrayList<Produto> produtos, String codigo) {

        boolean codigoValido = false;

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo().equals(codigo)) {
                codigoValido = false;
                break;

            } else {

                codigoValido = true;
            }
        }

        return codigoValido;

    }

    public static int getProdutoProcurado(ArrayList<Produto> produtos, String codigo) {

        boolean encontrado = false;

        int index = 0;

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo().equals(codigo)) {

                index = i;
                encontrado = true;

            }
        }

        if (encontrado == false) {
            index = -1;
            System.out.println("\nCODIGO NAO ENCONTRADO");
        }

        return index;

    }

    public static double getVendasTotais(ArrayList<Venda> vendas){
        double total = 0;

        if (vendas.size() > 0){

            for(Venda v : vendas){

                total = total +  v.getQuantidade()*v.getProduto().getValor();

            }
        } 

        return total;
    }

}       