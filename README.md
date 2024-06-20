# projetoUno_MC322

Desenvolvimento de um UNO utilizando os conceitos de POO visto em aula
_________________________________________________________________________________________________________________________________

#Classe Baralho.

Descrição: Classe responsável pelo baralho usado na partida. Nele é criado uma lista de cartas chamada baralho

Método formar baralho → cria um baralho utilizando todas as cartas que devem ter no jogo: 
    2 cartas "NUMERO" de 1 a 9 de cada cor colorida = 72 cartas
    1 carta "NUMERO" 0 de cada cor colorida = 4 cartas
    2 cartas "INVERTE" de cada cor colorida = 8 cartas
    2 cartas "BLOQUEIO" de cada cor colorida = 8 cartas
    2 cartas "COMPRA_MAIS_2" de cada cor colorida = 8 cartas
    4 cartas "COMPRA_MAIS_4" pretas = 4 cartas
    4 cartas "ALTERACOR" = 4 cartas
    Total = 108 cartas    

Método comprar cartas → Método para comprar cartas quando o jogador precisar. Se o baralho estiver vazio, ele atualiza o baralho com uma pilha de descarte embaralhada 

Método reabastecer baralho → coloca todas as cartas do descarte no baralho novamente

Método embaralhar → Deixa o baralho com todas as cartas em ordem aleatória sempre que necessário

Métodos getters, setters e toString
_________________________________________________________________________________________________________________________________

#Classe Cartas.

Descrição: Classe responsável pelo modelo que todas as cartas devem ter, adicionando os métodos e funções de cada tipo de cartas

_________________________________________________________________________________________________________________________________

#Classe Mesa.


_________________________________________________________________________________________________________________________________

#Classe Jogador.

_________________________________________________________________________________________________________________________________

#Main

_________________________________________________________________________________________________________________________________

#Classe OperacaoCarta

_________________________________________________________________________________________________________________________________

#Classe OperacaoJogador

_________________________________________________________________________________________________________________________________

#ENUMs

TipoDeCarta

Cor