var database = require('./database.js');
var db = database.getOrganizationsDb();

function getRelatedOrganizations(votedPersonId) {
	return db.findAll(function(item) {
		return item['relatedPersonIds'].indexOf(parseInt(votedPersonId, 10)) !== -1;
	});
}

function getOrganizations() {
	return db.getAll();
}

module.exports = {
	getRelatedOrganizations : getRelatedOrganizations,
	getOrganizations : getOrganizations
}
