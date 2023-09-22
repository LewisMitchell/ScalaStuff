//imported in build.sbt
import scala.reflect.runtime.universe._
case class MyFancyCaseClass[A](value: A)

//TypeTags can be thought of as objects which carry along all type information available at compile time, to runtime
//rather than being Seq at runtime it's Seq[Int] or Seq[String] so we can pattern match properly
def doSomething[T: TypeTag](thing: MyFancyCaseClass[T]) = {
  typeOf[T] match {
    //This function knows the _actual_ type of the MyFancyCaseClass instance
    case t if t =:= typeOf[Seq[Int]] => s"Thing of Seq[Int]: ${thing.value}"
    case t if t =:= typeOf[Seq[String]] => s"Thing of Seq[String]: ${thing.value}"
    case t if t =:= typeOf[Int] => s"Thing of Int: ${thing.value}"
    case _ => "Other thing"
  }
}

doSomething(MyFancyCaseClass(1))
doSomething(MyFancyCaseClass("some string"))
doSomething(MyFancyCaseClass(Seq(1,2,3)))
doSomething(MyFancyCaseClass(Seq("1","2","3")))