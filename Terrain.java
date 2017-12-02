package map;

import java.util.ArrayList;

public class Terrain {

    protected int x, y;
    protected TerrainType type;
    protected ArrayList<Event> events;
    protected ArrayList<Npc> npcs;

    public Terrain(TerrainType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        events = null;
        npcs = null;
    }

    @Override
    public String toString() {
        if (type == TerrainType.LAND) {
            return "l";
        }
        else if (type == TerrainType.HOLE) {
            return "h";
        }
        else if (type == TerrainType.PLACE) {
            return "p";
        }
        else if (type == TerrainType.WATER) {
            return "w";
        }
        else {
            return "u";
        }
    }

    public TerrainType getType() {
        return type;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Npc> getNpcs() {
        return npcs;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
