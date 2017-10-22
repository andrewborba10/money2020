var sha256 = require('sha256');

const SALT = "df9b31ae-c7a9-4518-9af2-3422263646a9";

var ssn = 555333333;
var ssnToken = sha256(SALT + ssn);

var lastName = "HANSEU";
var lastNameToken = sha256(SALT + lastName);

var dob = "06/10/1947";
var dateOfBirthToken = sha256(SALT + dob);

var qs = "?ssnToken=" + ssnToken + "&dateOfBirthToken=" + dateOfBirthToken + "&lastNameToken=" + lastNameToken + "&saltVersion=V1";

console.log("query string");
console.log(qs);