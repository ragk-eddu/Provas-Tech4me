package Classes.Pessoas;

import java.sql.Date;

import Classes.Residencia.Adress;

public class Gerente extends Funcionario {

    private Float salario;
    
    public Gerente(String nome, String cpf, Date nascimento, Adress adress){
        super(nome, cpf, nascimento, adress);
        
    }
    
    public Gerente(){
    }  public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }


    @Override
    public String toString() {
        return String.format("\n %21s %10s %10d %15.2f %15.2f", getSalario());
    }
    
    
}
