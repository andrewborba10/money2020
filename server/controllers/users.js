var database = require('./database.js');
var db = database.getUsersDb();

function getUser(userId) {
	return db.findByProperty('userId', userId);
}

module.exports = {
	getUser : getUser
}
