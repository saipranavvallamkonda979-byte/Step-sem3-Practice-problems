import java.util.Arrays;

public class Problem5 {

    // Candidate class
    static class Candidate implements Comparable<Candidate> {

        private String name;
        private double cgpa;
        private int codingScore;

        // Constructor
        public Candidate(String name, double cgpa, int codingScore) {
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
        }

        // Getter methods
        public String getName() {
            return name;
        }

        public double getCgpa() {
            return cgpa;
        }

        public int getCodingScore() {
            return codingScore;
        }

        // Method 1: CGPA only
        static boolean isEligible(double cgpa) {
            return cgpa >= 7.5;
        }

        // Method 2: CGPA + coding score
        static boolean isEligible(double cgpa, int codingScore) {
            return cgpa >= 6.5 && codingScore >= 60;
        }

        // Composite score
        double getCompositeScore() {
            return cgpa * 10 + codingScore * 0.5;
        }

        // Compare candidates by composite score
        @Override
        public int compareTo(Candidate other) {
            return Double.compare(
                other.getCompositeScore(),
                this.getCompositeScore()
            );
        }
    }

    // Shortlist and rank
    static String shortlistAndRank(Candidate[] candidates) {

        Candidate[] shortlisted = new Candidate[candidates.length];
        int count = 0;

        // Find eligible candidates
        for (Candidate c : candidates) {

            if (Candidate.isEligible(c.getCgpa()) ||
                Candidate.isEligible(c.getCgpa(), c.getCodingScore())) {

                shortlisted[count] = c;
                count++;
            }
        }

        // Create final array
        Candidate[] finalList = Arrays.copyOf(shortlisted, count);

        // Sort using Comparable
        Arrays.sort(finalList);

        // Display result
        String result = "";

        for (int i = 0; i < finalList.length; i++) {

            result += (i + 1) + ". "
                    + finalList[i].getName()
                    + " ("
                    + finalList[i].getCompositeScore()
                    + ")";

            if (i < finalList.length - 1) {
                result += " | ";
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Candidate[] candidates = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };

        System.out.println(shortlistAndRank(candidates));
    }
}