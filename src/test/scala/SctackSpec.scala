import org.scalatest.Matchers.{a, convertToAnyShouldWrapper}
import org.scalatest.{DiagrammedAssertions, FlatSpec}


class StackSpec extends FlatSpec with DiagrammedAssertions {

  it should "Stack生成時はEmptyStack" in {
    assert(Stack() === EmptyStack)
  }
  "EmptyStack" should "popしようとすると、例外を送出する" in {
    val stack = Stack()
    assertThrows[IllegalArgumentException](
      stack.pop
    )
  }
  "EmptyStack" should "isEmptyの戻り値はtrue" in {
    val stack = Stack()
    assert(stack.isEmpty === true)
  }
  "EmptyStack" should "pushすると、その型のNonEmptyStackが得られる" in {
    val stack = Stack()

    val intStack = stack.push(1)
    intStack shouldBe a[NonEmptyStack[Int]]

    val strStack = stack.push("str")
    strStack shouldBe a[NonEmptyStack[String]]
  }
  "NonEmptyStack" should "型パラメータのサブクラスもpushできる" in {
    val s = Stack()
    s.push(100: AnyVal).push(1.1111: Double) shouldBe a[NonEmptyStack[AnyVal]]
  }
  "NonEmptyStack" should "pushした値が,popでFILOの順序で取り出せる" in {
    val s = Stack()
    val pushedStack = s.push(100: AnyVal).push(1.1111: Double)
    assert(pushedStack.pop._1 === 1.1111)
    assert(pushedStack.pop._2.pop._1 === 100)
    assert(pushedStack.pop._2.pop._2 === EmptyStack)
  }
  "NonEmptyStack" should "isEmptyの戻り値はfalse" in {
    val s = Stack().push(100000)
    assert(s.isEmpty === false)
  }

}
