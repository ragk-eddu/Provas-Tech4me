import java.util.Scanner;

import javax.naming.InvalidNameException;

public class App {
    public static void main(String[] args) throws Exception {

        try (Scanner in = new Scanner(System.in)) {
            Double valorEmprestimo, rendaMensal;
            int qntdParcelas;

            System.out.println("============================\nOperadora de financiamentos:\n============================\n\nInforme sua renda mensal:");
            rendaMensal = in.nextDouble();

            System.out.println("\nInforme o valor total do emprestimo desejado:");
            valorEmprestimo = in.nextDouble();

            System.out.println("\nInforme a quantidade de parcelas que deseja pagar:");
            qntdParcelas = in.nextInt();

            if(valorEmprestimo<rendaMensal*10 && valorEmprestimo/qntdParcelas<=rendaMensal*0.3){ // Yes
                System.out.println("\nFinanciamento Aceito :)\n\n");

            } else { System.out.println("\nFinanciamento rejeitado :("); }
        }

        
    }
}
