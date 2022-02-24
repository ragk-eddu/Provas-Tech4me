import loja.*;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Venda> vendas = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"
        );
        
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

                boolean encontrado = true;

                System.out.println("\nCODIGO:");
                codigo = in.nextLine();




                if (produtos.size() > 0) {

                    do {

                        encontrado = encontrarCodigo(produtos, codigo);

                        if(encontrado==true){

                            codigoInvalido("\nCODIGO INVALIDO! TENTE NOVAMENTE!\n=================================", produtos);
                                    
                            System.out.println("\n\nCODIGO:");
                            codigo = in.nextLine();

                        }
                       

                    } while ((encontrado == true));

                }

                if (!encontrarCodigo(produtos, codigo)) {

                    System.out.println("\nNOME:");
                    nome = in.nextLine();

                    System.out.println("\nVALOR:");
                    valor = in.nextDouble();

                    System.out.println("\nQUANTIDADE:");
                    quantidade = in.nextInt();

                    System.out.println("\nPRODUTO CADASTRADO COM SUCESSO!");
                    in.nextLine();

                    Produto produto = new Produto(codigo, nome, valor, quantidade);

                    produtos.add(produto);

                } 

                voltarMenu(in);

            } else if (opcao == 2) {
                
                // COMPLETADO DE FORMA MAIS SIMPLES
                // TESTES REALIZADOS : FILTER

                if(produtos.size()>0){    String codigo = "~12efs";

                    System.out.println("\nINSIRA CODIGO DO PRODUTO:");
                    codigo = in.nextLine();

                    
                    
                    if((encontrarCodigo(produtos, codigo))){
                        
                        System.out.printf(
                                "\n************************************************************\n%40s\n************************************************************\n",
                                " CONSULTA DE PRODUTO ");
        
                        Produto produtoProcurado = new Produto();

                        produtoProcurado = getProdutoProcurado(produtos, codigo);

                        System.out.println("\n------------------------------------------------------------");

                        System.out.printf("%10s %15s %15s %15s", "CODIGO", "NOME", "VALOR", "QUANTIDADE");
                        System.out.println("\n------------------------------------------------------------");

                        System.out.println(produtoProcurado.toString());

                        System.out.println("\n------------------------------------------------------------");

                    }else {

                        codigoInvalido("\nPRODUTO NAO ENCONTRADO!\n=======================", produtos);

                    }
                } else {

                    System.out.println("\nNENHUM PRODUTO CADASTRADO!");

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

                ArrayList<Venda> vendasPeriodo = new ArrayList<>();
                String dataFinal ="";
                String dataInicial ="";
                LocalDate dataI = LocalDate.now();
                LocalDate dataF = LocalDate.now();
                
                if(vendas.size()>0){
                    
                    System.out.println("\nINSIRA [1] ou [2]:\n\n[1] TODAS AS VENDAS\n[2] SELECIONAR PERIODO\n[3] BUSCA DATA UNICA\n");
                    int opcao1 = in.nextInt();
                    in.nextLine();

                    switch(opcao1){

                        case 1:

                            System.out.println(
                                    "\nRELATORIO DE VENDAS"); // | PERIODO:\t\tINICIALIZACAO:   " + dataInicioPrograma + " \n\t\t\t\t        FINALIZACAO:     " + getDataAtual());

                            System.out.printf(
                                    "\n*****************************************************************************\n%48s\n*****************************************************************************",
                                    "VENDAS DE PRODUTOS");

                            System.out.println(
                                    "\n-----------------------------------------------------------------------------");

                            System.out.printf("%5s %7s %7s %10s %10s %16s %15s", "DATA", "  ","   ", "NOME", "QNTD", "VALOR UNICO",
                                    "VALOR TOTAL");
                            System.out.println(
                                    "\n-----------------------------------------------------------------------------");

                            // System.out.println("SEM FILTRO");
                            // vendas.stream()
                            //         .forEach(System.out::println);

                            // System.out.println("COM FILTRO");
                            // vendas.stream()
                            // .sorted(((Comparator.comparing(Venda::getData))))
                            // .forEach(System.out::println);System.out.println("COM FILTRO");
                            
                            Stream<Venda> filtroV = vendas.stream()
                            .sorted(((Comparator.comparing(Venda::getData))));
                            filtroV.forEach(System.out::println);
                            
                            System.out.println(
                                    "\n-----------------------------------------------------------------------------");

                            System.out.printf("%5s %69.2f"," TOTAL", getVendasTotais(vendas));
                            

                            System.out.println(
                                    "\n-----------------------------------------------------------------------------");

                            System.out.printf("%5s %63.2f", " VALOR MEDIO", getVendasTotais(vendas)/vendas.size());

                            System.out.println(
                                    "\n-----------------------------------------------------------------------------");


                        break;

                        case 2:

                            if(vendas.size()>0){
                                
                                System.out.println("\n=============INFORME O PERIODO==============\n\nFORMATO [yyyy-MM-dd]\nEXEMPLO 2022-01-01\n\nDATA INICIAL:");
                                dataInicial = in.nextLine();
                                
                                
                                
                                if(!dataValida(dataInicial)){
                                    System.out.println("DATA INVALIDA!\nTENTE NOVAMENTE");
                                    
                                }  else { 
                                        
                                    System.out.println("\nDATA FINAL:");
                                    dataFinal = in.nextLine();
                                    
                                    if(!dataValida(dataFinal)){
                                        System.out.println("DATA INVALIDA!\nTENTE NOVAMENTE");
                                    }
                                    
                                }} else {
                                    
                                    System.out.println("\nNENHUMA VENDA REALIZADA!");
                                }
                                
                                
                                if(dataValida(dataInicial) && dataValida(dataFinal)){   
                                    
                                        dataI = LocalDate.parse(dataInicial);
                                        dataF = LocalDate.parse(dataFinal);
                                        
                                        vendasPeriodo = vendasPeriodo(dataInicial, dataFinal, vendas);

                                        if(vendasPeriodo.size() > 0){
                                    

                                        System.out.println(
                                                "\nRELATORIO DE VENDAS"); // | PERIODO:\t\tINICIALIZACAO:   " + dataInicioPrograma + " \n\t\t\t\t        FINALIZACAO:     " + getDataAtual());

                                        System.out.printf(
                                                "\n*****************************************************************************\n%48s\n*****************************************************************************",
                                                "VENDAS DE PRODUTOS");

                                        System.out.println(
                                                "\n-----------------------------------------------------------------------------");

                                        System.out.printf("%5s %16s %10s %10s %16s %15s", "DATA", "", "NOME", "QNTD", "VALOR UNICO",
                                                "VALOR TOTAL");
                                        System.out.println(
                                                "\n-----------------------------------------------------------------------------");

                                        vendasPeriodo.stream()
                                                .forEach(System.out::println);

                                        System.out.println(
                                                "\n-----------------------------------------------------------------------------");

                                        System.out.printf("%5s %69.2f"," TOTAL", getVendasTotais(vendasPeriodo));
                                        

                                        System.out.println(
                                                "\n-----------------------------------------------------------------------------");

                                        System.out.printf("%5s %63.2f", " VALOR MEDIO", getVendasTotais(vendasPeriodo)/vendasPeriodo.size());

                                        System.out.println(
                                                "\n-----------------------------------------------------------------------------");
                                        
                                        } else {
                                            System.out.println("\nNENHUMA VENDA ENCONTRADA PARA O PERIODO!");
                                        }
                                    }

                        break;

                        case 3: 

                            // VENDAS DIA

                            if(vendas.size()>0){

                                System.out.println(
                                    "\n=========================INFORME O DIA A SER BUSCADO=========================\n\nFORMATO [yyyy-MM-dd]\nEXEMPLO 2022-01-01\n\nDIA:");
                                String dia = in.nextLine();
                                LocalDate data = LocalDate.parse(dia);

                                if(dataValida(dia)){

                                    Map<LocalDate, List<Venda>> vendasPorData;

                                    vendasPorData = vendas.stream()
                                    .filter(v -> v.getData().equals(data))
                                    .collect(Collectors.groupingBy(Venda::getData));

                                    List<Venda> vendasDia = vendas.stream()
                                    .filter(v -> v.getData().equals(data))
                                    .collect(Collectors.toList());

                                    System.out.println(
                                    "\n-----------------------------------------------------------------------------");

                                    System.out.printf("%6s %7s %7s %10s %10s %16s %15s", "DATA", "  ","   ", "NOME", "QNTD", "VALOR UNICO",
                                        "VALOR TOTAL");
                                    System.out.println(
                                        "\n-----------------------------------------------------------------------------");
                                        
                                    vendasPorData.entrySet()
                                    .forEach(v -> System.out.printf("%s\n", v.getValue()));

                                    System.out.println(
                                        "\n-----------------------------------------------------------------------------");

                                    System.out.printf("%5s %69.2f"," TOTAL", getVendasTotais(vendasDia));
                                

                                    System.out.println(
                                            "\n-----------------------------------------------------------------------------");

                                    System.out.printf("%5s %63.2f", " VALOR MEDIO", getVendasTotais(vendasDia)/vendasDia.size());

                                    System.out.println(
                                            "\n-----------------------------------------------------------------------------");

                                }
                                

                            } else { 

                                System.out.println("NENHUMA VENDA REALIZADA");
                            }

                        break;

                    }

                } else {

                    System.out.println("\nNENHUMA VENDA REALIZADA");
                }

                voltarMenu(in);

            } else if (opcao == 5) {

                Produto produtoVendido = new Produto();
                String codigo = "";
                int quantidade;
                String dataVenda;
                // LocalDate date;

                if (produtos.size() > 0){    
                    
                    System.out.printf(
                            "\n************************************************************\n%40s\n************************************************************\n",
                            "   REALIZAR VENDA   ");

                    System.out.println("\nCODIGO:");
                    codigo = in.nextLine();

                    // do{

                    // } while (validarCodigo( produtos, codigo) == false);

                    if(!encontrarCodigo(produtos, codigo)) { 
                        System.out.println("\nCODIGO NAO ENCONTRADO!");
                    } else {
                            produtoVendido = getProdutoProcurado(produtos, codigo);

                            System.out.println("\nQUANTIDADE:");
                            quantidade = in.nextInt();
                            in.nextLine();

                            if (quantidade <= produtoVendido.getQuantidade()) {

                                System.out.println("\nFORMATO [yyyy-MM-dd]\nDATA:");
                                dataVenda = in.nextLine();
                                // date = LocalDate.parse(dataVenda, formatter);

                                if(!dataValida(dataVenda)){
                                    System.out.println("DATA INVALIDA!\nTENTE NOVAMENTE");
                
                                }  else { 

                                    Venda venda = new Venda(produtoVendido, quantidade, LocalDate.parse(dataVenda));
                                    vendas.add(venda);
                                    System.out.println(vendas);

                                    }

                                } else {

                                    System.out.println("\nNAO HA SUFICIENTE DO PRODUTO EM ESTOQUE\n\nESTOQUE ATUAL:\n");
                                    System.out.println("\n------------------------------------------------------------");

                                    System.out.printf("%10s %15s %15s %15s", "CODIGO", "NOME", "VALOR", "QUANTIDADE");
                                    System.out.println("\n------------------------------------------------------------");

                                    System.out.println(produtoVendido);

                                    System.out.println("\n------------------------------------------------------------");
                                    in.nextLine();
                            }
                        }
                    } else { 
                        System.out.println("\nNENHUM PRODUTO CADASTRADO");
                    }
 

                voltarMenu(in);

            }

            
            else if (opcao == 6) {

                String codigo;
                int quantidade;

                System.out.printf(
                        "\n************************************************************\n%43s\n************************************************************\n",
                        "   ABASTECIMENTO DE ESTOQUE   ");

                        if (produtos.size() == 0) {
                            System.out.println("\nNENHUM PRODUTO CADASTRADO!");
                        } else {
        
                            System.out.printf(
                                    "\nPRODUTOS EM ESTOQUE:");
        
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

                            System.out.println("\nCODIGO:");
                            codigo = in.nextLine();

                            if(encontrarCodigo(produtos, codigo)){    
                    
                            int index = produtos.indexOf(getProdutoProcurado(produtos, codigo));

                            if(index != -1){System.out.println("\nQUANTIDADE:");

                            quantidade = in.nextInt();
                            in.nextLine();

                            produtos.get(index).setQuantidade(quantidade + produtos.get(index).getQuantidade());

                            System.out.println("\nPRODUTO ABASTECIDO!");
                        
                                }
                            }
    
                        }
                
                voltarMenu(in);
            }
            

            // // TESTES
            // else if (opcao == 21) {

            //     Produto p = new Produto("1", "1", 100, 123);
            //     Produto p1 = new Produto("1", "1", 200, 321);

            //     vendas.add(new Venda(p, 14, LocalDate.parse("2001-01-01")));
            //     vendas.add(new Venda(p, 12, LocalDate.parse("2001-01-01")));
            //     vendas.add(new Venda(p1, 4, LocalDate.parse("2001-01-01")));
                 
            //     voltarMenu(in);

            // }

        } while (opcao != 0);

        System.out.println("\nFim do programa!");

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

    public static ArrayList<Venda> vendasPeriodo (String dataInicial, String dataFinal, ArrayList<Venda> vendas){
        
        ArrayList<Venda> vendasPeriodo = new ArrayList<>();

        LocalDate dataI = LocalDate.parse(dataInicial);
        LocalDate dataF = LocalDate.parse(dataFinal);

        int indexInicial = 0;
        int indexFinal = vendas.size();


        // Ordenando 
        List<Venda> vendasCollected = vendas.stream()
                            .sorted(((Comparator.comparing(Venda::getData))))
                            .collect(Collectors.toList());

                
        for (Venda v : vendasCollected) {

            // System.out.println("\ndataI " + dataI + v.getData().isAfter(dataI) );
            if(v.getData().isAfter(dataI)){

                indexInicial = vendas.indexOf(v);
                break;

            } 
        }

        for (Venda v : vendasCollected) {

            // System.out.println("\ndataF " + dataF + v.getData().isAfter(dataF));
            if(v.getData().isAfter(dataF)){

                indexFinal = vendas.indexOf(v);
                break;
                
            } 
            
        }

        for(int j = indexInicial; j < indexFinal; j++){
            vendasPeriodo.add(vendas.get(j));
        }
        

        return vendasPeriodo;
    }  


    public static String getDataAtual() {

        String data;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        data = formatter.format(date);
        return data;

    }

    public static boolean encontrarCodigo(ArrayList<Produto> produtos, String codigo) {

        boolean encontrado = false;

        long qntdCodigosIguais = produtos.stream()
        .filter(p -> p.getCodigo().equals(codigo)).count();

        if(qntdCodigosIguais > 0){

            encontrado = true;

        } else {

            encontrado = false;

        }

        return encontrado;

    }

    public static Produto getProdutoProcurado(ArrayList<Produto> produtos, String codigo) {

        
        Produto produtoEncontrado = produtos.stream()
        .filter(p -> p.getCodigo().equals(codigo))
        .findAny().orElse(null);

        if(produtoEncontrado==null){
            System.out.println("\nCODIGO NAO ENCONTRADO");
        }

        return produtoEncontrado;

    }

    public static double getVendasTotais(List<Venda> vendas){
        double total = 0;

        if (vendas.size() > 0){

            for(Venda v : vendas){

                total = total +  v.getQuantidade()*v.getProduto().getValor();

            }
        } 

        return total;
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

    public static boolean dataValida(String data) {
        boolean valida = false;

        if (data.matches("\\d{4}-\\d{2}-\\d{2}")){
            valida = true;
        } 

        return valida;
     }

     public static void codigoInvalido(String string, ArrayList<Produto> produtos){

        System.out.println("\n" + string + "\n\nPRODUTOS ATUAIS:");
        System.out.println("\n------------------------------------------------------------");

        System.out.printf("%10s %15s %15s %15s", "CODIGO", "NOME", "VALOR", "QUANTIDADE");
        System.out.println("\n------------------------------------------------------------");

        for (Produto p : produtos) {

            System.out.println(p.toString());
            
        }

        System.out.println("\n------------------------------------------------------------");
         
     }
}       