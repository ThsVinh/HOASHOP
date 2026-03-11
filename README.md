# Flower Shop - Dự Án Java

## Giới thiệu
Đây là một ứng dụng Java đơn giản có khả năng phục vụ các tệp HTML, CSS, JavaScript và JSON.

## Yêu cầu
- Java 11 trở lên
- Maven 3.6+

## Cấu trúc Dự Án

```
flowershop/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/flowershop/
│   │   │       └── Main.java          # File chính của ứng dụng
│   │   └── resources/
│   │       └── public/
│   │           ├── index.html         # Trang chủ
│   │           ├── style.css          # Stylesheet
│   │           └── script.js          # JavaScript
│   └── test/
│       └── java/
│           └── com/flowershop/
├── pom.xml                             # Maven configuration
└── README.md
```

## Hướng dẫn Sử Dụng

### 1. Biên dịch Dự Án
```bash
mvn clean compile
```

### 2. Chạy Ứng Dụng
```bash
mvn exec:java -Dexec.mainClass="com.flowershop.Main"
```

Hoặc chạy trực tiếp qua IDE của bạn:
- Nhấp chuột phải vào file `Main.java`
- Chọn "Run 'Main.main()'"

### 3. Truy Cập Ứng Dụng
Mở trình duyệt và truy cập:
```
http://localhost:8080
```

## Tính Năng Hiện Tại

✅ Phục vụ file HTML, CSS, JavaScript tĩnh
✅ Hỗ trợ nhiều loại Content-Type
✅ Xử lý lỗi 404 và 500
✅ API endpoint cơ bản
✅ Responsive design

## Mở Rộng Dự Án

### Thêm Database
Thêm dependency vào `pom.xml`:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Sử Dụng Spring Boot
Thay thế `pom.xml` để sử dụng Spring Boot:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.0</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

### Thêm Logging
```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

## Những Tệp HTML Có Thể Thêm
- Tạo file mới trong `src/main/resources/public/`
- Truy cập qua `http://localhost:8080/filename.html`

### Ví dụ:
```
src/main/resources/public/
├── index.html         # http://localhost:8080/
├── about.html         # http://localhost:8080/about.html
├── products.html      # http://localhost:8080/products.html
├── contact.html       # http://localhost:8080/contact.html
└── images/
    └── logo.png       # http://localhost:8080/images/logo.png
```

## Xử Lý Yêu Cầu API

Thêm các endpoint mới trong class `ApiHandler`:

```java
if (path.equals("/api/products")) {
    // Trả về danh sách sản phẩm
} else if (path.equals("/api/contact")) {
    // Xử lý form liên hệ
}
```

## Dừng Ứng Dụng
Nhấn `Ctrl+C` trong terminal để dừng server.

## Liên Hệ & Hỗ Trợ
Để mở rộng chức năng:
- Thêm database
- Tích hợp authentication
- Triển khai lên cloud

---

**Tạo lúc**: 2026-03-11
**Phiên bản**: 1.0-SNAPSHOT
