case class MyFancyCaseClass[A](value: A)

def doSomething(model: MyFancyCaseClass[_]) ={
  model match {
    case MyFancyCaseClass(value: Int) => "The value is an int"
    case MyFancyCaseClass(value: String) => "The value is a string"
    case MyFancyCaseClass(value: Seq[Int]) => "The value is a seq int"
    // ^ The JVM converts Seq[Int] to Seq so all Seq[A]'s will be read as Seq
    case _ => "An unknown value"
  }
}

doSomething(MyFancyCaseClass(1)) //output: the value is an int
doSomething(MyFancyCaseClass("some string")) //output: the value is a string
doSomething(MyFancyCaseClass(Seq(1,2,3))) //output: the value is a seq int
doSomething(MyFancyCaseClass(Seq("1","2","3"))) //output: an unknown value, right?
doSomething(MyFancyCaseClass(true)) //output: an unknown value