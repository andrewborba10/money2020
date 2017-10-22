var express = require('express');
var votes = require('../controllers/votes.js');
var router = express.Router();

router.post('/', function(req, res, next) {
	console.log('here');
	console.log(req.params);
	console.log(req.args);
	console.log(req.body);
	var electionId = req.body.electionId;
	var votedPersonId = req.body.votedPersonId;
	console.log(electionId);

	votes.submitVote(electionId, votedPersonId)
});

router.get('/:electionId', function(req, res, next) {
	var electionId = req.params.electionId;
	var electionResults = votes.getElectionResults(electionId);

	res.json(electionResults);
});

module.exports = router;
