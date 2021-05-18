package dev.dommi.gameserver.backend.adapter.database;

import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseObject {
    public int id;

    public DatabaseObject(int id){
        this.id = id;
    }


    abstract public List<String> getParamNames();

    abstract public List<Object> getValues();

}
