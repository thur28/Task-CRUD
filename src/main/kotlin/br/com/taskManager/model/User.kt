package br.com.taskManager.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    var username: String = "",

    @JsonIgnore
    @Column(nullable = false, unique = true)
    var password: String = "",

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var tasks: List<Task> = mutableListOf()
){
}