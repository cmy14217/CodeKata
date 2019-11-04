package pers.chen.kata.args;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pers.chen.kata.args.parsers.DirectorySchemaParser;
import pers.chen.kata.args.parsers.LoggingSchemaParser;
import pers.chen.kata.args.parsers.PortSchemaParser;
import pers.chen.kata.args.parsers.SchemaParser;

@Getter
@AllArgsConstructor
public enum SchemaEnum {
  L("-l", new LoggingSchemaParser()),
  P("-p", new PortSchemaParser()),
  D("-d", new DirectorySchemaParser());

  private String flag;
  private SchemaParser schemaParser;
}
