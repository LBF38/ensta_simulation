package td2_1;

import enstabretagne.base.logger.CategoriesGenerator;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;

public class CheckRandom {
    public static void main(String[] args) {
        Logger.load();
        MoreRandom random = new MoreRandom();
        random.setSeed(123456789);
        CategoriesGenerator categories = new CategoriesGenerator(-10, 10, 10, 1, 1);
        Logger.DataSimple("Gaussian", "Segment", "Value");
        for (int i = 0; i < 1000; i++) {
            double value = random.nextGaussian();
            var cat = categories.getSegmentOf(value);
            if (cat == null)
                System.out.println("null");
            Logger.DataSimple("Gaussian", cat, value);
        }
        Logger.Terminate();
    }
}
