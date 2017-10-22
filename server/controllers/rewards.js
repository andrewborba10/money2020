var database = require('./database.js');
var db = database.getRewardsDb();
var usersDb = database.getUsersDb();

function hasCachedRewards(userId) {
	return db.has(function (item) {
		return item['userId'] == userId;
	});
}

function getCachedRewards(userId) {
	return db.findByProperty(userId)['rewards'];
}

function cacheRewards(userId, rewardsResponse) {
	db.add({
		'userId' : userId,
		'rewards' : rewardsResponse
	});
}

function deductRewards(userId, amountDeducted) {
	rewards = db.findByProperty(userId);
	rewards['rewards']['balance'] = rewards['rewards']['balance'] - amountDeducted;

	user = usersDb.findByProperty('userId', userId);
	user['totalDonations'] = user['totalDonations'] + amountDeducted;


	console.log(rewards);
}

module.exports = {
	hasCachedRewards : hasCachedRewards,
	getCachedRewards: getCachedRewards,
	cacheRewards : cacheRewards,
	deductRewards : deductRewards
}
