/*source F:/File Kuliah/File Data Semester 5/TugasAkhir/DataMining/dbTA.sql*/
/*source F:\File Kuliah\File Data Semester 5\TugasAkhir\DataMining\dbTA.sql*/
DROP DATABASE IF EXISTS dbTA;
CREATE DATABASE dbTA;
USE dbTA;

CREATE TABLE tblItem
(
ItemStore VARCHAR(20) PRIMARY KEY
);

INSERT INTO tblItem VALUES
("Bread"),
("Bagel"),
("Cheese"),
("Diaper"),
("Eggs"),
("Meat"),
("Milk"),
("Pencil"),
("Wine");

CREATE TABLE tblDetailBeli
(
notransaksi VARCHAR(10),
ItemStore VARCHAR(20),
FOREIGN KEY(ItemStore) REFERENCES 
tblItem(ItemStore)
);

LOAD DATA LOCAL INFILE 'F:/File Kuliah/File Data Semester 5/TugasAkhir/DataMining/Data.csv' 
INTO TABLE tblDetailBeli 
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"' 
IGNORE 1 LINES;

SELECT * FROM tblDetailBeli;

/*Minimum support count*/
SET @msc=20;


/*=====================================Iterasi 1=====================================*/

SELECT Item1.ItemStore AS ITEMSET,
	(
	SELECT COUNT(*)
	FROM tblDetailBeli AS A
	WHERE A.ItemStore = Item1.ItemStore
	) AS FREKUENSI
FROM tblItem AS Item1
WHERE 
	(
	SELECT COUNT(*)
	FROM tblDetailBeli AS A
	WHERE A.ItemStore = Item1.ItemStore
	) >= @msc;


/*=====================================ITERASI 2=====================================*/
SELECT CONCAT(Item1.ItemStore, ',', 
Item2.ItemStore) AS ITEMSET,
	(
	SELECT COUNT(*)
	FROM tblDetailBeli AS tblA INNER JOIN
	tblDetailBeli AS tblB ON
	(tblA.notransaksi = tblB.notransaksi)
	WHERE tblA.ItemStore = Item1.ItemStore
	AND tblB.ItemStore = Item2.ItemStore
	) AS FREKUENSI
FROM tblItem AS item1,
tblItem AS item2
WHERE Item2.ItemStore > Item1.ItemStore
AND (
	SELECT COUNT(*)
	FROM tblDetailBeli AS tblA INNER JOIN
	tblDetailBeli AS tblB ON
	(tblA.notransaksi = tblB.notransaksi)
	WHERE tblA.ItemStore = Item1.ItemStore
	AND tblB.ItemStore = Item2.ItemStore
	) >= @msc;

/*=====================================ITERASI 3=====================================*/
SELECT CONCAT(Item1.ItemStore, ', ',
Item2.ItemStore, ', ',
Item3.ItemStore) AS ITEMSET,
	(
	SELECT COUNT(*)
	FROM tblDetailBeli AS tblA
	INNER JOIN tblDetailBeli AS tblB
	ON(tblA.notransaksi = tblB.notransaksi)
	INNER JOIN tblDetailBeli AS tblC 
	ON(tblA.notransaksi = tblC.notransaksi)
	WHERE tblA.ItemStore = Item1.ItemStore AND
	tblB.ItemStore = Item2.ItemStore AND
	tblC.ItemStore = Item3.ItemStore
	) AS FREKUENSI
FROM tblItem AS Item1,
tblItem AS Item2,
tblItem AS Item3
WHERE Item1.ItemStore > Item2.ItemStore
AND Item2.ItemStore > Item3.ItemStore
AND (
	SELECT COUNT(*)
	FROM tblDetailBeli AS tblA
	INNER JOIN tblDetailBeli AS tblB
	ON(tblA.notransaksi = tblB.notransaksi)
	INNER JOIN tblDetailBeli AS tblC 
	ON(tblA.notransaksi = tblC.notransaksi)
	WHERE tblA.ItemStore = Item1.ItemStore AND
	tblB.ItemStore = Item2.ItemStore AND
	tblC.ItemStore = Item3.ItemStore
	) >= @msc;

/*=====================================ITERASI 4=====================================*/
SELECT CONCAT(Item1.ItemStore, ', ',
Item2.ItemStore, ', ',
Item3.ItemStore,', ',
Item4.ItemStore) AS ITEMSET,
	(
	SELECT COUNT(*)
	FROM tblDetailBeli AS tblA
	INNER JOIN tblDetailBeli AS tblB
	ON(tblA.notransaksi = tblB.notransaksi)
	INNER JOIN tblDetailBeli AS tblC 
	ON(tblA.notransaksi = tblC.notransaksi)
    INNER JOIN tblDetailBeli AS tblD 
	ON(tblA.notransaksi = tblD.notransaksi)
	WHERE tblA.ItemStore = Item1.ItemStore AND
	tblB.ItemStore = Item2.ItemStore AND
	tblC.ItemStore = Item3.ItemStore AND
	tblD.ItemStore = Item4.ItemStore
	) AS FREKUENSI
FROM tblItem AS Item1,
tblItem AS Item2,
tblItem AS Item3,
tblItem AS Item4
WHERE Item2.ItemStore > Item1.ItemStore
AND Item3.ItemStore > Item2.ItemStore
AND Item4.ItemStore > Item3.ItemStore
AND (
	SELECT COUNT(*)
	FROM tblDetailBeli AS tblA
	INNER JOIN tblDetailBeli AS tblB
	ON(tblA.notransaksi = tblB.notransaksi)
	INNER JOIN tblDetailBeli AS tblC 
	ON(tblA.notransaksi = tblC.notransaksi)
    INNER JOIN tblDetailBeli AS tblD 
	ON(tblA.notransaksi = tblD.notransaksi)
	WHERE tblA.ItemStore = Item1.ItemStore AND
	tblB.ItemStore = Item2.ItemStore AND
	tblC.ItemStore = Item3.ItemStore AND
	tblD.ItemStore = Item4.ItemStore
	) >= @msc;

/*=====================================ITERASI 5=====================================*/
SELECT CONCAT(Item1.ItemStore, ', ',
Item2.ItemStore, ', ',
Item3.ItemStore,', ',
Item4.ItemStore,', ',
Item5.ItemStore) AS ITEMSET,
	(
	SELECT COUNT(*)
	FROM tblDetailBeli AS tblA
	INNER JOIN tblDetailBeli AS tblB
	ON(tblA.notransaksi = tblB.notransaksi)
	INNER JOIN tblDetailBeli AS tblC 
	ON(tblA.notransaksi = tblC.notransaksi)
	INNER JOIN tblDetailBeli AS tblD 
	ON(tblA.notransaksi = tblD.notransaksi)
	INNER JOIN tblDetailBeli AS tblE 
	ON(tblA.notransaksi = tblE.notransaksi)
	WHERE tblA.ItemStore = Item1.ItemStore AND
	tblB.ItemStore = Item2.ItemStore AND
	tblC.ItemStore = Item3.ItemStore AND
	tblD.ItemStore = Item4.ItemStore AND
	tblE.ItemStore = Item5.ItemStore
	) AS FREKUENSI
FROM tblItem AS Item1,
tblItem AS Item2,
tblItem AS Item3,
tblItem AS Item4,
tblItem AS Item5
WHERE Item2.ItemStore > Item1.ItemStore
AND Item3.ItemStore > Item2.ItemStore
AND Item4.ItemStore > Item3.ItemStore
AND Item5.ItemStore > Item4.ItemStore
AND (
	SELECT COUNT(*)
	FROM tblDetailBeli AS tblA
	INNER JOIN tblDetailBeli AS tblB
	ON(tblA.notransaksi = tblB.notransaksi)
	INNER JOIN tblDetailBeli AS tblC 
	ON(tblA.notransaksi = tblC.notransaksi)
    INNER JOIN tblDetailBeli AS tblD
	ON(tblA.notransaksi = tblD.notransaksi)
	INNER JOIN tblDetailBeli AS tblE
	ON(tblA.notransaksi = tblE.notransaksi)
	WHERE tblA.ItemStore = Item1.ItemStore 
    AND	tblB.ItemStore = Item2.ItemStore 
    AND	tblC.ItemStore = Item3.ItemStore
    AND tblD.ItemStore = Item4.ItemStore
	AND tblE.ItemStore = Item5.ItemStore
	) >= @msc;

