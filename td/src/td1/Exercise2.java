package td1;

import enstabretagne.base.logger.IRecordable;
import enstabretagne.base.logger.Logger;

public class Exercise2 {
    public static void main(String[] args) {
        Logger.load();
        Logger.Information(null, "information function", "informative message");
        Logger.Warning("Here is a warning", "function string", "some message");
        //Logger.Fatal("A fatal error logged", "something", "a message");
        Dog d = new Dog();
        Logger.Data(d);
        Logger.Data(new IRecordable() {
            @Override
            public String[] getTitles() {
                return new String[]{"Nom", "Prénom", "Age"};
            }

            @Override
            public String[] getRecords() {
                return new String[]{"VERRON", "Olivier", "900"};
            }

            @Override
            public String getClassement() {
                return "Personnes";
            }
        });
        Logger.Information(null, null, "add some data from td1.Dog class");
        Logger.Terminate();
    }
}
