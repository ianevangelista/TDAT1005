-- MySQL
-- DROP-setninger i tilfelle vi må kjøre scriptet på nytt.

/*DROP TABLE leilighet;
DROP TABLE andelseier;
DROP TABLE bygning;
DROP TABLE borettslag;
DROP TABLE poststed;*/

-- Lager tabellene, legger inn NOT NULL- og UNIQUE-krav der det er naturlig
-- Vær forsiktig med å legge inn slike krav, det er vanskelig å forandre
-- dem i ettertid.

CREATE TABLE poststed(
  postnr SMALLINT,
  poststed VARCHAR(20) NOT NULL,
  CONSTRAINT poststed_pk PRIMARY KEY(postnr));

CREATE TABLE borettslag(
  bolag_navn VARCHAR(20),
  bolag_adr VARCHAR(40) NOT NULL UNIQUE,
  etabl_aar SMALLINT NOT NULL,
  postnr SMALLINT NOT NULL,
  CONSTRAINT borettslag_pk PRIMARY KEY(bolag_navn));

CREATE TABLE bygning(
  bygn_id INTEGER NOT NULL AUTO_INCREMENT,
  bygn_adr VARCHAR(40) NOT NULL,
  ant_etasjer INTEGER  DEFAULT 1,
  bolag_navn VARCHAR(20) NOT NULL,
  postnr SMALLINT NOT NULL,
  CONSTRAINT bygning_pk PRIMARY KEY(bygn_id));

CREATE TABLE leilighet(
  leil_nr INTEGER NOT NULL AUTO_INCREMENT,
  ant_rom SMALLINT NOT NULL,
  ant_kvm REAL NOT NULL,
  etasje SMALLINT DEFAULT 1,
  bygn_id INTEGER NOT NULL,
  and_eier_nr INTEGER NOT NULL UNIQUE,
  CONSTRAINT leilighet_pk PRIMARY KEY(leil_nr));

CREATE TABLE andelseier(
  and_eier_nr INTEGER NOT NULL AUTO_INCREMENT,
  fornavn VARCHAR(30) NOT NULL,
  etternavn VARCHAR(30) NOT NULL,
  telefon CHAR(15),
  ansiennitet SMALLINT,
  bolag_navn VARCHAR(20) NOT NULL,
  CONSTRAINT andelseier_pk PRIMARY KEY(and_eier_nr));

-- Fremmednøkler

ALTER TABLE borettslag
  ADD CONSTRAINT borettslag_fk1 FOREIGN KEY(postnr)
  REFERENCES poststed(postnr);

ALTER TABLE bygning
  ADD CONSTRAINT bygning_fk1 FOREIGN KEY(postnr)
  REFERENCES poststed(postnr);

ALTER TABLE bygning
  ADD CONSTRAINT bygning_fk2 FOREIGN KEY(bolag_navn)
  REFERENCES borettslag(bolag_navn);

ALTER TABLE leilighet
  ADD CONSTRAINT leilighet_fk1 FOREIGN KEY(bygn_id)
  REFERENCES bygning(bygn_id);

ALTER TABLE leilighet
  ADD CONSTRAINT leilighet_fk2 FOREIGN KEY(and_eier_nr)
  REFERENCES andelseier(and_eier_nr);

ALTER TABLE andelseier
  ADD CONSTRAINT andelseier_fk2 FOREIGN KEY(bolag_navn)
  REFERENCES borettslag(bolag_navn);



-- Legger inn gyldige data

INSERT INTO poststed(postnr, poststed) VALUES(2020, 'Skedsmokorset');
INSERT INTO poststed(postnr, poststed) VALUES(6408, 'Aureosen');
INSERT INTO poststed(postnr, poststed) VALUES(7033, 'Trondheim');
INSERT INTO poststed(postnr, poststed) VALUES(7020, 'Trondheim');

INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('Tertitten', 'Åsveien 100', 1980, 7020);
INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('Sisiken', 'Brurød', 1990, 7033);
INSERT INTO borettslag(bolag_navn, bolag_adr, etabl_aar, postnr) VALUES('Lerken', 'Storgt 5', 2000, 6408);

INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Even', 'Trulsbo', '56667743', 3, 'Tertitten');
INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Anna', 'Olsen', '45674588', 10, 'Tertitten');
INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Ingrid', 'Olsen', '45785388', 8, 'Tertitten');
INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Arne', 'Torp', '78565388', 7, 'Tertitten');
INSERT INTO andelseier(and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
                        VALUES(DEFAULT, 'Arne', 'Martinsen', '78555388', 4, 'Sisiken');

INSERT INTO bygning(bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr) VALUES(DEFAULT, 'Åsveien 100a', 3, 'Tertitten', 7020);
INSERT INTO bygning(bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr) VALUES(DEFAULT, 'Åsveien 100b', 3, 'Tertitten', 7020);
INSERT INTO bygning(bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr) VALUES(DEFAULT, 'Åsveien 100c', 6, 'Tertitten', 7020);
INSERT INTO bygning(bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr) VALUES(DEFAULT, 'Storgt 10', 2, 'Sisiken', 7020);

-- bruker defaultverdien for antall etasjer
INSERT INTO bygning(bygn_id, bygn_adr, bolag_navn, postnr) VALUES(DEFAULT, 'Åsveien 100', 'Tertitten', 7020);

INSERT INTO leilighet(leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr) VALUES(DEFAULT, 5, 110, 3, 1, 1);
INSERT INTO leilighet(leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr) VALUES(DEFAULT, 5, 110, 3, 1, 2);
INSERT INTO leilighet(leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr) VALUES(DEFAULT, 2, 50, 1, 3, 3);

-- bruker defaultverdien for etasje
INSERT INTO leilighet(leil_nr, ant_rom, ant_kvm, bygn_id, and_eier_nr) VALUES(DEFAULT, 5, 110, 1, 4);

-- 1. select * from borettslag where etabl_aar > 1975 and etabl_aar < 1985
-- 2. select fornavn, etternavn, ansiennitet from andelseier order by ansiennitet desc
-- 3. select * from borettslag order by etabl_aar limit 1
-- 4. select distinct bygn_adr from bygning, leilighet where leilighet.bygn_id = bygning.bygn_id and ant_rom > 3
-- 5. select count(*) from borettslag, bygning where borettslag.bolag_navn = bygning.bolag_navn and borettslag.bolag_navn = "Tertitten"
-- 6. select * from bygning, borettslag where borettslag.bolag_navn = bygning.bolag_navn order by borettslag.bolag_navn
-- 7. select count(*) as "Antall bygninger" from borettslag, bygning, where borettslag.bolag_navn = bygning.bolag_navn and borettslag.bolag_navn = "Tertitten"
-- 8. select etasje from leilighet, borettslag, bygning where borettslag.bolag_navn = bygning.bolag_navn and bygning.bygn_id = leilighet.bygn_id order by etasje desc limit 1
-- 9. select fornavn, etternavn, telefon from andelseier where and_eier_nr not in (select and_eier_nr from leilighet)
-- 10. select count(*) as "Antall eiere fra borettslag", bolag_navn from andelseier group by bolag_navn order by count(*) desc
-- 11. select fornavn, etternavn, leil_nr from andelseier left outer join leilighet on andelseier.and_eier_nr = leilighet.and_eier_nr
-- 12. select distinct borettslag.bolag_navn from borettslag, bygning, leilighet where borettslag.bolag_navn = bygning.bolag_navn and bygning.bygn_id = leilighet.bygn_id and ant_rom = 4
-- 13. select postnr, poststed, count(*) as "antall andelseiere" from poststed natural join bygning natural join leilighet natural join andelseier group by postnr 
