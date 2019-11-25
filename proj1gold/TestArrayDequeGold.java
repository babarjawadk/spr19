import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void TestStudentArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String message = "";

        for (int i = 0; i < 1000; i++) {
            int r = StdRandom.uniform(40);

            //assertEquals(sad.size(), ads.size());
            if (ads.size() == 0 && r < 20) {
                r = 25;
            }

            if (r >= 0 && r < 10) {
                message += "removeFirst()\n";
                Integer expected = sad.removeFirst();
                Integer actual = ads.removeFirst();
                assertEquals(message, expected, actual);

            } else if (r >= 10 && r < 20) {
                message += "removeLast()\n";
                Integer expected = sad.removeLast();
                Integer actual = ads.removeLast();
                assertEquals(message, expected, actual);
            } else if (r >= 20 && r < 30) {
                message += "addFirst(" + i + ")\n";
                sad.addFirst(i);
                ads.addFirst(i);

                /*Integer expected = sad.get(0);
                Integer actual = ads.get(0);
                assertEquals(expected, actual);*/
            } else if (r >= 30 && r < 40) {
                message += "addLast(" + i + ")\n";
                sad.addLast(i);
                ads.addLast(i);
                /*assertEquals(sad.size(), ads.size());
                Integer expected = sad.get(sad.size() - 1);
                Integer actual = ads.get(ads.size() - 1);
                assertEquals(expected, actual);*/
            }


        }







    }
}
