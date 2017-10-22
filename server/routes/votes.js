var express = require('express');
var votes = require('../controllers/votes.js');
var router = express.Router();

router.post('/', function(req, res, next) {
	var userId = req.body.token;
	var electionId = req.body.electionId;
	var politicianId = req.body.politicianId;

	votes.submitVote(userId, electionId, politicianId);
	res.status(200).json({});
});

router.get('/:electionId', function(req, res, next) {
	var electionId = req.params.electionId;
	var electionResults = votes.getElectionResults(electionId);

	res.json(electionResults);
});

module.exports = router;
