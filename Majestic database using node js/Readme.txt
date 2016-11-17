Assignment use the Majestic Database and create a Node Js program to do the query from database.

part 1

1) login into the system set all the paths as mentioned below.
open 'csgrads1'
export PATH=/people/cs/m/mxb135330/software/nodejs-0.12.7/bin:$PATH
export LD_LIBRARY_PATH=/people/cs/m/mxb135330/software/instantclient:$LD_LIBRARY_PATH
export OCI_INC_DIR=/people/cs/m/mxb135330/software/instantclient/sdk/include
export OCI_LIB_DIR=/people/cs/m/mxb135330/software/instantclient
export NODE_PATH=/people/cs/m/mxb135330/node_modules
export NODE_PATH=/people/cs/a/axk167131/node_modules
npm rebuild

2)Load the data
2a. create schema.
	open 'csoracle'.
	export PATH=$ORACLE_HOME/bin:$PATH
	open the db. use 
	sh opendb.sh
	start clean_data.sql
	start majestic_schema.sql
	exit the shell.
2b. load the databa
	sh load.sh
	
	Time Taken: "report"
	Table MAJESTIC_TABLE:
	1000000 Rows successfully loaded.

	Check the log file:
	majestic_data_loader.log
	for more information about the load.
=================================================================
	real    0m17.861s
	user    0m3.333s
	sys     0m0.243s
	runtime
	18
	start
	1479082107
	end
    1479082125
=================================================================

3) Execute query and note down time.
 sh opendb.sh
 start query_india_domains.sql
================================================================= 
	Time:
	3427 rows selected.
	Elapsed: 00:00:00.21
=================================================================
 start query_top_domains.sql
	Time :
	64 rows selected.

	Elapsed: 00:00:00.04
=================================================================
    
 start query_wikipedia.sql

	GLOBALRANK   TDLLRANK
	---------- ----------
         6          1
	Elapsed: 00:00:00.04
 =================================================================
 Part B:
 Start with the node on csgrads1.
 4) cd node
	node Majesticapp.js
	Web server listening on localhost: 5210

	http://csgrads1.utdallas.edu:5210/index
	you will find two options:
	click on the buttons :
	
	Search with Domain Name

	Search Domain Get
	Search Domain Post

	Search with TLD

	TLD Search Get
	TLD Search Post 
	
	Execute them for different combination of get/post and 2 queries.
	
	We dont take action for the part B 3rd part, which is not done in this assignment.
	
5) Curl data.
	cd Curl
	to get the time for post request for both queries.
	sh Curl_domain_Name_post.sh
==================================================================================
	results:
<h2>Domain Name Details:</h2><br>
<h4> You searched for the following Domain Name:   <i>wikipedia.org</i><h4>
<p> Here are the Results,It contains 1 rows</p>
<style>
table, th, td {
    border: 3px solid black;
}
</style>
 <TABLE>
 <tr>
 <td><b>DOMAIN NAME</b></td>
 <td><b>global Ranking</b></td>
 <td><b>TDLL RANK</b></td>
 <tr>
 <tr> <td>wikipedia.org</td><td>6</td><td>1</td></tr>
 </TABLE>
<form action="/index" method="get">
    <br><br>
    <input type="submit" value="home">
</form>

real    0m0.140s
user    0m0.003s
sys     0m0.003s
==============================================================================

	sh curl_tld_post.sh
===============================================================================

<h2>TLD Name Details:</h2><br>
<h4> You searched for the following TLD Name:   <i>in</i><h4>
<p> Here are the Results,It contains 100 rows</p>
<style>
table, th, td {
    border: 3px solid black;
}
</style>
 <TABLE>
 <tr>
 <td><b>DOMAIN NAME</b></td>
 <td><b>Global Rank</b></td>
 <tr>
 <tr> <td>blogspot.in</td><td>542</td></tr><tr> <td>google.co.in</td><td>618</td></tr><tr> <td>nic.in</td><td>946</td></tr><tr> <td>ernet.in</td><td>2655</td></tr><tr> <td>intoday.in</td><td>2903</td></tr><tr> <td>lnkd.in</td><td>2905</td></tr><tr> <td>res.in</td><td>3678</td></tr><tr> <td>amazon.in</td><td>3956</td></tr><tr> <td>ind.in</td><td>5855</td></tr><tr> <td>linkd.in</td><td>5912</td></tr><tr> <td>acrp.in</td><td>5975</td></tr><tr> <td>ibtimes.co.in</td><td>7070</td></tr><tr> <td>bbc.in</td><td>7344</td></tr><tr> <td>iitb.ac.in</td><td>9747</td></tr><tr> <td>topnews.in</td><td>9764</td></tr><tr> <td>productrating.in</td><td>9793</td></tr><tr> <td>tripadvisor.in</td><td>10955</td></tr><tr> <td>speakingtree.in</td><td>10638</td></tr><tr> <td>businessinsider.in</td><td>10833</td></tr><tr> <td>rbi.org.in</td><td>11483</td></tr><tr> <td>catb.in</td><td>13787</td></tr><tr> <td>oneindia.in</td><td>13790</td></tr><tr> <td>skicc.in</td><td>15382</td></tr><tr> <td>india.gov.in</td><td>16854</td></tr><tr> <td>iitk.ac.in</td><td>13885</td></tr><tr> <td>chanelhandbags.net.in</td><td>15660</td></tr><tr> <td>iitm.ac.in</td><td>14153</td></tr><tr> <td>net4.in</td><td>15924</td></tr><tr> <td>hpage.co.in</td><td>16100</td></tr><tr> <td>groupit.in</td><td>16152</td></tr><tr> <td>attain.co.in</td><td>14671</td></tr><tr> <td>shreebalajipackaging.in</td><td>14765</td></tr><tr> <td>erolove.in</td><td>14869</td></tr><tr> <td>ebay.in</td><td>13438</td></tr><tr> <td>olympe.in</td><td>12002</td></tr><tr> <td>wisdomite.in</td><td>13544</td></tr><tr> <td>consumeradvice.in</td><td>15174</td></tr><tr> <td>huffingtonpost.in</td><td>18204</td></tr><tr> <td>eice.in</td><td>19972</td></tr><tr> <td>netload.in</td><td>21521</td></tr><tr> <td>thebase.in</td><td>21611</td></tr><tr> <td>xblog.in</td><td>21620</td></tr><tr> <td>toypark.in</td><td>23405</td></tr><tr> <td>spsgroup.co.in</td><td>21784</td></tr><tr> <td>norw.in</td><td>20391</td></tr><tr> <td>adultnet.in</td><td>23681</td></tr><tr> <td>pioneerautomation.in</td><td>20777</td></tr><tr> <td>heck.in</td><td>20856</td></tr><tr> <td>anixsoft.in</td><td>19327</td></tr><tr> <td>agroproduct.in</td><td>22473</td></tr><tr> <td>pinboard.in</td><td>19415</td></tr><tr> <td>allevents.in</td><td>24276</td></tr><tr> <td>gmsss47d.in</td><td>28979</td></tr><tr> <td>movers5th.in</td><td>25963</td></tr><tr> <td>sdpsdelhi6.in</td><td>27734</td></tr><tr> <td>topservants.co.in</td><td>27877</td></tr><tr> <td>zzxx.in</td><td>28013</td></tr><tr> <td>gen.in</td><td>28143</td></tr><tr> <td>44t.in</td><td>25244</td></tr><tr> <td>freeorg.in</td><td>26708</td></tr><tr> <td>scroll.in</td><td>28300</td></tr><tr> <td>ondeweb.in</td><td>25305</td></tr><tr> <td>msitsolutions.in</td><td>23963</td></tr><tr> <td>iiitmk.ac.in</td><td>28485</td></tr><tr> <td>klyuniv.ac.in</td><td>26993</td></tr><tr> <td>bgr.in</td><td>28523</td></tr><tr> <td>foaidindia.in</td><td>28588</td></tr><tr> <td>re2l.in</td><td>30198</td></tr><tr> <td>indianvisaonline.gov.in</td><td>31772</td></tr><tr> <td>airindia.in</td><td>33243</td></tr><tr> <td>porndairy.in</td><td>34875</td></tr><tr> <td>sitew.in</td><td>30326</td></tr><tr> <td>freebitco.in</td><td>30684</td></tr><tr> <td>revmkt.in</td><td>32287</td></tr><tr> <td>hdwallpapers.in</td><td>30842</td></tr><tr> <td>bookmyads.in</td><td>35435</td></tr><tr> <td>guardian.in</td><td>30932</td></tr><tr> <td>indianrail.gov.in</td><td>31044</td></tr><tr> <td>m.in</td><td>34227</td></tr><tr> <td>koreaedu.in</td><td>32835</td></tr><tr> <td>mullimalar.in</td><td>33033</td></tr><tr> <td>steag.in</td><td>33042</td></tr><tr> <td>vasaneye.in</td><td>37488</td></tr><tr> <td>interiorhouse.in</td><td>39084</td></tr><tr> <td>iitd.ac.in</td><td>40528</td></tr><tr> <td>airtel.in</td><td>39087</td></tr><tr> <td>moinmo.in</td><td>36211</td></tr><tr> <td>tvc.in</td><td>36276</td></tr><tr> <td>vrlogistics.in</td><td>37937</td></tr><tr> <td>mobie.in</td><td>41605</td></tr><tr> <td>tinylink.in</td><td>39486</td></tr><tr> <td>newtechfasteners.in</td><td>40926</td></tr><tr> <td>olx.in</td><td>41214</td></tr><tr> <td>uf9.in</td><td>41494</td></tr><tr> <td>mvision.in</td><td>38435</td></tr><tr> <td>du.ac.in</td><td>38513</td></tr><tr> <td>hamacademy.co.in</td><td>38594</td></tr><tr> <td>xplore360.in</td><td>41107</td></tr><tr> <td>sbi.co.in</td><td>38701</td></tr><tr> <td>locanto.in</td><td>40207</td></tr>
 </TABLE>
<form action="/index" method="get">
    <br><br>
    <input type="submit" value="home">
</form>

real    0m0.021s
user    0m0.001s
sys     0m0.004s

============================================================================== 


	
