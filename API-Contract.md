**POST /login**
----
Login to the application

* **Data Params**

  | Name           | Type   | Description                        |
    |----------------|--------|------------------------------------|
  | email          | string | User's email                       |
  | password       | string | User's password                    |
* **Headers**

  | Name           | Type   | Description                        |
    |----------------|--------|------------------------------------|
  | Content-Type   | string | `application/json`                 |
* **Success Response:**
* **Code:** 200
  **Content:**
  ```json
  {
    status: "success",
    message: String,
    data: {
        id: String,
        name: String,
        email: String,
        token: String
    }
  }
  ```
* **Error Response:**
    * **Code:** 400
      **Content:**
      ```json
      {
        status: "error",
        message: String,
        data: null
      }
      ```

**POST /register**
----
Register to the application

* **Data Params**

  | Name           | Type   | Description                        |
    |----------------|--------|------------------------------------|
  | name           | string | User's name                        |
  | email          | string | User's email                       |
  | password       | string | User's password                    |
* **Headers**

  | Name           | Type   | Description                        |
    |----------------|--------|------------------------------------|
  | Content-Type   | string | `application/json`                 |
* **Success Response:**
* **Code:** 200
  **Content:**
  ```json
  {
    status: "success",
    message: String,
    data: null
  }
  ```
* **Error Response:**
    * **Code:** 400
      **Content:**
      ```json
      {
        status: "error",
        message: String,
        data: null
      }
      ```

**GET /nutrients?date=:date**
----
Get nutrients for a daily meal

* **Query Params**
  | Name | Type | Description |
  |------|--------|------------------------------|
  | date | string | The date in `YYYY-MM-DD` format |
* **Headers**

  | Name           | Type   | Description                        |
    |----------------|--------|------------------------------------|
  | Content-Type   | string | `application/json`                 |
  | Authorization  | string | `Bearer <OAuth Token>`             |
* **Success Response:**
* **Code:** 200
  **Content:**
  ```json
  {
    status: "success",
    message: String,
    data: [
        {
            id: String,
            user_id: String,
            food_name: String,
            carbohydrate: Float,
            proteins: Float,
            fat: Float,
            calories: Float,
            created_at: Date,
        },
        // Another object
    ]
  }
  ```
* **Error Response:**
    * **Code:** 400
      **Content:**
      ```json
      {
        status: "error",
        message: String,
        data: null
      }
      ```

**POST /predict**
----
Predict nutrients for a meal

* **Data Params**

  | Name  | Type | Description       |
  |-------|------|-------------------|
  | image | file | Image of the food |

* **Headers**

  | Name           | Type   | Description                        |
    |----------------|--------|------------------------------------|
  | Content-Type   | string | `multipart/form-data`              |
  | Authorization  | string | `Bearer <OAuth Token>`             |
* **Success Response:**
* **Code:** 200
  **Content:**
  ```json
  {
    status: "success",
    message: String,
    data: {
        id: String,
        user_id: String,
        food_name: String,
        carbohydrate: Float,
        proteins: Float,
        fat: Float,
        calories: Float,
        created_at: Date,
    }
  }
  ```
* **Error Response:**
    * **Code:** 400
      **Content:**
      ```json
      {
        status: "error",
        message: String,
        data: null
      }
      ```
