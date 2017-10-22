var express = require('express');
var votes = require('../controllers/votes.js');
var router = express.Router();

router.post('/', function(req, res, next) {
	var electionId = req.body.electionId;
	var votedPersonId = req.body.votedPersonId;

	votes.submitVote(electionId, votedPersonId);
	res.status(200).json({});
});

router.get('/:electionId', function(req, res, next) {
	var electionId = req.params.electionId;
	var electionResults = votes.getElectionResults(electionId);

	res.json(electionResults);
});

module.exports = router;
