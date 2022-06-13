package Classes.Pessoas;

import java.util.Date;
import Classes.Residencia.Adress;

public class Funcionario {

    private String nome, cpf;
    private Date nacimento;
    private Adress adress;
    private Date nascimento;

    public Funcionario(){
    }

    public Funcionario(String nome, String cpf, Date nascimento, Adress adress){

        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.adress = adress;

    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Date getNacimento() {
        return nacimento;
    }
    public void setNacimento(Date nacimento) {
        this.nacimento = nacimento;
    }
    public Adress getAdress() {
        return adress;
    }
    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    
}
