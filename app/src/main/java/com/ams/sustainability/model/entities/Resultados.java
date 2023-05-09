
package com.ams.sustainability.model.entities;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.DataQueryBuilder;

import java.util.Date;
import java.util.List;

public class Resultados {
    private String ownerId;
    private Double transporte;
    private Date updated;
    private Date created;
    private Double huella;
    private Double tecnologia;
    private Double hogar;
    private Double ropa;
    private String objectId;
    private Double alimentacion;
    private BackendlessUser id_user;

    public String getOwnerId() {
        return ownerId;
    }

    public Double getTransporte() {
        return transporte;
    }

    public void setTransporte(Double transporte) {
        this.transporte = transporte;
    }

    public Date getUpdated() {
        return updated;
    }

    public Date getCreated() {
        return created;
    }

    public Double getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Double tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Double getHogar() {
        return hogar;
    }

    public void setHogar(Double hogar) {
        this.hogar = hogar;
    }

    public Double getRopa() {
        return ropa;
    }

    public void setRopa(Double ropa) {
        this.ropa = ropa;
    }

    public String getObjectId() {
        return objectId;
    }

    public Double getAlimentacion() {
        return alimentacion;
    }

    public Double getHuella() {
        return huella;
    }

    public void setHuella(Double huella) {
        this.huella = huella;
    }

    public void setAlimentacion(Double alimentacion) {
        this.alimentacion = alimentacion;
    }

    public BackendlessUser getId_user() {
        return id_user;
    }

    public void setId_user(BackendlessUser id_user) {
        this.id_user = id_user;
    }

    public Resultados save() {
        return Backendless.Data.of(Resultados.class).save(this);
    }

    public void saveAsync(AsyncCallback<Resultados> callback) {
        Backendless.Data.of(Resultados.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(Resultados.class).remove(this);
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(Resultados.class).remove(this, callback);
    }

    public static Resultados findById(String id) {
        return Backendless.Data.of(Resultados.class).findById(id);
    }

    public static void findByIdAsync(String id, AsyncCallback<Resultados> callback) {
        Backendless.Data.of(Resultados.class).findById(id, callback);
    }

    public static Resultados findFirst() {
        return Backendless.Data.of(Resultados.class).findFirst();
    }

    public static void findFirstAsync(AsyncCallback<Resultados> callback) {
        Backendless.Data.of(Resultados.class).findFirst(callback);
    }

    public static Resultados findLast() {
        return Backendless.Data.of(Resultados.class).findLast();
    }

    public static void findLastAsync(AsyncCallback<Resultados> callback) {
        Backendless.Data.of(Resultados.class).findLast(callback);
    }

    public static List<Resultados> find(DataQueryBuilder queryBuilder) {
        return Backendless.Data.of(Resultados.class).find(queryBuilder);
    }

    public static List<Resultados> find2(DataQueryBuilder queryBuilder, AsyncCallback<List<Resultados>> asyncCallback) {
        return Backendless.Data.of(Resultados.class).find(queryBuilder);
    }

    public static void findAsync(DataQueryBuilder queryBuilder, AsyncCallback<List<Resultados>> callback) {
        Backendless.Data.of(Resultados.class).find(queryBuilder, callback);
    }


}