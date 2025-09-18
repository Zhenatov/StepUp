# StepUp
Пришлось использовать liquibase, т.к. последняя версия (11.12.0) flyway, доступная в Maven Repository, не поддерживает postgresql 14 версии

# Документация

## Оглавление
1. [Структура сообщений](#структура-сообщений)
2. [Примеры сообщений](#примеры-сообщений)
3. [Список методов API](#список-методов-api)

---

## Структура сообщений

**users** *(UsersDto)*  
• **id**: Long  
• **username**: String  
• **paymentDtoList**: List\<PaymentDto>

└── **payments** *(PaymentDto)*  
&emsp;• **id**: Long  
&emsp;• **productsDtoList**: List\<ProductsDto>  
&emsp;• **sum**: Double  
&emsp;• **userId**: Long

&emsp;└── **products** *(ProductsDto)*  
&emsp;&emsp;• **id**: Long  
&emsp;&emsp;• **accountNumber**: Long  
&emsp;&emsp;• **balance**: Double  
&emsp;&emsp;• **productType**: String

---

## Примеры сообщений

### Пример UsersDto
```json
{
  "id": 1,
  "username": "alpha",
  "paymentDtoList": [
    {
      "id": 101,
      "productsDtoList": [
        {
          "id": 201,
          "accountNumber": 1234567890,
          "balance": 1500.75,
          "productType": "CARD"
        },
        {
          "id": 202,
          "accountNumber": 9876543210,
          "balance": 250.00,
          "productType": "ACCOUNT"
        }
      ],
      "sum": 1750.75,
      "userId": 1
    },
    {
      "id": 102,
      "productsDtoList": [
        {
          "id": 203,
          "accountNumber": 1111222233,
          "balance": 500.00,
          "productType": "CARD"
        },
        {
          "id": 204,
          "accountNumber": 4444555566,
          "balance": 300.00,
          "productType": "ACCOUNT"
        }
      ],
      "sum": 800.00,
      "userId": 1
    }
  ]
}
```

### Пример PaymentDto
```json
{
  "id": 101,
  "productsDtoList": [
    {
      "id": 201,
      "accountNumber": 1234567890,
      "balance": 1500.75,
      "productType": "CARD"
    },
    {
      "id": 202,
      "accountNumber": 9876543210,
      "balance": 250.00,
      "productType": "ACCOUNT"
    }
  ],
  "sum": 1750.75,
  "userId": 1
}
```

### Пример ProductsDto
```json
{
  "id": 301,
  "accountNumber": 5555555555,
  "balance": 999.99,
  "productType": "CARD"
}
```


---

## Список методов API

**/v1**  
└─ **/payment**  
&emsp;└─ **/pay** *(MethodType:POST; RequestBody:PaymentDto; ResponseBody:HttpsStatus)*  
&emsp;└─ **/search**  
&emsp;&emsp;└─ **all-product**  *(MethodType:Get; RequestParam:Long; ResponseBody:List<ProductsDto>)*  
&emsp;&emsp;└─ **all-payments**  *(MethodType:Get; RequestParam:Long; ResponseBody:List<PaymentDto>)*  
└─ **/users**  
&emsp;&emsp;└─ **{userId}**  *(MethodType:Get; PathVariable:Long; ResponseBody:UsersDto)*  
└─ **/products**  
&emsp;&emsp;└─ **/search**  
&emsp;&emsp;&emsp;└─ **/id**  *(MethodType:Get; RequestParam:Long; ResponseBody:ProductsDto)*  
&emsp;&emsp;&emsp;└─ **/user-id**  *(MethodType:Get; RequestParam:Long; ResponseBody:List<ProductsDto>)*  
