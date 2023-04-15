package am.adrian.dungeonkeeper.model.gameobject.creature

import am.adrian.dungeonkeeper.model.gameobject.GameObject
import am.adrian.dungeonkeeper.model.gameobject.trait.BelongsTo
import am.adrian.dungeonkeeper.model.gameobject.trait.HasSize

interface Creature : GameObject, HasSize, BelongsTo
