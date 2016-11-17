/**
For each dept whose avg emp salary is more than $30,000,
reterieve the dept name and the no of empolyees 
working for that dept.
**/

  SELECT count(ssn) as NO_OF_EMP ,D.DNAME as Dept_name
  FROM 
  DEPARTMENT D, EMPLOYEE E
  WHERE
  D.Dnumber =E.Dno
  GROUP BY D.Dnumber,D.DNAME
  having AVG(salary) >= 30000

