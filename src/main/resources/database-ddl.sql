--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.7
-- Dumped by pg_dump version 9.6.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: consulta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.consulta (
    co_id bigint NOT NULL,
    co_data_consulta date,
    co_dt_hr_marcacao timestamp without time zone,
    ho_id bigint,
    md_id bigint,
    pa_id bigint
);


ALTER TABLE public.consulta OWNER TO postgres;

--
-- Name: consulta_co_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.consulta_co_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.consulta_co_id_seq OWNER TO postgres;

--
-- Name: consulta_co_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.consulta_co_id_seq OWNED BY public.consulta.co_id;


--
-- Name: exame; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exame (
    ex_id bigint NOT NULL,
    ex_nome character varying(255)
);


ALTER TABLE public.exame OWNER TO postgres;

--
-- Name: exame_ex_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.exame_ex_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exame_ex_id_seq OWNER TO postgres;

--
-- Name: exame_ex_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.exame_ex_id_seq OWNED BY public.exame.ex_id;


--
-- Name: horario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.horario (
    ho_id bigint NOT NULL,
    ho_hora_consulta time without time zone
);


ALTER TABLE public.horario OWNER TO postgres;

--
-- Name: medicamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medicamento (
    me_id bigint NOT NULL,
    me_fabricante character varying(255),
    me_nome_fabrica character varying(255),
    me_nome_generico character varying(255)
);


ALTER TABLE public.medicamento OWNER TO postgres;

--
-- Name: medico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medico (
    md_id bigint NOT NULL,
    md_crm character varying(255),
    md_especialidade character varying(255),
    md_nome character varying(255),
    us_id bigint
);


ALTER TABLE public.medico OWNER TO postgres;

--
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paciente (
    pa_id bigint NOT NULL,
    pa_cpf character varying(255),
    pa_dt_hr_cadastro timestamp without time zone,
    pa_nome character varying(255),
    pa_sexo character varying(255)
);


ALTER TABLE public.paciente OWNER TO postgres;

--
-- Name: prescricao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prescricao (
    pr_id bigint NOT NULL,
    pr_dt_hr_entregue timestamp without time zone,
    pr_entregue boolean,
    me_id bigint,
    pa_id bigint
);


ALTER TABLE public.prescricao OWNER TO postgres;

--
-- Name: solicitacao_exame; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.solicitacao_exame (
    se_id bigint NOT NULL,
    se_arquivo_mimetype character varying(255),
    se_dt_hr_cadastro timestamp without time zone,
    se_dt_hr_entregue timestamp without time zone,
    se_nome_arq_resultado character varying(255),
    se_resultado bytea,
    ex_id bigint,
    md_id bigint,
    pa_id bigint
);


ALTER TABLE public.solicitacao_exame OWNER TO postgres;

--
-- Name: historico_paciente; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.historico_paciente AS
 SELECT row_number() OVER () AS id,
    historico.pa_id,
    historico.dt_hr,
    historico.tipo,
    historico."desc"
   FROM ( SELECT paciente.pa_id,
            to_char(paciente.pa_dt_hr_cadastro, 'DD/MM/YYYY HH24:MI:SS'::text) AS dt_hr,
            'PACIENTE'::text AS tipo,
            concat('Paciente ', paciente.pa_nome, ' foi cadastrado(a).') AS "desc"
           FROM public.paciente
        UNION
         SELECT co.pa_id,
            to_char(co.co_dt_hr_marcacao, 'DD/MM/YYYY HH24:MI:SS'::text) AS dt_hr,
            'CONSULTA_MARCACAO'::text AS tipo,
            concat('Consulta marcada para ', to_char((co.co_data_consulta + ho.ho_hora_consulta), 'DD/MM/YYYY às HH24:MI'::text), ' com ', md.md_nome, '.') AS "desc"
           FROM ((public.consulta co
             JOIN public.horario ho ON ((co.ho_id = ho.ho_id)))
             JOIN public.medico md ON ((co.md_id = md.md_id)))
        UNION
         SELECT co.pa_id,
            to_char((co.co_data_consulta + ho.ho_hora_consulta), 'DD/MM/YYYY HH24:MI:SS'::text) AS dt_hr,
            'CONSULTA_REALIZADA'::text AS tipo,
            concat('Consulta realizada com ', md.md_nome, '.') AS "desc"
           FROM ((public.consulta co
             JOIN public.horario ho ON ((co.ho_id = ho.ho_id)))
             JOIN public.medico md ON ((co.md_id = md.md_id)))
          WHERE (now() > (co.co_data_consulta + ho.ho_hora_consulta))
        UNION
         SELECT se.pa_id,
            to_char(se.se_dt_hr_cadastro, 'DD/MM/YYYY HH24:MI:SS'::text) AS dt_hr,
            'SOLIC_EXAME_CADASTRO'::text AS tipo,
            concat('Exame ', ex.ex_nome, ' solicitado por ', md.md_nome, '.') AS "desc"
           FROM ((public.solicitacao_exame se
             JOIN public.exame ex ON ((se.ex_id = ex.ex_id)))
             JOIN public.medico md ON ((se.md_id = md.md_id)))
        UNION
         SELECT se.pa_id,
            to_char(se.se_dt_hr_entregue, 'DD/MM/YYYY HH24:MI:SS'::text) AS dt_hr,
            'SOLIC_EXAME_ENTREGUE'::text AS tipo,
            concat('Resultado do exame ', ex.ex_nome, ' entregue.') AS "desc"
           FROM (public.solicitacao_exame se
             JOIN public.exame ex ON ((se.ex_id = ex.ex_id)))
        UNION
         SELECT pr.pa_id,
            to_char(pr.pr_dt_hr_entregue, 'DD/MM/YYYY HH24:MI:SS'::text) AS dt_hr,
            'PRESCRICAO'::text AS tipo,
            concat('Medicamento ', me.me_nome_generico, ' prescrito.') AS "desc"
           FROM (public.prescricao pr
             JOIN public.medicamento me ON ((pr.me_id = me.me_id)))) historico
  ORDER BY historico.dt_hr;


ALTER TABLE public.historico_paciente OWNER TO postgres;

--
-- Name: horario_ho_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.horario_ho_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.horario_ho_id_seq OWNER TO postgres;

--
-- Name: horario_ho_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.horario_ho_id_seq OWNED BY public.horario.ho_id;


--
-- Name: medicamento_me_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.medicamento_me_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.medicamento_me_id_seq OWNER TO postgres;

--
-- Name: medicamento_me_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.medicamento_me_id_seq OWNED BY public.medicamento.me_id;


--
-- Name: medico_md_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.medico_md_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.medico_md_id_seq OWNER TO postgres;

--
-- Name: medico_md_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.medico_md_id_seq OWNED BY public.medico.md_id;


--
-- Name: paciente_pa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.paciente_pa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.paciente_pa_id_seq OWNER TO postgres;

--
-- Name: paciente_pa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.paciente_pa_id_seq OWNED BY public.paciente.pa_id;


--
-- Name: prescricao_pr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prescricao_pr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prescricao_pr_id_seq OWNER TO postgres;

--
-- Name: prescricao_pr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prescricao_pr_id_seq OWNED BY public.prescricao.pr_id;


--
-- Name: recepcionista; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recepcionista (
    re_id bigint NOT NULL,
    re_cpf character varying(255),
    re_nome character varying(255),
    us_id bigint
);


ALTER TABLE public.recepcionista OWNER TO postgres;

--
-- Name: recepcionista_re_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recepcionista_re_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recepcionista_re_id_seq OWNER TO postgres;

--
-- Name: recepcionista_re_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recepcionista_re_id_seq OWNED BY public.recepcionista.re_id;


--
-- Name: solicitacao_exame_se_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.solicitacao_exame_se_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.solicitacao_exame_se_id_seq OWNER TO postgres;

--
-- Name: solicitacao_exame_se_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.solicitacao_exame_se_id_seq OWNED BY public.solicitacao_exame.se_id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    us_id bigint NOT NULL,
    us_ativado boolean,
    us_login character varying(255),
    us_senha character varying(255),
    us_tipo_usuario character varying(255)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: usuario_us_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_us_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_us_id_seq OWNER TO postgres;

--
-- Name: usuario_us_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_us_id_seq OWNED BY public.usuario.us_id;


--
-- Name: consulta co_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta ALTER COLUMN co_id SET DEFAULT nextval('public.consulta_co_id_seq'::regclass);


--
-- Name: exame ex_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exame ALTER COLUMN ex_id SET DEFAULT nextval('public.exame_ex_id_seq'::regclass);


--
-- Name: horario ho_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.horario ALTER COLUMN ho_id SET DEFAULT nextval('public.horario_ho_id_seq'::regclass);


--
-- Name: medicamento me_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicamento ALTER COLUMN me_id SET DEFAULT nextval('public.medicamento_me_id_seq'::regclass);


--
-- Name: medico md_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medico ALTER COLUMN md_id SET DEFAULT nextval('public.medico_md_id_seq'::regclass);


--
-- Name: paciente pa_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente ALTER COLUMN pa_id SET DEFAULT nextval('public.paciente_pa_id_seq'::regclass);


--
-- Name: prescricao pr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescricao ALTER COLUMN pr_id SET DEFAULT nextval('public.prescricao_pr_id_seq'::regclass);


--
-- Name: recepcionista re_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recepcionista ALTER COLUMN re_id SET DEFAULT nextval('public.recepcionista_re_id_seq'::regclass);


--
-- Name: solicitacao_exame se_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitacao_exame ALTER COLUMN se_id SET DEFAULT nextval('public.solicitacao_exame_se_id_seq'::regclass);


--
-- Name: usuario us_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN us_id SET DEFAULT nextval('public.usuario_us_id_seq'::regclass);


--
-- Data for Name: consulta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.consulta (co_id, co_data_consulta, co_dt_hr_marcacao, ho_id, md_id, pa_id) FROM stdin;
\.


--
-- Name: consulta_co_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.consulta_co_id_seq', 1, false);


--
-- Data for Name: exame; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.exame (ex_id, ex_nome) FROM stdin;
1	Eletrocardiograma (ECG)
2	Hemograma Completo com Contagem de Plaquetas
3	Prova de Função Pulmonar
4	Ultrasonografia
\.


--
-- Name: exame_ex_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.exame_ex_id_seq', 4, true);


--
-- Data for Name: horario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.horario (ho_id, ho_hora_consulta) FROM stdin;
1	08:00:00
2	09:00:00
3	10:00:00
4	11:00:00
5	13:00:00
6	14:00:00
7	15:00:00
8	16:00:00
\.


--
-- Name: horario_ho_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.horario_ho_id_seq', 8, true);


--
-- Data for Name: medicamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.medicamento (me_id, me_fabricante, me_nome_fabrica, me_nome_generico) FROM stdin;
1	FARMACÊUTICA LTDA	Paracetamol	Paracetamol
2	FARMACÊUTICA LTDA	Dipirona Monoidratada	Dipirona
\.


--
-- Name: medicamento_me_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.medicamento_me_id_seq', 2, true);


--
-- Data for Name: medico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.medico (md_id, md_crm, md_especialidade, md_nome, us_id) FROM stdin;
1	5896	CLINICO_GERAL	João da Silva Costa Santos	4
2	5596	CLINICO_GERAL	Igor Fernando Basso	5
3	6605	CLINICO_GERAL	Susy Ataide dos Santos	6
4	5891	CLINICO_GERAL	Taciana Dieminger Albring	7
5	5234	CLINICO_GERAL	Luisy Caroline Gomes Passos	8
\.


--
-- Name: medico_md_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.medico_md_id_seq', 5, true);


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paciente (pa_id, pa_cpf, pa_dt_hr_cadastro, pa_nome, pa_sexo) FROM stdin;
1	28515743370	2018-06-08 00:35:44.802678	Amanda Cristina Venanci	FEMININO
2	04836438748	2018-06-08 00:35:44.813793	Diogo Fernando de Almeida Ronnau	MASCULINO
\.


--
-- Name: paciente_pa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.paciente_pa_id_seq', 2, true);


--
-- Data for Name: prescricao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prescricao (pr_id, pr_dt_hr_entregue, pr_entregue, me_id, pa_id) FROM stdin;
\.


--
-- Name: prescricao_pr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prescricao_pr_id_seq', 1, false);


--
-- Data for Name: recepcionista; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recepcionista (re_id, re_cpf, re_nome, us_id) FROM stdin;
1	51386597821	Willian dos Santos de Barros	9
2	39311580805	Bruno Sangoi Carlesso	10
3	89615159778	Giovana Oliveira de Araújo	11
\.


--
-- Name: recepcionista_re_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recepcionista_re_id_seq', 3, true);


--
-- Data for Name: solicitacao_exame; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.solicitacao_exame (se_id, se_arquivo_mimetype, se_dt_hr_cadastro, se_dt_hr_entregue, se_nome_arq_resultado, se_resultado, ex_id, md_id, pa_id) FROM stdin;
\.


--
-- Name: solicitacao_exame_se_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.solicitacao_exame_se_id_seq', 1, false);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (us_id, us_ativado, us_login, us_senha, us_tipo_usuario) FROM stdin;
1	t	admin	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	ADMINISTRADOR
2	f	dsouza	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	ADMINISTRADOR
3	f	apostingher	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	ADMINISTRADOR
4	f	jsantos	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	MEDICO
5	f	ibasso	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	MEDICO
6	f	ssantos	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	MEDICO
7	f	talbring	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	MEDICO
8	f	lpassos	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	MEDICO
9	f	wbarros	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	RECEPCIONISTA
10	f	bcarlesso	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	RECEPCIONISTA
11	f	garaujo	55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251	RECEPCIONISTA
\.


--
-- Name: usuario_us_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_us_id_seq', 11, true);


--
-- Name: consulta consulta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT consulta_pkey PRIMARY KEY (co_id);


--
-- Name: exame exame_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exame
    ADD CONSTRAINT exame_pkey PRIMARY KEY (ex_id);


--
-- Name: horario horario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.horario
    ADD CONSTRAINT horario_pkey PRIMARY KEY (ho_id);


--
-- Name: medicamento medicamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medicamento
    ADD CONSTRAINT medicamento_pkey PRIMARY KEY (me_id);


--
-- Name: medico medico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (md_id);


--
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (pa_id);


--
-- Name: prescricao prescricao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescricao
    ADD CONSTRAINT prescricao_pkey PRIMARY KEY (pr_id);


--
-- Name: recepcionista recepcionista_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recepcionista
    ADD CONSTRAINT recepcionista_pkey PRIMARY KEY (re_id);


--
-- Name: solicitacao_exame solicitacao_exame_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitacao_exame
    ADD CONSTRAINT solicitacao_exame_pkey PRIMARY KEY (se_id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (us_id);


--
-- Name: solicitacao_exame fk_7ucb55hkycsnnnn02gjscfnub; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitacao_exame
    ADD CONSTRAINT fk_7ucb55hkycsnnnn02gjscfnub FOREIGN KEY (md_id) REFERENCES public.medico(md_id);


--
-- Name: solicitacao_exame fk_87q176iomdjwk4mcyox71l01a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitacao_exame
    ADD CONSTRAINT fk_87q176iomdjwk4mcyox71l01a FOREIGN KEY (pa_id) REFERENCES public.paciente(pa_id);


--
-- Name: consulta fk_c5nn4sk8sfqfsv10cgvn3vdqd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fk_c5nn4sk8sfqfsv10cgvn3vdqd FOREIGN KEY (md_id) REFERENCES public.medico(md_id);


--
-- Name: prescricao fk_cfor5ual1ai7icea9m5isk2as; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescricao
    ADD CONSTRAINT fk_cfor5ual1ai7icea9m5isk2as FOREIGN KEY (pa_id) REFERENCES public.paciente(pa_id);


--
-- Name: medico fk_dkre4lpcfclo4inp3nj2l7c1q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medico
    ADD CONSTRAINT fk_dkre4lpcfclo4inp3nj2l7c1q FOREIGN KEY (us_id) REFERENCES public.usuario(us_id);


--
-- Name: recepcionista fk_fygbfkp7tqxf44qxiakeq8980; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recepcionista
    ADD CONSTRAINT fk_fygbfkp7tqxf44qxiakeq8980 FOREIGN KEY (us_id) REFERENCES public.usuario(us_id);


--
-- Name: prescricao fk_ky2cmkfwlk19hp456doj8i219; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prescricao
    ADD CONSTRAINT fk_ky2cmkfwlk19hp456doj8i219 FOREIGN KEY (me_id) REFERENCES public.medicamento(me_id);


--
-- Name: consulta fk_scjr9yy0agvpibck5db1a4hsg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fk_scjr9yy0agvpibck5db1a4hsg FOREIGN KEY (ho_id) REFERENCES public.horario(ho_id);


--
-- Name: solicitacao_exame fk_t0qrtjshaxxeu513w965ijmxe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitacao_exame
    ADD CONSTRAINT fk_t0qrtjshaxxeu513w965ijmxe FOREIGN KEY (ex_id) REFERENCES public.exame(ex_id);


--
-- Name: consulta fk_tmh7ct2921qg076831dvdhq7b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fk_tmh7ct2921qg076831dvdhq7b FOREIGN KEY (pa_id) REFERENCES public.paciente(pa_id);


--
-- PostgreSQL database dump complete
--

