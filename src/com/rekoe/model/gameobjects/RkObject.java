package com.rekoe.model.gameobjects;

import java.util.UUID;

import org.nutz.lang.random.R;

public abstract class RkObject {

    /**
     * Unique id, for all game objects such as: items, players, monsters.
     */
    private String objectId;

    public RkObject()
    {
    	this.objectId = R.UU16(UUID.randomUUID());
    }

    /**
     * Returns unique ObjectId of AionObject
     *
     * @return Int ObjectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Returns name of the object.<br>
     * Unique for players, common for NPCs, items, etc
     *
     * @return name of the object
     */
    public abstract String getName();

}
