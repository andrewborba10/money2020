var express = require('express');
var verify = require('../controllers/verify.js');
var router = express.Router();


router.post('/', function(req, res, next) {
	var ssn = req.body.ssn;
	var dob = req.body.dateOfBirth;
    var lastName = req.body.lastName;

    var data = {
        "ssn": ssn,
        "dob": dob,
        "lastName": lastName
    }

    verify.startVerification(data, (results, err) => {
        if (err) {
            res.status(400).send({});
        } else {
            res.status(200).send(
                {
                    "Success": "Successfully verified identity.", 
                    "token": "Nate"
                }
            );
        }
    });
});

module.exports = router;
