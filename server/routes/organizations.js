var express = require('express');
var organizations = require('../controllers/organizations.js');
var router = express.Router();

router.get('/:politicianId', function(req, res, next) {
	var politicianId = req.params.politicianId;
	var orgs = organizations.getRelatedOrganizations(politicianId);

	res.json({'organizations': orgs});
});

router.get('/', function(req, res, next) {
	res.json({'organizations': organizations.getOrganizations()});
});

router.post('/pledge', function(req, res, next) {
	userId = req.body.token;
	organizationId = req.body.organizationId;

	res.json({'organization': organizations.pledgeOrganization(userId, organizationId)})
});

module.exports = router;
