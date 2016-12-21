# botchan
rebirth of the waifu

Another attempt to make a qt bot for a Discord server using [Discord4J](https://github.com/austinv11/Discord4J).

The goal is to make a behavior which seems a bit more human than a bot with commands, or at least trying to do it.

The bot need a text file at its root folder named `token.txt` with a token in it (eh).

##Getting a bot
1. Go [here](https://discordapp.com/developers/applications/me).
2. Create/Use a bot, get a token : 
![pic1](https://a.pomf.cat/sbvyue.png)
3. Copy-paste the token in `token.txt` without linebreak at the end or any other special character.
4. Go there :
```
https://discordapp.com/api/oauth2/authorize?client_id=?????&scope=webhook.incoming&redirect_uri=https%3A%2F%2Fnicememe.website&response_type=code
```
with ????? being the ClientID of the bot (see below)
![pic2](https://a.pomf.cat/tomxyj.png)
5. hf
