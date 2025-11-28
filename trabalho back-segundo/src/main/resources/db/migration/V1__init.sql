CREATE TABLE professor (
  id SERIAL PRIMARY KEY,
  matricula VARCHAR(100) NOT NULL UNIQUE,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE aluno (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  cpf VARCHAR(20) NOT NULL UNIQUE,
  ra VARCHAR(50) NOT NULL UNIQUE,
  ano_ingresso INTEGER,
  periodo_atual INTEGER
);

CREATE TABLE disciplina (
  id SERIAL PRIMARY KEY,
  professor_id INTEGER REFERENCES professor(id),
  codigo VARCHAR(100) NOT NULL UNIQUE,
  descricao VARCHAR(255) NOT NULL,
  ementa TEXT
);

CREATE TABLE alunos_disciplina (
  id SERIAL PRIMARY KEY,
  aluno_id INTEGER REFERENCES aluno(id),
  disciplina_id INTEGER REFERENCES disciplina(id),
  nota1_bim DOUBLE PRECISION,
  nota2_bim DOUBLE PRECISION,
  faltas1_bim INTEGER,
  faltas2_bim INTEGER,
  matriculado BOOLEAN,
  situacao VARCHAR(20)
);

CREATE TABLE aulas_dadas (
  id SERIAL PRIMARY KEY,
  disciplina_id INTEGER REFERENCES disciplina(id),
  data DATE,
  observacoes TEXT
);

CREATE TABLE aulas_dadas_presencas (
  id SERIAL PRIMARY KEY,
  aula_dada_id INTEGER REFERENCES aulas_dadas(id),
  aluno_id INTEGER REFERENCES aluno(id),
  falta BOOLEAN
);
