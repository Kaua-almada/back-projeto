package Domain;

import org.json.JSONObject;
import java.util.List;

public class Product {
    private int id = 0;
    public String title = "";
    public String value = "";
    public String image = "";
    public String description = "";

    // Constructors
    public Product() {
    }

    public Product(String title, String value, String image, String description) {
        this.title = title;
        this.value = value;
        this.image = image;
        this.description = description;
    }

    public Product(int id, String title, String value, String image, String description) {
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("value", value);
        json.put("image", image);
        json.put("description", description);
        return json;
    }

    public static Product getProduct(int index, List<Product> productList) {
        if (index >= 0 && index < productList.size()) {
            return productList.get(index);
        } else {
            return null;
        }
    }

    public static List<Product> getAllProducts(List<Product> productList) {
        return productList;
    }

    public JSONObject listToJson(List<Product> productList) {
        JSONObject json = new JSONObject();
        if (!productList.isEmpty()) {
            int keyJson = 0;
            for (Product product : productList) {
                JSONObject valueJson = new JSONObject();
                valueJson.put("title", product.getTitle());
                valueJson.put("value", product.getValue());
                valueJson.put("image", product.getImage());
                valueJson.put("description", product.getDescription());
                json.put(String.valueOf(keyJson), valueJson);
                keyJson++;
            }
            return json;
        } else {
            return null;
        }
    }
}
