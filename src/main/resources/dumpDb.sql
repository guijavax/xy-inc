--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

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
-- Name: coordenates; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.coordenates (
    id_coordenate character varying NOT NULL,
    name_coordenates character varying(255) NOT NULL,
    coordenate_x integer NOT NULL,
    coordenate_y integer NOT NULL
);


ALTER TABLE public.coordenates OWNER TO postgres;

--
-- Name: schema_version; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.schema_version (
    version_rank integer NOT NULL,
    installed_rank integer NOT NULL,
    version character varying(50) NOT NULL,
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.schema_version OWNER TO postgres;

--
-- Name: sequence_id_coordenate; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_id_coordenate
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_id_coordenate OWNER TO postgres;

--
-- Data for Name: coordenates; Type: TABLE DATA; Schema: public; Owner: postgres
--

insert into public.coordenates values
(1,	'Lanchonete',	27,	12),
(2,	'Posto',	31,	18),
(3,	'Joalheria',	15,	12),
(4,	'Floricultura',	19,	21),
(5,	'Pub',	12,	8),
(6,	'Supermecado',	23,	6),
(7,	'Churrascaria',	28,	2);


--
-- Data for Name: schema_version; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	01	create table coordenates	SQL	V01__create_table_coordenates.sql	1398806298	postgres	2019-02-03 23:22:12.223626	28	t
2	2	02	create sequence coordenates	SQL	V02__create_sequence_coordenates.sql	0	postgres	2019-02-03 23:54:42.622914	9	t
\.


--
-- Name: sequence_id_coordenate; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_id_coordenate', 15, true);


--
-- Name: coordenates_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.coordenates
    ADD CONSTRAINT coordenates_pk PRIMARY KEY (id_coordenate);


--
-- Name: schema_version_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.schema_version
    ADD CONSTRAINT schema_version_pk PRIMARY KEY (version);


--
-- Name: schema_version_ir_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace:
--

CREATE INDEX schema_version_ir_idx ON public.schema_version USING btree (installed_rank);


--
-- Name: schema_version_s_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace:
--

CREATE INDEX schema_version_s_idx ON public.schema_version USING btree (success);


--
-- Name: schema_version_vr_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace:
--

CREATE INDEX schema_version_vr_idx ON public.schema_version USING btree (version_rank);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: SEQUENCE sequence_id_coordenate; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE public.sequence_id_coordenate FROM PUBLIC;
REVOKE ALL ON SEQUENCE public.sequence_id_coordenate FROM postgres;
GRANT ALL ON SEQUENCE public.sequence_id_coordenate TO postgres;


--
-- PostgreSQL database dump complete
--

