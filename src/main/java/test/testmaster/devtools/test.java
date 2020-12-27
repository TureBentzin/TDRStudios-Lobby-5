package test.testmaster.devtools;

import de.bentzin.tools.DevTools;
import de.bentzin.tools.task.Operation;
import de.bentzin.tools.time.Timing;

public class test {

    public static void main(String[] args) {

        Operation operation = new Operation("testOperation", false) {
            @Override
            public void run() {
                getLog().send("Ausgabe im Multi Thread! " + getOperationUUID());
            }
        };

        Timing timing = new Timing(5);
        DevTools.getOperationManager().executeOperation(operation , timing);
    }
}

