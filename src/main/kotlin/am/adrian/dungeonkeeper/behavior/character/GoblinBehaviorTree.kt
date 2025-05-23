package am.adrian.dungeonkeeper.behavior.character

import am.adrian.dungeonkeeper.behavior.BehaviorTree
import am.adrian.dungeonkeeper.behavior.node.ExpandableNode
import am.adrian.dungeonkeeper.behavior.node.action.EatAction
import am.adrian.dungeonkeeper.behavior.node.and.AndNode
import am.adrian.dungeonkeeper.behavior.node.condition.IsHungerFullCondition
import am.adrian.dungeonkeeper.behavior.node.condition.IsHungryCondition
import am.adrian.dungeonkeeper.behavior.node.loop.WhileNode
import am.adrian.dungeonkeeper.behavior.node.or.OrNode
import am.adrian.dungeonkeeper.model.gameobject.creature.Goblin

class GoblinBehaviorTree(val creature: Goblin) : BehaviorTree {

    override val rootNode: ExpandableNode = OrNode("Root Node")

    init {
        val isHungry = IsHungryCondition(creature)
        val isHungerFull = IsHungerFullCondition(creature)
        val eat = EatAction(creature)

        val rootEatNode = AndNode("Eating node")
        val loopEatingNode = WhileNode("Loop eating node")

        /* Node hierarchy:
         * rootEatNode (AND)
         *  ├── isHungry (Condition)
         *  └── loopEatingNode (WHILE)
         *       ├── eat (Action)
         *       └── isHungerFull (Condition)
         */

        rootNode.addChild(rootEatNode)
        rootEatNode.addChild(isHungry)
        rootEatNode.addChild(loopEatingNode)
        loopEatingNode.addChild(eat)
        loopEatingNode.addChild(isHungerFull)
    }
}
