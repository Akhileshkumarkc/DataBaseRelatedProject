SET TIMING ON
SELECT GLOBALRANK
	,TDLLRANK
FROM MAJESTIC_TABLE T
WHERE T.Domain like 'wikipedia.org';