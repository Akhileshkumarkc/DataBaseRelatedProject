LOAD DATA
INFILE works_on_data.csv
APPEND INTO TABLE WORKS_ON
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS(ESSN,pno,hours)