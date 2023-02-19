## Все university cnt=198
https://stud-api.sabir.pro/universities/all

list of University

## Одно university 
https://stud-api.sabir.pro/universities/{id}

University

## Все dormitories cnt=218
https://stud-api.sabir.pro/dormitories/all

list of Dormitories

## Одно dormitory
https://stud-api.sabir.pro/dormitories/{id}

Dormitory

У меня некоторые так загружатся не хотели. И оф. сайт их не использует

## Все events cnt=248
https://stud-api.sabir.pro/events/all

list of Events

Там много усторевших евентов. Нужно фильтровать по .details.dates

## Все reviews cnt=4
https://stud-api.sabir.pro/reviews

list of Review

## Все labs cnt=153
https://stud-api.sabir.pro/labs/all

list of Lab

## Lab
https://stud-api.sabir.pro/labs/{id}

Lab

## Получить себя
https://stud-api.sabir.pro/me

User в types.ts

## Логин
POST https://stud-api.sabir.pro/users/login

request: {"email": string,"password": string}

response: {"token":"r:9556c469ab83d6c5a73c0f345e9fdc70"} 

## Регистрация
POST https://stud-api.sabir.pro/users/signup

request: {"email": string,"password": string}

response: {"token":"r:9556c469ab83d6c5a73c0f345e9fdc70"} 

## Запрос сразу при регистрации
PUT https://stud-api.sabir.pro/users

request: {"name":"Что","phone":"где","studentRoleType":"student"}

response: User

## Edit User
POST https://stud-api.sabir.pro/users

request: EditUser 

response: User

## Бронирование жилья
POST https://stud-api.sabir.pro/bookings

request: DormitoryBookingRequest

response: DormitoryBooking

## Получить бронирования жилья
https://stud-api.sabir.pro/bookings/my

response: [DormitoryBooking]

## Бронирование эвентов
POST https://stud-api.sabir.pro/event-bookings

request: EventBookingRequest

response: EventBooking

## Бронирования эвентов
https://stud-api.sabir.pro/event-bookings/my

response: [EventBooking]

## Загрузить фотку
POST https://stud-api.sabir.pro/file/upload

```
-----------------------------267743659616070297051075925392
Content-Disposition: form-data; name="file"; filename="Screenshot 2023-02-01 at 11.08.34.png"
Content-Type: image/png
```

response: {"url":"https://stud-files.sabir.pro/files/uKGxGlxIQC-6a95693f7b294177ee38135eb27d770ede4c098b4432d01872588f7ae11a5b42.png","hash":"6a95693f7b294177ee38135eb27d770ede4c098b4432d01872588f7ae11a5b42"}


## Оставить отзыв
POST https://stud-api.sabir.pro/reviews

request: ReviewRequest

response: Review

## Отменить бронирование
PUT https://stud-api.sabir.pro/bookings
request: 
{"id":"C0LWkzghs0","status":"canceled","dates":{"from":"2023-02-19","to":"2023-02-19"}}
