package com.mysample.springwas.util;

public enum UUID_NAMESPACE {
    PERSON("9b20c0dd-1416-4796-8b74-bf740b1734ef");

    private final String uuid;
    private final String uuidWithOutHyphen;

    UUID_NAMESPACE(String uuid) {
        this.uuid = uuid;
        this.uuidWithOutHyphen = uuid.replace("-", "");
    }

    private String getUuid() {
        return uuid;
    }

    private String getUuidWithOutHyphen() {
        return uuidWithOutHyphen;
    }
}
