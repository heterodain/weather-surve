package com.heterodain.weather.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heterodain.weather.model.CurrentWeather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

/**
 * Open Weather Mapサービス
 */
@Service
@Slf4j
public class OpenWeatherMapService {
    /** 天気情報APIのURL */
    private static final String CURRENT_WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?id=%s&mode=json&lang=ja&units=metric&appid=%s";

    /** JSONパーサー */
    @Autowired
    private ObjectMapper om;

    /**
     * 現在の天気を取得
     * 
     * @param cityId 都市ID
     * @param apiKey APIアクセスキー
     * @return 現在の天気
     * @throws IOException
     */
    public CurrentWeather getCurrentWeather(String cityId, String apiKey) throws IOException {
        var url = String.format(CURRENT_WEATHER_API_URL, cityId, apiKey);
        log.debug("request > {}", url);

        // HTTP GET
        var conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setDoInput(true);

        var resCode = conn.getResponseCode();
        if (resCode != 200) {
            throw new IOException("OpenWeatherMap Response Code " + resCode);
        }

        JsonNode json;
        try (var is = conn.getInputStream()) {
            json = om.readTree(is);
        }

        var result = new CurrentWeather();
        result.setWeather(json.at("/weather/0/description").textValue());
        result.setTemperature(json.at("/main/temp").doubleValue());
        result.setPressure(json.at("/main/pressure").intValue());
        result.setHumidity(json.at("/main/humidity").intValue());
        result.setWindSpeed(json.at("/wind/speed").doubleValue());
        result.setCloudness(json.at("/clouds/all").intValue());
        result.setRain1h(json.at("/rain/1h").doubleValue());
        result.setSnow1h(json.at("/snow/1h").doubleValue());

        return result;
    }
}
