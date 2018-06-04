DROP DATABASE IF EXISTS TESTESISGER;
CREATE DATABASE TESTESISGER;
USE TESTESISGER;
/* CRIAÇÃO DAS TABELAS ESSENCIAIS
*/
DROP TABLE IF EXISTS PESSOA;
CREATE TABLE PESSOA(
	ID_PES INT NOT NULL AUTO_INCREMENT,
    NOME_PES VARCHAR(50),
    SOBRENOME_PES VARCHAR(100),
    EMAIL_PES VARCHAR(255),
    DATA_NASCIMENTO_PES DATE,
    ATIVO_PES BOOLEAN,
    CONSTRAINT PK_PESSOA PRIMARY KEY (ID_PES)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS FOTO_PERFIL;
CREATE TABLE FOTO_PERFIL(
	ID_PES INT NOT NULL,
    ID_FOT INT NOT NULL AUTO_INCREMENT,
    FOTO_FOT LONGBLOB,
    CONSTRAINT PK_FOTO_PERFIL PRIMARY KEY (ID_FOT),
    CONSTRAINT FK_FOTO_PERFIL_PESSOA FOREIGN KEY (ID_PES) REFERENCES PESSOA(ID_PES)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS TELEFONE;
CREATE TABLE TELEFONE(
	ID_PES INT NOT NULL,
    DDD_TEL CHAR(2),
    TELEFONE_TEL VARCHAR(10), 
    OPERADORA_TEL VARCHAR(20),
    ATIVO_TEAL BOOLEAN,
    CONSTRAINT PK_TELEFONE PRIMARY KEY (ID_PES, DDD_TEL, TELEFONE_TEL),
    CONSTRAINT FK_TELEFONE_PESSOA FOREIGN KEY (ID_PES) REFERENCES PESSOA(ID_PES)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS PROFESSOR;
CREATE TABLE PROFESSOR(
    CODIGO_PRO VARCHAR(6),
    ID_PES INT NOT NULL,
    CONSTRAINT PK_PROFESSOR PRIMARY KEY(CODIGO_PRO),
    CONSTRAINT FK_PROFESSOR_PESSOA FOREIGN KEY (ID_PES) REFERENCES PESSOA(ID_PES)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS CURSO;
CREATE TABLE CURSO(
	CODIGO_CUR VARCHAR(3) NOT NULL,
    NOME_CUR VARCHAR(50),
    CONSTRAINT PK_CURSO PRIMARY KEY (CODIGO_CUR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS ANOSEMESTRE;
CREATE TABLE ANOSEMESTRE(
	ANO_ANS INT NOT NULL,
    SEMESTRE_ANS INT(1) NOT NULL,
    DataINI_ANS Date,
    DataFIM_ANS Date,
    ATIVO_ANS TINYINT(1),
    CONSTRAINT PK_ANOSEMESTRE PRIMARY KEY (ANO_ANS, SEMESTRE_ANS)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP DATABASE IF EXISTS DISCIPLINA;
CREATE TABLE DISCIPLINA(
	CODIGO_DIS VARCHAR(5) NOT NULL,
    NOME_DIS VARCHAR(255),
    CONSTRAINT PK_MATERIA PRIMARY KEY (CODIGO_DIS)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS SALA;
CREATE TABLE SALA(
	ID_SAL INT NOT NULL AUTO_INCREMENT,
    NOME_SAL VARCHAR(50),
    CONSTRAINT PK_SALA PRIMARY KEY(ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;




DROP TABLE IF EXISTS GRUPO;
CREATE TABLE GRUPO(
	ID_GRP INT NOT NULL AUTO_INCREMENT,
    NOME_GRP VARCHAR(50),
    HIERARQUIA_GRP INT DEFAULT 0,
    ATIVO_GRP BOOLEAN,
    CONSTRAINT PK_GRUPO PRIMARY KEY (ID_GRP)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS USUARIO;
CREATE TABLE USUARIO(
    ID_GRP INT NOT NULL,
    LOGIN_USR VARCHAR(25) NOT NULL,
    SENHA_USR VARCHAR(255) NOT NULL,
    ID_PES INT NOT NULL,
    CONSTRAINT PK_USUARIO PRIMARY KEY(LOGIN_USR),
    CONSTRAINT FK_USUARIO_GRUPO FOREIGN KEY(ID_GRP) REFERENCES GRUPO(ID_GRP),
    CONSTRAINT FK_USUARIO_PESSOA FOREIGN KEY (ID_PES) REFERENCES PESSOA(ID_PES)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS LOGIN;
CREATE TABLE LOGIN(
	LOGIN_USR VARCHAR(25) NOT NULL,
    DATA_LOG DATETIME,
    ID_LOG INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT PK_LOGIN PRIMARY KEY (ID_LOG),
    CONSTRAINT FK_LOGIN_USUARIO FOREIGN KEY (LOGIN_USR) REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS COMPUTADOR;
CREATE TABLE COMPUTADOR(
	ID_SAL INT NOT NULL,
    NUMERO_PC INT NOT NULL,
    ATIVO_PC BOOLEAN DEFAULT TRUE,
    CONSTRAINT PK_COMPUTADOR PRIMARY KEY(NUMERO_PC, ID_SAL),
    CONSTRAINT FK_COMPUTADOR_SALA FOREIGN KEY(ID_SAL) REFERENCES SALA(ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS MONITOR;
CREATE TABLE MONITOR(
	ID_SAL INT NOT NULL,
    NUMERO_MON INT NOT NULL,
    CONSTRAINT PK_MONITOR PRIMARY KEY (NUMERO_MON, ID_SAL),
    CONSTRAINT FK_MONITOR_SALA FOREIGN KEY (ID_SAL) REFERENCES SALA(ID_SAL) 
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


/*
	MODELAGEM DE PENDÊNCIAS
*/

DROP TABLE IF EXISTS TURNO;
CREATE TABLE TURNO(
    ID_TUR INT NOT NULL AUTO_INCREMENT,
	PERIODO_TUR INT NOT NULL,
    HORA_INICIO_TUR TIME NOT NULL,
    HORA_FIM_TUR TIME NOT NULL,
    REGISTRO_TUR Date,
    ATIVO_TUR TINYINT(1),
    CONSTRAINT PK_TURNO PRIMARY KEY (ID_TUR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;	
DROP TABLE IF EXISTS FIM_TURNO;
CREATE TABLE FIM_TURNO(
	ID_TUR INT NOT NULL AUTO_INCREMENT,
    DATA_FTU DATE,
    CONSTRAINT PK_FIM_TURNO PRIMARY KEY (ID_TUR),
    CONSTRAINT FK_FIM_TURNO_TURNO FOREIGN KEY (ID_TUR) REFERENCES TURNO(ID_TUR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS USUARIO_TURNO;
CREATE TABLE USUARIO_TURNO(
	LOGIN_USR VARCHAR(25) NOT NULL,
    ID_TUR INT NOT NULL,
    CONSTRAINT PK_USUARIO_TURNO PRIMARY KEY(LOGIN_USR, ID_TUR),
    CONSTRAINT FK_USUARIO_TURNO_TURNO FOREIGN KEY (ID_TUR) REFERENCES TURNO(ID_TUR),
    CONSTRAINT FK_USUARIO_TURNO_USUARIO FOREIGN KEY(LOGIN_USR) REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS ORDEM_SERVICO;
CREATE TABLE ORDEM_SERVICO(
	ID_ORS INT NOT NULL AUTO_INCREMENT,
	EXECUTADA_ORS BOOLEAN,
    DESCRICAO_ORS VARCHAR(1000),
    ATIVO_ORS BOOLEAN,
    CONSTRAINT PK_ORDEM_SERVICO PRIMARY KEY (ID_ORS)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS ORDEM_SERVICO_COMPUTADOR;
CREATE TABLE ORDEM_SERVICO_COMPUTADOR(
	ID_ORS INT NOT NULL,
    ID_SAL INT NOT NULL,
    NUMERO_PC INT NOT NULL,
    CONSTRAINT PK_ORDEM_SERVICO_COMPUTADOR PRIMARY KEY (ID_ORS),
    CONSTRAINT FK_ORDEM_SERVICO_COMPUTADOR_ORDEM_SERVICO FOREIGN KEY (ID_ORS)
    REFERENCES ORDEM_SERVICO(ID_ORS),
    CONSTRAINT FK_ORDEM_SERVICO_COMPUTADOR_COMPUTADOR FOREIGN KEY (ID_SAL,
    NUMERO_PC) REFERENCES COMPUTADOR(ID_SAL, NUMERO_PC)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS ORDEM_SERVICO_SALA;
CREATE TABLE ORDEM_SERVICO_SALA(
	ID_ORS INT NOT NULL,
    ID_SAL INT NOT NULL,
    CONSTRAINT PK_ORDEM_SERVICO_SALA PRIMARY KEY (ID_ORS),
    CONSTRAINT FK_ORDEM_SERVICO_SALA_ORDEM_SERVICO FOREIGN KEY (ID_ORS)
    REFERENCES ORDEM_SERVICO(ID_ORS),
    CONSTRAINT FK_ORDEM_SERVICO_SALA_SALA FOREIGN KEY (ID_SAL) REFERENCES SALA(ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS SUB_ORDEM;
CREATE TABLE SUB_ORDEM(
	ID_SOR INT NOT NULL AUTO_INCREMENT,
    ID_ORS INT NOT NULL,
    DATA_GERADA_SOR DATETIME,
    DATA_PARA_SER_EXECUTADA_SOR DATETIME,
    DATA_EXECUTADA_SOR DATETIME,
    LOGIN_USR VARCHAR(255) NOT NULL,
    JUSTIFICATIVA_SOR VARCHAR(300),
    CONSTRAINT PK_SUB_ORDEM PRIMARY KEY(ID_SOR),
    CONSTRAINT FK_SUB_ORDEM_ORDEM FOREIGN KEY(ID_ORS) REFERENCES ORDEM_SERVICO(ID_ORS),
    CONSTRAINT FK_SUB_ORDEM_USUARIO FOREIGN KEY(LOGIN_USR) REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS SUB_ORDEM_USUARIO;
CREATE TABLE SUB_ORDEM_USUARIO(
	ID_SOR INT NOT NULL,
    LOGIN_USR VARCHAR(25),
    CONSTRAINT PK_SUB_ORDEM_USUARIO PRIMARY KEY(ID_SOR, LOGIN_USR),
    CONSTRAINT FK_SUB_ORDEM_USUARIO_ORDEM FOREIGN KEY (ID_SOR) REFERENCES SUB_ORDEM(ID_SOR),
    CONSTRAINT FK_SUB_ORDEM_USUARIO_USUARIO FOREIGN KEY (LOGIN_USR) REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS SUB_ORDEM_TURNO;
CREATE TABLE SUB_ORDEM_TURNO(
	ID_SOR INT NOT NULL,
    ID_TUR INT NOT NULL,
    CONSTRAINT PK_SUB_ORDEM_TURNO PRIMARY KEY(ID_SOR, ID_TUR),
    CONSTRAINT FK_SUB_ORDEM_TURNO_ORDEM FOREIGN KEY (ID_SOR) REFERENCES SUB_ORDEM(ID_SOR),
    CONSTRAINT FK_SUB_ORDEM_TURNO_TURNO FOREIGN KEY (ID_TUR) REFERENCES TURNO(ID_TUR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS SUBORDEM_VISUALIZADA;
CREATE TABLE SUBORDEM_VISUALIZADA(
	LOGIN_USR VARCHAR(25) NOT NULL,
    ID_SOR INT NOT NULL,
    CONSTRAINT PK_SUBORDEM_VISUALIZADA PRIMARY KEY (ID_SOR, LOGIN_USR),
    CONSTRAINT FK_SUBORDEM_VISUALIZADA_SUBORDEM FOREIGN KEY (ID_SOR) REFERENCES SUB_ORDEM(ID_SOR),
    CONSTRAINT FK_SUBORDEM_VISUALIZADA_USUARIO FOREIGN KEY(LOGIN_USR)
    REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS MURAL;
CREATE TABLE MURAL(
	ID_MUR INT NOT NULL AUTO_INCREMENT,
    DATA_REGISTRO_MUR DATETIME,
    DESCRICAO_MUR VARCHAR(255),
    TITULO_MUR VARCHAR(255),
    ID_TUR INT NOT NULL,
    ATIVO_MUR BOOLEAN DEFAULT TRUE,
    CONSTRAINT PK_MURAL PRIMARY KEY(ID_MUR),
    CONSTRAINT FK_MURAL_TURNO FOREIGN KEY(ID_TUR) REFERENCES TURNO(ID_TUR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS TURNO_MURAL;
CREATE TABLE TURNO_MURAL(
	ID_MUR INT NOT NULL,
    ID_TUR INT NOT NULL,
    ATIVO_TMU BOOLEAN DEFAULT TRUE,
    CONSTRAINT PK_TURNO_MURAL PRIMARY KEY (ID_MUR, ID_TUR),
    CONSTRAINT FK_TURNO_MURAL_TURNO FOREIGN KEY(ID_TUR) REFERENCES TURNO(ID_TUR),
    CONSTRAINT FK_TURNO_MURAL_MURAL FOREIGN KEY(ID_MUR) REFERENCES MURAL(ID_MUR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS MURAL_VISUALIZADO;
CREATE TABLE MURAL_VISUALIZADO(
	ID_MUR INT NOT NULL,
    LOGIN_USR VARCHAR(25) NOT NULL,
    APAGADO_MUV BOOLEAN,
    DATA_REGISTRO_MUV DATETIME,
    CONSTRAINT PK_RECADOS_VISUALIZADOS PRIMARY KEY RECADOS_VISUALIZADOS(ID_MUR, LOGIN_USR),
    CONSTRAINT FK_MURAL_VISUALIZADO_MURAL FOREIGN KEY(ID_MUR) REFERENCES MURAL(ID_MUR),
    CONSTRAINT FK_MURAL_VISUALIZADO_USUARIO FOREIGN KEY(LOGIN_USR)
    REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS RECADOS;
CREATE TABLE RECADOS(
	ID_REC INT NOT NULL AUTO_INCREMENT,
    DATA_REGISTRO_REC DATETIME,
    DESCRICAO_REC VARCHAR(255),
    LOGIN_USR VARCHAR(25) NOT NULL,
    TITULO_REC VARCHAR(255),
    ATIVO_REC BOOLEAN,
    CONSTRAINT PK_RECADOS PRIMARY KEY MURAL(ID_REC),
    CONSTRAINT FK_RECADOS_USUARIO FOREIGN KEY (LOGIN_USR) REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS RECADOS_USUARIO_ALVO;
CREATE TABLE RECADOS_USUARIO_ALVO(
	ID_REC INT NOT NULL,
    LOGIN_USR VARCHAR(25) NOT NULL,
    APAGADO_RUA BOOLEAN,
    VISUALIZADO_RUA BOOLEAN,
    CONSTRAINT PK_RECADOS_USUARIO_ALVO PRIMARY KEY(ID_REC, LOGIN_USR),
    CONSTRAINT FK_RECADOS_USUARIO_ALVO_RECADOS FOREIGN KEY(ID_REC) REFERENCES RECADOS(ID_REC),
    CONSTRAINT FK_RECADOS_USUARIO_ALVO_USUARIO FOREIGN KEY(LOGIN_USR) REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS ESCALAS;
CREATE TABLE ESCALAS(
	ID_ESC INT NOT NULL AUTO_INCREMENT,
    ENTRADA_ESC TIME NOT NULL,
    SAIDA_ESC TIME NOT NULL,
    DIA_INI DATE,
    DIA_FIM DATE,
    ATIVO_ESC BOOLEAN,
    CONSTRAINT PK_ESCALAS PRIMARY KEY(ID_ESC)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS USUARIO_ESCALA;
CREATE TABLE USUARIO_ESCALA(
	LOGIN_USR VARCHAR(25) NOT NULL,
    ID_ESC INT NOT NULL,
    CONSTRAINT PK_USUARIO_ESCALA PRIMARY KEY (LOGIN_USR, ID_ESC),
    CONSTRAINT FK_USUARIO_ESCALA_USUARIO FOREIGN KEY (LOGIN_USR) REFERENCES USUARIO(LOGIN_USR),
    CONSTRAINT FK_USUARIO_ESCALA_ESCALA FOREIGN KEY (ID_ESC) REFERENCES ESCALAS(ID_ESC)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS DEPARTAMENTO;
CREATE TABLE DEPARTAMENTO(
	ID_DEP INT NOT NULL AUTO_INCREMENT,
    NOME_DEP VARCHAR(100),
    CONSTRAINT PK_DEPARTAMENT PRIMARY KEY (ID_DEP)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS CHAMADO;
CREATE TABLE CHAMADO(
	NUMERO_CHM INTEGER NOT NULL,
    SERVICOS_CHM VARCHAR(300),
    DESCRICAO_CHM VARCHAR(300),
    DATA_GERADO_CHM DATETIME NOT NULL,
    DATA_CHAMADO_CHM DATETIME NOT NULL,
    LOGIN_USR VARCHAR(25) NOT NULL,
    CONSTRAINT PK_CHAMADOS PRIMARY KEY(NUMERO_CHM),
    CONSTRAINT FK_CHAMADOS_USUARIO FOREIGN KEY(LOGIN_USR) REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS CHAMADO_SERVICO;
CREATE TABLE CHAMADO_SERVICO(
	NUMERO_CHM INT NOT NULL,
    ID_DEP INT NOT NULL,
    CONSTRAINT PK_CHAMADO_SERVICO PRIMARY KEY (NUMERO_CHM, ID_DEP),
    CONSTRAINT FK_CHAMADO_SERVICO_DEPARTAMENTO FOREIGN KEY (ID_DEP)
    REFERENCES DEPARTAMENTO(ID_DEP),
    CONSTRAINT FK_CHAMADO_SERVICO_CHAMADO FOREIGN KEY (NUMERO_CHM) REFERENCES CHAMADO(NUMERO_CHM)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS CHAMADO_SALA;
CREATE TABLE CHAMADO_SALA(
	NUMERO_CHM INT NOT NULL,
    ID_SAL INT NOT NULL,
    CONSTRAINT PK_CHAMADO_SALA PRIMARY KEY (NUMERO_CHM, ID_SAL),
    CONSTRAINT FK_CHAMADO_SALA_CHAMADO FOREIGN KEY (NUMERO_CHM) REFERENCES CHAMADO(NUMERO_CHM),
    CONSTRAINT FK_CHAMADO_SALA_SALA FOREIGN KEY (ID_SAL) REFERENCES SALA(ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS CHAMADO_COMPUTADOR;
CREATE TABLE CHAMADO_COMPUTADOR(
	NUMERO_CHM INT NOT NULL,
    ID_SAL INT NOT NULL,
    NUMERO_PC INT NOT NULL,
    CONSTRAINT PK_CHAMADO_COMPUTADOR PRIMARY KEY (NUMERO_CHM, ID_SAL, NUMERO_PC),
    CONSTRAINT FK_CHAMADO_COMPUTADOR_CHAMADO FOREIGN KEY (NUMERO_CHM) 
    REFERENCES CHAMADO(NUMERO_CHM),
    CONSTRAINT FK_CHAMADO_COMPUTADOR_COMPUTADOR FOREIGN KEY (ID_SAL,
    NUMERO_PC) REFERENCES COMPUTADOR(ID_SAL, NUMERO_PC)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS CHAMADO_MONITOR;
CREATE TABLE CHAMADO_MONITOR(
	NUMERO_CHM INT NOT NULL,
    ID_SAL INT NOT NULL,
    NUMERO_MON INT NOT NULL,
    CONSTRAINT PK_CHAMADO_MONITOR PRIMARY KEY (NUMERO_CHM, ID_SAL, NUMERO_MON),
    CONSTRAINT FK_CHAMADO_MONITOR_CHAMADO FOREIGN KEY (NUMERO_CHM) 
    REFERENCES CHAMADO(NUMERO_CHM),
    CONSTRAINT FK_CHAMADO_MONITOR_MONITOR FOREIGN KEY (ID_SAL,
    NUMERO_MON) REFERENCES MONITOR(ID_SAL, NUMERO_MON)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

/*
	MODELAGEM DE GRADE DE HORÁRIOS
*/

DROP TABLE IF EXISTS RESERVA;
CREATE TABLE RESERVA(
	ID_RES INT NOT NULL AUTO_INCREMENT,
    CODIGO_DIS VARCHAR(8) NOT NULL,
    CODIGO_PRO VARCHAR(6) NOT NULL,
    LOGIN_USR VARCHAR(25) NOT NULL,
    ANO_ANS INT NOT NULL,
    SEMESTRE_ANS INT(1) NOT NULL,
    CODIGO_CUR VARCHAR(3),
    TURMA_RES CHAR(1),
    ATIVO_RES BOOLEAN,
    CONSTRAINT PK_RESERVAS PRIMARY KEY (ID_RES),
    CONSTRAINT FK_RESERVAS_COD_DISCIPLINA FOREIGN KEY(CODIGO_DIS)
    REFERENCES DISCIPLINA(CODIGO_DIS),
    CONSTRAINT FK_RESERVAS_COD_PROFESSOR FOREIGN KEY(CODIGO_PRO) 
    REFERENCES PROFESSOR(CODIGO_PRO),
    CONSTRAINT FK_RESERVAS_USUARIO FOREIGN KEY(LOGIN_USR) REFERENCES USUARIO(LOGIN_USR),
    CONSTRAINT FK_RESERVAS_ANOSEMESTRE FOREIGN KEY (ANO_ANS, SEMESTRE_ANS)
    REFERENCES ANOSEMESTRE(ANO_ANS, SEMESTRE_ANS),
    CONSTRAINT FK_RESERVA_CURSO FOREIGN KEY (CODIGO_CUR) REFERENCES CURSO(CODIGO_CUR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS SEMESTRAL;
CREATE TABLE SEMESTRAL(
	ID_RES INT NOT NULL,
    ID_SAL INT NOT NULL,
    ID_SEM INT NOT NULL AUTO_INCREMENT,
    DIA_SEMANA_SEM INT NOT NULL,
    HORA_INICIO_SEM TIME,
    HORA_TERMINO_SEM TIME,
    OBSERVACAO_SEM VARCHAR(255),
    ATIVO_SEM BOOLEAN,
    CONSTRAINT PK_SEMESTRAL PRIMARY KEY(ID_SEM),
    CONSTRAINT FK_SEMESTRAL_SALA FOREIGN KEY(ID_SAL) REFERENCES SALA(ID_SAL),
    CONSTRAINT FK_SEMESTRAL_RESERVA FOREIGN KEY(ID_RES) REFERENCES RESERVA(ID_RES)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS EXPORADICO;
CREATE TABLE EXPORADICO(
	ID_RES INT NOT NULL,
    ID_SAL INT NOT NULL,
    DATA_MARCADA_EXP DATE,
    HORA_INICIO_EXP TIME,
    HORA_FIM_EXP TIME,
    OBSERVACAO_EXP VARCHAR(255),
    ATIVO_EXP BOOLEAN,
    CONSTRAINT PK_EXPORADICO PRIMARY KEY(ID_RES, DATA_MARCADA_EXP, HORA_INICIO_EXP, HORA_FIM_EXP),
    CONSTRAINT FK_EXPORADICO_RESERVA FOREIGN KEY(ID_RES) REFERENCES RESERVA(ID_RES),
    CONSTRAINT FK_EXPORADICO_SALA FOREIGN KEY(ID_SAL) REFERENCES SALA(ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
/*
DROP TABLE IF EXISTS HISTORICO_SEMESTRAL;
CREATE TABLE HISTORICO_SEMESTRAL(
CODIGO_DIS VARCHAR(8) NOT NULL,
CODIGO_PRO VARCHAR(6) NOT NULL,
LOGIN_USR VARCHAR(25) NOT NULL,
ANO_ANS INT NOT NULL,
SEMESTRE_ANS INT(1) NOT NULL,
CODIGO_CUR VARCHAR(3),
TURMA_RES CHAR(1),
NOME_SAL VARCHAR(50),
ID_HSEM INT NOT NULL AUTO_INCREMENT,
DIA_SEMANA_SEM INT NOT NULL,
HORA_INICIO_SEM TIME,
HORA_TERMINO_SEM TIME,
OBSERVACAO_SEM VARCHAR(255),
ATIVO_SEM BOOLEAN
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;	
*/
/*
	CRIAR TABELA LIAPE CONTROL
*/

DROP TABLE IF EXISTS PERMISSAO;
CREATE TABLE PERMISSAO(
	ID_PER INT NOT NULL AUTO_INCREMENT,
    NOME_PER VARCHAR(50),
    CONSTRAINT PK_PERMISSAO PRIMARY KEY(ID_PER)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS GRUPO_PERMISSAO;
CREATE TABLE GRUPO_PERMISSAO(
	ID_PER INT NOT NULL,
    ID_GRP INT NOT NULL,
    CONSTRAINT PK_GRUPO_PERMISSAO PRIMARY KEY (ID_PER, ID_GRP),
    CONSTRAINT FK_GRUPO_PERMISSAO_PERMISSAO FOREIGN KEY(ID_PER) REFERENCES PERMISSAO(ID_PER),
    CONSTRAINT FK_GRUPO_PERMISSAO_GRUPO FOREIGN KEY (ID_GRP) REFERENCES GRUPO(ID_GRP)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS LICENCA_SOFTWARE_COMPUTADOR;
CREATE TABLE LICENCA_SOFTWARE_COMPUTADOR(
	ID_LSC INT NOT NULL AUTO_INCREMENT,
    LICENCA_LSC VARCHAR(255),
    ID_SAL INT NOT NULL,
    NUMERO_PC INT NOT NULL,
    ATIVO_LSC BOOLEAN,
    EXPIRA_LSC DATE,
    REGISTRO_LSC DATE,
    CONSTRAINT PK_LICENCA_SOFTWARE_COMPUTADOR PRIMARY KEY(ID_LSC),
    CONSTRAINT FK_LICENCA_SOFTWARE_COMPUTADOR FOREIGN KEY (NUMERO_PC, ID_SAL) REFERENCES
    COMPUTADOR(NUMERO_PC, ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS LICENCA_SOFTWARE_SALA;
CREATE TABLE LICENCA_SOFTWARE_SALA(
	ID_LSS INT NOT NULL AUTO_INCREMENT,
    LICENCA_LSS VARCHAR(255),
    ID_SAL INT NOT NULL,
    ATIVO_LSS BOOLEAN,
    EXPIRA_LSS DATE,
    REGISTRO_LSS DATE,
    CONSTRAINT PK_LICENCA_SOFTWARE_SALA PRIMARY KEY(ID_LSS),
    CONSTRAINT FK_LICENCA_SOFTWARE_SALA_SALA FOREIGN KEY (ID_SAL) REFERENCES
    SALA(ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS ALUNO;
CREATE TABLE ALUNO(
    CODIGO_ALU VARCHAR(6) NOT NULL,
    NOME_ALU VARCHAR(50),
    SOBRENOME_ALU VARCHAR(50),
    DATA_REGISTRO_ALU DATE,
    CODIGO_CUR VARCHAR(3),
    CONSTRAINT PK_ALUNO PRIMARY KEY ALUNO(CODIGO_ALU),
    CONSTRAINT FK_ALUNO_CURSO FOREIGN KEY (CODIGO_CUR) REFERENCES CURSO(CODIGO_CUR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

		
DROP TABLE IF EXISTS USUARIO_ATIVO_COMPUTADOR;
CREATE TABLE ALUNO_ATIVO_COMPUTADOR(
	ID_AAC INT NOT NULL AUTO_INCREMENT,
    CODIGO_ALU VARCHAR(6) NOT NULL,
    DATA_LOGON_AAC DATETIME,
    NUMERO_PC INT NOT NULL,
    ID_SAL INT NOT NULL,
    CONSTRAINT PK_ALUNO_ATIVO_COMPUTADOR PRIMARY KEY(ID_AAC),
    CONSTRAINT FK_ALUNO_ATIVO_COMPUTADOR_ALUNO FOREIGN KEY (CODIGO_ALU)
    REFERENCES ALUNO(CODIGO_ALU),
    CONSTRAINT FK_ALUNO_ATIVO_COMPUTADOR_COMPUTADOR FOREIGN KEY (NUMERO_PC, ID_SAL)
    REFERENCES COMPUTADOR(NUMERO_PC, ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS LOG_ACAO;
CREATE TABLE LOG_ACAO(
	ID_LOA INT NOT NULL AUTO_INCREMENT,
    DESCRICAO_LOA VARCHAR(255),
    CONSTRAINT PK_LOG_ACAO PRIMARY KEY(ID_LOA)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS BLACK_LIST_SERVICOS;
CREATE TABLE BLACK_LIST_SERVICOS(
	NOME_BLS VARCHAR(50) NOT NULL,
    DATA_REGISTRO_BLS DATETIME NOT NULL,
    LOGIN_USR VARCHAR(25) NOT NULL,
    CONSTRAINT PK_BLACK_LIST_SERVICOS PRIMARY KEY (NOME_BLS),
    CONSTRAINT FK_BLACK_LIST_SERVICOS_USUARIO FOREIGN KEY (LOGIN_USR)
    REFERENCES USUARIO(LOGIN_USR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS ATIVADADES_ALUNO;
CREATE TABLE ATIVIDADES_ALUNO(
	ID_ATA INT NOT NULL AUTO_INCREMENT,
    HORA_EXERCICIO_ATA DATETIME, 
    NOME_ATA VARCHAR(50),
    CODIGO_ALU VARCHAR(6) NOT NULL,
    CONSTRAINT PK_ATIVIDADES_ALUNO PRIMARY KEY (ID_ATA),
    CONSTRAINT FK_ATIVIDADES_ALUNO_ALUNO FOREIGN KEY (CODIGO_ALU) REFERENCES ALUNO(CODIGO_ALU)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS AGENDAMENTO_DADOS_SALA;
CREATE TABLE AGENDAMENTO_DADOS_SALA(
	ID_ADS INT NOT NULL AUTO_INCREMENT,
    HORARIO_ADS DATETIME NOT NULL,
    CANCELADO_ADS BOOLEAN NOT NULL,
    ID_SAL INT NOT NULL,
    CONSTRAINT PK_AGENDAMENTO_DADOS_SALA PRIMARY KEY (ID_ADS),
    CONSTRAINT FK_AGENDAMENTO_DADOS_SALA_SALA FOREIGN KEY (ID_SAL) REFERENCES SALA(ID_SAL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS AGENDAMENTO_DADOS_COMPUTADOR;
CREATE TABLE AGENDAMENTO_DADOS_COMPUTADOR(
	ID_ADS INT NOT NULL,
    ID_SAL INT NOT NULL,
    NUMERO_PC INT NOT NULL,
    CONSTRAINT PK_AGENDAMENTO_DADOS_COMPUTADOR PRIMARY KEY (ID_ADS, ID_SAL, NUMERO_PC),
    CONSTRAINT FK_AGENDAMENTO_DADOS_COMPUTADOR_AGENDAMENTO_DADOS_SALA FOREIGN KEY 
    (ID_ADS) REFERENCES AGENDAMENTO_DADOS_SALA(ID_ADS),
    CONSTRAINT FK_AGENDAMENTO_DADOS_COMPUTADOR_COMPUTADOR FOREIGN KEY (ID_SAL,
    NUMERO_PC) REFERENCES COMPUTADOR (ID_SAL, NUMERO_PC)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


DROP TABLE IF EXISTS AULAS_LIAPE;
CREATE TABLE AULAS_LIAPE(
	ID_AUL INT NOT NULL AUTO_INCREMENT,
    SALA_AUL VARCHAR(255),
    DESCRICAO_AUL VARCHAR(255),
    HORA_INICIO_AUL TIME,
    HORA_FIM_AUL TIME,
    ATIVO_AUL BOOLEAN,
    STATUS_AUL INT DEFAULT 0,
    DATA_AUL DATE,
    CONSTRAINT PK_AULAS_LIAPE PRIMARY KEY (ID_AUL)
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;