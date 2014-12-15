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