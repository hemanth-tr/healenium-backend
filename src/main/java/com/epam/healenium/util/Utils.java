package com.epam.healenium.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

@Slf4j
@UtilityClass
public class Utils {

    /**
     * Builds ID for element that represent selector meta
     *
     * @param className the fully qualified name of the class
     * @param locator   the selector value
     * @param url
     * @return hashed key of locator
     */
    public String buildKey(String className, String locator, String url) {
        String rawKey = className.concat(url) + locator.hashCode();
        return DigestUtils.md5DigestAsHex(rawKey.trim().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Builds ID for healing record
     *
     * @param elementId  unique identifier of selector META
     * @param pageSource searching context object
     * @return hashed key of healing element
     */
    public String buildHealingKey(String elementId, String pageSource) {
        String rawKey = elementId + pageSource.hashCode();
        return DigestUtils.md5DigestAsHex(rawKey.trim().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Builds Screenshot name
     *
     * @return name
     */
    public String buildScreenshotName() {
        return "screenshot_" + LocalDateTime.now().format(DateTimeFormatter
                .ofPattern("dd-MMM-yyyy-hh-mm-ss.SSS").withLocale(Locale.US)) + ".png";
    }

    public String trimQueryString(String url) {
        int queryStringStartIndex = url.indexOf("?");
        return url.substring(0, queryStringStartIndex);
    }

    public String ignoreWorkflowUUID(String urlWithoutQueryString) {

        URL url = null;
        try {
            url = new URL(urlWithoutQueryString);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return urlWithoutQueryString;
        }

        String[] urlPath = url.getPath().split("/");
        String finalUrl = "/";
        for (int i = 0; i < urlPath.length; i++) {

            if (urlPath[i] == null || urlPath[i].isEmpty() || isUUID(urlPath[i])) {
                continue;
            }

            finalUrl += urlPath[i] + (i == urlPath.length - 1 ? "":"/");
        }

        return finalUrl;
    }

    private Boolean isUUID(String id) {

        try {
            UUID.fromString(id);
        } catch (Exception e) {
            System.out.println(id + ", isUUID: " + e.getMessage());
            return false;
        }

        return true;
    }
}