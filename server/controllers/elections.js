var database = require('./database.js');
var db = database.getElectionsDb();
var userVotesDb = database.getUserVotesDb();

function getElection(electionId) {
	return db.findByProperty('electionId', electionId);
}

function getElections() {
	return db.getAll();
}

function getVotesForUser(userId) {
	console.log(userVotesDb);
	votes = userVotesDb.findAllByProperty('userId', userId);

	return votes;
}

module.exports = {
	getElection : getElection,
	getElections : getElections,
	getVotesForUser : getVotesForUser
}
