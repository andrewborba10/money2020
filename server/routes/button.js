var express = require('express');
var button = require('../controllers/button.js')
var router = express.Router();


router.get('/', function(req, res, next) {
    button.getButton();

    res.status(200).send(
        {
            "Success": "Successfully retrieved button url", 
            "url": "http://api.devexhacks.com/oauth2/authorize?client_id=vgw3sf4f8nq3b98i1gdfr8wpx4gpty0ska52&grant_type=authorization_code&redirect_uri=http://mywebsite.com&scope=verify&response_type=code"
        }
    );
});

module.exports = router;
