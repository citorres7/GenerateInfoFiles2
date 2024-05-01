import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateInfoFiles {

    public static void main(String[] args) {
        try {
            // Create files
            createSalesMenFile(10, "SalesmanInfo.txt");
            createProductsFile(20, "ProductsInfo.txt");

            // Example vendor sales data
            List<Sale> sales = new ArrayList<>();
            sales.add(new Sale("John", 100.50));
            sales.add(new Sale("Mary", 75.25));
            sales.add(new Sale("Peter", 150.75));

            // Generate sales report CSV file
            generateSalesReport(sales, "sales_report.csv");

            System.out.println("Files generated successfully.");
        } catch (IOException e) {
            System.err.println("Error generating files: " + e.getMessage());
        }
    }

    // Method to generate random salesman information files
    private static void createSalesMenFile(int salesmanCount, String fileName) throws IOException {
        Random random = new Random();
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < salesmanCount; i++) {
                String typeDocument = "Type" + (random.nextInt(10) + 1);
                String numberDocument = "Number" + (random.nextInt(1000) + 1);
                writer.println(typeDocument + ";" + numberDocument);
                for (int j = 0; j < 3; j++) {
                    String productId = "ProductId" + (random.nextInt(20) + 1);
                    int amount = random.nextInt(10) + 1;
                    writer.println(productId + ";" + amount);
                }
            }
        }
    }

    // Method to generate random product information file
    private static void createProductsFile(int productCount, String fileName) throws IOException {
        Random random = new Random();
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < productCount; i++) {
                String productId = "ProductId" + (random.nextInt(20) + 1);
                String productName = "ProductName" + (random.nextInt(100) + 1);
                double price = random.nextDouble() * 100; // Random price between 0 and 100
                writer.println(productId + ";" + productName + ";" + price);
            }
        }
    }

    // Method to generate the sales report CSV file
    private static void generateSalesReport(List<Sale> sales, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write headers to the CSV file
            writer.append("Vendor");
            writer.append(",");
            writer.append("Sales Amount");
            writer.append("\n");

            // Write sales data to the CSV file
            for (Sale sale : sales) {
                writer.append(sale.getVendor());
                writer.append(",");
                writer.append(String.valueOf(sale.getSalesAmount()));
                writer.append("\n");
            }

            System.out.println("CSV file generated successfully.");
        } catch (IOException e) {
            System.err.println("Error generating CSV file: " + e.getMessage());
        }
    }
}

class Sale {
    private String vendor;
    private double salesAmount;

    public Sale(String vendor, double salesAmount) {
        this.vendor = vendor;
        this.salesAmount = salesAmount;
    }

    public String getVendor() {
        return vendor;
    }

    public double getSalesAmount() {
        return salesAmount;
    }
}