package com.flowershop;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final int PORT = 8080;
    private static final String RESOURCE_PATH = "src/main/resources/public";

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        
        // Handler cho file tĩnh (HTML, CSS, JS)
        server.createContext("/", new StaticFileHandler());
        
        // Handler cho API (có thể mở rộng sau)
        server.createContext("/api/", new ApiHandler());
        
        server.setExecutor(null);
        server.start();
        
        System.out.println("Server đang chạy tại: http://localhost:" + PORT);
        System.out.println("Nhấn Ctrl+C để dừng server");
    }

    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestPath = exchange.getRequestURI().getPath();
            
            // Nếu là root, trả về index.html
            if (requestPath.equals("/")) {
                requestPath = "/index.html";
            }
            
            Path filePath = Paths.get(RESOURCE_PATH + requestPath);
            
            try {
                if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                    byte[] response = Files.readAllBytes(filePath);
                    String contentType = getContentType(filePath.toString());
                    
                    exchange.getResponseHeaders().set("Content-Type", contentType);
                    exchange.sendResponseHeaders(200, response.length);
                    
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response);
                    }
                } else {
                    sendError(exchange, 404, "File không tìm thấy");
                }
            } catch (Exception e) {
                sendError(exchange, 500, "Lỗi server: " + e.getMessage());
            }
        }
    }

    static class ApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            
            String response = "{\"message\": \"Chào mừng đến API Flower Shop\"}";
            
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    private static String getContentType(String fileName) {
        if (fileName.endsWith(".html")) return "text/html";
        if (fileName.endsWith(".css")) return "text/css";
        if (fileName.endsWith(".js")) return "application/javascript";
        if (fileName.endsWith(".json")) return "application/json";
        if (fileName.endsWith(".png")) return "image/png";
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return "image/jpeg";
        if (fileName.endsWith(".gif")) return "image/gif";
        if (fileName.endsWith(".svg")) return "image/svg+xml";
        return "text/plain";
    }

    private static void sendError(HttpExchange exchange, int statusCode, String message) throws IOException {
        String response = "<html><body><h1>" + statusCode + "</h1><p>" + message + "</p></body></html>";
        byte[] responseBytes = response.getBytes();
        
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
}
