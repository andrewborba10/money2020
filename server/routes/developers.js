var express = require('express');
var developers = require('../controllers/developers.js');
var router = express.Router();

router.get('/', function(req, res, next) {
	res.json({'developers': developers.getDevelopers()});
});

module.exports = router;
