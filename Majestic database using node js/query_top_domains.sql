--query_top_domains
SET TIMING ON
SELECT T.DOMAIN
FROM MAJESTIC_TABLE T
WHERE T.REFSUBNETS > = 100000;