package com.example.testproject.service;

import com.example.testproject.data.entity.ListenerEntity;

public interface ListenerService {

    ListenerEntity getEntity(Long id);

    void saveEntity(ListenerEntity listenerEntity);

    void updateEntity(ListenerEntity listenerEntity);

    void removeEntity(ListenerEntity listenerEntity);
}
