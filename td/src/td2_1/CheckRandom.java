package td2_1;

import enstabretagne.base.logger.CategoriesGenerator;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;

public class CheckRandom {
    public static void main(String[] args) {
        Logger.load();
        MoreRandom random = new MoreRandom();
        random.setSeed(123456789);
        CategoriesGenerator categories = new CategoriesGenerator(-10, 10, 10, 0, 1000);
        for (int i = 0; i < 1000; i++) {
            Logger.Data(random.nextGaussian(0, 1));
            // TODO: make it work !
        }
        Logger.Terminate();
    }
}
