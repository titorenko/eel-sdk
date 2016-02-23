package io.eels.component.json

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.sksamuel.scalax.io.Using
import io.eels.{InternalRow, Column, FrameSchema, Reader, Source}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataInputStream, FileSystem, Path}

import scala.collection.JavaConverters._

case class JsonSource(path: Path) extends Source with Using {

  val reader = new ObjectMapper().readerFor(classOf[JsonNode])

  def createInputStream: FSDataInputStream = {
    val fs = FileSystem.get(new Configuration)
    fs.open(path)
  }

  override def schema: FrameSchema = {
    using(createInputStream) { in =>
      val roots = reader.readValues[JsonNode](in)
      val node = roots.next()
      val columns = node.fieldNames.asScala.map(Column.apply).toList
      FrameSchema(columns)
    }
  }

  val in = createInputStream
  val roots = reader.readValues[JsonNode](in)

  override def readers: Seq[Reader] = {
    val part = new Reader {
      override def iterator: Iterator[InternalRow] = new Iterator[InternalRow] {

        val iter = roots.asScala
        override def hasNext: Boolean = iter.hasNext
        override def next(): InternalRow = nodeToRow(iter.next)

        def nodeToRow(node: JsonNode): InternalRow = {
          //val columns = node.fieldNames.asScala.map(Column.apply).toList
          //val fields = node.fieldNames.asScala.map { name => Field(node.get(name).textValue) }.toList
          //Row(columns, fields)
          node.elements.asScala.map(_.textValue).toList
        }
      }

      override def close(): Unit = in.close()
    }
    Seq(part)
  }
}
