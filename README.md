# 简易点菜下单系统（Java/Spring Boot）

这是一个用于演示“客户点菜下单、商家接单上菜、商家维护菜单定价与图片提示”的简易后端项目框架。

## 1. 项目结构

```text
ordering-system
├── pom.xml
├── src/main/java/com/example/orderingsystem
│   ├── OrderingSystemApplication.java
│   ├── controller
│   │   ├── MenuController.java
│   │   └── OrderController.java
│   ├── dto
│   │   ├── CreateOrderRequest.java
│   │   ├── UpdateImageRequest.java
│   │   └── UpdatePriceRequest.java
│   ├── entity
│   │   ├── CustomerOrder.java
│   │   └── MenuItem.java
│   ├── repository
│   │   ├── CustomerOrderRepository.java
│   │   └── MenuItemRepository.java
│   └── service
│       ├── MenuService.java
│       └── OrderService.java
└── src/main/resources
    ├── application.yml
    ├── schema.sql
    └── data.sql
```

## 2. Java 文件职责

- `entity`：定义数据库实体（菜品、订单）。
- `repository`：数据库访问接口（JPA）。
- `service`：核心业务逻辑。
- `controller`：对外 API。
- `dto`：请求参数校验和封装。

## 3. 核心接口设计

### 客户端（下单）

1. `GET /api/menu-items`：查看菜单。
2. `POST /api/orders`：提交订单。
3. `GET /api/orders`：查看所有订单（可用于简单后台页）。

下单请求示例：

```json
{
  "menuItemId": 1,
  "quantity": 2,
  "customerName": "张三"
}
```

### 商家端（接单、上菜、改价、传图）

1. `PATCH /api/orders/{orderId}/receive`：商家确认收到订单。
2. `PATCH /api/orders/{orderId}/serve`：商家标记已上菜。
3. `PATCH /api/menu-items/{menuItemId}/price`：修改菜品价格。
4. `PATCH /api/menu-items/{menuItemId}/image`：更新菜品图片地址。
5. `GET /api/orders/received`：查看已接单待上菜订单。

改价请求示例：

```json
{
  "price": 30.5
}
```

上传图片请求示例：

```json
{
  "imageUrl": "https://example.com/new-image.jpg"
}
```

## 4. 数据库设置建议

项目默认使用 H2 内存数据库，方便快速演示：

- JDBC URL：`jdbc:h2:mem:orderdb;MODE=MYSQL;DB_CLOSE_DELAY=-1`
- 用户名：`sa`
- H2 控制台：`/h2-console`

线上建议切换 MySQL/PostgreSQL：

1. 修改 `application.yml` 的 `spring.datasource.*`。
2. 将 `ddl-auto` 调整为 `validate` 或使用 Flyway/Liquibase。
3. 给 `customer_order.status`、`customer_order.created_at` 添加索引提升查询性能。

## 5. 提示文字（上传菜单图 / 修改价格）

在 `MenuItem` 中预留了以下字段，前端可直接展示：

- `imageUploadHint`：上传菜单图片提示。
- `priceEditHint`：修改价格提示。

同时在服务层更新时会刷新提示文案：

- 更新图片后提示：`请上传清晰菜品图（建议1:1比例，大小不超过2MB）。`
- 修改价格后提示：`修改价格后请通知前台和线上渠道，避免前后端价格不一致。`

## 6. 运行方式

```bash
mvn spring-boot:run
```

启动后可使用 Postman/curl 调试以上接口。
