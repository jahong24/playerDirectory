# playerDirectory
REST Application for a baseball player directory. Player information is stored in MySQL. This application demonstrates usage of Spring Data JPA. 

Sample Request (Add a player):
{
  "firstName" : "Aaron",
  "lastName" : "Judge",
  "team" : "Yankees",
  "jerseyNumber" : "99",
  "position" : "RF"
}

/mlb/players/all --> Retrieve all players in directory

/mlb/players/{team} --> Retrieve all players in directory from a particular team

/mlb/players/?id={id} --> Retrieve a player in directory based on id
