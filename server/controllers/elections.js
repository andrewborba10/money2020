var database = require('./database.js');
var db = database.getElectionsDb();
var userVotesDb = database.getUserVotesDb();

function getElection(electionId) {
	return db.findByProperty('electionId', electionId);
}

function getElections() {
	return db.getAll();
}

function getElectionForUser(userId) {
	console.log(userVotesDb);
	return userVotesDb.findByProperty('userId', userId);
}

module.exports = {
	getElection : getElection,
	getElections : getElections,
	getElectionForUser : getElectionForUser
}
