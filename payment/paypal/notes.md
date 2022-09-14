## overview
https://www.youtube.com/watch?v=0kmYgFVXgXE

### keys and ids
1. Client ID
2. Access token
3. Business account credentials

## Orders API (backend)
ref: https://developer.paypal.com/docs/api/orders/v2/

### payment flow: 
1. seller create an order with paypal api
2. seller authorizes the payment during checkout 
3. seller but you only transfer (capture) the funds to your acquiring bank when the goods or services are delivered.

### code

#### order
```
curl -v -X POST https://api-m.sandbox.paypal.com/v2/checkout/orders \
-H "Content-Type: application/json" \
-H "Authorization: Bearer Access-Token" \
-H "PayPal-Request-Id: 7b92603e-77ed-4896-8e78-5dea2050476a" \
-d '{
  "intent": "AUTHORIZE",
  "purchase_units": [
    {
      "reference_id": "d9f80740-38f0-11e8-b467-0ed5f89f718b",
      "amount": {
        "currency_code": "USD",
        "value": "100.00"
      }
    }
  ],
  "payment_source": {
    "card": {
      "number": "4111111111111111",
      "expiry": "2020-02",
      "name": "John Doe",
      "billing_address": {
        "address_line_1": "2211 N First Street",
        "address_line_2": "17.3.160",
        "admin_area_1": "CA",
        "admin_area_2": "San Jose",
        "postal_code": "95131",
        "country_code": "US"
      }
    },
    "stored_credential": {
      "payment_initiator": "MERCHANT",
      "payment_type": "RECURRING",
      "usage": "SUBSEQUENT",
      "previous_network_transaction_reference": {
        "id": "156GHJ654SFH543",
        "network": "VISA"
      }
    }
  }
}'
```
#### authorize (it may be combined with order part)
```
var express = require('express');
var request = require('request');
express()
  .post('/my-server/handle-approve/:id', function(req, res) {
    var OrderID = req.params.id;
    request.post('https://api-m.sandbox.paypal.com/v2/checkout/orders/' + OrderID + '/authorize', {
        headers: {
            Content-Type: "application/json",
            Authorization: "Bearer &lt;Access-Token&gt;",
            PayPal-Partner-Attribution-Id: &lt;BN-Code&gt;
        }
    }, function(err, response, body) {
        if (err) {
            console.error(err);
            return res.sendStatus(500);
        }
        res.json({
            status: 'success'
        });
    });
});
```
#### capture
```
curl -v -X POST https://api-m.sandbox.paypal.com/v2/payments/authorizations/5O190127TN364715T/capture \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <Access-Token>" \
  -d '{
    "payment_instruction": {
      "disbursement_mode": "INSTANT",
      "platform_fees": [
        {
          "amount": {
            "currency_code": "USD",
            "value": "2.00"
           }
        }
      ]
    }
  }'
```

## Web UI
### 1. Add the JavaScript SDK 
```<script src="https://www.paypal.com/sdk/js?client-id=YOUR_CLIENT_ID"></script>```

### 2. code
Create a PayPal Checkout component.
```
import React from "react";
import ReactDOM from "react-dom"
const PayPalButton = paypal.Buttons.driver("react", { React, ReactDOM });
class YourComponent extends React.Component {
  createOrder(data, actions) {
    return actions.order.create({
      purchase_units: [
        {
          amount: {
            value: "0.01",
          },
        },
      ],
    });
  }
  onApprove(data, actions) {
    return actions.order.capture();
  }
  render() {
    return (
      <PayPalButton
        createOrder={(data, actions) => this.createOrder(data, actions)}
        onApprove={(data, actions) => this.onApprove(data, actions)}
      />
    );
  }
}
```

## others
### sdk and config
https://developer.paypal.com/sdk/js/configuration/

Note: 
Load the JavaScript SDK directly from https://www.paypal.com/sdk/js. Don't include it in a bundle or host it yourself. For more information, see Load the JavaScript SDK from the PayPal server.

#### example
```
import { loadScript } from "@paypal/paypal-js";
loadScript({ "client-id": YOUR_CLIENT_ID })
.then((paypal) => {
    // start to use the PayPal JS SDK script
})
.catch((err) => {
    console.error("failed to load the PayPal JS SDK script", err);
});
```
