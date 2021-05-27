# weather-surve
Open Weather Mapから天気情報を取得して、Ambientでグラフ化します。  
(Get Weather Information form "Open Weather Map" and graphed in "Ambient")

![SpringBoot](https://img.shields.io/badge/SpringBoot-2.4.5-green.svg)
![Lombok](https://img.shields.io/badge/Lombok-1.18.20-green.svg) 
![Jackson](https://img.shields.io/badge/Jackson-2.11.4-green.svg) 

`TODO Youtubeへのリンク`

[Open Weather Map](https://openweathermap.org/)  
[Ambient](https://ambidata.io/)

## 必要要件 (Requirement)
- Java 8 以降 (Java 8 or higher)
- Maven

## 使い方 (Usage)
1. application.ymlを編集して、WEBサービスの接続情報を記入してください。  
(Edit application.yml and fills connect information of WEB service)  

2. JARモジュール生成 (Create JAR)
```command
mvn clean package
```
3. 実行 (Execute)
```command
java -jar weather-surve-1.0.jar
```
