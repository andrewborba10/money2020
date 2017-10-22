var database = require('./database.js');
var db = database.getOrganizationsDb();
var usersDb = database.getUsersDb();

function getRelatedOrganizations(politicianId) {
	return db.findAll(function(item) {
		return item['relatedPersonIds'].indexOf(parseInt(politicianId, 10)) !== -1;
	});
}

function getOrganization(organizationId) {
	return db.findByProperty('organizationId', organizationId);
}

function pledgeOrganization(userId, organizationId) {
	organization = getOrganization(organizationId);

	if (usersDb.has(function(item) {
		return item['userId'] == userId
	})) {
		pledgedOrganization = usersDb.findByProperty('userId', userId);
		pledgedOrganization['pledgedOrganization'] = organization;
		return pledgedOrganization;
	}

	pledgedOrganization = {
		'userId' : userId,
		'pledgedOrganization' : organization,
		'totalDonations' : 0
	};

	user = usersDb.findByProperty('userId', userId);
	user['pledgedOrganization'] = organization;

	return pledgedOrganization;
}

function getOrganizations() {
	return db.getAll();
}

module.exports = {
	getRelatedOrganizations : getRelatedOrganizations,
	getOrganizations : getOrganizations,
	pledgeOrganization : pledgeOrganization
}
