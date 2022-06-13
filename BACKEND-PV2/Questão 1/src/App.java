import java.util.List;
import java.util.Scanner;

import javax.naming.InvalidNameException;
import javax.xml.validation.Validator;

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

            System.out.println(processamento(valorEmprestimo, rendaMensal, qntdParcelas));

        } 
    }

    
    public static String processamento (Double valorEmprestimo, Double rendaMensal, int qntdParcelas){

        String resultado;

        if(valorEmprestimo<rendaMensal*10 && valorEmprestimo/qntdParcelas<=rendaMensal*0.3){
            resultado = "\nFinanciamento Aceito :)\n\n";

        } else { 
            resultado ="\nFinanciamento rejeitado :(\n\n";
        }

        return resultado;
    }
}           

