function Database(initStore) {
	this.items = initStore;

	this.add = function(value) {
		this.items.push(value);
	};

	this.getAll = function() {
		return this.items;
	};

	this.has = function(matcher) {
		items = this.findAll(matcher);
		return items != null && items.length > 0;
	}

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

		return null;
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
				"personId" : 0,
				"name" : "Hillary Clinton",
				"party" : "Democratic"
			},
			{
				"personId" : 1,
				"name" : "Donald Trump",
				"party" : "Republican"
			},
			{
				"personId" : 2,
				"name" : "Jill Stein",
				"party" : "Green"
			}
		],
		"title" : "2016 Presidential Election",
		"description" : "Election for the president of the United States.",
		"dateOpen" : "11/01/2016",
		"dateClosed" : "11/15/2016",
		"isOpen" : true
	},

	{
		"electionId" : 2,
		"politicians" : [
			{
				"personId" : 3,
				"name" : "Catherine Cortez Masto",
				"party" : "Democratic"
			},
			{
				"personId" : 4,
				"name" : "Joe Heck",
				"party" : "Republican"
			},
			{
				"personId" : 5,
				"name" : "Tom Jones",
				"party" : "Independent"
			}
		],
		"title" : "2016 Nevada U.S. Senate",
		"description" : "Election for the Nevada U.S. Senate position.",
		"dateOpen" : "11/01/2016",
		"dateClosed" : "11/30/2016",
		"isOpen" : true		
	},

	{
		"electionId" : 3,
		"politicians" : [
			{
				"personId" : 6,
				"name" : "Dina Titus",
				"party" : "Democratic"
			},
			{
				"personId" : 7,
				"name" : "Mark Amodei",
				"party" : "Republican"
			},
			{
				"personId" : 8,
				"name" : "Cresent Hardy",
				"party" : "Independent"
			}
		],
		"title" : "2016 Nevada U.S. House",
		"description" : "Election for the Nevada House Representative.",
		"dateOpen" : "11/01/2016",
		"dateClosed" : "11/30/2016",
		"isOpen" : true		
	},

	{
		"electionId" : 4,
		"politicians" : [
			{
				"personId" : 9,
				"name" : "Oscar Goodman",
				"party" : "Democratic"
			},
			{
				"personId" : 10,
				"name" : "Arnie Adamsen",
				"party" : "Republican"
			},
			{
				"personId" : 11,
				"name" : "Kate Hallberg",
				"party" : "Independent"
			}
		],
		"title" : "2016 City of Las Vegas Judge",
		"description" : "Election for the judge of the city of Las Vegas, Nevada.",
		"dateOpen" : "06/01/2016",
		"dateClosed" : "06/15/2016",
		"isOpen" : false
	},

	{
		"electionId" : 5,
		"politicians" : [
			{
				"personId" : 12,
				"name" : "Carolyn Goodman",
				"party" : "Democratic"
			},
			{
				"personId" : 13,
				"name" : "Starvos Anthony",
				"party" : "Republican"
			},
			{
				"personId" : 14,
				"name" : "Phil Cory",
				"party" : "Independent"
			}
		],
		"title" : "2016 City of Las Vegas Mayor",
		"description" : "Election for the mayor of the city of Las Vegas, Nevada.",
		"dateOpen" : "06/01/2016",
		"dateClosed" : "06/15/2016",
		"isOpen" : false
	}	
]);

var organizationsDb = new Database([
	{
		"organizationId" : 1,
		"title" : "Clinton Foundation",
		"description" : "We believe that the best way to unlock human potential is through the power of creative collaboration. That's why we build partnerships between businesses, NGOs, governments, and individuals everywhere to work faster, leaner, and better; to find solutions that last; and to transform lives and communities from what they are today to what they can be, tomorrow.",
		"relatedPersonIds" : [0, 3, 6, 9, 12]
	},
	{
		"organizationId" : 2,
		"title" : "American Civil Liberties Union",
		"description" : "The American Civil Liberties Union was founded in 1920 and is our nation's guardian of liberty. The ACLU works in the courts, legislatures and communities to defend and preserve the individual rights and liberties guaranteed to all people in this country by the Constitution and laws of the United States.",
		"relatedPersonIds" : [0, 3, 6, 9, 12]
	},
	{
		"organizationId" : 3,
		"title" : "National Organization for Women",
		"description" : "The National Organization for Women is the largest organization of feminist grassroots activists in the United States. NOW has hundreds of chapters and hundreds of thousands of members and activists in all 50 states and the District of Columbia.",
		"relatedPersonIds" : [0, 3, 6, 9, 12]
	},
	{
		"organizationId" : 4,
		"title" : "EarthJustice",
		"description" : "EarthJustice is the largest nonprofit environmental law organization in the country, working to protect wildlife, for healthy communities, and for cleaner energy options. The organization represents its clients free of charge.",
		"relatedPersonIds" : [2, 5, 8, 11, 14]
	},
	{
		"organizationId" : 5,
		"title" : "CrowdPac",
		"description" : "We want to fight the power of Big Money in politics by helping small dollar donors engage in the political process. We want more citizens to vote and run for office. And we want to give people a platform to take action - whether it’s by joining a political community, or organizing one of their own.",
		"relatedPersonIds" : [2, 5, 8, 11, 14]
	},
	{
		"organizationId" : 6,
		"title" : "Heritage Foundation",
		"description" : "The mission of The Heritage Foundation is to formulate and promote conservative public policies based on the principles of free enterprise, limited government, individual freedom, traditional American values, and a strong national defense.",
		"relatedPersonIds" : [1, 4, 7, 10, 13]
	},
	{
		"organizationId" : 7,
		"title" : "Planned Parenthood",
		"description" : "Planned Parenthood is a trusted health care provider, an informed educator, a passionate advocate, and a global partner helping similar organizations around the world. Planned Parenthood delivers vital reproductive health care, sex education, and information to millions of people worldwide.",
		"relatedPersonIds" : [1, 4, 7, 10, 13]
	}
]);

var votesDb = new Database([
	{
		"electionId" : 4,
		"votedPersonId" : 9,
		"votes" : 140376
	},
	{
		"electionId" : 4,
		"votedPersonId" : 10,
		"votes" : 72459
	},
	{
		"electionId" : 4,
		"votedPersonId" : 11,
		"votes" : 12835
	},

	{
		"electionId" : 5,
		"votedPersonId" : 12,
		"votes" : 110287
	},
	{
		"electionId" : 5,
		"votedPersonId" : 13,
		"votes" : 85935
	},
	{
		"electionId" : 5,
		"votedPersonId" : 14,
		"votes" : 3778
	}	
]);

var rewardsDb = new Database([]);

var usersDb = new Database([]);

module.exports = {
	getElectionsDb : function() {
		return electionsDb; 
	},
	getOrganizationsDb : function() {
		return organizationsDb;
	},
	getVotesDb : function() {
		return votesDb;
	},
	getRewardsDb : function() {
		return rewardsDb;
	},
	getUsersDb : function() {
		return usersDb;
	}
};
