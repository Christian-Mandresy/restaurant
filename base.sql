create sequence seqProduit start with 1 increment by 1;

create sequence seqTypeProduit start with 1 increment by 1;

create sequence seqComposant start with 1 increment by 1;

create sequence seqComposantProduit start with 1 increment by 1;

create sequence seqTable start with 1 increment by 1;

create sequence seqTypeTable start with 1 increment by 1;

create sequence seqIngredient start with 1 increment by 1;

create sequence seqIngredientComposant start with 1 increment by 1;

create sequence seqStockProduit start with 1 increment by 1;

create sequence seqStockIngredient start with 1 increment by 1;

create sequence seqEnregistrementProduit start with 1 increment by 1;

create sequence seqEnregistrementIngredient start with 1 increment by 1;

create sequence seqCommande start with 1 increment by 1;

create sequence seqCaisse start with 1 increment by 1;

create sequence seqEntreeCaisse start with 1 increment by 1;

create sequence seqSortieCaisse start with 1 increment by 1;

create sequence seqPaiementCommande start with 1 increment by 1;

create sequence seqDetailsCommande start with 1 increment by 1;

create sequence seqRevient start with 1 increment by 1;

create sequence seqServeur start with 1 increment by 1;

create sequence seqCommandeServeur start with 1 increment by 1;

create sequence seqTypeUtilisateur start with 1 increment by 1;

create sequence seqUtilisateur start with 1 increment by 1;

create sequence seqLivraison start with 1 increment by 1;

create sequence seqFini start with 1 increment by 1;

create table TypeProduit(
    id VARCHAR(10) primary key,
    nom VARCHAR(50),
    priorite int
);

create table Produit(
    id varchar(10) primary key,
    typeProduit varchar(10),
    nom varchar(50),
    prix float,
    fini int
);

create table Composant(
    id varchar(10) primary key,
    nom varchar(50),
    quantite float
);

create table ComposantProduit(
    id varchar(10) primary key,
    produit varchar(10),
    composant varchar(10),
    quantite int
);

create table TypeTable (
    id varchar(10) primary key,
    nom varchar(50)
);

create table TableRestau (
    id varchar(10) primary key,
    typeTable varchar(10),
    nom varchar(50),
    occupe int
);

create table Ingredient (
    id varchar(10) primary key,
    nom varchar(50)
);

create table IngredientComposant (
    id varchar(10) primary key,
    composant varchar(10),
    ingredient varchar(10),
    quantite int
);

create table StockProduit (
    id varchar(10) primary key,
    produit varchar(10),
    quantite float,
    dateEntre date,
    prix int
);

create table StockIngredient (
    id varchar(10) primary key,
    ingredient varchar(10),
    quantite float,
    dateEntre date,
    prix int
);

create table EnregistrementProduit(
    id varchar(10) primary key,
    produit varchar(10),
    quantite float,
    prix float,
    dateEnregistrement date
);

create table EnregistrementIngredient(
    id varchar(10) primary key,
    ingredient varchar(10),
    quantite float,
    prix float,
    dateEnregistrement date
);

CREATE TABLE Commande(
    id VARCHAR(10) primary key,
    tableRestau VARCHAR(10),
    dateCommande TimeStamp
);

CREATE TABLE Caisse(
    id VARCHAR(10) primary key,
    nom VARCHAR(50)
);

CREATE TABLE EntreeCaisse(
    id VARCHAR(10) primary key,
    caisse VARCHAR(10),
    dateEntre TimeStamp,
    montant float,
    motif text
);

CREATE TABLE SortieCaisse(
    id VARCHAR(10) primary key,
    caisse VARCHAR(10), 
    dateEntre TimeStamp,
    montant float,
    motif text
);

create table PaiementCommande(
    id varchar(10) primary key,
    nomClient varchar(50),
    tableRestau varchar(10),
    commande varchar(10),
    datePaiement TimeStamp,
    montant float
);

create table TypeUtilisateur(
    id varchar(10) primary key,
    nom varchar(20)
);

create table Utilisateur(
    id varchar(10) primary key,
    typeUtilisateur varchar(10),
    nom varchar(50),
    email varchar(50),
    mdp varchar(100)
);

-- CREATE TABLE Reservation(
--     id VARCHAR(10) primary key,
--     tablee VARCHAR(10) FOREIGN key REFERENCES Tablee(id),
--     datee datetime
-- );

create table DetailsCommande(
    id varchar(10) primary key,
    commande VARCHAR(10),
    produit VARCHAR(10),
    quantite int,
    prix float,
    depense float
);

create table Revient(
    id varchar(10) primary key,
    prixMin float,
    prixMax float,
    pourcentage float
);

create table Serveur(
    id varchar(10) primary key,
    utilisateur varchar(10),
    nom varchar(50)
);

create table CommandeServeur(
    id varchar(10) primary key,
    idServeur varchar(10),
    idCommande varchar(10)
);

create table Fini(
    id varchar(10) primary key,
    produit varchar(10),
    detailsCommande varchar(10),
    quantite int
);

/* commande detailsCommande fini */
/* commande Produit quantite */

create table Livraison(
    id varchar(10) primary key,
    utilisateur varchar(10),
    fini varchar(10),
    dateLivraison TimeStamp,
    addresse varchar(50),
    surPlace int
);

create view TypeEtProduit as
select
    tp.id as idTypeProduit,
    p.id as idProduit,
    tp.nom as nomTypeProduit,
    tp.priorite,
    p.nom as nomProduit,
    p.prix,
    p.fini
from
    TypeProduit as tp full
    join Produit as p on p.typeProduit = tp.id;

create view ComposantIngredient as
select
    ic.id as idComposantIngredient,
    c.id as idComposant,
    c.nom as nomComposant,
    i.id as idIngredient,
    i.nom as nomIngredient,
    ic.quantite
from
    IngredientComposant as ic
    full join Composant as c on ic.composant = c.id
    full join Ingredient as i on ic.ingredient = i.id;

create view QuantiteProduit as
select
    sp.produit,
    p.nom,
    sum(sp.quantite) as sommeQuantite
from
    StockProduit as sp full join 
    Produit as p 
on
    sp.produit=p.id
group by
    sp.produit,p.nom;

create view QuantiteIngredient as
select
    si.ingredient,
    i.nom,
    si.prix,
    sum(quantite) as sommeQuantite
from
    Stockingredient as si full join
    Ingredient as i on si.ingredient=i.id
group by
    si.ingredient,i.nom,si.prix;

create view EnregistrementProduitUtilise as
select
    ep.produit,
    p.nom,
    sum(ep.quantite) as sommeQuantite
from
    EnregistrementProduit as ep full join
    Produit as p on ep.produit=p.id
group by
    ep.produit,p.nom;

create view EnregistrementIngredientUtilise as
select
    ei.ingredient,
    i.nom,
    sum(ei.quantite) as sommeQuantite
from
    EnregistrementIngredient as ei full join
    Ingredient as i on ei.ingredient=i.id
group by
    ingredient,i.nom;

create view qteIngredientProduit as
select 
    p.id as id,
    p.nom as produit, 
    i.nom as ingredient, 
    ((cp.quantite*ig.quantite)/(cp.quantite)) as quantite,
    (((cp.quantite*ig.quantite)/(cp.quantite))*(select prix from stockIngredient where dateentre=(select max(dateentre) from stockingredient where ingredient=i.id) and ingredient=i.id)/1000) as prix 
from 
    produit p 
    join composantProduit cp on cp.produit=p.id 
    join ingredientComposant ig on ig.composant=cp.composant 
    join composant c on c.id=cp.composant 
    join ingredient i on i.id=ig.ingredient 
    join stockIngredient si on si.ingredient=i.id;

create view pourboire as
select
	s.id idServeur,
	s.nom Serveur,
	dc.commande Commande,
	sum(dc.prix) as Prix,
	c.dateCommande
from
	CommandeServeur as cs join Commande as c on cs.idCommande=c.id join
    Serveur as s on cs.idServeur=s.id join
	DetailsCommande as dc on dc.Commande=c.id
group by
	s.id,Serveur,c.dateCommande,dc.commande;

create view pourboireDetail as
select
	s.id idServeur,
	s.nom Serveur,
	dc.commande Commande,
	p.nom Produit,
	dc.prix as Prix,
	c.dateCommande
from
	CommandeServeur as cs join Commande as c on cs.idCommande=c.id join
    Serveur as s on cs.idServeur=s.id join
	DetailsCommande as dc on dc.Commande=c.id join
	Produit as p on dc.produit=p.id
group by
	s.id,Serveur,c.dateCommande,p.nom,dc.commande,dc.prix;

create view commandeComplet as
select 
    dc.*,
    c.datecommande,
    c.tableRestau,
    cs.idserveur as serveur,
    (select nom from produit where id=dc.produit) as nomproduit 
from 
    detailscommande dc 
    join commande c on c.id=dc.commande
    join commandeserveur cs on cs.idcommande=c.id;

create view produitNonFini as
select
    c.id Commande,
    dc.commande DetailsCommande,
    p.id Produit,
    p.nom,
    (dc.quantite-f.quantite) quantite
from
    detailsCommande as dc join commande as c on dc.commande=c.id
    join produit as p on dc.produit=p.id
    join fini as f on dc.id=f.detailsCommande
where
	dc.quantite-f.quantite>0;

create view finiProduit as
select
    f.id,
    f.produit,
    p.nom,
    f.detailsCommande,
    f.quantite
from
    fini as f join produit as p on f.produit=p.id;

/*select 
    qip.id,
    qip.produit quantiteIngredient,
    qip.ingredient,
    sum(qip.quantite) quantite,
    sum(qip.prix) prix 
from 
    qteIngredientProduit as qip
group by 
    qip.id,qip.produit,qip.ingredient;

select 
	dc.commande,
	dc.produit,
	c.dateCommande,
	qip.ingredient,
	sum(qip.quantite) quantite,
	sum(qip.prix) prix 
from 
	detailsCommande as dc inner join Commande as c on dc.commande=c.id
	inner join Produit as p on p.id=dc.produit
	inner join qteIngredientProduit as qip on qip.produit=p.id
group by
	dc.commande,dc.produit,c.dateCommande,qip.ingredient;*/

select
    '1' as id,
    ei.ingredient,
	i.nom,
    sum(ei.quantite) quantite,
    avg(ei.prix) prix,
    (select '2020/01/01') dateEnregistrement 
from 
    EnregistrementIngredient as ei join Ingredient as i on i.id=ei.ingredient
where 
    ei.dateEnregistrement 
between '2020/01/01' and '2022/01/31' 
    group by i.nom,ei.ingredient order by quantite,prix;

alter table Produit add foreign key(typeProduit) references TypeProduit(id);
    
alter table ComposantProduit add foreign key(produit) references Produit(id);

alter table ComposantProduit add foreign key(composant) references Composant(id);
    
alter table TableRestau add foreign key(typeTable) references TypeTable(id);

alter table IngredientComposant add foreign key(composant) references Composant(id);

alter table IngredientComposant add foreign key(ingredient) references Ingredient(id);

alter table IngredientComposant add foreign key(composant) references Composant(id);

alter table IngredientComposant add foreign key(ingredient) references Ingredient(id);

alter table StockProduit add foreign key(produit) references Produit(id);

alter table StockIngredient add foreign key(ingredient) references Ingredient(id);

alter table EnregistrementProduit add foreign key(produit) references Produit(id);

alter table EnregistrementIngredient add foreign key(ingredient) references Ingredient(id);

alter table Commande add foreign key(tableRestau) REFERENCES TableRestau(id);

alter table EntreeCaisse add foreign key(caisse) references Caisse(id);

alter table SortieCaisse add foreign key(caisse) references Caisse(id);

alter table PaiementCommande add foreign key(tableRestau)references TableRestau(id);

alter table PaiementCommande add foreign key(commande) references Commande(id);

alter table DetailsCommande add foreign key(commande) references Commande(id);

alter table DetailsCommande add foreign key(produit) references  Produit(id);

alter table CommandeServeur add foreign key(idServeur) references Serveur(id);

alter table CommandeServeur add foreign key(idCommande) references Commande(id);

alter table Utilisateur add foreign key(typeUtilisateur) references TypeUtilisateur(id);

alter table Serveur add foreign key(utilisateur) references  Utilisateur(id);

alter table Livraison add foreign key(utilisateur) references Utilisateur(id);

alter table Livraison add foreign key(fini) references Fini(id);

alter table Fini add foreign key(produit) references Produit(id);

alter table Fini add foreign key(detailsCommande) references DetailsCommande(id);

insert into
    TypeProduit
values
    (nextval('seqTypeProduit'), 'Entree', 4),
    (nextval('seqTypeProduit'), 'Boisson', 3),
    (nextval('seqTypeProduit'), 'Resistance', 2),
    (nextval('seqTypeProduit'), 'Dessert', 1);

insert into
    Produit
values
    (nextVal('seqProduit'), '1', 'Salade et mortadelle', 1200, 0),
    (nextVal('seqProduit'), '1', 'Salade Cesar', 1000, 0),
    (nextVal('seqProduit'), '2', 'Vin Maroparasy', 2500, 1),
    (nextVal('seqProduit'), '2', 'Coca-Cola', 1500, 1),
    (nextVal('seqProduit'), '3', 'Nem Vegan', 4000, 0),
    (nextVal('seqProduit'), '3', 'Poulet aux frites', 3500, 0),
    (nextVal('seqProduit'), '3', 'Mine-sao speciale crevette', 3500, 0),
    (nextVal('seqProduit'), '4', 'Salade de fruits simple', 1500, 0),
    (nextVal('seqProduit'), '4', 'Salade de fruits special', 2500, 0);

insert into
    Composant
values
    (nextval('seqComposant'), 'Tige Nem',500),
    (nextval('seqComposant'), 'Poulet',500),
    (nextval('seqComposant'), 'Frite',500),
    (nextval('seqComposant'), 'Salade',500),
    (nextval('seqComposant'), 'Vinegrette',500),
    (nextval('seqComposant'), 'Mortadelle',500),
    (nextval('seqComposant'), 'Pate',500),
    (nextval('seqComposant'), 'Legume Saute',500),
    (nextval('seqComposant'), 'Crevette',500),
    (nextval('seqComposant'), 'Banane',500),
    (nextval('seqComposant'), 'Peche',500),
    (nextval('seqComposant'), 'Ananas',500),
    (nextval('seqComposant'), 'Mangue',500),
    (nextval('seqComposant'), 'Raisin',500),
    (nextval('seqComposant'), 'Fraise',500),
    (nextval('seqComposant'), 'Pasteque',500);

insert into
    ComposantProduit
values
    (nextval('seqComposantProduit'), '1', '4',150),
    (nextval('seqComposantProduit'), '1', '5',50),
    (nextval('seqComposantProduit'), '1', '6',200),
    (nextval('seqComposantProduit'), '2', '4',150),
    (nextval('seqComposantProduit'), '2', '5',50),
    (nextval('seqComposantProduit'), '5', '1',350),
    (nextval('seqComposantProduit'), '5', '4',100),
    (nextval('seqComposantProduit'), '5', '5',50),
    (nextval('seqComposantProduit'), '6', '2',350),
    (nextval('seqComposantProduit'), '6', '3',250),
    (nextval('seqComposantProduit'), '7', '6',300),
    (nextval('seqComposantProduit'), '7', '7',100),
    (nextval('seqComposantProduit'), '7', '8',150),
    (nextval('seqComposantProduit'), '7', '9',50),
    (nextval('seqComposantProduit'), '8', '10',100),
    (nextval('seqComposantProduit'), '8', '11',100),
    (nextval('seqComposantProduit'), '8', '12',100),
    (nextval('seqComposantProduit'), '8', '13',100),
    (nextval('seqComposantProduit'), '9', '13',100),
    (nextval('seqComposantProduit'), '9', '14',100),
    (nextval('seqComposantProduit'), '9', '15',100),
    (nextval('seqComposantProduit'), '9', '16',100);

insert into TypeTable values(nextVal('seqTypeTable'),'Longue'),
(nextVal('seqTypeTable'),'Simple');

insert into TableRestau values(nextVal('seqTable'),'1','Table 1',1),
(nextVal('seqTable'),'1','Table 2',0),
(nextVal('seqTable'),'1','Table 3',1),
(nextVal('seqTable'),'2','Table 4',0),
(nextVal('seqTable'),'2','Table 5',1),
(nextVal('seqTable'),'2','Table 6',0),
(nextVal('seqTable'),'1','Table 7',1),
(nextVal('seqTable'),'1','Table 8',0),
(nextVal('seqTable'),'2','Table 9',0),
(nextVal('seqTable'),'1','Table 10',0);

insert into Ingredient values(nextVal('seqIngredient'),'Huile'),
(nextVal('seqIngredient'),'Sel fin'),
(nextVal('seqIngredient'),'Gros sel'),
(nextVal('seqIngredient'),'Sucre'),
(nextVal('seqIngredient'),'Sucre glace'),
(nextVal('seqIngredient'),'Poivre'),
(nextVal('seqIngredient'),'Sauce'),
(nextVal('seqIngredient'),'Vinaigre'),
(nextVal('seqIngredient'),'Peau de nem'),
(nextVal('seqIngredient'),'Ail'),
(nextVal('seqIngredient'),'Tomates'),
(nextVal('seqIngredient'),'Oignon'),
(nextVal('seqIngredient'),'Oeuf'),
(nextVal('seqIngredient'),'Salade'),
(nextVal('seqIngredient'),'Moutarde'),
(nextVal('seqIngredient'),'Viande hache'),
(nextVal('seqIngredient'),'Viande poulet'),
(nextVal('seqIngredient'),'Pates'),
(nextVal('seqIngredient'),'Pates blanche'),
(nextVal('seqIngredient'),'Mortadelle'),
(nextVal('seqIngredient'),'Carotte'),
(nextVal('seqIngredient'),'Haricot vert'),
(nextVal('seqIngredient'),'Pomme de terre'),
(nextVal('seqIngredient'),'Banane'),
(nextVal('seqIngredient'),'Peche'),
(nextVal('seqIngredient'),'Ananas'),
(nextVal('seqIngredient'),'Mangue'),
(nextVal('seqIngredient'),'Raisin'),
(nextVal('seqIngredient'),'Fraise'),
(nextVal('seqIngredient'),'Pasteque'),
(nextVal('seqIngredient'),'Crevette');

insert into IngredientComposant values(nextVal('seqIngredientComposant'),'1','9',80),
(nextVal('seqIngredientComposant'),'1','16',350),
(nextVal('seqIngredientComposant'),'1','1',100),
(nextVal('seqIngredientComposant'),'1','2',10),
(nextVal('seqIngredientComposant'),'1','6',10),
(nextVal('seqIngredientComposant'),'1','10',10),
(nextVal('seqIngredientComposant'),'1','19',100),
(nextVal('seqIngredientComposant'),'2','1',100),
(nextVal('seqIngredientComposant'),'2','2',10),
(nextVal('seqIngredientComposant'),'2','6',10),
(nextVal('seqIngredientComposant'),'2','10',10),
(nextVal('seqIngredientComposant'),'2','11',50),
(nextVal('seqIngredientComposant'),'2','12',20),
(nextVal('seqIngredientComposant'),'2','17',300),
(nextVal('seqIngredientComposant'),'3','1',100),
(nextVal('seqIngredientComposant'),'3','2',10),
(nextVal('seqIngredientComposant'),'3','10',10),
(nextVal('seqIngredientComposant'),'3','23',600),
(nextVal('seqIngredientComposant'),'4','14',300),
(nextVal('seqIngredientComposant'),'5','1',30),
(nextVal('seqIngredientComposant'),'5','2',15),
(nextVal('seqIngredientComposant'),'5','4',30),
(nextVal('seqIngredientComposant'),'5','8',150),
(nextVal('seqIngredientComposant'),'5','11',100),
(nextVal('seqIngredientComposant'),'5','12',50),
(nextVal('seqIngredientComposant'),'6','20',200),
(nextVal('seqIngredientComposant'),'7','18',450),
(nextVal('seqIngredientComposant'),'8','1',30),
(nextVal('seqIngredientComposant'),'8','2',10),
(nextVal('seqIngredientComposant'),'8','6',10),
(nextVal('seqIngredientComposant'),'8','7',20),
(nextVal('seqIngredientComposant'),'8','10',10),
(nextVal('seqIngredientComposant'),'8','21',200),
(nextVal('seqIngredientComposant'),'8','22',200),
(nextVal('seqIngredientComposant'),'9','1',30),
(nextVal('seqIngredientComposant'),'9','2',10),
(nextVal('seqIngredientComposant'),'9','6',10),
(nextVal('seqIngredientComposant'),'9','31',150),
(nextVal('seqIngredientComposant'),'10','24',100),
(nextVal('seqIngredientComposant'),'11','25',100),
(nextVal('seqIngredientComposant'),'12','26',100),
(nextVal('seqIngredientComposant'),'13','27',100),
(nextVal('seqIngredientComposant'),'14','28',100),
(nextVal('seqIngredientComposant'),'15','29',100),
(nextVal('seqIngredientComposant'),'16','30',100),
(nextVal('seqIngredientComposant'),'10','5',100),
(nextVal('seqIngredientComposant'),'11','5',100),
(nextVal('seqIngredientComposant'),'12','5',100),
(nextVal('seqIngredientComposant'),'13','5',100),
(nextVal('seqIngredientComposant'),'14','5',100),
(nextVal('seqIngredientComposant'),'15','5',100),
(nextVal('seqIngredientComposant'),'16','5',100);

insert into StockProduit values(nextVal('seqStockProduit'),'1',10,'2022/03/22',1200),
(nextVal('seqStockProduit'),'2',15,'2022/03/22',1000),
(nextVal('seqStockProduit'),'3',20,'2022/02/21',2500),
(nextVal('seqStockProduit'),'4',12,'2022/03/02',1500),
(nextVal('seqStockProduit'),'5',20,'2022/03/24',4000),
(nextVal('seqStockProduit'),'6',10,'2022/01/12',3500),
(nextVal('seqStockProduit'),'7',7,'2022/02/25',3500),
(nextVal('seqStockProduit'),'8',8,'2022/02/12',1500),
(nextVal('seqStockProduit'),'9',6,'2022/01/22',2500);

insert into StockIngredient values(nextVal('seqStockIngredient'),'1',20,'2022/03/1',3500),
(nextVal('seqStockIngredient'),'2',25,'2022/03/10',3000),
(nextVal('seqStockIngredient'),'3',23,'2022/03/12',2500),
(nextVal('seqStockIngredient'),'4',24,'2022/03/21',1500),
(nextVal('seqStockIngredient'),'5',25,'2022/03/23',3000),
(nextVal('seqStockIngredient'),'6',20,'2022/03/23',2000),
(nextVal('seqStockIngredient'),'7',21,'2022/03/12',1500),
(nextVal('seqStockIngredient'),'8',23,'2022/03/12',1000),
(nextVal('seqStockIngredient'),'9',25,'2022/03/14',2000),
(nextVal('seqStockIngredient'),'10',10,'2022/03/15',1500),
(nextVal('seqStockIngredient'),'11',15,'2022/03/17',3500),
(nextVal('seqStockIngredient'),'12',23,'2022/03/19',2500),
(nextVal('seqStockIngredient'),'13',7,'2022/03/11',3000),
(nextVal('seqStockIngredient'),'14',15,'2022/03/21',1500),
(nextVal('seqStockIngredient'),'15',20,'2022/03/21',2000),
(nextVal('seqStockIngredient'),'16',10,'2022/03/11',1000),
(nextVal('seqStockIngredient'),'17',15,'2022/03/15',2300),
(nextVal('seqStockIngredient'),'18',16,'2022/03/17',1500),
(nextVal('seqStockIngredient'),'19',17,'2022/03/16',5000),
(nextVal('seqStockIngredient'),'20',12,'2022/03/18',1200),
(nextVal('seqStockIngredient'),'21',28,'2022/03/17',2600),
(nextVal('seqStockIngredient'),'22',29,'2022/03/15',2500),
(nextVal('seqStockIngredient'),'23',13,'2022/03/19',1500),
(nextVal('seqStockIngredient'),'24',15,'2022/03/14',3200),
(nextVal('seqStockIngredient'),'25',14,'2022/03/21',3500),
(nextVal('seqStockIngredient'),'26',16,'2022/03/23',3000),
(nextVal('seqStockIngredient'),'27',18,'2022/03/22',1200),
(nextVal('seqStockIngredient'),'28',17,'2022/03/23',1500),
(nextVal('seqStockIngredient'),'29',18,'2022/03/22',2300),
(nextVal('seqStockIngredient'),'30',19,'2022/03/19',1500),
(nextVal('seqStockIngredient'),'31',20,'2022/03/20',1500);

insert into EnregistrementProduit values(nextVal('seqEnregistrementProduit'),'1',10,2000,'2022/01/23'),
(nextVal('seqEnregistrementProduit'),'2',15,2000,'2022/01/23'),
(nextVal('seqEnregistrementProduit'),'3',20,2000,'2022/01/23'),
(nextVal('seqEnregistrementProduit'),'4',12,2000,'2022/01/23'),
(nextVal('seqEnregistrementProduit'),'5',20,2000,'2022/01/23'),
(nextVal('seqEnregistrementProduit'),'6',10,2000,'2022/01/23'),
(nextVal('seqEnregistrementProduit'),'7',7,2000,'2022/01/23'),
(nextVal('seqEnregistrementProduit'),'8',8,2000,'2022/01/23'),
(nextVal('seqEnregistrementProduit'),'9',6,2000,'2022/01/23');

insert into EnregistrementIngredient values(nextVal('seqEnregistrementIngredient'),'1',200,1500,'2021/10/15'),
(nextVal('seqEnregistrementIngredient'),'2',250,2000,'2021/10/30'),
(nextVal('seqEnregistrementIngredient'),'3',230,3000,'2021/1/30'),
(nextVal('seqEnregistrementIngredient'),'4',240,3000,'2021/2/20'),
(nextVal('seqEnregistrementIngredient'),'5',250,2000,'2021/3/30'),
(nextVal('seqEnregistrementIngredient'),'6',200,2000,'2021/2/20'),
(nextVal('seqEnregistrementIngredient'),'7',210,1500,'2021/5/30'),
(nextVal('seqEnregistrementIngredient'),'8',230,2500,'2021/7/30'),
(nextVal('seqEnregistrementIngredient'),'9',250,3000,'2021/8/30'),
(nextVal('seqEnregistrementIngredient'),'10',100,5000,'2021/5/30'),
(nextVal('seqEnregistrementIngredient'),'11',150,1000,'2021/2/20'),
(nextVal('seqEnregistrementIngredient'),'12',230,5000,'2021/6/30'),
(nextVal('seqEnregistrementIngredient'),'13',70,1600,'2021/8/30'),
(nextVal('seqEnregistrementIngredient'),'14',150,1000,'2021/8/30'),
(nextVal('seqEnregistrementIngredient'),'15',200,4000,'2021/2/20'),
(nextVal('seqEnregistrementIngredient'),'16',100,5000,'2021/4/30'),
(nextVal('seqEnregistrementIngredient'),'17',150,2000,'2021/4/30'),
(nextVal('seqEnregistrementIngredient'),'18',160,3000,'2021/5/30'),
(nextVal('seqEnregistrementIngredient'),'19',170,4000,'2021/6/30'),
(nextVal('seqEnregistrementIngredient'),'20',120,15000,'2021/7/30'),
(nextVal('seqEnregistrementIngredient'),'21',280,1400,'2021/8/30'),
(nextVal('seqEnregistrementIngredient'),'22',290,1100,'2021/9/30'),
(nextVal('seqEnregistrementIngredient'),'23',130,1600,'2021/10/30'),
(nextVal('seqEnregistrementIngredient'),'24',150,1000,'2021/10/30'),
(nextVal('seqEnregistrementIngredient'),'25',140,1500,'2021/11/30'),
(nextVal('seqEnregistrementIngredient'),'26',160,1400,'2021/11/30'),
(nextVal('seqEnregistrementIngredient'),'27',180,1200,'2021/2/20'),
(nextVal('seqEnregistrementIngredient'),'28',170,1300,'2021/2/20'),
(nextVal('seqEnregistrementIngredient'),'29',180,1500,'2021/1/30'),
(nextVal('seqEnregistrementIngredient'),'30',190,1000,'2021/1/30'),
(nextVal('seqEnregistrementIngredient'),'31',200,1500,'2021/3/30'),
(nextVal('seqEnregistrementIngredient'),'1',200,1500,'2022/6/30'),
(nextVal('seqEnregistrementIngredient'),'2',250,1600,'2022/6/30'),
(nextVal('seqEnregistrementIngredient'),'3',230,1700,'2022/6/30'),
(nextVal('seqEnregistrementIngredient'),'4',240,1700,'2022/8/30'),
(nextVal('seqEnregistrementIngredient'),'5',250,1800,'2022/9/30'),
(nextVal('seqEnregistrementIngredient'),'6',200,1800,'2022/8/30'),
(nextVal('seqEnregistrementIngredient'),'7',210,1900,'2022/5/30'),
(nextVal('seqEnregistrementIngredient'),'8',230,1900,'2022/5/30'),
(nextVal('seqEnregistrementIngredient'),'9',250,2500,'2022/4/30'),
(nextVal('seqEnregistrementIngredient'),'10',100,2500,'2022/4/30'),
(nextVal('seqEnregistrementIngredient'),'11',150,3000,'2022/4/30'),
(nextVal('seqEnregistrementIngredient'),'12',230,3000,'2022/5/30'),
(nextVal('seqEnregistrementIngredient'),'13',70,4000,'2022/6/30'),
(nextVal('seqEnregistrementIngredient'),'14',150,4000,'2022/10/30'),
(nextVal('seqEnregistrementIngredient'),'15',200,500,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'16',100,500,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'17',150,600,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'18',160,600,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'19',170,1200,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'20',120,1500,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'21',280,1500,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'22',290,1200,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'23',130,1300,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'24',150,1400,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'25',140,2000,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'26',160,2000,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'27',180,2000,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'28',170,2000,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'29',180,5000,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'30',190,5000,'2022/01/30'),
(nextVal('seqEnregistrementIngredient'),'31',200,6000,'2022/01/30');

insert into Commande values(nextVal('seqCommande'),'1','2022/03/24 10:10:10'),
(nextVal('seqCommande'),'2','2022/03/24 15:10:30'),
(nextVal('seqCommande'),'3','2022/03/24 16:01:01'),
(nextVal('seqCommande'),'4','2022/03/24 17:12:12'),
(nextVal('seqCommande'),'5','2022/03/24 08:30:30'),
(nextVal('seqCommande'),'6','2022/03/24 17:17:17'),
(nextVal('seqCommande'),'7','2022/03/24 12:12:12');

insert into Caisse values(nextVal('seqCaisse'),'Caisse 1'),
(nextVal('seqCaisse'),'Caisse 2');

insert into EntreeCaisse values(nextVal('seqEntreeCaisse'),'1','2022/03/15 10:10:10',1500,'None'),
(nextVal('seqEntreeCaisse'),'1','2022/03/15 10:10:10',1500,'None'),
(nextVal('seqEntreeCaisse'),'2','2022/03/14 15:10:10',500,'None'),
(nextVal('seqEntreeCaisse'),'2','2022/05/12 15:30:10',2500,'None'),
(nextVal('seqEntreeCaisse'),'2','2022/04/11 10:50:10',500,'None'),
(nextVal('seqEntreeCaisse'),'2','2022/03/12 10:30:10',5500,'None'),
(nextVal('seqEntreeCaisse'),'1','2022/01/14 10:10:10',1500,'None'),
(nextVal('seqEntreeCaisse'),'1','2022/01/15 10:50:10',1800,'None'),
(nextVal('seqEntreeCaisse'),'1','2022/01/14 16:10:10',2500,'None'),
(nextVal('seqEntreeCaisse'),'1','2022/01/16 18:10:10',9500,'None'),
(nextVal('seqEntreeCaisse'),'1','2022/04/05 17:10:10',2500,'None'),
(nextVal('seqEntreeCaisse'),'1','2022/03/05 14:20:10',3500,'None'),
(nextVal('seqEntreeCaisse'),'1','2022/05/25 15:50:10',5500,'None'),
(nextVal('seqEntreeCaisse'),'2','2022/04/15 12:10:10',1500,'None');

insert into SortieCaisse values(nextVal('seqSortieCaisse'),'1','2021/10/12 12:12:12',1500,'None'),
(nextVal('seqSortieCaisse'),'1','2022/01/02 15:15:15',3000,'None'),
(nextVal('seqSortieCaisse'),'2','2022/02/12 10:10:10',2000,'None'),
(nextVal('seqSortieCaisse'),'1','2022/02/15 08:10:20',1500,'None'),
(nextVal('seqSortieCaisse'),'2','2022/03/14 15:10:10',1200,'None'),
(nextVal('seqSortieCaisse'),'1','2022/01/16 17:00:00',300,'None'),
(nextVal('seqSortieCaisse'),'1','2022/02/20 08:00:00',1600,'None'),
(nextVal('seqSortieCaisse'),'2','2022/03/15 12:10:00',1400,'None'),
(nextVal('seqSortieCaisse'),'2','2022/02/14 07:00:00',1200,'None'),
(nextVal('seqSortieCaisse'),'1','2022/01/12 10:00:00',300,'None'),
(nextVal('seqSortieCaisse'),'1','2022/02/03 17:30:00',500,'None');

insert into DetailsCommande values(nextVal('seqDetailsCommande'),'1','5',3,4000,500),
(nextVal('seqDetailsCommande'),'1','3',1,2500,200),
(nextVal('seqDetailsCommande'),'1','1',2,1200,500),
(nextVal('seqDetailsCommande'),'1','8',1,1500,500),
(nextVal('seqDetailsCommande'),'2','6',1,3500,500),
(nextVal('seqDetailsCommande'),'3','2',1,1000,500),
(nextVal('seqDetailsCommande'),'3','7',1,3500,500),
(nextVal('seqDetailsCommande'),'4','5',1,4000,500),
(nextVal('seqDetailsCommande'),'4','8',3,1500,500),
(nextVal('seqDetailsCommande'),'4','9',1,2500,500),
(nextVal('seqDetailsCommande'),'5','6',1,3500,500),
(nextVal('seqDetailsCommande'),'6','7',2,3500,500),
(nextVal('seqDetailsCommande'),'6','1',1,1200,500),
(nextVal('seqDetailsCommande'),'7','6',1,3500,500);

insert into PaiementCommande values(nextVal('seqPaiementCommande'),'Jean','1','1','2022/03/27 17:30:00',10000),
(nextVal('seqPaiementCommande'),'Anthoine','2','2','2022/03/27 10:10:10',3500),
(nextVal('seqPaiementCommande'),'Theo','3','3','2022/03/27 08:15:30',4500),
(nextVal('seqPaiementCommande'),'Paul','4','4','2022/03/27 15:10:30',8000),
(nextVal('seqPaiementCommande'),'Nick','5','5','2022/03/27 12:00:00',3500),
(nextVal('seqPaiementCommande'),'Andrew','6','6','2022/03/27 17:00:00',4000),
(nextVal('seqPaiementCommande'),'Tom','7','7','2022/03/27 16:30:00',3500);

insert into TypeUtilisateur values(nextVal('seqTypeUtilisateur'),'Serveur'),
(nextVal('seqTypeUtilisateur'),'Cuisinier'),
(nextVal('seqTypeUtilisateur'),'Caissier'),
(nextVal('seqTypeUtilisateur'),'Livreur');

insert into Utilisateur values(nextVal('seqUtilisateur'),'1','Pierre','pierre@gmail.com','1234'),
(nextVal('seqUtilisateur'),'1','Paul','paul@gmail.com','2345'),
(nextVal('seqUtilisateur'),'1','Jean','jean@gmail.com','3456'),
(nextVal('seqUtilisateur'),'1','Jeane','jeane@gmail.com','4567'),
(nextVal('seqUtilisateur'),'1','Mat','mat@gmail.com','7890'),
(nextVal('seqUtilisateur'),'2','Jack','jack@gmail.com','9876'),
(nextVal('seqUtilisateur'),'2','Anne','anne@gmail.com','8765'),
(nextVal('seqUtilisateur'),'2','Larry','larry@gmail.com','7654'),
(nextVal('seqUtilisateur'),'3','Marrie','marrie@gmail.com','6543'),
(nextVal('seqUtilisateur'),'3','Garry','garry@gmail.com','3210'),
(nextVal('seqUtilisateur'),'4','Harry','harry@gmail.com','0246'),
(nextVal('seqUtilisateur'),'4','Barry','barry@gmail.com','2468'),
(nextVal('seqUtilisateur'),'4','Steeve','steeve@gmail.com','4680'),
(nextVal('seqUtilisateur'),'4','Christian','christian@gmail.com','1357'),
(nextVal('seqUtilisateur'),'4','Teddy','teddy@gmail.com','3579');

insert into Serveur values(nextVal('seqServeur'),'1','Serveur 1'),
(nextVal('seqServeur'),'2','Serveur 2'),
(nextVal('seqServeur'),'3','Serveur 3'),
(nextVal('seqServeur'),'4','Serveur 4'),
(nextVal('seqServeur'),'5','Serveur 5');

insert into CommandeServeur values(nextVal('seqCommandeServeur'),'1','1'),
(nextVal('seqCommandeServeur'),'1','2'),
(nextVal('seqCommandeServeur'),'2','3'),
(nextVal('seqCommandeServeur'),'3','4'),
(nextVal('seqCommandeServeur'),'4','5'),
(nextVal('seqCommandeServeur'),'4','6'),
(nextVal('seqCommandeServeur'),'5','7');

insert into Fini values(nextVal('seqFini'),'5','1',2),
(nextVal('seqFini'),'3','2',1),
(nextVal('seqFini'),'1','3',1),
(nextVal('seqFini'),'8','4',1),
(nextVal('seqFini'),'6','5',1),
(nextVal('seqFini'),'2','6',1),
(nextVal('seqFini'),'7','7',1),
(nextVal('seqFini'),'5','8',1),
(nextVal('seqFini'),'8','9',2),
(nextVal('seqFini'),'9','10',1),
(nextVal('seqFini'),'6','11',1),
(nextVal('seqFini'),'7','12',2),
(nextVal('seqFini'),'1','13',1),
(nextVal('seqFini'),'6','14',1);

insert into Livraison values(nextVal('seqLivraison'),'11','1','2022/04/04 11:30:00','111 N Ambohidratrimo',1),
(nextVal('seqLivraison'),'11','2','2022/04/04 11:30:00','222 N Andoharanofotsy',1),
(nextVal('seqLivraison'),'11','3','2022/04/04 11:30:00','333 N Analakely',1),
(nextVal('seqLivraison'),'11','4','2022/04/04 11:30:00','444 N Ivato',1),
(nextVal('seqLivraison'),'1','5','2022/04/04 15:00:00',null,0),
(nextVal('seqLivraison'),'12','6','2022/04/04 15:00:00','123 N Tanjombato',1),
(nextVal('seqLivraison'),'12','7','2022/04/04 11:30:00','456 N Ivato',1),
(nextVal('seqLivraison'),'13','8','2022/04/04 11:30:00','789 N Ambohibao',1),
(nextVal('seqLivraison'),'13','9','2022/04/04 11:30:00','159 N Ambohidratrimo',1),
(nextVal('seqLivraison'),'13','10','2022/04/04 15:00:00','357 N Analakely',1),
(nextVal('seqLivraison'),'2','11','2022/04/04 15:00:00',null,0),
(nextVal('seqLivraison'),'3','12','2022/04/04 11:30:00',null,0),
(nextVal('seqLivraison'),'3','13','2022/04/04 15:00:00',null,0),
(nextVal('seqLivraison'),'4','14','2022/04/04 15:00:00',null,0);