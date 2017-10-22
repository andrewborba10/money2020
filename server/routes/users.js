var express = require('express');
var users = require('../controllers/users.js');
var router = express.Router();

router.get('/:token', function(req, res, next) {
	var userId = req.params.token;
	var user = users.getUser(userId);

	res.json({'user' : user});
});

module.exports = router;
