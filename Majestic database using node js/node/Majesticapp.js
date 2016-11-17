//Load Express Framework
var express = require('express');

//Load Mustache Template Engine
var mustachex = require('mustachex');

//Load Oracle
var oracledb = require('oracledb');

//Call express
var app = express();

var bodyParser = require('body-parser');

var orawrap = require('orawrap');
var dbConfig = {
    user: 'axk167131',
    password: 'axk167131',
    connectString: 'csoracle.utdallas.edu/student.csoracle.utdallas.edu',
    poolMax: 20,
    poolMin: 2,
    poolIncrement: 2,
    poolTimeout: 10
};
var search_tld_name = function(tld_name,max_limit,res){
	
	var sql_statement =  "SELECT DOMAIN,GLOBALRANK FROM MAJESTIC_TABLE WHERE TLD ='"+ tld_name+"'";
    console.log(sql_statement);
 orawrap.execute(
         sql_statement,
		 [],
		 {maxRows: parseInt(max_limit)},
         function(err, result)
         {
           if (err) {
              console.error(err);
              res.render('error',
                {
                  message: err.message
                }
              );

           }
           else {
             if (result.rows.length >0) {
               
			   // res.render('domain_name_result',
               // {
                 // domain: result.rows[0][0],
                 // globalrank: result.rows[0][1],
                 // tdllrank: result.rows[0][2]
               // }
			   
			   //code
			   var j =0;
			   var length = result.rows.length;
			   console.log("length"+ length);
			   var str="";
			   while(j < length){
				   console.log("ENtered" +j);
					str += "<tr> <td>";
					str +=	result.rows[j][0];
					str +=	"</td>";
					str +=	"<td>";
					str += result.rows[j][1]  ; 
					str +=	"</td>";
					str += "</tr>";					
					j+=1;
				}
				console.log(str);
				res.render('tld_search_result.html',
					{
					rows : length,
					search_key: tld_name,
					list: str
					});
			   //code ends

             }
             else {
				 var error_msg = "There is no TLD with the name ="+tld_name;
               res.render('error',
               {
                  message: error_msg

               }
               );
             }
           }
        }
 );
	
}
var search_domain_name = function(domain_name,res){
	var sql_statement =  "SELECT DOMAIN,GLOBALRANK,TDLLRANK FROM MAJESTIC_TABLE T WHERE T.Domain like '" + domain_name +"'";
    console.log(sql_statement);
 orawrap.execute(
         sql_statement,
		 [],
		 {maxRows: 1000},
         function(err, result)
         {
           if (err) {
              console.error(err);
              res.render('error',
                {
                  message: err.message
                }
              );

           }
           else {
             if (result.rows.length >0) {
               
			   // res.render('domain_name_result',
               // {
                 // domain: result.rows[0][0],
                 // globalrank: result.rows[0][1],
                 // tdllrank: result.rows[0][2]
               // }
			   
			   //code
			   var j =0;
			   var length = result.rows.length;
			   console.log("length"+ length);
			   var str="";
			   while(j < length){
				   console.log("ENtered" +j);
					/* str += "<ul>";
					str += "<li> Domain Name:";
					str +=	result.rows[j][0];
					str +=	"</li>";
					str +=	"<li>Global Ranking:";
					str += result.rows[j][1]  ; 
					str +=	"</li>";
					str +=	"<li>TDLL RANK:";
					str += result.rows[j][2];
					str +=	"</li>";
					str += "</ul>";
					str += "<ul>"; */
					str += "<tr> <td>";
					str +=	result.rows[j][0];
					str +=	"</td>";
					str +=	"<td>";
					str += result.rows[j][1]  ; 
					str +=	"</td>";
					str +=	"<td>";
					str += result.rows[j][2];
					str +=	"</td>";
					str += "</tr>";
/* 					str +=  "\n"
					str += "Domain Name: \t"	;
					str +=	result.rows[j][0];
					str +=	"\n";
					str +=	"\n Global Ranking: \t";
					str += result.rows[j][1]  ; 
					str +=	"";
					str +=	"\n TDLL RANK: \t";
					str += result.rows[j][2];
					str +=	"\n";
					str += "\n";  */
					
					j+=1;
				}
				console.log(str);
				res.render('domain_name_result',
					{
					rows : length,
					search_key: domain_name,
					list: str
					});
			   //code ends

             }
             else {
				 var error_msg = "There is no Domain with the name ="+domain_name;
               res.render('error',
               {
                  message: error_msg

               }
               );
             }
           }
        }
 );
};
//Set Global App Settings
app.engine('html', mustachex.express);
app.use(express.static(__dirname + '/public'));
app.set('view engine', 'html');
app.set('views', __dirname + '/views');

app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true }));
//1. Index page
app.get('/index',function(req,res){
	res.render('index.html');
});


//2.  two pages for domain-search entry
app.get('/domain_search_get', function(req, res) {
  res.render('domain_search_get.html');
});
app.get('/domain_search_post', function(req, res) {
  res.render('domain_search_post.html');
});

//2. post method for domain -search view
app.post('/search_domain_name_post', function(req, res) {

 var domain_name = req.body.domain_name;
 search_domain_name(domain_name,res);
 
});
//2. get method for domain -search view
app.get('/search_domain_name_get', function(req, res) {

 var domain_name = req.param('domain_name');
 search_domain_name(domain_name,res);
 
});

//3.two pages for TLD - Search Entry
app.get('/tld_search_get', function(req,res){
	res.render('tld_search_get.html');
});
app.get('/tld_search_post', function(req,res){
	res.render('tld_search_post.html');
});


//3. get method for domain -search view
app.get('/tld_search_get_result', function(req, res) {

 var tld_name = req.param('tld_name');
 var max_limit = req.param('Max_limit');
 search_tld_name(tld_name,max_limit,res);
 
});

//3. post method for -Search Entry
app.post('/tld_search_post_result', function(req, res) {

 var tld_name = req.body.tld_name;
 var max_limit = req.body.Max_limit;
 search_tld_name(tld_name,max_limit,res);
 
});



//Create Server
var port = Number(process.env.PORT || 5210);
orawrap.createPool(dbConfig, function(err, pool) {
   // The pool that was created is provided in the callback function,  
   // but it's rarely needed as it's stored within orawrap for use later 
   if (err) throw err;
   //Start the web server now that the pool is ready to handle incoming requests 
   app.listen(port, "csgrads1.utdallas.edu", function() {
       console.log('Web server listening on localhost: ' + port);
   });
});
