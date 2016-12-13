package check.parser;

public class Candidate implements Comparable<Candidate> {

    private String word;
    private long occurrenceCount;
    private boolean palindrome;

    public Candidate(String word, boolean palindrome) {
        this.word = word;
        this.palindrome = palindrome;
        this.occurrenceCount = 1;
    }

    public void increaseCount() {
        occurrenceCount++;
    }

    @Override
    public int compareTo(Candidate o) {
        int compare = Long.compare(o.occurrenceCount, occurrenceCount);
        return compare == 0 ? word.compareTo(o.word) : compare;
    }

    public String getWord() {
        return word;
    }

    public Long getOccurrenceCount() {
        return occurrenceCount;
    }

    public boolean isPalindrome() {
        return palindrome;
    }

}
