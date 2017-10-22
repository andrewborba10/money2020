var express = require('express');
var votes = require('../controllers/votes.js');
var router = express.Router();

router.post('/', function(req, res, next) {
	var userId = req.args.userId;
	var electionId = req.args.electionId;
	var votedPersonId = req.args.votedPersonId;

	votes.submitVote(userId, electionId, votedPersonId)
});

router.get('/:electionId', function(req, res, next) {
	var electionId = req.params.electionId;
	var electionResults = votes.getElectionResults(electionId);

	res.json(electionResults);
});

module.exports = router;
