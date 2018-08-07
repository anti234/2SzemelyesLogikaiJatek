# 2SzemelyesLogikaiJatek

Leírás
------
Ez az alkalmazás beadandó projekt a [Programozási Technológia](http://www.inf.unideb.hu/~jeszy/prt/) és a 
[Programozási Környezetek](http://www.inf.unideb.hu/~jeszy/progkorny/) tárgyakhoz a [Debreceni Egyetem Informatika Karán](http://w1.inf.unideb.hu/).

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
* Maven
* Java
* Üzleti Logika
* JUnit Tesztek
* JavaFX Felhasználói Felület
* Xml feldolgozás
* Logolás
* Dokumentáció

Használat
---------
A pom.xml -t tartalmazó mappában nyiss egy terminált.

1. módszer:

> $ mvn package

> $ java -jar target/LogikaiJatek-1.0-jar-with-dependencies

2. módszer:

> $ mvn compile exec:java

License
-------
**Apache License, Version 2.0.**
