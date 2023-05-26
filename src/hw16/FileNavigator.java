package hw16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileNavigator {
    private Map<String, List<FileData>> fileMap;
    public FileNavigator() {
        fileMap = new HashMap<> ();
    }
    public void add(FileData fileData) {
        String path = fileData.getPath ();
        if (pathExists(path) && !pathMatches(fileData, path)) {
            System.out.println ("Error: The path and file path do not match.");
            return;
        }
        if (fileMap.containsKey (path)) {
            fileMap.get(path).add(fileData);
        } else {
            List<FileData> fileList = new ArrayList<> ();
            fileList.add(fileData);
            fileMap.put(path, fileList);
        }
    }
    public List<FileData> find(String path) {
        return fileMap.getOrDefault (path, new ArrayList<> ());
    }
    public List<FileData> filterBySize(long maxSize) {
        List<FileData> filteredFiles = new ArrayList<> ();
        for (List<FileData> fileList : fileMap.values ()) {
            for (FileData fileData : fileList) {
                if (fileData.getSize () <= maxSize) {
                    filteredFiles.add (fileData);
                }
            }
        }
        return filteredFiles;
    }
    public void remove(String path) {
        fileMap.remove(path);
    }
    public List<FileData> sortBySize() {
        List<FileData> allFiles = new ArrayList<> ();
        for (List<FileData> fileList : fileMap.values ()) {
            allFiles.addAll(fileList);
        }
        allFiles.sort ((file1, file2) -> Long.compare (file1.getSize (), file2.getSize ()));
        return allFiles;
    }
    private boolean pathExists(String path) {
        return fileMap.containsKey (path);
    }
    private boolean pathMatches(FileData fileData, String path) {
        return fileData.getPath().equals(path);
    }
}


