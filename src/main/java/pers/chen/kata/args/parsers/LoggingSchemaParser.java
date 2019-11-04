package pers.chen.kata.args.parsers;

public class LoggingSchemaParser implements SchemaParser<Boolean> {

  @Override
  public Boolean parse(String arg) {
    if (null == arg) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  @Override
  public String type() {
    return "logging: ";
  }
}
