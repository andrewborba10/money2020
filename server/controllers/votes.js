var database = require('./database.js');
var elections = require('./elections.js');
var db = database.getVotesDb();
var userVotesDb = database.getUserVotesDb();

function getVote(electionId, politicianId) {
	return db.find(function(item) {
		return item['electionId'] == electionId && item['politicianId'] == politicianId;
	});
}

function submitVote(userId, electionId, politicianId) {
	if (getVote(electionId, politicianId) == null) {
		db.add({
			'electionId' : electionId,
			'politicianId' : politicianId, 
			'votes' : 0
		});
	}

	// Update vote count
	getVote(electionId, politicianId)['votes'] = getVote(electionId, politicianId)['votes'] + 1;

	// Track who user voted for
	userVotesDb.add({
		'userId' : userId,
		'electionId' : electionId,
		'politicianId' : politicianId
	});
}

function getElectionResults(electionId) {
	votes = db.findAll(function(item) {
		return item['electionId'] == electionId
	});

	results = [];

	for (idx in votes) {
		vote = votes[idx];

		results.push({
			'politicianId' : vote['politicianId'],
			'votes' : vote['votes']
		});
	}

	return results;
}

module.exports = {
	submitVote : submitVote,
	getElectionResults : getElectionResults
}
