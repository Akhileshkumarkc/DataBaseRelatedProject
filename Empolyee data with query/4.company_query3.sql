/**
Ret the names of all empolyees whose supervisor's
supervisor has '888665555' for ssn
**/
SELECT FNAME||' '||LNAME
FROM Employee
WHERE SUPER_SSN IN (
		SELECT SSN
		FROM Employee
		WHERE SUPER_SSN = '888665555'
		);