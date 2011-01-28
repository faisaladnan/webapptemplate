-- Sequence: hibernate_sequence

-- DROP SEQUENCE hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 147
  CACHE 1;
ALTER TABLE hibernate_sequence OWNER TO postgres;


-- Table: address

-- DROP TABLE address;

CREATE TABLE address
(
  id integer NOT NULL,
  person_id integer,
  address character varying(255),
  city character varying(50) NOT NULL,
  state character varying(50),
  zip_postal character varying(30) NOT NULL,
  country character varying(50) NOT NULL,
  created timestamp without time zone,
  CONSTRAINT address_pkey PRIMARY KEY (id),
  CONSTRAINT fk_address_person_id FOREIGN KEY (person_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE address OWNER TO postgres;


-- Table: authorities

-- DROP TABLE authorities;

CREATE TABLE authorities
(
  username character varying(50) NOT NULL,
  authority character varying(50) NOT NULL,
  CONSTRAINT fk_authorities_users FOREIGN KEY (username)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authorities OWNER TO postgres;

-- Index: ix_auth_username

-- DROP INDEX ix_auth_username;

CREATE UNIQUE INDEX ix_auth_username
  ON authorities
  USING btree
  (username, authority);

-- Table: blacklisted_msisdn

-- DROP TABLE blacklisted_msisdn;

CREATE TABLE blacklisted_msisdn
(
  id integer NOT NULL,
  customer_id integer,
  msisdn character varying(255),
  created time without time zone DEFAULT now(),
  CONSTRAINT blacklisted_msisdn_pkey PRIMARY KEY (id),
  CONSTRAINT fk_blacklisted_msisdn_customer_id FOREIGN KEY (customer_id)
      REFERENCES customer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE blacklisted_msisdn OWNER TO postgres;

-- Table: contacts

-- DROP TABLE contacts;

CREATE TABLE contacts
(
  id integer NOT NULL,
  firstname character varying(30),
  lastname character varying(30),
  telephone character varying(15),
  email character varying(30),
  created timestamp without time zone DEFAULT now(),
  CONSTRAINT contacts_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contacts OWNER TO postgres;

-- Table: customer

-- DROP TABLE customer;

CREATE TABLE customer
(
  id integer NOT NULL DEFAULT nextval('customer_sequence'::regclass),
  address character varying(255),
  created timestamp without time zone DEFAULT now(),
  customer_name character varying(255),
  CONSTRAINT customer_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE customer OWNER TO postgres;

-- Table: person

-- DROP TABLE person;

CREATE TABLE person
(
  id integer NOT NULL,
  first_name character varying(50) NOT NULL,
  last_name character varying(50) NOT NULL,
  created timestamp without time zone,
  CONSTRAINT person_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE person OWNER TO postgres;

-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  username character varying(50) NOT NULL,
  "password" character varying(50) NOT NULL,
  enabled bit(1) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users OWNER TO postgres;
