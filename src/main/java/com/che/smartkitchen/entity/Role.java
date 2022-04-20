package com.che.smartkitchen.entity;

import lombok.Data;

import javax.persistence.Entity;
@Data
@Entity
public class Role extends AbstractEntity{
    private String name;

    private String title;




}
