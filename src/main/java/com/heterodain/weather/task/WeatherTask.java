package com.heterodain.weather.task;

import java.time.ZonedDateTime;

import com.heterodain.weather.config.ServiceConfig;
import com.heterodain.weather.model.CurrentWeather;
import com.heterodain.weather.service.AmbientService;
import com.heterodain.weather.service.OpenWeatherMapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

/**
 * Open Weather Mapから天気情報を取得して、Ambientに送信するタスク
 */
@Component
@Slf4j
public class WeatherTask {
    /** サービス設定 */
    @Autowired
    private ServiceConfig serviceConfig;

    /** Open Weather Mapサービス */
    @Autowired
    private OpenWeatherMapService openWeaherMapService;

    /** Ambientサービス */
    @Autowired
    private AmbientService ambientService;

    /**
     * 5分毎にOpen Weather Mapからデータ取得して、Ambientに送信
     */
    @Scheduled(cron = "0 */5 * * * *")
    public void getCurrentWeather() {
        CurrentWeather currentWeather;
        try {
            var config = serviceConfig.getOpenWeatherMap();
            currentWeather = openWeaherMapService.getCurrentWeather(config.getCityId(), config.getApiKey());
            log.debug("{}", currentWeather);
        } catch (Exception e) {
            log.warn("Open Weather Mapからのデータ取得に失敗しました。", e);
            return;
        }

        try {
            var config = serviceConfig.getAmbientWeather();
            ambientService.send(config, ZonedDateTime.now(), currentWeather.getWeather(),
                    currentWeather.getTemperature().doubleValue(), currentWeather.getHumidity().doubleValue(),
                    currentWeather.getPressure().doubleValue(), currentWeather.getWindSpeed().doubleValue(),
                    currentWeather.getCloudness().doubleValue(), currentWeather.getRain1h().doubleValue(),
                    currentWeather.getSnow1h().doubleValue());
        } catch (Exception e) {
            log.warn("Ambientへのデータ送信に失敗しました。", e);
        }
    }
}
