LOAD DATA
INFILE grade_report_data.csv
APPEND INTO TABLE GRADE_REPORT
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS(STUDENT_NUMBER,SECTION_ID ,GRADE)