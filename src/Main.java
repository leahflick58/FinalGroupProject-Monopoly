import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Property> propertyCards = new HashMap<>();
        propertyCards.put("Beans on Broad", new Property(100, 500, 200));
        System.out.println(propertyCards);
    }
}