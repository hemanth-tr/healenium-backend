package com.epam.healenium.model.dto;

import com.epam.healenium.util.Utils;
import lombok.Data;
@Data
public class RequestDto {

    private String locator;
    private String className;
    private String methodName;
    private String url;
    private byte[] screenshot;

    public String getUrl() {
        String urlWithoutQueryString = Utils.trimQueryString(url);
        String finalUrl = Utils.ignoreWorkflowUUID(urlWithoutQueryString);
        return finalUrl;
    }
}
