Como um piloto pode ter mais uma aeronave cadastrada fiz uma repetição para obter todas aeronaves referentes a um piloto, pensei em usar uma matriz pra aeronave em que o primeiro indice fosse o do piloto e o segundo fosse para as aeronaves dele. 

Mas não consegui implementar essa ideia muito bem. Tentei inicialmente fazer com que Aeronave fosse filha de Piloto, mas não consegui usar o typecast direto da lista de pilotos porque a classe dele era Pessoa. Então fiz com que a Aeronave fosse filha de Pessoa e o typecast funcionou. 

O problema apareceu quando fui tentar usar o get dos atributos de aeronave (Modelo e Categoria). Acredito que tenha algum problema no jeito que usei para inserir a aeronave dentro de pilotos, para isso tive que mudar o construtor de Pilotos para receber uma Aeronave,  fazer uma repeticao no cadastro e depois inserir a matriz de Aeronaves dentro do piloto e só assim coloca-lo dentro da lista de pilotos (Classe Pessoa).

Cadu, o que eu sugiro:
1 tira a relação de herança entre Piloto e Pessoa
2 colocar Piloto dentro de Aeronave
3 no cadastro de pilotos, pedir também dados da aeronave (*modelo, categoria e piloto*) - e no setPiloto informar como parametro o piloto recem criado).
4 na listagem vc vai buscar no vetor de aeronaves todos os pilotos (aeronave[i].getPilotos().getCpf() cujo CPF é igual ao do piloto que vc está imprimindo.