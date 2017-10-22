var express = require('express');
var http = require('http');
var request = require('request');
var router = express.Router();

function getRewardsDetails(res) {
	var options = {
		url : 'http://api.devexhacks.com/rewards/accounts/+jaR3Du6APE+x4kQue7NB5B3s8P1SDCKUMnePdqKMMQTh1NOnzYjjwoO7vJ4efuJTXFEosem897LbHrdUjWXw==',
		headers : {
			'Authorization' : 'Bearer eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwicGNrIjoxLCJhbGciOiJkaXIiLCJ0diI6Miwia2lkIjoiYTdxIn0..Q8EPUTo189PyagVaeXKw9XgvYN1pEz5Vgp1bgF4Hj9TE2anFkmGILcf7UX9iO6L0cUTgJQm3blatkUZUyUKc6cHFyyuVPKmtZDIU2zmP6VEhxmroUfeqh8YJnOEw9LRVKU1Pq4fVRuZMsIM1Mf6F2oMOAFL8JTw7AK4CQVUWtti4KHaNBtDX9cHOuwRtDbKhQbmySLP0g5ENzrC9gWMLprmq66hX5bI4TAiF2f7KlgjtT9lvph9pLyDsfBhtOanWj6gVmYMqxcNQlUHcgtsH3nlthX1PsOKQppDtmS09hPELzTxEn2kxk2btJ0KPy2iQFQyDSWfER1xgJnFDASr1sg8MNeQh3Qjmp4vuruQMimu1IFVvb1cIsIDS7cWPCUPa2UFYz9YfW1uXVnUpOyZTCWZ3E28YL70Rn2TbP4Hw030rgBWF5Ok1YD51e7BWJXXCq1lIWUG85WmjWZ5Il4nVNZBxBFDPR7lQMG2Gw36ibffzfTDwwHfWhlpkmbqtRLawKEVtYNDcpIvocujQJFHlwCRJ9uex5BXJzQQ6Mrp1cvxp3sp65mU5EPSU4J1OK0Iuj8Yv.I3YRuEIDtnqtHjjjrb9OK0A'
		}
	};

	return request(options, function(error, response, body) {
		console.log(error);
		console.log(response);
		console.log(body);
		return JSON.parse(body);
	});
	// return http.get(url, function(response) {
	// 	console.log(res);
	// 	response.pipe(res);
	// }).on('error', function(e) {
	// 	console.log(e);
	// 	res.sendStatus(500);
	// }).end();	
}

router.get('/', function(req, res, next) {
	res.json(getRewardsDetails(res));
});

module.exports = router;
