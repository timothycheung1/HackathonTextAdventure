package map;

public class Place extends Terrain {
    private Map map;

    public Place(TerrainType type, int x, int y) {
        super(type, x, y);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setMap(String[][] data, Map parent) {
        map = new Map(data, parent);
    }

    public Map getMap() {
        return map;
    }

}
