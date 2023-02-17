## Все university cnt=198
https://stud-api.sabir.pro/universities/all

list of University

## Все dormitories cnt=218
https://stud-api.sabir.pro/dormitories/all

list of Dormitories

## Все events cnt=248
https://stud-api.sabir.pro/events/all

list of Events

Там много усторевших евентов. Нужно фильтровать по .details.dates

## Все reviews cnt=4
https://stud-api.sabir.pro/reviews

list of Review

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

## edit user
POST https://stud-api.sabir.pro/users

request: EditUser 

response: User

