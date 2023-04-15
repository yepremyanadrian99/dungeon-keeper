package am.adrian.dungeonkeeper.behavior.character

import am.adrian.dungeonkeeper.behavior.BehaviorTree
import am.adrian.dungeonkeeper.behavior.node.ExpandableNode
import am.adrian.dungeonkeeper.behavior.node.action.EatAction
import am.adrian.dungeonkeeper.behavior.node.condition.IsHungryCondition
import am.adrian.dungeonkeeper.behavior.node.selector.SelectorNode
import am.adrian.dungeonkeeper.behavior.node.sequence.SequenceNode
import am.adrian.dungeonkeeper.model.gameobject.creature.Goblin

class GoblinBehaviorTree(val creature: Goblin) : BehaviorTree {

    override val rootNode: ExpandableNode

    init {
        rootNode = SelectorNode("Root Node")

        val isHungry = IsHungryCondition(creature)
        val eat = EatAction(creature)

        val eatingNode = SequenceNode("Eating node")
        eatingNode.addChild(isHungry)
        eatingNode.addChild(eat)

        rootNode.addChild(eatingNode)
    }
}
