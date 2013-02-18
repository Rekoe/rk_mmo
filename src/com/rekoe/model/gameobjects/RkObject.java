package com.rekoe.model.gameobjects;

import java.util.UUID;

import org.nutz.lang.random.R;
/**
 * @author 科技㊣²º¹³
 * Feb 16, 2013 2:35:33 PM
 * http://www.rekoe.com
 * QQ:5382211
 */
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
