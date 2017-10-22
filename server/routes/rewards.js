var express = require('express');
var Promise = require('promise');
var request = require('request');
var router = express.Router();

function getAuthorizationHeader() {
	return 'Bearer eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwicGNrIjoxLCJhbGciOiJkaXIiLCJ0diI6Miwia2lkIjoiYTdxIn0..Q8EPUTo189PyagVaeXKw9XgvYN1pEz5Vgp1bgF4Hj9TE2anFkmGILcf7UX9iO6L0cUTgJQm3blatkUZUyUKc6cHFyyuVPKmtZDIU2zmP6VEhxmroUfeqh8YJnOEw9LRVKU1Pq4fVRuZMsIM1Mf6F2oMOAFL8JTw7AK4CQVUWtti4KHaNBtDX9cHOuwRtDbKhQbmySLP0g5ENzrC9gWMLprmq66hX5bI4TAiF2f7KlgjtT9lvph9pLyDsfBhtOanWj6gVmYMqxcNQlUHcgtsH3nlthX1PsOKQppDtmS09hPELzTxEn2kxk2btJ0KPy2iQFQyDSWfER1xgJnFDASr1sg8MNeQh3Qjmp4vuruQMimu1IFVvb1cIsIDS7cWPCUPa2UFYz9YfW1uXVnUpOyZTCWZ3E28YL70Rn2TbP4Hw030rgBWF5Ok1YD51e7BWJXXCq1lIWUG85WmjWZ5Il4nVNZBxBFDPR7lQMG2Gw36ibffzfTDwwHfWhlpkmbqtRLawKEVtYNDcpIvocujQJFHlwCRJ9uex5BXJzQQ6Mrp1cvxp3sp65mU5EPSU4J1OK0Iuj8Yv.I3YRuEIDtnqtHjjjrb9OK0A';
}

function getRewardsAccount(authorizationHeader, callback) {
	var options = {
		url : 'https://api.devexhacks.com/rewards/accounts/',
		headers : { 
			'Authorization' : authorizationHeader
		}
	};

	request(options, function(error, response, body) {
		callback(JSON.parse(body)['rewardsAccounts']);
	});	
}

function getRewardsAccountReferenceId(rewardsAccountsResponse) {
	for (idx in rewardsAccountsResponse) {
		rewardsAccount = rewardsAccountsResponse[idx];
		if (rewardsAccount['rewardsCurrency'] == 'Cash') {
			return rewardsAccount['rewardsAccountReferenceId'];
		}
	}

	throw 'Could not get rewards account reference id';
}

function getRewardsDetails(authorizationHeader, rewardsAccountReferenceId, callback) {
	var options = {
		url : 'http://api.devexhacks.com/rewards/accounts/' + rewardsAccountReferenceId,
		headers : {
			'Authorization' : authorizationHeader
		}
	};

	return request(options, function(error, response, body) {
		callback(JSON.parse(body));
	});	
}

function getRewardsResponse(rewardsDetailsResponse) {
	return {
		'accountName' : rewardsDetailsResponse['accountDisplayName'],
		'balance' : rewardsDetailsResponse['rewardsBalance'],
		'rewardsType' : rewardsDetailsResponse['rewardsCurrencyDescription']
	}
}

router.get('/', function(req, res, next) {
	var authorizationHeader = getAuthorizationHeader();
	
	getRewardsAccount(authorizationHeader, function(rewardsAccountsResponse) {
		var rewardsAccountReferenceId = getRewardsAccountReferenceId(rewardsAccountsResponse);
		getRewardsDetails(authorizationHeader, rewardsAccountReferenceId, function(rewardsDetailsResponse) {
			var rewardsResponse = getRewardsResponse(rewardsDetailsResponse);
			res.json(rewardsResponse);
		});
	});
});

module.exports = router;
