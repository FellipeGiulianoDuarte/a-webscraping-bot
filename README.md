# ShoppingBot

- **browser: Browser**
- **productSelector: ProductSelector**
- **emailSender: EmailSender**

## Methods

- `runAutomation(): void`

# ProductSelector

- **browser: Browser**
- **selectedProducts: List<Product>**

## Methods

- `searchAndSelectProducts(keywords: String, n: int): void`
- `openProductDetails(): void`
- `getSelectedProducts(): List<Product>`

# EmailSender

- **selectedProducts: List<Product>**

## Methods

- `emailProductDetails(email: String): void`

# BrowserManager

- **instance : BrowserContext**

# Product

- **title: String**
- **price: double**

## Methods

- `getTitle(): String`
- `getPrice(): double`
