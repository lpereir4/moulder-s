package moulder

import org.jsoup.Jsoup
import org.jsoup.nodes._
import scala.collection.JavaConversions._

case class MoulderUtils(private val doc: Document) { 
  def e(name: String) = doc.createElement(name);
	
  def copy(e: Element) = { 
    val res = doc.createElement(e.tagName());
    e.attributes().foreach(a=>res.attr(a.getKey(), a.getValue()))
    res.html(e.html())
    res
  }
}

trait Value[+A] extends Function0[Option[A]] { 
  def bind(elementAndData : (Element, Option[Any])): Unit = { }
}


trait Moulder { 
  def process(elementAndData: (Element, Option[Any]), u: MoulderUtils): List[(Node, Option[Any])]
}


case class MoulderShop() { 
  private val sm = M.SubMoulder()

  def register(selector: String, moulders: List[Moulder]) = sm.register(selector, moulders)
  def register(selector: String, moulders: Moulder*) = sm.register(selector, moulders : _*)

  def process(document: Document) = sm.process((document, None), MoulderUtils(document))

  def process(stream: java.io.InputStream) = { 
    val doc = Jsoup.parse(stream, null, "#");
    sm.process((doc, None), MoulderUtils(doc))
  }
}