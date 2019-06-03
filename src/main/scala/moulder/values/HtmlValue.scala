package moulder.values

import org.jsoup.Jsoup
import org.jsoup.nodes._
import moulder.Value
import scala.collection.JavaConverters._

case class HtmlValue(private val delegate: Value[String]) extends Value[List[Node]] {

  def apply() = delegate().map({ h: String =>
    asScalaIterator[Node](Jsoup.parseBodyFragment(h).body().childNodes().iterator()).toList
  })
}
