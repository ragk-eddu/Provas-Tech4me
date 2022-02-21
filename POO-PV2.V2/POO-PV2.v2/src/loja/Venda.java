package loja;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Venda {

    private String data;
    private Produto produto;
    private int quantidade;

    public Venda() {

    }

    public Venda(Produto produto, int quantidade) {

        this.quantidade = quantidade;
        this.produto = produto;

        produto.setQuantidade(produto.getQuantidade() - quantidade);

        setDataVenda();
        this.data = getData();

    }

    public double getVendasTotais(ArrayList<Venda> vendas){
        double total = 0;

        if (vendas.size() > 0){

            for(Venda v : vendas){

                total = total +  v.getQuantidade()*v.getProduto().getValor();

            }
        } 

        return total;
    }

    public String getData() {
        return data;
    }

    public void setDataVenda() {

        String data;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy      HH:mm");
        Date date = new Date(System.currentTimeMillis());
        data = formatter.format(date);
        this.data = data;
    }

    public String getDataAtual() {

        String data;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:s");
        Date date = new Date(System.currentTimeMillis());
        data = formatter.format(date);
        return data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {

        return String.format("\n %20s %10s %10d %15.2f %15.2f", getData(), produto.getNome(), getQuantidade(),
                produto.getValor(), produto.getValor() * quantidade);
    }

}


