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
				"party" : "Democratic",
				"imageUrl" : "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Hillary_Clinton_official_Secretary_of_State_portrait_crop.jpg/220px-Hillary_Clinton_official_Secretary_of_State_portrait_crop.jpg"
			},
			{
				"personId" : 1,
				"name" : "Donald Trump",
				"party" : "Republican",
				"imageUrl" : "https://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2017/05/12/104466932-PE_Color.240x240.jpg"
			},
			{
				"personId" : 2,
				"name" : "Jill Stein",
				"party" : "Green",
				"imageUrl" : "https://twt-media.washtimes.com/media/image/2016/08/17/jill_stein_b.jpg"
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
				"party" : "Democratic",
				"imageUrl" : "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Catherine_Cortez_Masto_official_portrait.jpg/1200px-Catherine_Cortez_Masto_official_portrait.jpg"
			},
			{
				"personId" : 4,
				"name" : "Joe Heck",
				"party" : "Republican",
				"imageUrl" : "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/141212-A-ZP772-001.jpeg/220px-141212-A-ZP772-001.jpeg"
			},
			{
				"personId" : 5,
				"name" : "Tom Jones",
				"party" : "Independent",
				"imageUrl" : "https://upload.wikimedia.org/wikipedia/commons/c/c8/Chris_Collins_official_photo.jpg"
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
				"party" : "Democratic",
				"imageUrl" : "https://pbs.twimg.com/profile_images/669584452322877442/z7pOtlJI.jpg"
			},
			{
				"personId" : 7,
				"name" : "Mark Amodei",
				"party" : "Republican",
				"imageUrl" : "https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Mark_Amodei.jpg/220px-Mark_Amodei.jpg"
			},
			{
				"personId" : 8,
				"name" : "Cresent Hardy",
				"party" : "Independent",
				"imageUrl" : "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/2015-01-08_OfficialPhoto_RepCresentHardy_NV04.jpg/220px-2015-01-08_OfficialPhoto_RepCresentHardy_NV04.jpg"
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
				"party" : "Democratic",
				"imageUrl" : "http://www.vegas24seven.com/wp-content/uploads/2011/11/O.Goodman-LVCVA-Headshot.jpg"				
			},
			{
				"personId" : 10,
				"name" : "Arnie Adamsen",
				"party" : "Republican",
				"imageUrl" : "https://upload.wikimedia.org/wikipedia/commons/8/8f/Scott_Taylor_official_photo.jpg"
			},
			{
				"personId" : 11,
				"name" : "Kate Hallberg",
				"party" : "Independent",
				"imageUrl" : "https://i.pinimg.com/236x/37/6d/fa/376dfa0c31bd285e2bb0d139427a860b--politicians-the-list.jpg"
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
				"party" : "Democratic",
				"imageUrl" : "https://www.myvegasmag.com/os/resources/media/mayor_sp14.jpg"
			},
			{
				"personId" : 13,
				"name" : "Starvos Anthony",
				"party" : "Republican",
				"imageUrl" : "http://www.watchdogwag.com/wp-content/uploads/2015/01/Stavros-III.jpg"
			},
			{
				"personId" : 14,
				"name" : "Phil Cory",
				"party" : "Independent",
				"imageUrl" : "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Jim_Davis.jpg/1200px-Jim_Davis.jpg"
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
		"relatedPersonIds" : [0, 3, 6, 9, 12],
		"totalDonations" : "$7.1m",
		"imageUrl" : "http://www.clintonfoundation.org/sites/all/themes/custom/cf/cf-logo-big.png",
		"tags" : ["Universal Health Control", "Climate Control", "Disaster Relief"]
	},
	{
		"organizationId" : 2,
		"title" : "American Civil Liberties Union",
		"description" : "The American Civil Liberties Union was founded in 1920 and is our nation's guardian of liberty. The ACLU works in the courts, legislatures and communities to defend and preserve the individual rights and liberties guaranteed to all people in this country by the Constitution and laws of the United States.",
		"relatedPersonIds" : [0, 3, 6, 9, 12],
		"totalDonations" : "$5.7m",
		"imageUrl" : "https://upload.wikimedia.org/wikipedia/en/thumb/f/f3/American_Civil_Liberties_Union_logo.svg/1280px-American_Civil_Liberties_Union_logo.svg.png",
		"tags" : ["Civil Liberties", "Freedom of Expression", "Legal Protection for Minorities"]
	},
	{
		"organizationId" : 3,
		"title" : "National Organization for Women",
		"description" : "The National Organization for Women is the largest organization of feminist grassroots activists in the United States. NOW has hundreds of chapters and hundreds of thousands of members and activists in all 50 states and the District of Columbia.",
		"relatedPersonIds" : [0, 3, 6, 9, 12],
		"totalDonations" : "$9.8m",
		"imageUrl" : "http://bostonnow.org/wp-content/uploads/2016/09/NOW.png",
		"tags" : ["Women's Rights", "Equality", "Grassroots Activism"]
	},
	{
		"organizationId" : 4,
		"title" : "EarthJustice",
		"description" : "EarthJustice is the largest nonprofit environmental law organization in the country, working to protect wildlife, for healthy communities, and for cleaner energy options. The organization represents its clients free of charge.",
		"relatedPersonIds" : [2, 5, 8, 11, 14],
		"totalDonations" : "$6.8m",
		"imageUrl" : "https://yt3.ggpht.com/-W_3EWqxKSLM/AAAAAAAAAAI/AAAAAAAAAAA/43OT-enqW4Y/s900-c-k-no-mo-rj-c0xffffff/photo.jpg",
		"tags" : ["Climate and Energy Control", "Wildlife Preservation", "Environmental Law"]
	},
	{
		"organizationId" : 5,
		"title" : "CrowdPac",
		"description" : "We want to fight the power of Big Money in politics by helping small dollar donors engage in the political process. We want more citizens to vote and run for office. And we want to give people a platform to take action - whether it’s by joining a political community, or organizing one of their own.",
		"relatedPersonIds" : [2, 5, 8, 11, 14],
		"totalDonations" : "$1.5m",
		"imageUrl" : "https://res.cloudinary.com/crowdpac/image/upload/logo-square.jpg",
		"tags" : ["Independents Funding", "Transparency", "Crowd-fudning"]
	},
	{
		"organizationId" : 6,
		"title" : "Heritage Foundation",
		"description" : "The mission of The Heritage Foundation is to formulate and promote conservative public policies based on the principles of free enterprise, limited government, individual freedom, traditional American values, and a strong national defense.",
		"relatedPersonIds" : [1, 4, 7, 10, 13],
		"totalDonations" : "$5.2m",
		"imageUrl" : "http://dailysignal.com/wp-content/themes/daily-signal/assets/images/brand/the-heritage-foundation-logo-blue.png",
		"tags" : ["Free Enterprise", "Limited Government", "National Defense"]		
	},
	{
		"organizationId" : 7,
		"title" : "National Right to Life",
		"description" : "Founded in 1968, National Right to Life is the nation's oldest and largest pro-life organization. National Right to Life is the federation of 50 state right-to-life affiliates and more than 3,000 local chapters. Through education and legislation, National Right to Life is working to restore legal protection to the most defenseless members of our society who are threatened by abortion, infanticide, assisted suicide and euthanasia.",
		"relatedPersonIds" : [1, 4, 7, 10, 13],
		"totalDonations" : "$2.4m",
		"imageUrl" : "http://nrlc.org/site/wp-content/uploads/header1015.png",
		"tags" : ["Pro-Life", "Anti-Euthnasia", "Legal Protections"]
	}
]);

var votesDb = new Database([
	{
		"electionId" : 4,
		"politicianId" : 9,
		"votes" : 140376
	},
	{
		"electionId" : 4,
		"politicianId" : 10,
		"votes" : 72459
	},
	{
		"electionId" : 4,
		"politicianId" : 11,
		"votes" : 12835
	},

	{
		"electionId" : 5,
		"politicianId" : 12,
		"votes" : 110287
	},
	{
		"electionId" : 5,
		"politicianId" : 13,
		"votes" : 85935
	},
	{
		"electionId" : 5,
		"politicianId" : 14,
		"votes" : 3778
	}	
]);

var rewardsDb = new Database([]);

var usersDb = new Database([]);

var userVotesDb = new Database([]);

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
	},
	getUserVotesDb : function() {
		return userVotesDb;
	}
};
