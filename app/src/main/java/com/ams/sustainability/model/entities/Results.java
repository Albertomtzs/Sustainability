
package com.ams.sustainability.model.entities;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.DataQueryBuilder;

import java.util.Date;
import java.util.List;

public class Results {
    private String ownerId;
    private Double transport;
    private Date updated;
    private Date created;
    private Double carbon_footprint;
    private Double techonology;
    private Double house;
    private Double clothes;
    private String objectId;
    private Double food;
    private BackendlessUser id_user;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Double getTransport() {
        return transport;
    }

    public void setTransport(Double transport) {
        this.transport = transport;
    }

    public Date getUpdated() {
        return updated;
    }

    public Date getCreated() {
        return created;
    }

    public Double getTechonology() {
        return techonology;
    }

    public void setTechonology(Double techonology) {
        this.techonology = techonology;
    }

    public Double getHouse() {
        return house;
    }

    public void setHouse(Double house) {
        this.house = house;
    }

    public Double getClothes() {
        return clothes;
    }

    public void setClothes(Double clothes) {
        this.clothes = clothes;
    }

    public String getObjectId() {
        return objectId;
    }

    public Double getFood() {
        return food;
    }

    public Double getCarbon_footprint() {
        return carbon_footprint;
    }

    public void setCarbon_footprint(Double carbon_footprint) {
        this.carbon_footprint = carbon_footprint;
    }

    public void setFood(Double food) {
        this.food = food;
    }

    public BackendlessUser getId_user() {
        return id_user;
    }

    public void setId_user(BackendlessUser id_user) {
        this.id_user = id_user;
    }

    public Results save() {
        return Backendless.Data.of(Results.class).save(this);
    }

    public void saveAsync(AsyncCallback<Results> callback) {
        Backendless.Data.of(Results.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Results.class).remove(this);
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Results.class).remove(this, callback);
    }

    public static Results findById(String id) {
        return Backendless.Data.of(Results.class).findById(id);
    }

    public static void findByIdAsync(String id, AsyncCallback<Results> callback) {
        Backendless.Data.of(Results.class).findById(id, callback);
    }

    public static Results findFirst() {
        return Backendless.Data.of(Results.class).findFirst();
    }

    public static void findFirstAsync(AsyncCallback<Results> callback) {
        Backendless.Data.of(Results.class).findFirst(callback);
    }

    public static Results findLast() {
        return Backendless.Data.of(Results.class).findLast();
    }

    public static void findLastAsync(AsyncCallback<Results> callback) {
        Backendless.Data.of(Results.class).findLast(callback);
    }

    public static List<Results> find(DataQueryBuilder queryBuilder) {
        return Backendless.Data.of(Results.class).find(queryBuilder);
    }

    public static List<Results> find2(DataQueryBuilder queryBuilder, AsyncCallback<List<Results>> asyncCallback) {
        return Backendless.Data.of(Results.class).find(queryBuilder);
    }

    public static void findAsync(DataQueryBuilder queryBuilder, AsyncCallback<List<Results>> callback) {
        Backendless.Data.of(Results.class).find(queryBuilder, callback);
    }


}