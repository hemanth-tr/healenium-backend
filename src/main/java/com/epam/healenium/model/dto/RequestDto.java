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
        return Utils.trimQueryString(url);
    }
}
