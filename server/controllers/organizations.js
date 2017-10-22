var database = require('./database.js');
var db = database.getOrganizationsDb();
var usersDb = database.getUsersDb();

function getRelatedOrganizations(politicianId) {
	return db.findAll(function(item) {
		return item['relatedPoliticianIds'].indexOf(parseInt(politicianId, 10)) !== -1;
	});
}

function getOrganization(organizationId) {
	return db.findByProperty('organizationId', parseInt(organizationId, 10));
}

function pledgeOrganization(userId, organizationId) {
	console.log('organizationId');
	console.log(organizationId);
	organization = getOrganization(organizationId);
	console.log('organization');
	console.log(organization);

	if (usersDb.has(function(item) {
		return item['userId'] == userId
	})) {
		user = usersDb.findByProperty('userId', userId);
		user['organization'] = organization;
		return organization;
	}

	user = {
		'userId' : userId,
		'organization' : organization,
		'totalDonations' : 0
	};

	user = usersDb.findByProperty('userId', userId);
	user['organization'] = organization;

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
