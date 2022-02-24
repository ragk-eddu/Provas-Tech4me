package loja;

import java.util.ArrayList;

public class Produto {

    private String codigo;
    private String nome;
    private double valor;
    private int quantidade;

    public Produto() {
    }

    public Produto(String codigo, String nome, double valor, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public ArrayList<Produto> conferirEstoque(ArrayList<Produto> produtos, ArrayList<Venda> vendas) {

        ArrayList<Produto> produtosAtualizado = new ArrayList<>();

        for (Venda v : vendas) {

            for (Produto p : produtos) {
                if (p.getCodigo() == v.getProduto().getCodigo()) {
                    produtosAtualizado.add(new Produto(p.getCodigo(), p.getNome(), p.getValor(),
                            p.getQuantidade() - v.getQuantidade()));
                } else {
                    produtosAtualizado.add(p);
                }
            }
        }

        return produtosAtualizado;

    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {

        return String.format("\n%10s %15s %15.2f %15d", getCodigo(), getNome(), getValor(), getQuantidade());
    }


    public int getProdutoProcurado(ArrayList<Produto> produtos, String codigo) {

        boolean encontrado = false;

        int index = 0;
        
        for (Produto p : produtos){
            if(p.getCodigo().equals(codigo)){

                index = produtos.indexOf(p);
                encontrado = true;

            }
        }


        if (encontrado == false) {
            index = -1;
            System.out.println("\nCODIGO NAO ENCONTRADO");
        }

        return index;

    }
}
