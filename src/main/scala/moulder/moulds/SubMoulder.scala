package moulder.moulds

import helpers.MouldersApplier
import org.jsoup.nodes._
import moulder._
import scala.collection.JavaConverters._

case class SubMoulder() extends Moulder {
  private var cfg = List[(String, List[Moulder])]()

  def register(selector: String, moulders: List[Moulder]): SubMoulder = {
    cfg = (selector, moulders) :: cfg
    this
  }

  def register(selector: String, moulders: Moulder*): SubMoulder = {
    cfg = (selector, moulders.toList) :: cfg
    this
  }

  private def replace(e: Element, nodes: List[Node]): Unit = {
    nodes.foreach(n => e.before(n.outerHtml))
    e.remove()
  }

  override def process(element: Element): List[Node] = {
    cfg.foreach(sm => {
      val elements = element.select(sm._1)
      asScalaIterator[Element](elements.iterator()).foreach(e => replace(e, MouldersApplier.applyMoulders(sm._2, List(e))))
    })
    List(element)
  }
}
