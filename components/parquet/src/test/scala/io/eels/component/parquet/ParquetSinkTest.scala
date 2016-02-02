package io.eels.component.parquet

import io.eels.{Column, Field, Frame, FrameSchema, Row, SchemaType}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.scalatest.{Matchers, WordSpec}

class ParquetSinkTest extends WordSpec with Matchers {

  val frame = Frame(
    Row(Seq("name", "job", "location"), Seq("clint eastwood", "actor", "carmel")),
    Row(Seq("name", "job", "location"), Seq("elton john", "musician", "pinner"))
  )

  val fs = FileSystem.get(new Configuration)
  val path = new Path("test")

  "ParquetSink" should {
    "write schema" in {
      if (fs.exists(path))
        fs.delete(path, false)
      frame to ParquetSink(path)
      val people = ParquetSource("test")
      people.schema shouldBe FrameSchema(Seq(Column("name"), Column("job"), Column("location")))
      fs.delete(path, false)
    }
    "write data" in {
      if (fs.exists(path))
        fs.delete(path, false)
      frame to ParquetSink(path)
      val people = ParquetSource("test")
      people.toList shouldBe
        List(
          Row(
            Seq(
              Column("name", SchemaType.String, false),
              Column("job", SchemaType.String, false),
              Column("location", SchemaType.String, false)
            ),
            Seq(
              Field("clint eastwood"), Field("actor"), Field("carmel")
            )
          ),
          Row(
            Seq(
              Column("name", SchemaType.String, false),
              Column("job", SchemaType.String, false),
              Column("location", SchemaType.String, false)
            ),
            Seq(
              Field("elton john"), Field("musician"), Field("pinner")
            )
          )
        )
      fs.delete(path, false)
    }
  }
}
