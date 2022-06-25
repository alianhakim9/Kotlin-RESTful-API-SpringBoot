package com.alianhakim.kotlin.restful.entity

import org.hibernate.annotations.GenericGenerator
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
    val id: String,
    @Column(unique = true)
    val name: String,
    @Column
    val price: Long,
    @Column
    val quantity: Int,
    @Column(name = "created_at")
    val createdAt: Date,
    @Column(name = "updated_at")
    val updatedAt: Date?
)
