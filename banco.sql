
---





-- USUÁRIOS
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('ALUNO', 'PROFESSOR', 'BIBLIOTECARIO')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- LIVROS
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) UNIQUE,
    categoria VARCHAR(100),
    ano INTEGER,
    status VARCHAR(20) DEFAULT 'DISPONIVEL' CHECK (status IN ('DISPONIVEL', 'EMPRESTADO')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- EMPRÉSTIMOS
CREATE TABLE loans (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    book_id BIGINT REFERENCES books(id),
    data_emprestimo DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao_real DATE,
    multa DECIMAL(10,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- RESERVAS
CREATE TABLE reservations (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    book_id BIGINT REFERENCES books(id),
    data_reserva DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'ATIVA' CHECK (status IN ('ATIVA', 'CONCLUIDA', 'CANCELADA')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 🔥 DADOS DE TESTE
INSERT INTO users (matricula, senha, tipo) VALUES 
('2023001', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ALUNO'), -- 123456
('2023002', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'BIBLIOTECARIO'); -- 123456

INSERT INTO books (titulo, autor, isbn, categoria, ano, status) VALUES 
('Clean Code', 'Robert Martin', '9780132350884', 'Programação', 2008, 'DISPONIVEL'),
('Design Patterns', 'Gang of Four', '9780201633610', 'Arquitetura', 1994, 'DISPONIVEL'),
('Refatoração', 'Martin Fowler', '9788576082880', 'Programação', 2000, 'EMPRESTADO');