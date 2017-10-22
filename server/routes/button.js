var express = require('express');
var button = require('../controllers/button.js')
var router = express.Router();


router.get('/', function(req, res, next) {
    button.getButton((results, err) => {
        if (err) {
            res.status(400).send({});
        } else {
            res.status(200).send(results);;
        }
    });
});

module.exports = router;
