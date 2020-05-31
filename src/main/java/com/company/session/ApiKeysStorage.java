package com.company.session;

import java.util.HashSet;

public class ApiKeysStorage {
    public static boolean isIsInit() {
        return isInit;
    }

    public static void setIsInit(boolean isInit) {
        ApiKeysStorage.isInit = isInit;
    }

    private static boolean isInit = false;

    private static final HashSet<String> keys =
            new HashSet<String>();


    public static void addKey(String key) {
        keys.add(key);
    }

    public static void removeKey(String key) {
        keys.remove(key);
    }

    public static boolean containsKey(String key) {
        return keys.contains(key);
    }
}
