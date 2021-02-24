# ProgrammentwurfASE
Programmentwurf für die Vorlesung Advanced Software Engineering

## Implemted Features:
- Register / Login
- Ranks:
    - Default Admin
	- Admin: Moderator Features + manage Ranks
	- Moderator: Player Features + edit players + bann players
	- Player:  edit own info, report players, (WIP: add friends, manage guilds)
- Reports
- Banns
- User Management

## Planned Features:
- Friends
- Guilds

## Running the Project:

### Dev Mode
- First time build + run "docker-compose up --build"
- After first time run "docker-compose start"
- To remove it run "docker-compose down --volumes"

### Production Mode

- First time build + run "docker-compose -f docker-compose.prod.yml up --build"
- After first time run "docker-compose -f docker-compose.prod.yml start"
- To remove it run "docker-compose -f docker-compose.prod.yml down --volumes"
