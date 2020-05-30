# JavaDiscordBot
 
 This is a bot i developed back in 2017 and am setting it as open source.
 I updated the bot and dependencies to work with the latst one.
 - `-music` not working. Need to fix dependecies.
 - `-help` comamnd needs to be finished.

-----

A fast, reactive Java wrapper for the official [Discord Bot API](https://discordapp.com/developers/docs/intro).


-----
### Features

```
Here are just listed some of the various functions and >64 commands of the bot.
```
First start the bot then invite to your server.The bot automaticly creates channels upon server enter.

- **General commands**
  - `-help` - get list of all messages<br>
  - `-id` - get list of all servers where bot runs<br>
  - `-info` - info about the bot<br>
  - `-ping` - your ping<br>
  - `-user` - user stats<br>
  - `-stats` - server stats<br>
  
- **Fun / Chat commands**
  - `-8ball` - typical 8ball yes/no generator
  - `-cat` - send cute cat pictures (also with a spam function to send them in time periods)
  - `-clear` - typical clear command to clear messages
  - `-joke` - throw a yomama joke :^)
  - `-quote` - quote messages from channels on the guild<br>
  - `-stups` - nudge someone on the guild
  - `-vote` - create polls<br>
  - `-gif` - create a gif<br>
  - `-roll` - create a roll<br>



- **Server administration**
  - `-kick` - kick someone from the server
  - `-vkick` - kick someone out of the voice channel, also for a specific time period
  - `-mute` - mute members in text channels
  - `-blacklist` - disallow users to use the bot

- **Other functions**
  - `-music` - Music player with many functions
  - `-autorole` - Auto assign a role for the new members
  - `-music` - Music player with many functions


-----
### Installation
Compile The Jar, and upload to your server.

First start the JAR file with:
```bash
$ sudo screen -L -S Sirready sudo java -jar DiscordBot.jar
```
Edit the `SETTINGS.txt` file and set `bot-token` , `BOT_OWNER_ID` and the `database` settings.


You can restart or update the bot via the scripts.


