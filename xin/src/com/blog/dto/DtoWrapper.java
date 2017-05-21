package com.blog.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by xin on 17-5-21.
 */
public class DtoWrapper implements Dto {
    private Object object;

    public DtoWrapper(Object object) {
        this.object = object;
    }

    public static List<Dto> getInstances(Object o) {
        DtoWrapper dtoWrapper = new DtoWrapper(o);
        return Collections.singletonList(dtoWrapper);
    }

    public static List<Dto> getInstances(Collection<Object> collection) {
        List<Dto> dtos = new ArrayList<>();
        collection.forEach(o -> dtos.add(getInstance(o)));
        return dtos;
    }

    public static Dto getInstance(Object o) {
        return new DtoWrapper(o);
    }

    @Override
    public String toString() {
        return "DtoWrapper{" +
                "object=" + object +
                '}';
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
