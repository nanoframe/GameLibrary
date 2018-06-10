package com.paperatus.core.physics

interface PhysicsShape
class RectangleShape(val width: Float, val height: Float) : PhysicsShape
class CircleShape(val radius: Float) : PhysicsShape
