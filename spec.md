Техническое задание. Итерация 1.
====================

Управление пользоваетлями
--------
-   Authorize, register, login, logout пользователей, права на основе ролей: ADMIN, COUCH, LEARNER
-   Управление личными данными 
    Прототип <a href="https://www.coursera.org/account/profile">Сoursera profile</a>

    Регистрация:
    ----------
    -  Имя
    -  Фамилия
    -  Электронная почта
    -  Skype
    -  Location
    -  Источник информацмм о курсе
    -  Телефон

    Профиль
   ------
    -  Photo (храним на диске в сконфигурированном каталоге)
    -  About Me
    -  Website
    -  Company
    -  Почта gmail.com
    -  Skype
    -  Github
    -  VK
    
Управление курсами
--------
-  Главная страница с описанием (итерация 1: отображение статичной html)
   Прототип 
   -  <a href="https://class.coursera.org/reactive-002/lecture">Сoursera: Principles of Reactive Programming</a>
   -  <a href="http://javawebinar.ru/topjava/#toc">План занятий http://javawebinar.ru/topjava/#toc</a>
   -  <a href="https://class.coursera.org/reactive-002/lecture">Mongodb: MONGODB FOR JAVA DEVELOPERS</a>
    
-  Привязка к юзерам
-  Отображение курсов пользователя при входе в платформу 
   https://www.coursera.org/
   https://university.mongodb.com/dashboard 
    
Интеграция с почтовым сервером:
-------
-  Варианты
   -  http://blog.pivotal.io/cloud-foundry-pivotal/products/sending-email-from-cloud-foundry-java-applications-with-sendgrid
   -  https://github.com/cloudfoundry-incubator/notifications
   -  http://docs.pivotal.io/pivotalcf/opsguide/notifications.html

-  Подтверждение почты при регистрации
-  Напоминание пароля на почту
-  Организация отправки занятий для ведущих курс   

Подписки/рассылки:
-------
-  Подписка на рассылку платформы (флажок в профиле) 
-  Организация рассылки для ведущих курс: 
   -  управление списоками рассылки из зарегистрировавшихся пользователей
   -  загрузка письма в формате html
   -  отправление почты
-  Отписка от рассылки    

Локализация
-----------
-  Поменять французкий на русский
