package com.paperatus.core

class InvalidActionException(override val message: String? = null) : RuntimeException()

class AssetNotLoadedException(override val message: String? = null) : RuntimeException()

class ComponentException(override val message: String? = null) : RuntimeException()

class RenderException(override val message: String? = null) : RuntimeException()
