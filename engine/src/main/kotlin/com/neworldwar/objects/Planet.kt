package com.neworldwar.objects

import kotlinx.serialization.Serializable

@Serializable
data class Planet(override val _id: ID = ID()) : GameObject
