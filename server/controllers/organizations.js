var database = require('./database.js');
var db = database.getOrganizationsDb();
var userDb = database.getUsersDb();

function getRelatedOrganizations(votedPersonId) {
	return db.findAll(function(item) {
		return item['relatedPersonIds'].indexOf(parseInt(votedPersonId, 10)) !== -1;
	});
}

function getOrganization(organizationId) {
	return db.findByProperty('organizationId', organizationId);
}

function pledgeOrganization(userId, organizationId) {
	organization = getOrganization(organizationId);

	if (userDb.has(function(item) {
		return item['userId'] == userId
	})) {
		pledgedOrganization = userDb.findByProperty('userId', userId);
		pledgedOrganization['organization'] = organization;
		return organization;
	}

	userDb.add({
		'userId' : userId,
		'organization' : organization
	});

	return organization;
}

function getOrganizations() {
	return db.getAll();
}

module.exports = {
	getRelatedOrganizations : getRelatedOrganizations,
	getOrganizations : getOrganizations,
	pledgeOrganization : pledgeOrganization
}
