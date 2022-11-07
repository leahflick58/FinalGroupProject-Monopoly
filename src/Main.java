import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Properties> propertyCards = new HashMap<>();
        propertyCards.put("Beans on Broad", new Properties(100, 500, 200));
        System.out.println(propertyCards);
    }
}