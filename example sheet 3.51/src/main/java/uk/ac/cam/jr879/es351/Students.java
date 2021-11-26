package uk.ac.cam.jr879.es351;

import java.util.Map;
import java.util.TreeMap;

public class Students {

    TreeMap<String, Float> students;

    public Students(TreeMap<String, Float> students) {
        this.students = students;
    }

    public String[] getNames() {
        return students.keySet().toArray(String[]::new);
    }

    public String[] topPercent(float P) {
        int number = (int)(students.size() * P / 100);
        return students.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).limit(number).toArray(String[]::new);
    }

    public Float medianMark() {
        int middle = (students.size() - 1) / 2;
        return (Float)students.values().stream().sorted().toArray()[middle];
    }
}
