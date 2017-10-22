var express = require('express');
var verify = require('../controllers/verify.js');
var router = express.Router();

router.post('/', function(req, res, next) {
	var ssn = req.body.ssn;
	var dob = req.body.dateOfBirth;
	var lastName = req.body.lastName;

    res.status(200).send(
        {
            "Success": "Successfully verified identity.", 
            "token": "asbskdiekdokslkadfadsf342"
        }
    );
});

module.exports = router;
