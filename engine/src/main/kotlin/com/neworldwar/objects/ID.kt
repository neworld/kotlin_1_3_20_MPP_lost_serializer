package com.neworldwar.objects

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

expect class ID(id: String) {
    constructor()

    fun toHexString(): String

    fun toByteArray(): ByteArray
}

@Serializer(forClass = ID::class)
object IDSerializer : KSerializer<ID> {
    override val descriptor: SerialDescriptor = StringDescriptor

    override fun deserialize(input: Decoder): ID {
        val id = input.decodeString()
        return ID(id)
    }

    override fun serialize(output: Encoder, obj: ID) {
        output.encodeString(obj.toHexString())
    }
}
