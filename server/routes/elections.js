var express = require('express');
var elections = require('../controllers/elections.js');
var router = express.Router();

router.get('/:token', function(req, res, next) {
	var userId = req.params.token;
	var election = elections.getVotesForUser(userId);

	res.json({"votes" : election});
});

router.get('/', function(req, res, next) {
	res.json({'elections': elections.getElections()});
});

module.exports = router;
