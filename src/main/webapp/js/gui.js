var gui = new function() {

    var index = 0;

    var notNull = function(obj) {
        return typeof obj != 'undefined' && obj != null;
    }

    this.loadKills = function(missionInstanceId) {
        $.get('/rest/view/missioninstance/' + missionInstanceId, function(data) {
            // HUGE CHUNK OF DATA:..
            var kills = data.missionGameObjectKills;
            renderKills(kills);
        });
    }

    var renderKills = function(killData) {


        var index = 0;
        for(var a = 0; a < killData.length; a++) {
            var kill = killData[a];
            if(!notNull(kill.target.parentId)) {
                renderRow(kill, kill.target,  -1);
                index++;
            }

        }

        $("#killtable").treetable();
    };

    var renderRow = function(kill, child, parentIndex) {
        var myIndex = index++;
        var tr = '';

        var targetId = child.gameObjectId;
        var type =  typeof child.gameObjectDefinition != 'undefined' ? child.gameObjectDefinition.type : 'N/A';
        var targetPlayer = notNull(kill.target) && notNull(kill.target.player) ? kill.target.player.name : '';
        if(targetPlayer != '' && parentIndex != -1 && child.children.length == 0) type = targetPlayer;
        var player = notNull(kill.attacker) && notNull(kill.attacker.player) ? kill.attacker.player.name : '';
        var attacker = notNull(kill.attacker) && notNull(kill.attacker.gameObjectDefinition) ? kill.attacker.gameObjectDefinition.type : '[AI]';
        if(parentIndex != -1) {

            var tr = '<tr data-tt-id="' + myIndex + '" data-tt-parent-id="' + parentIndex + '"><td>' + type + '</td><td>' + targetId + '</td><td>' + attacker + '</td><td>' + player + '</td><td>' + child.country.name + '</td></tr>';

        } else {
            var tr = '<tr data-tt-id="' + myIndex + '"><td>' + type + '</td><td>' + targetId + '</td><td>' + attacker + '</td><td>' + player + '</td><td>' + child.country.name + '</td></tr>';
        }
        $("#killtable").append(tr);

        if(typeof child.children != 'undefined' && child.children != null && child.children.length > 0) {
            for(var a = 0; a < child.children.length; a++) {

                renderRow(kill, child.children[a], myIndex);
            }
        }
    }


};