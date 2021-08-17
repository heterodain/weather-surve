# weather-surve
Open Weather Mapから天気情報を取得して、Ambientでグラフ化します。  
(Get Weather Information form "Open Weather Map" and graphed in "Ambient")

![SpringBoot](https://img.shields.io/badge/SpringBoot-2.5.3-green.svg)
![Lombok](https://img.shields.io/badge/Lombok-1.18.20-green.svg) 
![Jackson](https://img.shields.io/badge/Jackson-2.11.4-green.svg) 

[![Video1](https://img.youtube.com/vi/13LnWvSBatA/0.jpg)](https://youtu.be/13LnWvSBatA)

[Open Weather Map](https://openweathermap.org/)  
[Ambient](https://ambidata.io/)

## 必要要件 (Requirement)
- Java 8 以降 (Java 8 or higher)
- Maven

## 使い方 (Usage)
1. application.ymlを編集して、WEBサービスの接続情報を記入してください。  
(Edit application.yml and fills connect information of WEB service)  

2. 実行 (Execute)
   - VS Code 上で実行 (Run on VS Code)  
     App.java を右クリックして実行してください。(Right-click on the App.java and run)

   - ターミナル上で実行 (Run on terminal)
     ```command
     mvn clean package
     java -jar weather-surve-1.0.jar
     ```
