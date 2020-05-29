# JavaDiscordBot
 
 This is a bot i developed back in 2017 and am setting it as open source.
 I updated the bot and dependencies to work with the latst one.
 - `-music` not working. Need to fix dependecies.
 - `-help` comamnd needs to be finished.

-----

A fast, reactive Java wrapper for the official [Discord Bot API](https://discordapp.com/developers/docs/intro).

Have some questions or want to join my developer community discord? Take a look! :^)
<br/><a href="https://discord.com/invite/BY6vgzN"><img src="https://toppng.com/uploads/preview/discord-logo-png-discord-ico-11562937135cilktsftux.png"/></a>

-----
### Get it!
[![Uptime Robot status](https://img.shields.io/uptimerobot/status/m779430970-e7fbeac99e0f5b24c277880c.svg)](#) &nbsp;

---
### Donate
https://paypal.me/DannyMotpan

<a href="#"><img src="https://i.imgur.com/CXi0AhX.png" width="300"/></a>

🏆 Goal *(this year's payment cicle)*  **`$0 / $50`**

**List of donators**

-----
### Note!

Music 

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
  - [x]`-roll` - create a roll<br>


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

Just download the latest version from **[Releases](https://github.com/SirReaDy/JavaDiscordBoT/releases)** and save it somewhere on your pc or server.

First start the JAR file with:
```bash
java -jar DiscordBot.jar
```
The bot will create a `SETTINGS.txt` file. Open it and enter your settings.
The most important is to set first the API token you'll get from [this page](https://discordapp.com/developers/applications/me) *(if you just created an bot account there).* Also its really important to set your Discord account ID as `BOT_OWNER_ID`! You can get your client id by right-clicking on your name in discord and select `Copy ID` *(for that, you need to enable developer mode in Discord!)*


Then restart the bot with
```bash
java -jar DiscordBot.jar
```

If you are running the bot on a Linux server via SSH, use **[screen](https://wiki.ubuntuusers.de/Screen/)** to run the bot as background process:
```bash
$ sudo screen -L -S Sirready sudo java -jar DiscordBot.jar
```
*(`-L` generates a logfile `screenlog.0` and `-S Sirready` set a name to the screen so you can reopen the screen with `sudo screen -r Sirready`)*

You also can create a bash file like this to start (and restart) the bot:
```bash
# resume running screen (if there is a screen running) to stop it with [STRG] + [C]
sudo screen -r Sirready
# cd to bot JAR location (enter YOUR path there)
cd Programs/Sirready
# start the bot in screen
sudo screen -L -S Sirready sudo java -jar DiscordBot.jar
```

After that, use the guild settings commands to configure the bot for your guild(s)

You can restart or update the bot via the scripts.
-----
### Used libraries

- <a href="https://github.com/DV8FromTheWorld/JDA">JDA</a>
- <a href="https://github.com/mwanji/toml4j">Toml4j</a>
- <a href="https://github.com/sedmelluq/lavaplayer">lavaplayer</a>
- <a href="https://github.com/koraktor/steam-condenser-java">steam-condenser</a>
- <a href="https://github.com/bertrandmartel/speed-test-lib">JSpeedTest</a>
- <a href="https://github.com/brunocvcunha/jiphy">jiphy</a>
