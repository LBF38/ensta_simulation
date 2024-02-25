package tatooine.Workshop;

import enstabretagne.base.logger.IRecordable;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@ToRecord(name = "Distances")
public class Distances {
    private static final HashMap<List<InitWorkshop.WorkshopType>, LogicalDuration> walkingDurations = new HashMap<>();

    public static void log() {
        Logger.Information(null, "Distances - log", "Log the Walking durations");
//        Logger.DataSimple("Distances - log", "Workshop1", "Workshop2", "Duration");
        for (var entry : walkingDurations.entrySet()) {
            Logger.Data(new IRecordable() {
                @Override
                public String[] getTitles() {
                    return new String[]{"Workshop1", "Workshop2", "Duration"};
                }

                @Override
                public String[] getRecords() {
                    return new String[]{entry.getKey().get(0).toString(), entry.getKey().get(1).toString(), entry.getValue().toString()};
                }

                @Override
                public String getClassement() {
                    return "Classement - Distances";
                }
            });
            // ^ TODO: check if IRecordable could work ?
//            Logger.DataSimple("Distances - log", entry.getKey().get(0).toString(), entry.getKey().get(1).toString(), entry.getValue().toString());
        }
    }

    public static void setWalkingDuration(InitWorkshop.WorkshopType workshop1, InitWorkshop.WorkshopType workshop2, LogicalDuration duration) {
        walkingDurations.put(List.of(workshop1, workshop2), duration);
        walkingDurations.put(List.of(workshop2, workshop1), duration);
    }

    public static LogicalDuration getWalkingDuration(InitWorkshop.WorkshopType workshop1, InitWorkshop.WorkshopType workshop2) {
        var duration = walkingDurations.get(List.of(workshop1, workshop2));
        if (duration == null) {
            Logger.Warning(Distances.class, "Distances - getWalkingDuration", "No duration found for the workshops %s and %s".formatted(workshop1, workshop2));
            return LogicalDuration.ofMinutes(0);
        }
        return duration;
    }

    public static void main(String[] args) {
        System.out.println("Testing Distances");
        setWalkingDuration(InitWorkshop.WorkshopType.BAIN, InitWorkshop.WorkshopType.DOUCHE, LogicalDuration.ofMinutes(5));
        System.out.println(getWalkingDuration(InitWorkshop.WorkshopType.BAIN, InitWorkshop.WorkshopType.DOUCHE));
        System.out.println(getWalkingDuration(InitWorkshop.WorkshopType.DOUCHE, InitWorkshop.WorkshopType.BAIN));
        System.out.println(getWalkingDuration(InitWorkshop.WorkshopType.BAIN, InitWorkshop.WorkshopType.BAIN));
        System.out.println(getWalkingDuration(InitWorkshop.WorkshopType.HOME, InitWorkshop.WorkshopType.BAIN));
    }

    @ToRecord(name = "Keys")
    public Set<List<InitWorkshop.WorkshopType>> getKeys() {
        return walkingDurations.keySet();
    }

    @ToRecord(name = "Values")
    public Collection<LogicalDuration> getValues() {
        return walkingDurations.values();
    }
}
