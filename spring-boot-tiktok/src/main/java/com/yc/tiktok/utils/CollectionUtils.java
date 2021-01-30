package com.yc.tiktok.utils;

import java.util.Collection;

public class CollectionUtils {

    // TODO: 2021/1/27 待重构 
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isPresent(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

}
