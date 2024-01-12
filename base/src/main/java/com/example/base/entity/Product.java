package com.example.base.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

/**
 * @author HungDV
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "products")
public class Product extends ModifyAuditable{

}
