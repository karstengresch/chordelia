/* TOENE */
delete
from `ton` commit

select
*
from ton

select
count(*) tonvorrat
from ton 


/* AKKORDE */

select count(id) from Akkord a
select max(id) from Akkord a

select max(c.county) from (select count(position) county from ton_akkord group by akkord_id) c

select max(position) county from ton_akkord group by akkord_id

select max(position) from ton_akkord
select max(akkord_id) from ton_akkord
select max(position) from TON_AKKORD where AKKORD_ID = ( select max(akkord_id) from ton_akkord)
select min(position) from TON_AKKORD where AKKORD_ID = 28671511

select (max(position)+1) from TON_AKKORD where AKKORD_ID = ( select max(akkord_id) from ton_akkord)

SELECT
*
FROM Akkord akkord 
WHERE basis_akkord_id 
--= 3541
between 3112 and 4112 
ORDER BY akkord.id ASC

delete
from ton_akkord
where akkord_id >= 1112

delete
from `akkord`

select
*
FROM akkord
WHERE anzahlToene = 6
AND id > 601
ORDER BY klangschaerfe DESC

select
max(anzahlToene) anzahl
from akkord

select
max(id)
from akkord

select
min(id)
from akkord
where anzahlToene = 4

select
max(id)
from akkord
where anzahlToene = 4

select
(max(id) - min(id)) anzahl
from akkord
where anzahlToene = 4

select
count(distinct id)
from akkord
where anzahlToene = 4

select
max(id)
from akkord
where anzahlToene = 5

select
max(basis_akkord_id)
from akkord


select
max(anzahltoene)
from akkord


select
distinct max(position)+1 anzahl
from ton_akkord

select
max(c.county) anzahl
from (select count(position) county from ton_akkord group by akkord_id) c

select
*
from berechnungs_informationen
update berechnungs_informationen set bereits_berechnete_toene = 6
delete
from ton_akkord
where akkord_id in (select id from akkord where basis_akkord_id = 5)

select
*
from akkord
where akkord.id between 1
and 1000 





/* SQL script to grab the worst performing indexes in the whole server */

SELECT
t.TABLE_SCHEMA AS `db` ,
t.TABLE_NAME AS `table` ,
s.INDEX_NAME AS `inde name` ,
s.COLUMN_NAME AS `field name` ,
s.SEQ_IN_INDEX `seq in index` ,
s2.max_columns AS `# cols` ,
s.CARDINALITY AS `card` ,
t.TABLE_ROWS AS `est rows` ,
ROUND(((s.CARDINALITY / IFNULL(t.TABLE_ROWS, 0.01)) * 100), 2) AS `sel %`
FROM INFORMATION_SCHEMA.STATISTICS s
INNER JOIN INFORMATION_SCHEMA.TABLES t ON s.TABLE_SCHEMA = t.TABLE_SCHEMA
AND s.TABLE_NAME = t.TABLE_NAME
INNER JOIN
(
   SELECT
   TABLE_SCHEMA , TABLE_NAME , INDEX_NAME , MAX(SEQ_IN_INDEX) AS max_columns
   FROM INFORMATION_SCHEMA.STATISTICS
   WHERE TABLE_SCHEMA != 'mysql'
   GROUP BY TABLE_SCHEMA, TABLE_NAME, INDEX_NAME
)
AS s2 ON s.TABLE_SCHEMA = s2.TABLE_SCHEMA
AND s.TABLE_NAME = s2.TABLE_NAME
AND s.INDEX_NAME = s2.INDEX_NAME
WHERE t.TABLE_SCHEMA != 'mysql' /* Filter out the mysql system DB */
AND t.TABLE_ROWS > 10 /* Only tables with some rows */
AND s.CARDINALITY IS NOT NULL /* Need at least one non-NULL value in the field */
AND
(
   s.CARDINALITY / IFNULL(t.TABLE_ROWS, 0.01)
)
< 1.00 /* Selectivity < 1.0 b/c unique indexes are perfect anyway */
ORDER BY `sel %`,
s.TABLE_SCHEMA,
s.TABLE_NAME /* Switch to `sel %` DESC for best non-unique indexes */ LIMIT 10



select
anzahlToene , 
min(id) min_id ,
max(id) max_id , 
count(id)
from akkord
group by anzahlToene

select max(id) max_id from akkord

select
anzahlToene , count(id)
from akkord
group by anzahlToene
