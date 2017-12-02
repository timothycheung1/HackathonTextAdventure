package reader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import map.Map;
import map.Place;

public class Reader {
    /**
     * Reads the main map
     * 
     * @param file
     *            the file containing the main overworld
     * @return
     */
    public static map.Map mapReader(String file) {
        String[][] metaData = null;
        ArrayList<String[][]> listOfMaps = new ArrayList<String[][]>();
        map.Map map = null;
        try {
            List<String> mapSet = Files.readAllLines(Paths.get(file));
            metaData = new String[mapSet.size()][];
            for (int i = 0; i < mapSet.size(); i++) {
                if (mapSet.get(i).contains(", ") && !mapSet.get(i).contains(":")) {
                    metaData[i] = mapSet.get(i).toLowerCase()
                            .substring(1, mapSet.get(i).length() - 1).split(", ");
                }
                else {
                    String[] otherTypeOfString = new String[1];
                    otherTypeOfString[0] = mapSet.get(i).toUpperCase().replace(":", "");
                    metaData[i] = otherTypeOfString;
                }
            }
            int start = 0;
            for (int i = 0; i < metaData.length; i++) {
                if (metaData[i].length > 0 && metaData[i][0].equals(">")) {
                    String[][] singleMapData = new String[i - start][];
                    for (int j = start; j < i; j++) {
                        singleMapData[j - start] = metaData[j];
                    }

                    listOfMaps.add(singleMapData);
                    start = i + 1;
                }
            }

            for (int i = 0; i < listOfMaps.size(); i++) {
                if (listOfMaps.get(i)[0][0].contains("M")) {
                    map = new Map(listOfMaps.get(i), null);
                }
            }
            for (int i = 0; i < listOfMaps.size(); i++) {
                if (listOfMaps.get(i)[0][0].contains("P")) {
                    String[] coords = listOfMaps.get(i)[0][0].replace("P(",
                            "").replace(")", "")
                            .split(", ");
                    if (map.getMap()[Integer.parseInt(coords[0])][Integer
                            .parseInt(coords[1])] instanceof Place) {
                        ((Place) map.getMap()[Integer.parseInt(coords[0])][Integer
                                .parseInt(coords[1])]).setMap(listOfMaps.get(i), map);
                    }
                    else {
                        throw new IllegalStateException();
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return map;
    }

    public static ArrayList<String> fileList() {

        File dir = new File("." + "/gameData");
        // System.out.println(dir.getPath());
        File[] listOfFiles = dir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return lowercaseName.endsWith(".txt");
            }

        });

        ArrayList<String> fileList = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                // System.out.println("File: " + listOfFiles[i].getName());
                fileList.add(listOfFiles[i].getName());
            }
            // else {
            // System.out.println("Directory: " + listOfFiles[i].getName());
            // }
        }
        return fileList;
    }

}
