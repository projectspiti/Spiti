## Run Tests

```powershell
mvn test
```

## Run App

```powershell
mvn spring-boot:run
```

## Adapter Pattern Sample API

```http
POST /api/checkout/pay
Content-Type: application/json
```

```json
{
  "orderId": "ORDER-101",
  "userId": 501,
  "amountInRupees": 485,
  "paymentMethod": "UPI"
}
```
