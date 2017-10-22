var express = require('express');
var organizations = require('../controllers/organizations.js');
var router = express.Router();

router.get('/:votedPersonId', function(req, res, next) {
	var votedPersonId = req.params.votedPersonId;
	var orgs = organizations.getRelatedOrganizations(votedPersonId);

	res.json({'organizations': orgs});
});

router.get('/', function(req, res, next) {
	res.json({'organizations': organizations.getOrganizations()});
});

router.post('/pledged', function(req, res, next) {
	userId = req.body.token;
	organizationId = req.body.organizationId;

	res.json({'organization': organizations.pledgeOrganization(userId, organizationId)})
});

module.exports = router;
