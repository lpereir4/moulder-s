package moulder

import moulder.values._
import scala.language.implicitConversions

object Values {
  implicit def fn2value[A](f: () => Option[A]): Value[A] = new Value[A] {
    def apply(): Option[A] = f()
  }

  implicit def any2value[A](v: A): Value[A] = new Value[A] {
    def apply() = Some(v)
  }

  def transform[A, B](delegate: Value[A], f: A => B) = ValueTransformer(delegate, f)

  def html(delegate: Value[String]) = HtmlValue(delegate)

}
