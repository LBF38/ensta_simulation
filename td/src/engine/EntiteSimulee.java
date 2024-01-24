package engine;

import java.util.ArrayList;

/**
 * Entité simulée.
 * A des propriétés et doit être initialisée.
 */
public class EntiteSimulee {
    private final InitData initData;
    private SimuEngine engine;

    public EntiteSimulee(SimuEngine engine, InitData initData) {
        this.engine = engine;
        this.engine.addEntiteSimulee(this);
        this.initData = initData;
    }

    public void addEvent(SimEvent event) {
        this.engine.addEvent(event);
    }
}
