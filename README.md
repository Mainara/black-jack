Blackjack
==========================

### What is it?

The implementation of the Blackjack game in Java. This is casino card game where players aim to have a hand value close
to 21 without
going over. Players are dealt two cards and can request additional cards to improve their hand. The
objective is to beat the dealer's hand without busting.

## Prerequisites

- Java
  17 - [How to install?](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
- Gradle 8 - [How to install?](https://gradle.org/install/)

## How to run it?

### Local
```bash
gradle clean build
gradle bootRun
```

### With docker
First, make sure you have docker installed:
```bash
docker --version
```

If not installed, [follow the steps](https://docs.docker.com/desktop/#:~:text=Install%20Docker%20Desktop) to install.

Run:
```bash
docker build -t blackjack .
docker run --network="host" blackjack
```

After the server is running, go to

```
http://localhost:8080/
```

## API

<details>
<summary> <b>Start a new game: </b> </summary>
<ul>
    <li>Method: POST</li>
    <li>Endpoint: /api/game/init</li>
    <li>Request Payload: N/A</li>
    <li>Response Payload: 

      {"playerCards": [
        {
            "rank": "SETE",
            "suit": "ESPADAS",
            "used": true,
            "revealed": true,
            "values": [
                7
            ]
        },
        {
            "rank": "AS",
            "suit": "PAUS",
            "used": true,
            "revealed": true,
            "values": [
                1,
                11
            ]
        }
    ],
    "dealerCards": [
        {
            "rank": "DEZ",
            "suit": "COPAS",
            "used": true,
            "revealed": true,
            "values": [
                10
            ]
        }
    ],
    "gameIsFinished": false}

</li>
</ul>
</details>

<details>
<summary> <b>Get game status: </b> </summary>
<ul>
    <li>Method: GET</li>
    <li>Endpoint: /api/game/status</li>
    <li>Request Payload: N/A</li>
    <li>Response Payload: 

      {currentWinner": "player",
    "playerPoints": 19,
    "dealerPoints": 12}

</li>
</ul>
</details>

<details>
<summary> <b>Player hit: </b> </summary>
<ul>
    <li>Method: POST</li>
    <li>Endpoint: /api/player/hit</li>
    <li>Request Payload: N/A</li>
    <li>Response Payload: 

      {"card": {
        "rank": "REI",
        "suit": "COPAS",
        "used": true,
        "revealed": true,
        "values": [
            11
        ]
    },
    "gameIsFinished": false,
    "status": {
        "currentWinner": "player",
        "playerPoints": 19,
        "dealerPoints": 14}}
</li>
</ul>
</details>

<details>
<summary> <b>Player stand: </b> </summary>
<ul>
    <li>Method: POST</li>
    <li>Endpoint: /api/player/stand</li>
    <li>Request Payload: N/A</li>
    <li>Response Payload: 

      [{
        "rank": "AS",
        "suit": "ESPADAS",
        "used": true,
        "revealed": true,
        "values": [
            1,
            11
        ]
    },
    {
        "rank": "OITO",
        "suit": "OURO",
        "used": true,
        "revealed": true,
        "values": [
            8
        ]
    },
    {
        "rank": "AS",
        "suit": "COPAS",
        "used": true,
        "revealed": true,
        "values": [
            1,
            11
        ]
    }]
</li>
</ul>
</details>

<details>
<summary> <b>Dealer play: </b> </summary>
<ul>
    <li>Method: POST</li>
    <li>Endpoint: /api/dealer/play</li>
    <li>Request Payload: N/A</li>
    <li>Response Payload: 

      {"dealerCards": [
        {
            "rank": "VALETE",
            "suit": "ESPADAS",
            "used": true,
            "revealed": false,
            "values": [
                11
            ]
        },
        {
            "rank": "OITO",
            "suit": "ESPADAS",
            "used": true,
            "revealed": true,
            "values": [
                8
            ]
        }
    ],
    "dealerBusted": false,
    "status": {
        "currentWinner": "player",
        "playerPoints": 21,
        "dealerPoints": 19
    },
    "gameIsFinished": true}
</li>
</ul>
</details>

<details>
<summary> <b>Shuffle deck: </b> </summary>
<ul>
    <li>Method: POST</li>
    <li>Endpoint: /api/dealer/shuffle</li>
    <li>Request Payload: N/A</li>
    <li>Response Payload: 

      [{
        "rank": "AS",
        "suit": "COPAS",
        "used": false,
        "revealed": true,
        "values": [
            1,
            11
        ]
    },
    {
        "rank": "REI",
        "suit": "ESPADAS",
        "used": false,
        "revealed": true,
        "values": [
            11
        ]
    },
    {
        "rank": "RAINHA",
        "suit": "PAUS",
        "used": false,
        "revealed": true,
        "values": [
            11
        ]
    },
    ...
    ]

</li>
</ul>
</details>

NOTE: To ensure that only initialized endpoints can be accessed before the game begins, any endpoint other than
**/api/game/init** will be blocked with an error message and a status of 403.

I have provided a [Postman collection](Blackjack.postman_collection.json) to facilitate the testing of endpoints.