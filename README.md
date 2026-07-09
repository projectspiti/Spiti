## Run Tests

```powershell
mvn test
```

## Run App

```powershell
mvn spring-boot:run
```

## Composite Pattern - Weekly Plan Summary

Meal, day plan, and week plan expose the same total methods.

```http
POST http://localhost:8080/api/plan-summary/weekly
Content-Type: application/json
```

```json
{
  "weekName": "Week 1",
  "days": [
    {
      "dayName": "Monday",
      "meals": [
        {
          "mealName": "Idli bowl",
          "calories": 320,
          "priceInRupees": 90
        },
        {
          "mealName": "Paneer bowl",
          "calories": 560,
          "priceInRupees": 275
        }
      ]
    }
  ]
}
```

## State Pattern - Order Lifecycle

Order state changes are explicit. Each state controls which next action is valid.

```text
CONFIRMED -> PREPARING -> OUT_FOR_DELIVERY -> DELIVERED
```

```http
POST http://localhost:8080/api/orders/ORDER-101/state/confirm
POST http://localhost:8080/api/orders/ORDER-101/state/prepare
POST http://localhost:8080/api/orders/ORDER-101/state/out-for-delivery
POST http://localhost:8080/api/orders/ORDER-101/state/deliver
```

## Observer Pattern - Order Status Notifications

Order status changes publish one event to multiple observers. The order lifecycle service does not directly know how kitchen dashboard, delivery partner, or user notification logic works.

Observers currently registered:

- `KitchenDashboardObserver`
- `DeliveryPartnerObserver`
- `UserPushNotificationObserver`

Example:

```http
POST http://localhost:8080/api/orders/ORDER-101/state/confirm
```

Response includes observer notifications:

```json
{
  "orderId": "ORDER-101",
  "previousStatus": null,
  "currentStatus": "CONFIRMED",
  "message": "Order confirmed",
  "observerNotifications": [
    {
      "observerName": "KitchenDashboard",
      "message": "Kitchen dashboard updated for order ORDER-101 with status CONFIRMED"
    }
  ]
}
```
