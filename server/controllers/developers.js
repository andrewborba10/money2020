function getDevelopers(userId) {
	return {
        developers: [
			{
				"developerId" : 0,
				"name" : "Aaron Barber",
				"party" : "Democratic",
				"imageUrl" : "https://media-exp2.licdn.com/media/p/7/005/0b3/1f3/0cf5eb3.jpg"
			},
			{
				"developerId" : 1,
				"name" : "Andrew Borba",
				"party" : "Democratic",
				"imageUrl" : "https://media-exp2.licdn.com/media/p/4/005/067/087/38646aa.jpg"
			},
			{
				"developerId" : 2,
				"name" : "Hajin Lee",
				"party" : "Green",
				"imageUrl" : "https://media-exp2.licdn.com/media/p/7/000/1d1/25c/1975557.jpg"
            },
            {
				"developerId" : 3,
				"name" : "Kevin Mark",
				"party" : "Republican",
				"imageUrl" : "https://media-exp2.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAz5AAAAJDgwZDg4NzM1LWJlYTMtNGI1OC04NTJlLThmMWE5M2QxMjE0Yw.jpg"
			}
		]
    };
}

module.exports = {
	getDevelopers : getDevelopers
}
