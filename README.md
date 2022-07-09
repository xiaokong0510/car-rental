# Car-rental Solution design

Running at: [1.15.245.16:10000](1.15.245.16:10000)

## WEB

A working customer facing web client online was provided to quickly test the service

![image-20220709124938242](http://image.kongxiao.top/20220709124947.png)

![image-20220709124951646](http://image.kongxiao.top/20220709124953.png)

## API

### car-info

get all detail car information. 

- URL：/v1/car/car-info

- METHOD:  GET

- response：

  ```json
  {
      "code": 200,
      "msg": "success",
      "data": [
          {
              "carId": 1,
              "carModel": "Toyota Camry",
              "inStock": 2
          },
          {
              "carId": 2,
              "carModel": "BMW 650",
              "inStock": 2
          }
      ]
  }
  ```

### rent-info

get all rent information by userName 

- URL：/v1/car/rent-info

- METHOD:  GET

- response：

  ```json
  {
      "code": 200,
      "msg": "success",
      "data": [
          {
              "rentInfoId": 1,
              "carId": 1,
              "carModel": "Toyota Camry",
              "num": 1,
              "state": 2,
              "startTime": "2022-06-26",
              "endTime": "2022-07-06",
              "actualReturnTime": "2022-06-26"
          }
      ]
  }
  ```



### rent car

get all rent information by userName 

- URL：/v1/car/rent

- METHOD:  POST

- rqeuset：

  ```json
  {
  	"userName":"test",
  	"carId":1,
  	"rentNum":1,
  	"startTime":"2022-06-26",
  	"rentDay":10
  }
  ```

- response：

  ```json
  {
      "code": 200,
      "msg": "success",
      "data": {
          "carId": 1,
          "carModel": "Toyota Camry",
          "num": 1,
          "rentInfoId": 2,
          "startTime": "2022-06-26",
          "endTime": "2022-07-06"
      }
  }
  ```

### return car

get all rent information by userName 

- URL：/v1/car/return

- METHOD:  POST

- rqeuset：

  ```json
  {
  	"userName":"test",
  	"rentId":2
  }
  ```

- response：

  ```json
  {
      "code": 200,
      "msg": "success",
      "data": {
          "carId": 1,
          "carModel": "Toyota Camry",
          "num": 1,
          "state": 2,
          "startTime": "2022-06-26",
          "endTime": "2022-07-06",
          "actualReturnTime": "2022-06-26"
      }
  }
  ```

## Reruen Code Definition

| code  | descrpiton |
| ----- | ---------- |
| 200   | success |
| 40001 | validate failed |
| 40002 | this car not exists |
| 40003 | the stock of this car is insufficient |
| 40004 | this rent not exists |
| 40005 | car has been return |
| 500 |        unknown error    |

