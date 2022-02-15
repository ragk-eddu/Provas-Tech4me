package classes;

import java.util.ArrayList;

public class Piloto extends Pessoa {

    private String breve;
    private int matricula;
    private ArrayList<Aeronave> aeronaves;

    public Piloto() {
    }

    public Piloto(String breve, int matricula, String nome, String cpf, ArrayList<Aeronave> aeronaves) {
        super(nome, cpf);
        this.breve = breve;
        this.matricula = matricula;
        this.aeronaves = aeronaves;
    }

    public ArrayList<Aeronave> getAeronaves() {
        return aeronaves;
    }

    public Piloto(String breve, int matricula, String nome, String cpf) {
        super(nome, cpf);
        this.breve = breve;
        this.matricula = matricula;

    }

    public String getBreve() {
        return breve;
    }

    public void setBreve(String breve) {
        this.breve = breve;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

}
