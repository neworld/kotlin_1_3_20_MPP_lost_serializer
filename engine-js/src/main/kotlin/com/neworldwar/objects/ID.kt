package com.neworldwar.objects

actual class ID actual constructor(private val id: String) {
    actual constructor() : this("")

    actual fun toHexString(): String = id

    actual fun toByteArray(): ByteArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString() = toHexString()
}
