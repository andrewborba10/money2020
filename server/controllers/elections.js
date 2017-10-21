var database = require('./database.js');
var db = database.getElectionsDb()

function getElection(electionId) {
	return db.findByProperty('electionId', 1);
}

function getElections() {
	return db.getAll();
}

module.exports = {
	getElection : getElection,
	getElections : getElections
}
