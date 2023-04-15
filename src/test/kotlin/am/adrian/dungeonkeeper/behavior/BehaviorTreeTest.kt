package am.adrian.dungeonkeeper.behavior

import am.adrian.dungeonkeeper.behavior.node.Node
import am.adrian.dungeonkeeper.behavior.node.action.ActionNode
import am.adrian.dungeonkeeper.behavior.node.condition.ConditionNode
import am.adrian.dungeonkeeper.behavior.node.selector.SelectorNode
import am.adrian.dungeonkeeper.behavior.node.sequence.SequenceNode
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BehaviorTreeTest {

    @Test
    fun `action node should return true`() {
        // Arrange
        class TestAction(
            override val name: String,
            override val action: Runnable
        ) : ActionNode

        val action = TestAction("Test action") { println("Test") }

        // Act
        val result = action.execute()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `condition node should return true or false depending on the boolean supplier`() {
        // Arrange
        class TestCondition(
            override val name: String,
            override val conditionSupplier: () -> Boolean
        ) : ConditionNode

        val condition1 = TestCondition("True") { true }
        val condition2 = TestCondition("False") { false }

        // Act
        val result1 = condition1.execute()
        val result2 = condition2.execute()

        // Assert
        assertTrue(result1)
        assertFalse(result2)
    }

    @Test
    fun `selector node should return true if at least one succeeds`() {
        // Arrange
        val condition = mockk<ConditionNode>()
        val action = mockk<ActionNode>()
        val subSelector = mockk<SelectorNode>()
        val sequence = mockk<SequenceNode>()

        val selector = SelectorNode("Test")
        selector.addChild(condition)
        selector.addChild(action)
        selector.addChild(subSelector)
        selector.addChild(sequence)

        every { condition.execute() } returns false
        every { action.execute() } returns false
        every { subSelector.execute() } returns true

        // Act
        val result = selector.execute()

        // Assert
        assertTrue(result)
        verify(exactly = 1) { condition.execute() }
        verify(exactly = 1) { action.execute() }
        verify(exactly = 1) { subSelector.execute() }
        verify(exactly = 0) { sequence.execute() }
    }

    @Test
    fun `selector node should return false if at all nodes fail`() {
        // Arrange
        val condition = mockk<ConditionNode>()
        val action = mockk<ActionNode>()
        val subSelector = mockk<SelectorNode>()
        val sequence = mockk<SequenceNode>()

        val selector = SelectorNode("Test")
        selector.addChild(condition)
        selector.addChild(action)
        selector.addChild(subSelector)
        selector.addChild(sequence)

        every { condition.execute() } returns false
        every { action.execute() } returns false
        every { subSelector.execute() } returns false
        every { sequence.execute() } returns false

        // Act
        val result = selector.execute()

        // Assert
        assertFalse(result)
        verify(exactly = 1) { condition.execute() }
        verify(exactly = 1) { action.execute() }
        verify(exactly = 1) { subSelector.execute() }
        verify(exactly = 1) { sequence.execute() }
    }

    @Test
    fun `sequence node should return true`() {
        // Arrange
        val condition = mockk<ConditionNode>()
        val action = mockk<ActionNode>()
        val selector = mockk<SelectorNode>()
        val subSequence = mockk<SequenceNode>()

        val sequence = SequenceNode("Test")
        sequence.addChild(condition)
        sequence.addChild(action)
        sequence.addChild(selector)
        sequence.addChild(subSequence)

        every { condition.execute() } returns true
        every { action.execute() } returns true
        every { selector.execute() } returns true
        every { subSequence.execute() } returns true

        // Act
        val result = sequence.execute()

        // Assert
        assertTrue(result)
        verify(exactly = 1) { condition.execute() }
        verify(exactly = 1) { action.execute() }
        verify(exactly = 1) { selector.execute() }
        verify(exactly = 1) { subSequence.execute() }
    }

    @Test
    fun `sequence node should return false when one node fails`() {
        // Arrange
        val condition = mockk<ConditionNode>()
        val action = mockk<ActionNode>()
        val selector = mockk<SelectorNode>()
        val subSequence = mockk<SequenceNode>()

        val sequence = SequenceNode("Test")
        sequence.addChild(condition)
        sequence.addChild(action)
        sequence.addChild(selector)
        sequence.addChild(subSequence)

        every { condition.execute() } returns true
        every { action.execute() } returns true
        every { selector.execute() } returns false

        // Act
        val result = sequence.execute()

        // Assert
        assertFalse(result)
        verify(exactly = 1) { condition.execute() }
        verify(exactly = 1) { action.execute() }
        verify(exactly = 1) { selector.execute() }
        verify(exactly = 0) { subSequence.execute() }
    }

    @Test
    fun `should execute action node`() {
        // Arrange
        val failingSequenceNode = mockk<SequenceNode>()
        val condition = mockk<ConditionNode>()
        val action = mockk<ActionNode>()

        every { failingSequenceNode.execute() } returns false
        every { condition.execute() } returns true
        every { action.execute() } returns true

        val behaviorTree = object : BehaviorTree {

            override val rootNode: Node

            init {
                val sequenceNode = SequenceNode("Test node")
                sequenceNode.addChild(condition)
                sequenceNode.addChild(action)

                val selectorNode = SelectorNode("Root node")
                selectorNode.addChild(failingSequenceNode)
                selectorNode.addChild(sequenceNode)

                rootNode = selectorNode
            }
        }

        // Act
        behaviorTree.execute()

        // Assert
        verify(exactly = 1) { failingSequenceNode.execute() }
        verify(exactly = 1) { condition.execute() }
        verify(exactly = 1) { action.execute() }
    }
}
