LOAD DATA
INFILE prereq_data.csv
APPEND INTO TABLE PREREQUISITE
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS(COURSE_NUMBER,PREREQUISITE_NUMBER)