package map;

public class Map {
    private Terrain[][] map;

    public Map(String[][] data, Map parent) {
        // for (String[] line : data) {
        // for (String s : line) {
        // System.out.print(s + " ");
        // }
        // System.out.println();
        // }
        map = new Terrain[data.length - 1][data[1].length];
        TerrainType inputType;
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data[i + 1].length; j++) {
                switch (data[i + 1][j]) {
                    case "l":
                        inputType = TerrainType.LAND;
                        break;
                    case "w":
                        inputType = TerrainType.WATER;
                        break;
                    case "p":
                        inputType = TerrainType.PLACE;
                        break;
                    case "h":
                        inputType = TerrainType.HOLE;
                        break;
                    default:
                        inputType = TerrainType.UNASSIGNED;
                        break;
                }
                if (inputType == TerrainType.UNASSIGNED) {
                    throw new IllegalArgumentException();
                }
                else if (inputType == TerrainType.PLACE) {
                    map[i][j] = new Place(inputType, i, j);
                    if (data[0][0].contains("P") && parent != null) {
                        ((Place) map[i][j]).setMap(parent);
                    }
                }
                else {
                    map[i][j] = new Terrain(inputType, i, j);
                }
            }
        }
    }

    public Terrain[][] getMap() {
        return map;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < map.length; i++) {
            s += "[";
            for (int j = 0; j < map[i].length - 1; j++) {
                s += map[i][j].toString() + ", ";
            }
            s += map[i][map[i].length - 1].toString() + "]\n";
        }
        return s;
    }
}
