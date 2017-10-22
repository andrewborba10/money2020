var request = require('request');
var queryString = require('query-string');
var sha256 = require('sha256');

const TOKEN = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwicGNrIjoxLCJhbGciOiJkaXIiLCJ0diI6Miwia2lkIjoiYTdxIn0..Jn908J-yYgcKMNJnBlhrJg.J15niJBubgAPK5cSeKL6g78C1yBxirQW9HKZ0k1usVdsFOWY392OlR_qUazosk6EiCW2ge-4ahSUo5x-qooNPNz7bb2hWAXmj4MY81oF3oLMHi6JcAPzjYsVHlZQsAYEKjyUCFR7h8h4WHNSukDgidJ6fqumOHflRWCex_eODLYGlJl-B5ksgMByssOZCOFNX-l8ylDscykKpj79steWwPI_mscsEszAbihjXIliQNw7ysEp0Xyxypcc3YCshYIbVpWNcvA1gvmXPeeC4BB0EhwMbtkPdKHAPohnH-Y0y2xJfqQJ64-sG_k_vIIryQTrzQS_cBDldnvbHWWjR1DdJ0B4bFlQz4ciiIt0j7TH6jJPdWPUzII5mKweoNNrSxtkiR0U5ZHPsOuh9dMB7dlaYJQEDm9f4bm8RGoVyLN4NHBhAGL2LAkwCQ7osIVxEvP5nfeP9qZsZTCzQGzvT-a0V8MycVPxAbP1Nem9BV4oY9SbtVaTCwZv948gYOnNEAGW.ysGRHpe2a1rWDtGh30nRdw";
const THREE_WAY_TOKEN = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwicGNrIjoxLCJhbGciOiJkaXIiLCJ0diI6Miwia2lkIjoiYTdxIn0..Q8EPUTo189PyagVaeXKw9XgvYN1pEz5Vgp1bgF4Hj9TE2anFkmGILcf7UX9iO6L0cUTgJQm3blatkUZUyUKc6cHFyyuVPKmtZDIU2zmP6VEhxmroUfeqh8YJnOEw9LRVKU1Pq4fVRuZMsIM1Mf6F2oMOAFL8JTw7AK4CQVUWtti4KHaNBtDX9cHOuwRtDbKhQbmySLP0g5ENzrC9gWMLprmq66hX5bI4TAiF2f7KlgjtT9lvph9pLyDsfBhtOanWj6gVmYMqxcNQlUHcgtsH3nlthX1PsOKQppDtmS09hPELzTxEn2kxk2btJ0KPy2iQFQyDSWfER1xgJnFDASr1sg8MNeQh3Qjmp4vuruQMimu1IFVvb1cIsIDS7cWPCUPa2UFYz9YfW1uXVnUpOyZTCWZ3E28YL70Rn2TbP4Hw030rgBWF5Ok1YD51e7BWJXXCq1lIWUG85WmjWZ5Il4nVNZBxBFDPR7lQMG2Gw36ibffzfTDwwHfWhlpkmbqtRLawKEVtYNDcpIvocujQJFHlwCRJ9uex5BXJzQQ6Mrp1cvxp3sp65mU5EPSU4J1OK0Iuj8Yv.I3YRuEIDtnqtHjjjrb9OK0D";

function verify(salt, finalCallback) {
    var userAuthToken = THREE_WAY_TOKEN;
    var ssn = 555333333;
    var ssnToken = sha256(salt + ssn);
    
    var lastName = "HANSEU";
    var lastNameToken = sha256(salt + lastName);
    
    var dob = "06/10/1947";
    var dateOfBirthToken = sha256(salt + dob);
    
    var queryParams = {
        'ssnToken': ssnToken,
        'dateOfBirthToken': dateOfBirthToken,
        'lastNameToken': lastNameToken,
        'saltVersion': 'V1'
      };
    
    var qs = queryString.stringify(queryParams);
    
    var options = {
        url: 'https://api.devexhacks.com/identity/proof?' + qs,
        headers: {
            'User-Agent': 'request',
            "Authorization": "Bearer " + THREE_WAY_TOKEN,
            "Accept": "application/json;v=1",
            "Client-Correlation-Id": "1534avpiedk09789"
        }
    };

    request(options, (error, response, body) => {
        if (!error && response.statusCode == 204) {
            var data = response.body;
            console.log('VICTORY: ');
            console.log(data);
            finalCallback({});
        }
    });
}

function getSalt(cb, finalCallback) {
    var options = {
        url: 'https://api.devexhacks.com/identity/salt',
        headers: {
            "Authorization": "Bearer " + THREE_WAY_TOKEN,
            "Accept": "application/json;v=1",
            "Client-Correlation-Id": "1534avpiedk09789"
        }
    };

    request.post(options, (error, response, body) => {
        if (!error && response.statusCode == 200) {
            var data = JSON.parse(response.body);
            cb(data.salt, finalCallback);
        }
    });
}

function startVerification(data, finalCallback) {
    // Right now the data needs to be hard-coded given the APIs.  This is where the data is passed though.
    var salt = getSalt((salt) => {
        verify(salt, finalCallback);
    }, finalCallback);
}

module.exports = {startVerification: startVerification}
