DROP TABLE IF EXISTS ordredetalj;
DROP TABLE IF EXISTS prisinfo;
DROP TABLE IF EXISTS ordrehode;
DROP TABLE IF EXISTS delinfo;
DROP TABLE IF EXISTS levinfo;

CREATE TABLE delinfo(
delnr       INTEGER,
beskrivelse VARCHAR(30) NOT NULL,
CONSTRAINT delinfo_pk PRIMARY KEY(delnr));

CREATE TABLE levinfo(
levnr   INTEGER,
navn    VARCHAR(20) NOT NULL,
adresse VARCHAR(20) NOT NULL,
levby   VARCHAR(20) NOT NULL,
fylke   VARCHAR(20) NOT NULL,
postnr  INTEGER NOT NULL,
CONSTRAINT levinfo_pk PRIMARY KEY(levnr));

CREATE TABLE ordrehode(
ordrenr INTEGER,
dato    DATE NOT NULL,
levnr   INTEGER NOT NULL,
status  CHAR(1) NOT NULL,
CONSTRAINT ordrehode_pk PRIMARY KEY(ordrenr),
CONSTRAINT ordrehode_fk FOREIGN KEY(levnr) REFERENCES levinfo(levnr));

CREATE TABLE ordredetalj(
ordrenr    INTEGER,
delnr        INTEGER NOT NULL,
kvantum   INTEGER NOT NULL,
CONSTRAINT ordredetalj_pk PRIMARY KEY(ordrenr,delnr),
CONSTRAINT ordredetalj_fk1 FOREIGN KEY(ordrenr) REFERENCES ordrehode(ordrenr),
CONSTRAINT ordredetalj_fk2 FOREIGN KEY(delnr) REFERENCES delinfo(delnr));

CREATE TABLE prisinfo(
delnr     INTEGER,
levnr     INTEGER,
katalognr CHAR(6),
pris      REAL,
CONSTRAINT prisinfo_pk PRIMARY KEY(delnr, levnr),
CONSTRAINT prisinfo_fk1 FOREIGN KEY(delnr)REFERENCES delinfo(delnr),
CONSTRAINT prisinfo_fk2 FOREIGN KEY(levnr)REFERENCES levinfo(levnr));

insert into delinfo(delnr, beskrivelse) values(51173,'Binders');
insert into delinfo(delnr, beskrivelse) values(1,'Kontorstol');
insert into delinfo(delnr, beskrivelse) values(51200,'Linjalsett');
insert into delinfo(delnr, beskrivelse) values(3,'Pult');
insert into delinfo(delnr, beskrivelse) values(4,'Skrivebord');
insert into delinfo(delnr, beskrivelse) values(1909,'Skriveunderlag');
insert into delinfo(delnr, beskrivelse) values(201,'Svarte kulepenner');
insert into delinfo(delnr, beskrivelse) values(202,'Blå kulepenner');

insert into levinfo(levnr, navn, adresse, levby, fylke, postnr) values(6,'Kontorekspressen AS','Skolegata 3','Oslo','Oslo',1234);
insert into levinfo(levnr, navn, adresse, levby, fylke, postnr) values(82,'Kontordata AS','Åsveien 178','Trondheim','S-Trøndelag',7023);
insert into levinfo(levnr, navn, adresse, levby, fylke, postnr) values(9,'Kontorutstyr AS','Villa Villekulla','Ås','Østfold',1456);
insert into levinfo(levnr, navn, adresse, levby, fylke, postnr) values(44,'Billig og Bra AS','Aveny 56','Oslo','Oslo',1222);
insert into levinfo(levnr, navn, adresse, levby, fylke, postnr) values(12,'Mister Office AS','Storgt 56','Ås','Østfold',1456);
insert into levinfo(levnr, navn, adresse, levby, fylke, postnr) values(81,'Kontorbutikken AS','Gjennomveien 78','Ål','Telemark',3345);

insert into ordrehode(ordrenr, dato, levnr, status)  values(11,'1986-05-10',6,'c');
insert into ordrehode(ordrenr, dato, levnr, status)  values(12,'1986-07-17',82,'c');
insert into ordrehode(ordrenr, dato, levnr, status)  values(13,'1986-09-13',44,'p');
insert into ordrehode(ordrenr, dato, levnr, status)  values(14,'1986-12-17',44,'p');
insert into ordrehode(ordrenr, dato, levnr, status)  values(15,'1987-01-03',44,'p');
insert into ordrehode(ordrenr, dato, levnr, status)  values(16,'1987-01-31',6,'c');
insert into ordrehode(ordrenr, dato, levnr, status)  values(17,'1987-05-14',6,'c');
insert into ordrehode(ordrenr, dato, levnr, status)  values(18,'1987-05-12',82,'p');


insert into ordredetalj(ordrenr, delnr, kvantum) values(11,1,5);
insert into ordredetalj(ordrenr, delnr, kvantum) values(11,201,100);
insert into ordredetalj(ordrenr, delnr, kvantum) values(11,202,100);
insert into ordredetalj(ordrenr, delnr, kvantum) values(11,1909,6);
insert into ordredetalj(ordrenr, delnr, kvantum) values(11,51200,20);
insert into ordredetalj(ordrenr, delnr, kvantum) values(12,3,2);
insert into ordredetalj(ordrenr, delnr, kvantum) values(12,201,50);
insert into ordredetalj(ordrenr, delnr, kvantum) values(12,202,60);
insert into ordredetalj(ordrenr, delnr, kvantum) values(13,51173,20);
insert into ordredetalj(ordrenr, delnr, kvantum) values(14,201,100);
insert into ordredetalj(ordrenr, delnr, kvantum) values(14,202,100);
insert into ordredetalj(ordrenr, delnr, kvantum) values(14,51173,30);
insert into ordredetalj(ordrenr, delnr, kvantum) values(15,201,100);
insert into ordredetalj(ordrenr, delnr, kvantum) values(15,202,100);
insert into ordredetalj(ordrenr, delnr, kvantum) values(16,201,50);
insert into ordredetalj(ordrenr, delnr, kvantum) values(16,202,50);
insert into ordredetalj(ordrenr, delnr, kvantum) values(16,51173,20);
insert into ordredetalj(ordrenr, delnr, kvantum) values(16,1909,10);
insert into ordredetalj(ordrenr, delnr, kvantum) values(17,1,10);
insert into ordredetalj(ordrenr, delnr, kvantum) values(17,3,1);
insert into ordredetalj(ordrenr, delnr, kvantum) values(17,4,5);
insert into ordredetalj(ordrenr, delnr, kvantum) values(18,3,2);
insert into ordredetalj(ordrenr, delnr, kvantum) values(18,4,8);

insert into prisinfo(delnr, levnr, katalognr, pris) values(51173,6,'37S',0.57);
insert into prisinfo(delnr, levnr, katalognr, pris) values(51173,44,'312/2',0.44);
insert into prisinfo(delnr, levnr, katalognr, pris) values(51173,82,'300021',0.35);
insert into prisinfo(delnr, levnr, katalognr, pris) values(1,6,'97s',120.00);
insert into prisinfo(delnr, levnr, katalognr, pris) values(1,9,'x120',219.99);
insert into prisinfo(delnr, levnr, katalognr, pris) values(51200,6,'54s',7.35);
insert into prisinfo(delnr, levnr, katalognr, pris) values(1909,9,'X7770',3.00);
insert into prisinfo(delnr, levnr, katalognr, pris) values(201,44,'100/1',1.60);
insert into prisinfo(delnr, levnr, katalognr, pris) values(201,6,'21s',1.90);
insert into prisinfo(delnr, levnr, katalognr, pris) values(202,44,'101/1',1.50);
insert into prisinfo(delnr, levnr, katalognr, pris) values(202,9,'22s',1.76);
insert into prisinfo(delnr, levnr, katalognr, pris) values(3,82,'2077',1299.00);
insert into prisinfo(delnr, levnr, katalognr, pris) values(4,82,'2177',899.00);
insert into prisinfo(delnr, levnr, katalognr, pris) values(201,82,'3140',2.60);
insert into prisinfo(delnr, levnr, katalognr, pris) values(202,82,'3141',1.50);
insert into prisinfo(delnr, levnr, katalognr, pris) values(3,6,'34P',1199.00);
insert into prisinfo(delnr, levnr, katalognr, pris) values(4,6,'67P',550.00);
insert into prisinfo(delnr, levnr, katalognr, pris) values(1909,6,'53P',0.85);
insert into prisinfo(delnr, levnr, katalognr, pris) values(202,6,'345u',6.50);
insert into prisinfo(delnr, levnr, katalognr, pris) values(3,9,'a48',1050.00);

-- a) List ut all informasjon (ordrehode og ordredetalj) om ordrer for leverandør nr 44.
select * from ordrehode, ordredetalj
where ordrehode.ordrenr = ordredetalj.ordrenr and ordrehode.levnr = 44;
-- b) Finn navn og by ("LevBy") for leverandører som kan levere del nummer 1.
select distinct levinfo.navn, levinfo.levby
from levinfo
join prisinfo on levinfo.levnr = prisinfo.levnr
where prisinfo.delnr = 1;
-- c) Finn nummer, navn og pris for den leverandør som kan levere del nummer 201 til billigst pris.
select levinfo.levnr, levinfo.navn, pris
from levinfo, prisinfo
where levinfo.levnr = prisinfo.levnr
and prisinfo.delnr = 201
order by pris asc limit 1
-- d) Lag fullstendig oversikt over ordre nr 16,
-- med ordrenr, dato, delnr, beskrivelse, kvantum, (enhets-)pris og beregnet beløp (=pris*kvantum).
select distinct ordredetalj.ordrenr, dato, delinfo.delnr, kvantum, beskrivelse, pris, (pris*kvantum) as 'beregnet beløp'
from ordrehode
join ordredetalj on ordrehode.ordrenr = ordredetalj.ordrenr
join delinfo on delinfo.delnr = ordredetalj.delnr
join prisinfo on prisinfo.delnr = prisinfo.delnr
where ordrehode.ordrenr = 16
-- e) Finn delnummer og leverandørnummer for deler som har en pris
-- som er høyere enn prisen for del med katalognr X7770.
select distinct ordredetalj.delnr, ordrehode.levnr from ordrehode
join ordredetalj on ordrehode.ordrenr = ordredetalj.ordrenr
join prisinfo on prisinfo.pris
where prisinfo.pris > (prisinfo.katalognr = 'X7770')
-- f) i) Tenk deg at tabellen levinfo skal deles i to. Sammenhengen mellom by og fylke skal tas ut av tabellen.
-- Det er unødvendig å lagre fylketilhørigheten for hver forekomst av by.
-- Lag én ny tabell som inneholder byer og fylker.
-- Fyll denne med data fra levinfo.

create table byogfylke (
  levby  VARCHAR(20) NOT NULL,
  fylke  VARCHAR(20) NOT NULL,
  CONSTRAINT by_og_fylke_pk PRIMARY KEY(levby));
)

-- Lag også en tabell som er lik levinfo unntatt kolonnen Fylke.
-- (Denne splittingen av tabellen levinfo gjelder bare i denne oppgaven.
-- I resten av oppgavesettet antar du at du har den opprinnelige levinfo-tabellen.)
create table levinfo_kopi (
  levnr   INTEGER,
  navn    VARCHAR(20) NOT NULL,
  adresse VARCHAR(20) NOT NULL,
  levby   VARCHAR(20) NOT NULL,
  postnr  INTEGER NOT NULL,
  CONSTRAINT levinfo_pk PRIMARY KEY(levnr),
  CONSTRAINT leveinfo_kopi_fk FOREIGN KEY(levby) REFERENCES byogfylke(levby)
);

-- Lag en virtuell tabell (view) slik at brukerne i størst mulig grad kan jobbe på samme måte mot de to nye tabellene
-- som den gamle. Prøv ulike kommandoer mot tabellen (select, update, delete, insert).
-- Hvilke begrensninger, hvis noen, har brukerne i forhold til tidligere?
create view view_byogfylke as select * from byogfylke;
select * from view_byogfylke;

create view view_levinfo as select * from levinfo_kopi;
select * from view_levinfo;

insert into view_byogfylke(levby, fylke) values('Bærum','Akershus');
-- opprettes men vises ikke i den virtuelle tabellen
-- kan ikke bruke distinct, group by, order by
update view_byogfylke set levby = 'Asker'
WHERE levby = 'Oslo';

-- Anta at en vurderer å slette opplysningene om de leverandørene som ikke er representert i Prisinfo-tabellen.
-- Finn ut hvilke byer en i tilfelle ikke får leverandør i. (Du skal ikke utføre slettingen.)
-- (Tips: Svaret skal bli kun én by, "Ål".)
select distinct levby
from levinfo
where levby
not in (select levby from prisinfo, levinfo
where (prisinfo.levnr = levinfo.levnr));

-- Finn leverandørnummer for den leverandør som kan levere ordre nr 18 til lavest totale beløp (vanskelig).
SELECT levnr, totalpris
FROM (SELECT b.tot as tot, SUM(pris) as totalpris, levnr
FROM (SELECT levnr, pris, ordrenr
FROM prisinfo JOIN ordredetalj
USING(delnr) WHERE ordrenr=18) as a
JOIN (SELECT COUNT(*) as tot, ordrenr
FROM ordredetalj GROUP BY ordrenr) as b
USING(ordrenr)
GROUP BY levnr HAVING COUNT(pris)=tot) as c
ORDER by totalpris ASC LIMIT 1;

-- Utgave tilpasset MySQL

-- Sletter tabeller

DROP TABLE bok_forfatter;
DROP TABLE forfatter;
DROP TABLE bok;
DROP TABLE forlag;
DROP TABLE konsulent;

-- Oppretter tabeller med entitetsintegritet (primærnøkkel)

CREATE TABLE forlag(
                     forlag_id INTEGER NOT NULL AUTO_INCREMENT,
                     forlag_navn VARCHAR(30),
                     adresse VARCHAR(30),
                     telefon CHAR(15),
                     CONSTRAINT forlag_pk PRIMARY KEY(forlag_id));

CREATE TABLE bok(
                  bok_id INTEGER NOT NULL AUTO_INCREMENT,
                  tittel VARCHAR(30),
                  utgitt_aar INTEGER,
                  forlag_id INTEGER,
                  CONSTRAINT bok_pk PRIMARY KEY(bok_id));

CREATE TABLE forfatter(
                        forfatter_id INTEGER NOT NULL AUTO_INCREMENT,
                        fornavn VARCHAR(20),
                        etternavn VARCHAR (30),
                        fode_aar INTEGER,
                        dod_aar INTEGER,
                        nasjonalitet VARCHAR(20),
                        CONSTRAINT forfatter_pk PRIMARY KEY(forfatter_id));

CREATE TABLE bok_forfatter(
                            bok_id INTEGER NOT NULL,
                            forfatter_id INTEGER NOT NULL,
                            CONSTRAINT bok_forfatter_pk PRIMARY KEY(bok_id, forfatter_id));

CREATE TABLE konsulent(
                        kons_id INTEGER NOT NULL AUTO_INCREMENT,
                        fornavn VARCHAR(20),
                        etternavn VARCHAR (30),
                        epost VARCHAR(30),
                        CONSTRAINT konsulent_pk PRIMARY KEY(kons_id));

-- Legger på referanseintegritet (fremmednøkler)

ALTER TABLE bok
  ADD CONSTRAINT bok_fk FOREIGN KEY(forlag_id)REFERENCES forlag(forlag_id);

ALTER TABLE bok_forfatter
  ADD CONSTRAINT bok_forfatter_fk1 FOREIGN KEY(bok_id)REFERENCES bok(bok_id);

ALTER TABLE bok_forfatter
  ADD CONSTRAINT bok_forfatter_fk2 FOREIGN KEY(forfatter_id)REFERENCES forfatter(forfatter_id);

-- Legger inn data i tabellene

INSERT INTO forlag VALUES(DEFAULT,'Tapir','Trondheim', '73590000');
INSERT INTO forlag VALUES(DEFAULT,'Gyldendal','Oslo', '22220000');
INSERT INTO forlag VALUES(DEFAULT,'Cappelen','Oslo', '22200000');
INSERT INTO forlag VALUES(DEFAULT,'Universitetsforlaget','Oslo', '23230000');
INSERT INTO forlag VALUES(DEFAULT,'Achehaug','Oslo', '22000000');
INSERT INTO forlag VALUES(DEFAULT,'Oktober','Oslo', '22002200');
INSERT INTO forlag VALUES(DEFAULT,'Tiden','Oslo', '22232223');
INSERT INTO forlag VALUES(DEFAULT,'Harper Collins','USA',NULL);

INSERT INTO bok VALUES(DEFAULT,'Tåpenes',1995,7);
INSERT INTO bok VALUES(DEFAULT,'Rebecca',1981,3);
INSERT INTO bok VALUES(DEFAULT,'Gutter er gutter',1995,5);
INSERT INTO bok VALUES(DEFAULT,'Microserfs',1991,8);
INSERT INTO bok VALUES(DEFAULT,'Generation X',1995,8);
INSERT INTO bok VALUES(DEFAULT,'Klosterkrønike',1982,3);
INSERT INTO bok VALUES(DEFAULT,'Universet',1988,3);
INSERT INTO bok VALUES(DEFAULT,'Nålen',1978,3);
INSERT INTO bok VALUES(DEFAULT,'Markens grøde',1917,2);
INSERT INTO bok VALUES(DEFAULT,'Victoria',1898,2);
INSERT INTO bok VALUES(DEFAULT,'Sult',1890,2);
INSERT INTO bok VALUES(DEFAULT,'Benoni',1908,2);
INSERT INTO bok VALUES(DEFAULT,'Rosa',1908,2);
INSERT INTO bok VALUES(DEFAULT,'Et skritt',1997,2);
INSERT INTO bok VALUES(DEFAULT,'Den femte',1996,2);
INSERT INTO bok VALUES(DEFAULT,'Villspor',1995,2);
INSERT INTO bok VALUES(DEFAULT,'Silkeridderen',1994,2);
INSERT INTO bok VALUES(DEFAULT,'Den hvite hingsten',1992,2);
INSERT INTO bok VALUES(DEFAULT,'Hunder',1992,2);
INSERT INTO bok VALUES(DEFAULT,'Bridget Jones',1995,5);
INSERT INTO bok VALUES(DEFAULT,'Se terapeuten',1998,3);
INSERT INTO bok VALUES(DEFAULT,'Sa mor',1996,3);
INSERT INTO bok VALUES(DEFAULT,'Jubel',1995,3);
INSERT INTO bok VALUES(DEFAULT,'Tatt av kvinnen',1993,3);
INSERT INTO bok VALUES(DEFAULT,'Supernaiv',1996,3);

INSERT INTO forfatter VALUES(DEFAULT,'John','Tool',1937, 1969, 'USA');
INSERT INTO forfatter VALUES(DEFAULT,'Ken','Follet',NULL, NULL, 'Britisk');
INSERT INTO forfatter VALUES(DEFAULT,'Stephen','Hawking',NULL, NULL, 'Britisk');
INSERT INTO forfatter VALUES(DEFAULT,'Jose','Saramago',1942, NULL, 'Portugisisk');
INSERT INTO forfatter VALUES(DEFAULT,'Douglas','Coupland',1961, NULL, 'Canadisk');
INSERT INTO forfatter VALUES(DEFAULT,'Nick','Hornby',1857, NULL, 'Britisk');
INSERT INTO forfatter VALUES(DEFAULT,'Knut','Hamsun',1859, 1952, 'Norsk');
INSERT INTO forfatter VALUES(DEFAULT,'Henning','Mankell',1948, NULL, 'Svensk');
INSERT INTO forfatter VALUES(DEFAULT,'Helen','Fielding',NULL, NULL, 'Britisk');
INSERT INTO forfatter VALUES(DEFAULT,'Hal','Sirowitz',NULL, NULL, 'USA');
INSERT INTO forfatter VALUES(DEFAULT,'Lars S.','Christensen',NULL, NULL, 'Norsk');
INSERT INTO forfatter VALUES(DEFAULT,'Erlend','Loe',NULL, NULL, 'Norsk');

INSERT INTO bok_forfatter VALUES(1, 1);
INSERT INTO bok_forfatter VALUES(2, 2);
INSERT INTO bok_forfatter VALUES(3, 6);
INSERT INTO bok_forfatter VALUES(4, 5);
INSERT INTO bok_forfatter VALUES(5, 5);
INSERT INTO bok_forfatter VALUES(6, 4);
INSERT INTO bok_forfatter VALUES(7, 3);
INSERT INTO bok_forfatter VALUES(8, 2);
INSERT INTO bok_forfatter VALUES(9, 7);
INSERT INTO bok_forfatter VALUES(10, 7);
INSERT INTO bok_forfatter VALUES(11, 7);
INSERT INTO bok_forfatter VALUES(12, 1);
INSERT INTO bok_forfatter VALUES(13, 1);
INSERT INTO bok_forfatter VALUES(14, 8);
INSERT INTO bok_forfatter VALUES(15, 8);
INSERT INTO bok_forfatter VALUES(16, 8);
INSERT INTO bok_forfatter VALUES(17, 8);
INSERT INTO bok_forfatter VALUES(18, 8);
INSERT INTO bok_forfatter VALUES(19, 8);
INSERT INTO bok_forfatter VALUES(20, 9);
INSERT INTO bok_forfatter VALUES(21, 10);
INSERT INTO bok_forfatter VALUES(22, 10);
INSERT INTO bok_forfatter VALUES(23, 11);
INSERT INTO bok_forfatter VALUES(24, 12);
INSERT INTO bok_forfatter VALUES(25, 12);

INSERT INTO konsulent VALUES(DEFAULT, 'Anne', 'Hansen', 'anne.hansen@xxx.com');
INSERT INTO konsulent VALUES(DEFAULT, 'Bjørn', 'Jensen', 'bjornj@yyy.com');
INSERT INTO konsulent VALUES(DEFAULT, 'Anne', 'Ås', 'anne.as@zzz.com');


-- 2a) Sett opp en SELECT-setning som er UNION mellom alle forlag med Oslo-nummer (telefonnummer begynner med 2)
-- og alle som ikke er Oslo-nummer. Får du med forlaget med NULL-verdi på telefonnummer?
-- Hvis ikke, utvid unionen med en mengde til.

SELECT * FROM forlag WHERE telefon LIKE '2%'
UNION
SELECT * FROM forlag WHERE telefon NOT LIKE '2%'
UNION
SELECT * FROM forlag WHERE telefon IS NULL;

-- 2b) Sett opp SQL-setninger som finner gjennomsnittlig alder på forfattere der fødselsåret er oppgitt.
 -- For forfattere der dødsåret ikke er oppgitt, skal du kun ta med de som er født etter 1900.
SELECT SUM(IF(dod_aar IS NULL, 2019, dod_aar) - fode_aar) / count(*)
FROM forfatter
WHERE fode_aar IS NOT NULL
AND IF(dod_aar IS NULL, fode_aar > 1900, dod_aar);

-- 2c) Sett opp SQL-setninger som finner hvor stor andel av forfatterne som ble med i beregningene under b).
SELECT count(*) / (SELECT count(*) FROM forfatter)
FROM forfatter WHERE fode_aar IS NOT NULL
AND IF(dod_aar IS NULL, fode_aar > 1900, dod_aar);







