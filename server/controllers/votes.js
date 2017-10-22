var database = require('./database.js');
var elections = require('./elections.js');
var db = database.getVotesDb()

function submitVote(userId, electionId, votedPersonId) {
	return db.add({
		'userId' : userId,
		'electionId' : electionId,
		'votedPersonId' : votedPersonId
	});
}

function getElectionResults(electionId) {
	votes = db.findAll(function(item) {
		return item['electionId'] == electionId
	});

	candidates = {};

	for (var idx in votes) {
		var vote = votes[idx];

		if (!(vote['votedPersonId'] in candidates)) {
			candidates[vote['votedPersonId']] = 0;
		}

		candidates[vote['votedPersonId']] = candidates[vote['votedPersonId']] + 1;
	}

	politicians = elections.getElection(electionId)['politicians'];

	console.log(candidates);
	console.log(politicians);

	results = [];

	for (idx in politicians) {
		politicianId = politicians[idx]['personId'];
		console.log(politicianId);
		if (politicianId in candidates) {
			results.push({
				"personId" : politicianId,
				"votes" : candidates[politicianId]
			})
		} else {
			results.push({
				"personId" : politicianId,
				"votes" : 0
			});
		}
	}

	return results;
}

module.exports = {
	submitVote : submitVote,
	getElectionResults : getElectionResults
}
