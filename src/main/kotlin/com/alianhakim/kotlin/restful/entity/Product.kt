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
    var name: String,
    @Column
    var price: Long,
    @Column
    var quantity: Int,
    @Column(name = "created_at")
    val createdAt: Date,
    @Column(name = "updated_at")
    var updatedAt: Date?
)
