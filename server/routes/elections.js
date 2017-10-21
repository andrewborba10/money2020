var express = require('express');
var router = express.Router();

function getElection(electionId) {
	return {
		"electionId" : 1,
		"politicians" : [
			{
				"personId" : 1,
				"name" : "Barack Obama"
			},
			{
				"personId" : 2,
				"name" : "Donald Trump"
			}
		],
		"title" : "2020 Presidential Election",
		"description" : "Election for the president of the United States.",
		"dateOpen" : "11/01/2020",
		"dateClosed" : "11/15/2020"
	};
}

function getElections() {
	return [
		getElection(1)
	];
}

router.get('/:id', function(req, res, next) {
	var electionId = req.params.id;
	var election = getElection(electionId);

	res.json(election);
});

router.get('/', function(req, res, next) {
	res.json(getElections());
});

module.exports = router;
