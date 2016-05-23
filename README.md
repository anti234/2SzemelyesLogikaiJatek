# 2SzemelyesLogikaiJatek

Leírás
------
Ez az alkalmazás beadandó projekt a Programozási Technológia és a Programozási Környezetek tárgyakhoz a [Debreceni Egyetem Informatika Karán](http://w1.inf.unideb.hu/).

Alkalmazás
----------
Egy két személyes logikai játék.

A játék működése
----------------
Jelölje ki azt a korongok, amelyikkel lépni szeretne, ekkor a kijelölt 
korong pirosra vált, majd kattintson arra a rácspontra, ahova lépni 
szeretne. 

Játék szabály
-------------
A játékosok felváltva lépnek egy saját koronggal átlósan egy 
szomszédos üres rácspontba. Az a Játékos nyer, akinek hamarabb 
sikerül átjuttatni az összes korongját a tábla szemköztni oldalára.

Tartalmaz
---------
* Üzleti Logika
* JUnit Tesztek
* JavaFX Felhasználói Felület
* Xml feldolgozás
* Logolás
* Dokumentáció

Használat
---------
Két módon történhet:
> $ mvn package

> $ cd target

> $ java -jar LogikaiJatek-1.0-jar-with-dependencies

Vagy

> $ mvn compile exec:java

License
-------
**Apache License, Version 2.0.**
