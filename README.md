# pathfinding-backend


Deze repository bevat de backend van het project Pathfinding.
Dit project is gemaakt in samenwerking met de Openbare Bibliotheek Eindhoven.

De backend zorgt voor de communicatie met de REST endpoint, bevat het JSON format en berekend het visualiseren van de signalen.

Tutorial:
De applicatie dient gestart te worden door de [RestService](https://github.com/ZachteBank/pathfinding-backend/blob/master/src/main/java/restserver/RestService.java) te starten. 
De rest server en de visualisatie worden dan beide gestart.

De endpoint voor de restcalls is: 

> 127.0.0.1:8070/pathfinding/add

Het moet een post call zijn met het volgende JSON formaat:
*Voorbeeld:*
   

     {
          "beacon": 1,
          "devices": [
            {
              "device": "00:0C:6E:D2:11:E6",
              "strength": "-86"
            },
            {
              "device": "11:0C:6E:D3:11:E6",
              "strength": "-50"
            },
            ]
        }
Uitleg:

    {
    	"beacon": "Het nummer van de beacon",
    	"devices": [
	    	"device": "mac address",
	    	"strength": "sterkte in dBm"
    	]
    }

De beacon verschijnt dan en kan worden verschoven zodat dit klopt met de juiste locatie. 
In de visualisatie zijn nog een aantal keybindings.

> f

Voor de FPS weer te geven

> d

Er worden nu hulp cirkels weer gegeven. Zo is het makkelijker om te vergelijken hoe "sterk" een signaal is.
