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

## Chain of Responsibility Pattern - Support Complaint Resolver

Support complaint requests move through a chain of handlers until the right team accepts the issue.

```text
PaymentSupportHandler -> KitchenSupportHandler -> DeliverySupportHandler -> RefundSupportHandler -> GeneralSupportHandler
```

```http
POST http://localhost:8080/api/support/complaints/resolve
Content-Type: application/json
```

```json
{
  "orderId": "ORDER-201",
  "issueType": "DELIVERY_DELAY",
  "description": "Lunch delivery is late"
}
```

Example response:

```json
{
  "orderId": "ORDER-201",
  "issueType": "DELIVERY_DELAY",
  "handledBy": "DeliverySupportHandler",
  "resolutionMessage": "Delivery support will track the rider and update the customer with a revised ETA"
}
```

Medical plan chain example:

```http
POST http://localhost:8080/api/medical-plan-router/route
```

## Command Pattern - Cart Commands

Cart actions are wrapped as command objects with `execute()`, `undo()`, and `idempotencyKey`.

```http
POST http://localhost:8080/api/carts/commands
POST http://localhost:8080/api/carts/CART-201/undo
```

## Template Method Pattern - Diet Plan Template

Diet plan creation uses a fixed skeleton:

```text
calcTarget -> pickMeals -> applyDayType -> applyIFWindow
```

`CutDietPlanTemplate` and `BulkDietPlanTemplate` override `pickMeals`.

```http
POST http://localhost:8080/api/diet-plan-templates/create
```

## Visitor Pattern - Order Reports

Same order object can generate different reports through visitors.

```http
POST http://localhost:8080/api/order-reports/generate
```

Supported report types:

- `KITCHEN_PREP`
- `INVOICE`
- `NUTRITION`

## Mediator Pattern - Kitchen Coordination

Kitchen order preparation is coordinated through one mediator instead of services calling each other directly.

```http
POST http://localhost:8080/api/kitchen/orders/coordinate
```

## Iterator Pattern - Meal Swap Suggestions

Meal swap suggestions are traversed through an iterator that hides filtering and storage details.

```http
POST http://localhost:8080/api/meal-swaps/suggestions
```
