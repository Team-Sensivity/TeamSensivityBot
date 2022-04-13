# TeamSensivityBot 
[![image](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/eC7Jcg7Nzt) [![Website](https://img.shields.io/badge/website-000000?style=for-the-badge&logo=hyperledger&logoColor=white)](https://sensivity.michel929.de)

## Commands
### UserCommands
 - **&login** Du erhälst einen LoginLink um dich im Dashboard anzumelden
 - **&perks** oder **&perks <<a>perk>** Du bekommst die komplette Liste aller DBD Perks oder du bekommst eine Beschreibung des Perks
 - **&points** Du bekommst deine Punkte angezeigt
 - **&register** Falls du keinen DashboardAccount hast kannst du dich so registrieren
 - **&token** Du erhälst einen Token zum einloggen
 - **&best** Gibt dir das Beste Meme zurück
 
### AdminCommands
 - **&nickname** Alle Nicknames auf dem Server werden zurückgesetzt
 - **&update** Alle UserInfos werden im Dashboard aktualisiert
 - **&start** Der Bot wird gestartet
 - **&exit** Der Bot wird gestoppt
 - **&status** Eine Status NAchricht wird erstellt
 - **&afk** Die AFK Funktion wird aktiviert bzw. deaktiviert
 - **&level <<g>level> <<g>xp>** Du erstellst ein neues Level

## PluginSupport
### Vorraussetztungen
 - Programmiersprache ist Java
 - Du hast einen GitAccount 
 - Das Plugin was du Programmierst beruht auf der [Discord JDA](https://github.com/DV8FromTheWorld/JDA)

### Wie funktionierts?
 1. Du Programmierst einen eigenen Vollständigen JDA Discord Bot
 2. Du erstellst eine GitRepo ([Hier Repo erstellen](https://github.com/orgs/Team-Sensivity/repositories))
 3. Du erstellst eine .gitignore Datei und in diese packst du Folgende Datei Arten:
    - MainFile

 4. Statt deiner MainFile erstellst du eine create.java Datei
    - In dieser Datei werden alle Listener Registriert
    - Die Klasse sollte so aussehen:
       ```
       public class Create {
         public void addListener(JDA builder){
           Start.getApi().addEventListener(new CommandListener());
           ...
         }
       }
       ```
 6. 
   
