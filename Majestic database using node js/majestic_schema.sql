CREATE TABLE majestic_table (
	GlobalRank INTEGER
	,TdllRank INTEGER
	,Domain VARCHAR(100)
	,TLD VARCHAR(100)
	,RefSubNets INTEGER
	,RefIPs INTEGER
	,IDN_Domain VARCHAR(100)
	,IDN_TLD VARCHAR(100)
	,PrevGlobalRank INTEGER
	,PrevTldRank INTEGER
	,PrevRefSubNets INTEGER
	,PrevRefIPs INTEGER
	,PRIMARY KEY (GlobalRank)
	);
