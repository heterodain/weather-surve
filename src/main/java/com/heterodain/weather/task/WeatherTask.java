package com.heterodain.weather.task;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.heterodain.weather.config.ServiceConfig;
import com.heterodain.weather.model.CurrentWeather;
import com.heterodain.weather.service.AmbientService;
import com.heterodain.weather.service.OpenWeatherMapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

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
     * 初期化
     */
    @PostConstruct
    public void init() {
        // TODO
    }

    /**
     * 終了処理
     */
    @PreDestroy
    public void destroy() {
        // TODO
    }

    /**
     * 15分毎にOpen Weather Mapからデータ取得して、Ambientに送信
     * 
     * @throws InterruptedException
     */
    @Scheduled(cron = "0 */15 * * * *")
    public void getCurrentWeather() throws IOException, InterruptedException {
        var owmConfig = serviceConfig.getOpenWeatherMap();
        var currentWeather = openWeaherMapService.getCurrentWeather(owmConfig.getCityId(), owmConfig.getApiKey());
        log.debug("{}", currentWeather);

        var ambientConfig = serviceConfig.getAmbientWeather();
        ambientService.send(ambientConfig, ZonedDateTime.now(), currentWeather.getWeather(),
                currentWeather.getTemperature().doubleValue(), currentWeather.getHumidity().doubleValue(),
                currentWeather.getPressure().doubleValue(), currentWeather.getWindSpeed().doubleValue(),
                currentWeather.getCloudness().doubleValue(), currentWeather.getRain1h().doubleValue(),
                currentWeather.getSnow1h().doubleValue());
    }
}
