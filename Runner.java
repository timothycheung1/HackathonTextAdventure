package main;

import java.util.ArrayList;

import map.Map;
import reader.Reader;

public class Runner {
    // private final static String DIRECTORY = System.getProperty("user.dir");
    private final static String GAME_DATA = "/gameData/";

    public static void main(String[] args) {
        // System.out.println(DIRECTORY);
        Map map = null;
        ArrayList<String> fileList = Reader.fileList();
        for (String fileName : fileList) {
            if (fileName.contains("Map"))
                map = Reader.mapReader("." + GAME_DATA + fileName);
        }
        System.out.println(map.toString());
        if (map.getMap()[4][2] instanceof map.Place)
            System.out.println(((map.Place) map.getMap()[4][2]).getMap().toString());
        if (map.getMap()[2][4] instanceof map.Place)
            System.out.println(((map.Place) map.getMap()[2][4]).getMap().toString());
    }

}
