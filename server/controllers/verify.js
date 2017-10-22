var request = require('request');
var queryString = require('query-string');
var sha256 = require('sha256');

const TOKEN = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwicGNrIjoxLCJhbGciOiJkaXIiLCJ0diI6Miwia2lkIjoiYTdxIn0..Jn908J-yYgcKMNJnBlhrJg.J15niJBubgAPK5cSeKL6g78C1yBxirQW9HKZ0k1usVdsFOWY392OlR_qUazosk6EiCW2ge-4ahSUo5x-qooNPNz7bb2hWAXmj4MY81oF3oLMHi6JcAPzjYsVHlZQsAYEKjyUCFR7h8h4WHNSukDgidJ6fqumOHflRWCex_eODLYGlJl-B5ksgMByssOZCOFNX-l8ylDscykKpj79steWwPI_mscsEszAbihjXIliQNw7ysEp0Xyxypcc3YCshYIbVpWNcvA1gvmXPeeC4BB0EhwMbtkPdKHAPohnH-Y0y2xJfqQJ64-sG_k_vIIryQTrzQS_cBDldnvbHWWjR1DdJ0B4bFlQz4ciiIt0j7TH6jJPdWPUzII5mKweoNNrSxtkiR0U5ZHPsOuh9dMB7dlaYJQEDm9f4bm8RGoVyLN4NHBhAGL2LAkwCQ7osIVxEvP5nfeP9qZsZTCzQGzvT-a0V8MycVPxAbP1Nem9BV4oY9SbtVaTCwZv948gYOnNEAGW.ysGRHpe2a1rWDtGh30nRdw";
const SALT = "df9b31ae-c7a9-4518-9af2-3422263646a9"

var queryParams = {
    'ssnToken': '66acd1fdb9bf0ff68dc9e9e97cf59cedc8e5a455edd1c3d08c6cd135e37f6784',
    'dateOfBirthToken': 'a3e420a3405f5e31af78ad408e7d1f02f1be62b829b798e304191cc13f658028',
    'lastNameToken': 'f90d3d3c9025de60679658b8a11ba239febd5b0b8fdcd2bfdc4a1ce363610f40',
    'saltVersion': 'V1'
  };

var qs = queryString.stringify(queryParams);

// console.log('qs');
// console.log(qs);

// var ssn = 123456789;
// var ssnToken = sha256(SALT + ssn);
// console.log("ssnToken:");
// console.log(ssnToken);

// var lastName = "MARK";
// var lastNameToken = sha256(SALT + lastName);
// console.log("lastNameToken");
// console.log(lastNameToken);


var options = {
    url: 'https://api.devexhacks.com/identity/proof/?' + qs,
    headers: {
        'User-Agent': 'request',
        "Authorization": "Bearer " + TOKEN,
        "Accept": "application/json;v=1",
        "Client-Correlation-Id": "1534avpiedk09789"
    }
};
  

function verify() {
    console.log('verify....');
    request(options, (error, response, body) => {
        console.log('error');
        console.log(error);
        console.log('status code:');
        console.log(response.statusCode);
        if (!error && response.statusCode == 200) {
            var data = response.body;
            console.log('data: ');
            console.log(data);
            // cb(result);
        }
    });
}

// verify();

module.exports = {}
