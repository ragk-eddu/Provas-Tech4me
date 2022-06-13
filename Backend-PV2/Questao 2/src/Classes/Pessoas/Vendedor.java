package Classes.Pessoas;

import java.sql.Date;
import Classes.Residencia.Adress;

public class Vendedor extends Funcionario implements EquipeVenda{

    private Float salario;


    public Vendedor(){
    }

    public Vendedor(String nome, String cpf, Date nascimento, Adress adress, Float salario){
        super(nome, cpf, nascimento, adress);
        this.salario = salario;
    }


    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }



    
}
