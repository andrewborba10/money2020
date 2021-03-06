var oauth = require('./oauth.js');
var request = require('request');

const REDIRECT_URI = "election-hacking://success";
const TOKEN = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwicGNrIjoxLCJhbGciOiJkaXIiLCJ0diI6Miwia2lkIjoiYTdxIn0..Jn908J-yYgcKMNJnBlhrJg.J15niJBubgAPK5cSeKL6g78C1yBxirQW9HKZ0k1usVdsFOWY392OlR_qUazosk6EiCW2ge-4ahSUo5x-qooNPNz7bb2hWAXmj4MY81oF3oLMHi6JcAPzjYsVHlZQsAYEKjyUCFR7h8h4WHNSukDgidJ6fqumOHflRWCex_eODLYGlJl-B5ksgMByssOZCOFNX-l8ylDscykKpj79steWwPI_mscsEszAbihjXIliQNw7ysEp0Xyxypcc3YCshYIbVpWNcvA1gvmXPeeC4BB0EhwMbtkPdKHAPohnH-Y0y2xJfqQJ64-sG_k_vIIryQTrzQS_cBDldnvbHWWjR1DdJ0B4bFlQz4ciiIt0j7TH6jJPdWPUzII5mKweoNNrSxtkiR0U5ZHPsOuh9dMB7dlaYJQEDm9f4bm8RGoVyLN4NHBhAGL2LAkwCQ7osIVxEvP5nfeP9qZsZTCzQGzvT-a0V8MycVPxAbP1Nem9BV4oY9SbtVaTCwZv948gYOnNEAGW.ysGRHpe2a1rWDtGh30nRdw";

var options = {
    url: 'https://api.devexhacks.com/identity/proof/tools/web-button?redirectURI=' + REDIRECT_URI,
    headers: {
        'User-Agent': 'request',
        "Authorization": "Bearer " + TOKEN,
        "Accept": "text/html;v=1",
        "Client-Correlation-Id": "1534avpiedk09789"
    }
};

function getButton(cb) {
    request(options, (error, response, body) => {
        if (!error && response.statusCode == 200) {
            var data = response.body;
            var result = {
                "Success": "Button successfully retrieved.",
                "url": parseButton(data) 
            }
            cb(result);
        }
    });
}

function parseButton(button) {
    var begin = button.indexOf("http");
    var end = button.indexOf("\'\">Verify");
    var result = button.slice(begin, end);
    return result;
    
}

module.exports = {getButton: getButton};