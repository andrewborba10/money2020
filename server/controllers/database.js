function Database(initStore) {
	this.items = initStore;

	this.add = function(value) {
		this.items.push(value);
	};

	this.getAll = function() {
		return this.items;
	};

	this.findByProperty = function(propertyName, propertyValue) {
		return this.find(function(item) {
			return item[propertyName] == propertyValue;
		});
	};

	this.find = function(matcher) {
		var matching = [];

		for (var idx in this.items) {
			item = this.items[idx];

			if (matcher(item)) {
				return item;
			}
		}
	}

	this.findAll = function(matcher) {
		var matching = [];

		for (var idx in this.items) {
			item = this.items[idx];

			if (matcher(item)) {
				matching.push(item);
			}
		}

		return matching;
	}
}

var electionsDb = new Database([
	{
		"electionId" : 1,
		"politicians" : [
			{
				"personId" : 1,
				"name" : "Barack Obama"
			},
			{
				"personId" : 2,
				"name" : "Donald Trump"
			}
		],
		"title" : "2020 Presidential Election",
		"description" : "Election for the president of the United States.",
		"dateOpen" : "11/01/2020",
		"dateClosed" : "11/15/2020"
	}
]);

var organizationsDb = new Database([
	{
		"donationId" : 1,
		"title" : "Clinton Foundation",
		"description" : "We believe that the best way to unlock human potential is through the power of creative collaboration...",
		"relatedPersonIds" : [1]
	}
]);

var votesDb = new Database([
	{
		"userId" : 1,
		"electionId" : 1,
		"votedPersonId" : 1
	},
	{
		"userId" : 2,
		"electionId" : 1,
		"votedPersonId" : 1
	}	
]);

module.exports = {
	getElectionsDb : function() {
		return electionsDb; 
	},
	getOrganizationsDb : function() {
		return organizationsDb;
	},
	getVotesDb : function() {
		return votesDb;
	}
};
