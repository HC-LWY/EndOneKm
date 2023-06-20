package com.qf.endOneKm;

import java.util.HashMap;

public class R extends HashMap {




    @Override
    public R put(Object key, Object value) {
          super.put(key, value);
          return this;
    }

    public static R ok(){
        return new R().put("code",200);
    }

    public static R error(){
        return new R().put("code",500);
    }
}
