/**
Ret the names of empolyees who make atleast $10,000 more than 
the empolyee who is paid the least in the company.
**/
SELECT FNAME||' '||LNAME from Employee
WHERE SALARY >=(SELECT MIN(SALARY) FROM Employee ) + 10000;