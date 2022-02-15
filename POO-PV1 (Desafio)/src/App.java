import java.util.ArrayList;

import classes.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("_____________________________\n");

        ArrayList<Pessoa> pessoas = new ArrayList<>();

        // ADD PESSOAS
        for (int i = 0; i < 10; i++) {
            Pessoa pessoa = new Pessoa("Cadu " + i, " 123");
            pessoas.add(pessoa);
        }

        // ADD PILOTOS
        for (int i = 0; i < 10; i++) {
            ArrayList<Aeronave> aeronaves = new ArrayList<>();

            if (i < 5) {
                for (int j = 0; j < (i + 2); j++) {
                    Aeronave aerove = new Aeronave("BBB", "CCC");
                    aeronaves.add(aerove);
                }
            }
            Piloto piloto = new Piloto(" AAA ", 123, "CaduPiloto " + i, " 123 ", aeronaves);
            pessoas.add(piloto);
        }

        // PRINT
        for (Pessoa c : pessoas) {
            if (c.getClass() == Piloto.class) {
                System.out.println(c.getNome() + ((Piloto) c).getBreve());
                int qntdAeronaves = ((Piloto) c).getAeronaves().size();
                for (int i = 0; i < qntdAeronaves; i++) {
                    System.out.println("Aeronave " + (i + 1) + " " + ((Piloto) c).getAeronaves().get(i).getModelo() +
                            " " + ((Piloto) c).getAeronaves().get(i).getCategoria());
                }
            } else {
                System.out.println(c.getNome() + c.getCpf());
            }

            System.out.println("\n");
        }

        // System.out.println(cadus.get(0).getNome());

        System.out.println("______________________________");
    }
}
