package controller;

import Domain.Product;
import Services.RespostaEndPoint;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {
    private static List<Product> productsList = new ArrayList<>();
    static RespostaEndPoint res = new RespostaEndPoint();

    public static class ProdutosHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                List<Product> getAllFromList = Product.getAllProducts(productsList);
                if (!getAllFromList.isEmpty()) {
                    for (Product product : getAllFromList) {
                        System.out.println("title: " + product.getTitle());
                        System.out.println("value: " + product.getValue());
                        System.out.println("image: " + product.getImage());
                        System.out.println("description: " + product.getDescription());
                        System.out.println("-------------------------");
                        System.out.println();
                    }
                    res.enviarResponseJson(exchange, new Product().listToJson(getAllFromList), 200);
                }

            } else if ("POST".equals(exchange.getRequestMethod())) {
                try (InputStream requestBody = exchange.getRequestBody()) {
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Product product = new Product(
                            json.getString("title"),
                            json.getString("value"),
                            json.getString("image"),
                            json.getString("description")
                    );
                    productsList.add(product);
                    res.enviarResponseJson(exchange, product.toJson(), 200);
                    System.out.println("productsList: " + product.toJson());
                } catch (Exception e) {
                    String responseJson = e.toString();
                    // res.enviarResponseJson(exchange, responseJson, 200);
                }
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                // Handle PUT request
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                // Handle DELETE request
            } else if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, -1);
                exchange.close();
                return;
            } else {
                // Handle other methods
            }
        }
    }
}
