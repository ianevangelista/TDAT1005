DROP TABLE IF EXISTS borettslag;
DROP TABLE IF EXISTS bygning;
DROP TABLE IF EXISTS leilighet;
DROP TABLE IF EXISTS andelseier;
/*
c)
*/
CREATE TABLE borettslag(
  borettslag_nr INT(3) AUTO_INCREMENT PRIMARY KEY,
  navn VARCHAR(30) NOT NULL,
  adresse VARCHAR(30) NOT NULL,
  antall_bygg INT(3),
  etableringsar INT(4)
);

CREATE TABLE bygning(
  bygg_nr INT(3) AUTO_INCREMENT PRIMARY KEY,
  antall_leiligheter INT(3),
  antall_etasjer INT(3),
  bygg_adresse VARCHAR(30) NOT NULL,
  borettslag_nr INT(3)
);

ALTER TABLE bygning
  ADD FOREIGN KEY(borettslag_nr)
  REFERENCES borettslag(borettslag_nr);

CREATE TABLE leilighet(
  leilighet_nr INT(4) AUTO_INCREMENT PRIMARY KEY,
  antall_rom INT(4),
  antall_m2 INT(4),
  etasje INT(1),
  bygg_nr INT(3)
);

ALTER TABLE leilighet
  ADD FOREIGN KEY(bygg_nr)
  REFERENCES bygning(bygg_nr);

CREATE TABLE andelseier(
  andelseier_nr INT(4) AUTO_INCREMENT PRIMARY KEY,
  navn VARCHAR(30) NOT NULL,
  leilighet_nr INT(3)
);

ALTER TABLE andelseier
  ADD FOREIGN KEY(leilighet_nr)
  REFERENCES leilighet(leilighet_nr);
/*
d)
*/

INSERT INTO borettslag(borettslag_nr, navn, adresse, antall_bygg, etableringsar) values (DEFAULT, 'Ians borettslag', 'Ians gate', 50, 1999);

INSERT INTO bygning(bygg_nr, antall_leiligheter, antall_etasjer, bygg_adresse, borettslag_nr) values (DEFAULT, 50, 3, 'Ians vei', 1);

INSERT INTO leilighet(leilighet_nr, antall_rom, antall_m2, etasje, bygg_nr) values (DEFAULT, 5, 50, 2, 1);
INSERT INTO leilighet(leilighet_nr, antall_rom, antall_m2, etasje, bygg_nr) values (DEFAULT, 5, 60, 2, 1);
INSERT INTO leilighet(leilighet_nr, antall_rom, antall_m2, etasje, bygg_nr) values (DEFAULT, 5, 70, 2, 1);



INSERT INTO andelseier(andelseier_nr, navn, leilighet_nr) values (DEFAULT, 'Ian', 1);
INSERT INTO andelseier(andelseier_nr, navn, leilighet_nr) values (DEFAULT, 'Kasper', 2);
INSERT INTO andelseier(andelseier_nr, navn, leilighet_nr) values (DEFAULT, 'Helene', 3);

/*
select andelseier.navn, leilighet.leilighet_nr, bygning.bygg_nr, borettslag.borettslag_nr FROM borettslag, bygning, leilighet, andelseier WHERE borettslag.borettslag_nr=bygning.borettslag_nr AND leilighet.bygg_nr=bygning.bygg_nr AND leilighet.leilighet_nr=andelseier.leilighet_nr
*/
