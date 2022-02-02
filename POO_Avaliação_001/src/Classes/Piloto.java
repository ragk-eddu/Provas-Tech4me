package Classes;

public class Piloto extends Pessoa {

    private String breve;
    private int matricula;

    public Piloto(String nome, String cpf, String breve, int matricula) {
        super(nome, cpf);
        this.breve = breve;
        this.matricula = matricula;
    }

    public Piloto() {
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
