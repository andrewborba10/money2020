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

module.exports = router;
