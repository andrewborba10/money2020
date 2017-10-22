var express = require('express');
var elections = require('../controllers/elections.js');
var router = express.Router();

router.get('/:electionId', function(req, res, next) {
	var electionId = req.params.electionId;
	var election = elections.getElection(electionId);

	res.json(election);
});

router.get('/', function(req, res, next) {
	res.json({'elections': elections.getElections()});
});

module.exports = router;
