// https://stud-api.sabir.pro/me
interface User {
  username: "dan.sot.2003@gmail.com";
  email: "dan.sot.2003@gmail.com";
  userRole: "user";
  name?: "Сотников Даниил Сергеевич";
  phone?: "+79069469277";
  studentRoleType?: "student";
  createdTimestamp: 1674911335152;
  starredEvents?: ["mWNp6gGkby"]; // list of event ids
  updatedTimestamp: 1676646980795;
  starredDormitories?: ["7Zbl2muIe4"]; // list of dormitory ids
  firstName?: "Даниил";
  lastName?: "Сотников";
  middleName?: "Сергеевич";
  birthday?: "24.12.2003";
  departureCity?: "Москва";
  gender?: "male";
  WoS?: "QL";
  WoS1?: "PO";
  socialUrl?: "vk.com";
  universityName?: "ВШЭ";
  avatar?: "https://stud-files.sabir.pro/files/PVbA7soK4S-6a95693f7b294177ee38135eb27d770ede4c098b4432d01872588f7ae11a5b42.png";
  id: "PVbA7soK4S";
  timestamp: 1674911335152;
}

interface EditUser {
  id: "PVbA7soK4S";
  email: "dan.sot.2003@gmail.com";
  firstName: "Даниил";
  lastName: "Сотников";
  middleName: "Сергеевич";
  gender: "male";
  departureCity: "Москва";
  phone: "+79069469277";
  socialUrl: "vk.com";
  universityName: "ВШ";
  avatar: "https://stud-files.sabir.pro/files/PVbA7soK4S-6a95693f7b294177ee38135eb27d770ede4c098b4432d01872588f7ae11a5b42.png";
  birthday: "24.12.2003";
  WoS: "QL";
  WoS1: "PO";
  studentRoleType: "student";
}

// https://stud-api.sabir.pro/dormitories/7Zbl2muIe4
// dormitory
interface Dormitory {
  userId: string; // "PtA4pFzxry"
  universityId: string; //"G5qYEw8lls"
  createdTimestamp: number; // 1648790889430
  details: DormitoryDetails;
  rooms: {
    // id: Room
    [key in string]: Room;
  };
  onModeration: boolean; // false
  id: "7Zbl2muIe4";
  timestamp: 1648790889430;
  updatedTimestamp: 1669816931225;
}

interface DormitoryDetails {
  "main-info": {
    name: "Студенческое общежитие ПВГУС";
    city: "Тольятти";
    street?: "ул. Ленинградская";
    houseNumber?: "29";
    coordinates?: { lat: "53.503659"; lng: "49.400055" };
    mealPlan: "nothing";
    maxDays: "30";
    minDays: "2";
    photos?: [
      "https://stud-files.sabir.pro/files/PtA4pFzxry-e6e200f4363190c4400ba0dba4958a16b719f4e33bc5e14f50e4ff6fdf8b871c.jpg",
      "https://stud-files.sabir.pro/files/PtA4pFzxry-f1e50fcce61fcf319452f4e94f18d0171ff78119185502ff72842ead96356670.png",
      "https://stud-files.sabir.pro/files/PtA4pFzxry-8d58ef1299a1161a74f77d61f179828623c25bf7cce08f62b7898c83dc081f4c.png",
      "https://stud-files.sabir.pro/files/PtA4pFzxry-be65d2c18d2792155689f6441858e681d68299bd0cf8b51ae8b85cf317f16c6e.png",
      "https://stud-files.sabir.pro/files/PtA4pFzxry-51e8bf95117ccd9d246a4e621143fe03644931295eea3c960ac3fca293b6e25d.png",
      "https://stud-files.sabir.pro/files/PtA4pFzxry-02a4d11680ad219c665470fdf3d920a8390e8445d2bfc80e8fc6d79ec47d87d6.png"
    ];
  };
  rules?: {
    committee: {
      name: "Студтуризм ПВГУС 2022";
      email: "st@tolgas.ru";
      phone: "+78482486816";
    };
    requiredUniDocuments: "Копия приказа о направлении обучающихся";
    requiredStudentsDocuments: "При заселение в общежитие   требуется:\n-паспорт гражданина, или иного документа, удостоверяющего личность; \n\n - квитанции об оплате за проживание в общежитии. \n- документ, подтверждающий обучение в образовательном учреждении высшего или среднего профессионального образования. ";
  };
  services?: [
    {
      isFree: false;
      id: "CB7M76RcAF";
      name: "Стирка личных вещей";
      description: "Помещение оборудованное стиральными машинами";
      price: "0";
    },
    {
      isFree: true;
      id: "S152yxwR9G";
      name: "Буфет, тренажерный зал";
      description: "Все перечисленные услуги ,за исключением буфета - бесплатны";
      price: "0";
    }
  ];
  documents: [
    "https://stud-files.sabir.pro/files/PtA4pFzxry-ec168de2331ed39abed6a463465fe8fddd39c9d4c388bc305bac4d2474eda47b.pdf",
    "https://stud-files.sabir.pro/files/PtA4pFzxry-d8c377565c358acd73ba43e23085e49dd7464c5b16fac7d90487f7dcd9b641dd.pdf",
    "https://stud-files.sabir.pro/files/PtA4pFzxry-6cdcfd26d601590e6f6d59c2c8b5c98fdabb567cacf5e75ba90f7e44dfc4933d.pdf",
    "https://stud-files.sabir.pro/files/PtA4pFzxry-7f7bff8642e4c10c109492e3d8ff669c5cc95e2298fd89226013e91c23445b0a.pdf",
    "https://stud-files.sabir.pro/files/PtA4pFzxry-9fd2fe0e465e0145cc89c9ed2020071d247b1de77db43698db1c61b5a30b4cb7.pdf"
  ];
  district: "ПФО";
}

interface Room {
  details: {
    dateRange: { from: 1662926400000; to: 1671912000000 };
    isFree: false;
    type: "3-х местный номер";
    price: "25";
    description: "В комнате 3 кровати, шкаф. Удобства на 1 этаже.";
    photos: [
      "https://stud-files.sabir.pro/files/PtA4pFzxry-720f1e9e904654f9afc8249a1d18c16d02b9be9fe09e63480135c7cf3353e4a1.png"
    ];
    amount: "12";
  };
  dormitoryId: "7Zbl2muIe4";
  userId: "PtA4pFzxry";
  universityId: "G5qYEw8lls";
  createdTimestamp: 1648791619173;
  updatedTimestamp: 1663059329050;
  onModeration: true;
  id: "Acu84i9BK0";
  timestamp: 1648791619173;
}

interface University {
  userId: "MlcLzJOAQC";
  name: "Первый университет";
  description: "описание";
  details: {
    photo: "https://stud-files.sabir.pro/files/MlcLzJOAQC-dc32c2f721509843baf81c78f96fb924d73128585b5cecc9df58c7ffac0e43a2.jpeg";
    name: "МФТИ";
    site: "https://mipt.ru";
    committee: "МФТИ";
    region: "Московская область";
    shortName: "МФТИ";
    district: "ЦФО";
  };
  isDebug?: true;
  onModeration: true;
  id: "DgxLyUYhBv";
  timestamp: 1647980801834;
  createdTimestamp: 1647980801834;
  updatedTimestamp: 1656061946992;
}

interface Event {
  details: {
    dates: { from: 1650056400000; to: 1650142800000; isRegular: true };
    name: "Пешеходная экскурсия по Ростову-на-Дону";
    link: "https://yandex.ru";
    price: "1000";
    description: "Загородная автобусная экскурсия в горные районы Северной Осетии по маршруту «Путешествие в долину водопадов»\n\nЦель экскурсии Ознакомление с культурно-историческим наследием современных осетин – потомков скифо-сармато-алан, а также посещением уникальных природных объектов континентального значения.\n\nКонечные пункты маршрута Владикавказ – Мидаграбинские водопады\nПродолжительность маршрута: 9 часов.\n\nОстановки:\n1.\tКобан;\n2.\tКахтисар;\n3.\tс. Даргавс;\n4.\tМидаграбинские водопады.\n\n1. Кобан\nКобан изначально состояло из двух населённых пунктов – Верхний и Нижний Кобан, которые в наши дни соединились в одно село.\n\nНа южной окраине села высится сторожевая башня фамилии Кануковых.";
    video: [
      "https://stud-files.sabir.pro/files/MlcLzJOAQC-d2b4ba173b4fd673fe86ed50f48a0daed646ee28e1ab0a7d39eb1f7b354a8384.mov"
    ];
    photos: [
      "https://stud-files.sabir.pro/files/MlcLzJOAQC-17075e6576fbcab8865410068bd94bc7aecfa84abefda870dc0637d2e258e944.jpg",
      "https://stud-files.sabir.pro/files/MlcLzJOAQC-cfb1a10ee63f7bd15dc14ae615fe79de0b3b3c54b48718afc274c5f6c5d83af3.jpg"
    ];
    type: "scientific";
    WoS: "QL";
  };
  userId: "MlcLzJOAQC";
  universityId: "DgxLyUYhBv";
  createdTimestamp: 1650476393333;
  updatedTimestamp: 1670863276275;
  onModeration: false;
  id: "ap5gug65RY";
  timestamp: 1650476393333;
}

interface Review {
  photos: [];
  topic: "Замечательная поездка";
  text: "Рекомендуем! ";
  rating: 4;
  dormitoryId?: "7Zbl2muIe4";
  eventId?: "ap5gug65RY";
  userId: "apkN4cAruB";
  published?: true;
  createdTimestamp: 1670488463935;
  onModeration: true;
  updatedTimestamp: 1676503492483;
  id: "5WnZaXx92f";
  timestamp: 1670488463935;
}

interface ReviewRequest {
  photos: [
    "https://stud-files.sabir.pro/files/uKGxGlxIQC-6a95693f7b294177ee38135eb27d770ede4c098b4432d01872588f7ae11a5b42.png"
  ];
  topic: "текст";
  text: "отзыв";
  rating: 5;
  eventId: "WkC61h8Pj1";
}

interface Lab {
  details: {
    name: "лаборатория 1";
    link: "https://yandex.ru";
    description: "фыва фыва фыва ";
    photos: [
      "https://stud-files.sabir.pro/files/MlcLzJOAQC-f4d358d67b19614f721782b44d59ba75812f4f43025e55c49b639737072dac37.jpg"
    ];
    coordinates: {
      lat: 55.75464699125826;
      lng: 37.38872558593749;
    };
    owner: {
      name: "owner1";
      position: "sss";
      phone: "ddd";
      email: "fff@ddd.com";
    };
    unit: {
      name: "unit1";
      phone: "unit1";
      email: "unit1@sabir.pro";
    };
    admin: {
      name: "admin1";
      phone: "admin-ph";
      email: "admin@sss.pro";
    };
    shortDescription: "short descript";
  };
  userId: "MlcLzJOAQC";
  universityId: "DgxLyUYhBv";
  onModeration: true;
  createdTimestamp: 1653859886766;
  updatedTimestamp: 1670864218030;
  id: "CCJnsL4JPI";
  timestamp: 1653859886766;
}

interface DormitoryBooking {
  dates: { from: "2023-03-14"; to: "2023-03-17" };
  author: {
    role: "user";
    name: "Фио точно";
    contacts: { phone: "телефон точно"; email: "mail@mail.ru" };
  };
  userId: "PVbA7soK4S";
  universityId: "ddasXacKVT";
  dormitoryId: "t46oXcQLKo";
  roomId: "yek4nZPkhE";
  quantity: 2;
  comment: "Не обращайте внимание на заявку";
  status: "new";
  from: "2023-03-14";
  to: "2023-03-17";
  id: "C0LWkzghs0";
  timestamp: 1676652740970;
  createdTimestamp: 1676652740970;
  updatedTimestamp: 1676652740970;
}

interface DormitoryBookingRequest {
  roomId: "yek4nZPkhE";
  dates: { from: "2023-03-14"; to: "2023-03-17" };
  quantity: "2";
  author: {
    role: "user";
    name: "Фио точно";
    contacts: { phone: "телефон точно"; email: "mail@mail.ru" };
  };
  comment: "Не обращайте внимание на заявку";
}

interface EventBooking {
  details: {
    quantity: "1";
    fullName: "Некто А Б";
    phone: "РЕАЛЬНЫЙ ТЕЛЕФОН";
    email: "mail@mail.ru";
    participants: [
      {
        fullName: "ПОПУТЧИК 123";
        phone: "ТЕЛЕФОН ПОПУТЧИКА";
        email: "почта@попутчик.рф";
      }
    ];
  };
  userId: "uKGxGlxIQC";
  universityId: "5SjLN2TEn0";
  status: "new";
  eventId: "WkC61h8Pj1";
  id: "Cyz0EEWmID";
  timestamp: 1676653880803;
  createdTimestamp: 1676653880803;
  updatedTimestamp: 1676653880803;
}

interface EventBookingRequest {
  eventId: "WkC61h8Pj1";
  details: {
    quantity: "1";
    fullName: "Некто А Б";
    phone: "РЕАЛЬНЫЙ ТЕЛЕФОН";
    email: "mail@mail.ru";
    participants: [
      {
        fullName: "ПОПУТЧИК 123";
        phone: "ТЕЛЕФОН ПОПУТЧИКА";
        email: "почта@попутчик.рф";
      }
    ];
  };
}
