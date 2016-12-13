package check.parser;

import java.io.*;
import java.util.*;

import static java.lang.String.format;
import static java.util.Collections.sort;

public class Main {

    private static final String FILE_LOCATION = "D:\\TEST\\test.txt";
    private static final int N = 10;

    public static void main(String[] args) {
        int topSize;
        String fileLocation;
        // read input or use defaults
        if (args.length == 2) {
            topSize = Integer.valueOf(args[0]);
            fileLocation = args[1];
        } else {
            topSize = N;
            fileLocation = FILE_LOCATION;
        }
        Map<String, Candidate> candidates = new HashMap<>();
        try (FileReader reader = new FileReader(fileLocation); BufferedReader buffer = new BufferedReader(reader)) {
            String line;
            while ((line = buffer.readLine()) != null) {
                processLine(line, candidates);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no test file, or path is incorrect");
        } catch (IOException e) {
            throw new RuntimeException("Can't read line from file");
        }
        if (!candidates.isEmpty()) {
            List<Candidate> allCandidates = new ArrayList<>(candidates.values());
            // get first N from sorted list:
            sort(allCandidates);
            List<Candidate> topCandidates = allCandidates.subList(0, Math.min(allCandidates.size(), topSize));
            int i = 0;
            for (Candidate candidate : topCandidates) {
                System.out.println(format("Candidate(%d) -> '%s', count=%d, isPalindrome (%s)", i, candidate.getWord(),
                        candidate.getOccurrenceCount(), candidate.isPalindrome() ? "true" : "false"));
                i++;
            }
        }
    }

    protected static void processLine(String line, Map<String, Candidate> candidates) {
        if (line.isEmpty()) {
            return;
        }
        String[] parts = line.split(" ");
        for (String part : parts) {
            if (candidates.containsKey(part)) {
                candidates.get(part).increaseCount();
            } else {
                candidates.put(part, new Candidate(part, checkPalindrome(part)));
            }
        }
    }

    protected static boolean checkPalindrome(String value) {
        for (int index = 0; index < (value.length() / 2); ++index) {
            if (value.charAt(index) != value.charAt(value.length() - index - 1)) {
                return false;
            }
        }
        return true;
    }

}
