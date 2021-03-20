package test.testmaster.devtools;

import de.tdrstudios.additional.debug.Point;
import de.tdrstudios.additional.debug.PointTrace;

public class Main {

    public static void main(String[] args) {
       new Main().to();
    }

    public void to() {
        System.out.println("1");
        new Point(Thread.currentThread()).point();
        System.out.println("2");
    }
}
