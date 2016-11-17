/**
Ret the names of all empolyees who work in department 
that has the empolyee with the highest salary among
all empolyees.
**/

SELECT Fname||' '||Lname
from Employee 
WHERE DNO IN (
SELECT DNO 
FROM Employee 
WHERE SALARY = (SELECT MAX(SALARY) FROM Employee  ));

