import java.util.HashMap;
import java.util.Map;

public class Railroads extends Property {
    Map<Integer, Integer> railRoadRent;

    public Railroads(String name, int rent, int price, int mortgage) {
        super(name, rent, price, mortgage);
        railRoadRent = new HashMap<>();
        railRoadRent.put(1, 25);
        railRoadRent.put(2, 50);
        railRoadRent.put(3, 100);
        railRoadRent.put(4, 200);
    }

    @Override
    public int getTotalRent(Player p) {
        int numRailroads = p.numberRailRoads();
        return railRoadRent.get(numRailroads);
    }
}
