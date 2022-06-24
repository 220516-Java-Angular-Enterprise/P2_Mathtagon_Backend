# MATHTAGON

---
## Roles
- Ryan Jacobo - Frontend Lead java junior developer
- Rayshaun Thompson - Backend Lead java junior developer
- Jeremiah Bolden - Backend java junior developer
- Payton Davis - Frontend java junior developer


## Overview
Users will be presented with algebra expressions of various difficulties. A timer will count down until they enter the </br>
answer in the text below, at which point they’ll be presented with a new problem and the timer continues. Each user will</br>
start with a set number hit points; while incorrect answers will reduce their hit points, correct answers will increase,/br>
the timer and longer streaks of correct answers will give a bigger bonus. The game ends when the timer hits zero.

![ERD ](https://user-images.githubusercontent.com/105669957/175309948-036e7d99-ef3a-42c4-9f93-566a0f3b2e5c.png)

## Technologies
| *Frontend*     | *Backend*     |
|----------------|---------------|
| Spring Angular | Java          |
| Spring Boot    | AWS RDS & EC2 |
| HTML           | Docker        |
| Typescript     | Jenkins       |
 |                | J-Unit        |
 |                | Mockito       |
 |                | Postman       |
 |                | Nodes         |

## MVP
- Users can create account with input validation
- Find API for creating and displaying math text, similar to LatEx
- Users should be able to report a question if they believe it is wrong
- Users and stat data stored in AWS DB
- Deploy app on AWS EC2
- Skipping a question will reduce the timer
- Math problem is displayed with a text field below it
- Math problems are presented beautifully with superscripts and math symbols

	
## Stretch Goals
- Users leaderboard
- Buttons for menu navigation
- Implement hints
- Give users more difficult problems as they get better
- Users have profiles they can customize and see other users at a glance: skills/stats in other math categories

# Multiplayer
- Find captcha API to avoid bots
- Users can try to match with other (dating app swiping/page format) and users after seeing their bio/stats
- Any users that are online and match to each other create instance of math battle
- If two users match with each other, they will begin a math battle a la tetris battle 
- Post battle stat update for public view in matching
- Choose which category of math to seek matches
- Admins can create problems
- Different battle types:
- correct answers reduce timer available for opponent (if given different questions)
- Actually tetris, correct answers put a block on the other players side
- Allow matches to occur when users are offline, schedule math battle for later
- Users with highest rank in a math category get a special title… or something
- Users can create math problems for other users to do and rate
- Number of Solutions by a user should be displayed on a users profile
- Users take questionnaire of math problems to see what they good at
- Users can wager points before battle starts but opponent must agree to wager
