-- Some simple stats events by player
SELECT p.name, k.KILLEDGAMETIME, mgo.gameobjectid, god.type as attacker_type, target.gameobjectid, target_god.type as target_type, COUNT(h.id) FROM PLAYER p
INNER JOIN MISSIONGAMEOBJECT mgo ON mgo.player_id = p.id
INNER JOIN GAMEOBJECTDEFINITION  god ON god.id=mgo.GAMEOBJECTDEFINITION_ID
INNER JOIN MISSIONINSTANCE_KILL k ON k.attacker_id=mgo.id
INNER JOIN MISSIONGAMEOBJECT target ON k.target_id = target.id
INNER JOIN GAMEOBJECTDEFINITION  target_god ON target_god.id=target.GAMEOBJECTDEFINITION_ID
INNER JOIN HIT  h ON h.target_id=target.id AND h.attacker_id=mgo.id
GROUP BY (p.name, k.KILLEDGAMETIME, mgo.gameobjectid, god.type, target.gameobjectid, target_god.type)
ORDER BY p.name, k.killedgametime

-- Count hits by gameobjectid
SELECT * FROM  HIT  h WHERE h.target_id=(SELECT id FROM MISSIONGAMEOBJECT mgo WHERE mgo.gameobjectid = 121874) ORDER BY hitgametime


-- List all kills of a mission
SELECT k.hitgametime, agod.type, ac.name, tgod.type, tc.name FROM MISSIONINSTANCE_KILL k
INNER JOIN MISSIONGAMEOBJECT a ON a.id=k.attacker_id
INNER JOIN MISSIONGAMEOBJECT t ON t.id=k.target_id
INNER JOIN GAMEOBJECTDEFINITION agod ON agod.id=a.gameobjectdefinition_id
INNER JOIN GAMEOBJECTDEFINITION tgod ON tgod.id=t.gameobjectdefinition_id
INNER JOIN COUNTRY ac ON ac.id=a.country_id
INNER JOIN COUNTRY tc ON tc.id=t.country_id
 WHERE k.missioninstance_id=1

 -- Same as above, but only for one side
 SELECT k.hitgametime, agod.type, ac.name, tgod.type, tc.name FROM MISSIONINSTANCE_KILL k
INNER JOIN MISSIONGAMEOBJECT a ON a.id=k.attacker_id
INNER JOIN MISSIONGAMEOBJECT t ON t.id=k.target_id
INNER JOIN GAMEOBJECTDEFINITION agod ON agod.id=a.gameobjectdefinition_id
INNER JOIN GAMEOBJECTDEFINITION tgod ON tgod.id=t.gameobjectdefinition_id
INNER JOIN COUNTRY ac ON ac.id=a.country_id
INNER JOIN COUNTRY tc ON tc.id=t.country_id
 WHERE k.missioninstance_id=1 AND ac.code=201

 -- Same again, but with number of hits on destroyed target included
 SELECT k.hitgametime, agod.type, ac.name, tgod.type, tc.name, COUNT(h.id) as hits FROM MISSIONINSTANCE_KILL k
INNER JOIN MISSIONGAMEOBJECT a ON a.id=k.attacker_id
INNER JOIN MISSIONGAMEOBJECT t ON t.id=k.target_id
INNER JOIN GAMEOBJECTDEFINITION agod ON agod.id=a.gameobjectdefinition_id
INNER JOIN GAMEOBJECTDEFINITION tgod ON tgod.id=t.gameobjectdefinition_id
INNER JOIN COUNTRY ac ON ac.id=a.country_id
INNER JOIN COUNTRY tc ON tc.id=t.country_id
LEFT OUTER JOIN HIT h ON h.target_id=t.id
 WHERE k.missioninstance_id <> 1 AND ac.code=101
GROUP BY (k.hitgametime, agod.type, ac.name, tgod.type, tc.name)

-- More of the same, but with time and missioninstance id
SELECT mi.id, k.hitgametime, agod.type, ac.name, tgod.type, tc.name, COUNT(h.id) as hits FROM MISSIONINSTANCE_KILL k
INNER JOIN MISSIONGAMEOBJECT a ON a.id=k.attacker_id
INNER JOIN MISSIONGAMEOBJECT t ON t.id=k.target_id
INNER JOIN GAMEOBJECTDEFINITION agod ON agod.id=a.gameobjectdefinition_id
INNER JOIN GAMEOBJECTDEFINITION tgod ON tgod.id=t.gameobjectdefinition_id
INNER JOIN COUNTRY ac ON ac.id=a.country_id
INNER JOIN COUNTRY tc ON tc.id=t.country_id
INNER JOIN MISSIONINSTANCE mi ON mi.id = k.missioninstance_id
LEFT OUTER JOIN HIT h ON h.target_id=t.id
 WHERE k.missioninstance_id =16 AND ac.code=201
GROUP BY (k.hitgametime, agod.type, ac.name, tgod.type, tc.name)
ORDER BY k.hitgametime