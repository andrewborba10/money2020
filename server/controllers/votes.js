var database = require('./database.js');
var elections = require('./elections.js');
var db = database.getVotesDb()

function getVote(electionId, votedPersonId) {
	return db.find(function(item) {
		return item['electionId'] == electionId && item['votedPersonId'] == votedPersonId;
	});
}

function submitVote(electionId, votedPersonId) {
	if (getVote(electionId, votedPersonId) == null) {
		db.add({
			'electionId' : electionId,
			'votedPersonId' : votedPersonId, 
			'votes' : 0
		});
	}

	getVote(electionId, votedPersonId)['votes'] = getVote(electionId, votedPersonId)['votes'] + 1;
}

function getElectionResults(electionId) {
	votes = db.findAll(function(item) {
		return item['electionId'] == electionId
	});

	results = [];

	for (idx in votes) {
		vote = votes[idx];

		results.push({
			'personId' : vote['votedPersonId'],
			'votes' : vote['votes']
		});
	}

	return results;
	// candidates = {};

	// for (var idx in votes) {
	// 	var vote = votes[idx];

	// 	if (!(vote['votedPersonId'] in candidates)) {
	// 		candidates[vote['votedPersonId']] = 0;
	// 	}

	// 	candidates[vote['votedPersonId']] = candidates[vote['votedPersonId']] + 1;
	// }

	// politicians = elections.getElection(electionId)['politicians'];

	// console.log(candidates);
	// console.log(politicians);

	// results = [];

	// for (idx in politicians) {
	// 	politicianId = politicians[idx]['personId'];
	// 	console.log(politicianId);
	// 	if (politicianId in candidates) {
	// 		results.push({
	// 			"personId" : politicianId,
	// 			"votes" : candidates[politicianId]
	// 		})
	// 	} else {
	// 		results.push({
	// 			"personId" : politicianId,
	// 			"votes" : 0
	// 		});
	// 	}
	// }

	// return results;
}

module.exports = {
	submitVote : submitVote,
	getElectionResults : getElectionResults
}
