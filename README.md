Camada de Domínio - Sistema de Gestão de Biblioteca Universitária
Este diretório contém as classes principais da camada de domínio do sistema, implementadas em Java. Elas representam as entidades centrais do domínio, seus atributos, relacionamentos e métodos de negócio, conforme descrito no documento de arquitetura.
Classes e Suas Funções:

User.java:

Representa o usuário do sistema (aluno, professor ou bibliotecário).
Atributos: id, matricula, senha (criptografada), tipo.
Relacionamentos: Possui listas de empréstimos (loans) e reservas (reservations) - associações um-para-muitos.
Métodos de negócio: Adicionar/remover empréstimos e reservas (mantendo bidirecionalidade), listar empréstimos ativos.


Book.java:

Representa um livro no acervo da biblioteca.
Atributos: id, titulo, autor, isbn, categoria, ano, status.
Relacionamentos: Possui listas de empréstimos (loans) e reservas (reservations) - associações um-para-muitos.
Métodos de negócio: Adicionar/remover empréstimos e reservas (mantendo bidirecionalidade), verificar disponibilidade, atualizar status baseado em empréstimos.


Loan.java:

Representa um empréstimo de livro.
Atributos: id, dataEmprestimo, dataDevolucaoPrevista, dataDevolucaoReal, multa.
Relacionamentos: Pertence a um User e um Book - associações muitos-para-um.
Métodos de negócio: Calcular multa com base em dias de atraso (assumindo taxa de 1.0 por dia), verificar se está atrasado.


Reservation.java:

Representa uma reserva de livro.
Atributos: id, dataReserva, status.
Relacionamentos: Pertence a um User e um Book - associações muitos-para-um.
Métodos de negócio: Verificar se está ativa, cancelar reserva.


UserType.java, BookStatus.java, ReservationStatus.java:

Enums para tipos de usuário, status de livro e status de reserva, respectivamente.