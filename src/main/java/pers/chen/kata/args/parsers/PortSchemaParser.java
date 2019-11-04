package pers.chen.kata.args.parsers;

public class PortSchemaParser implements SchemaParser<Integer> {

  @Override
  public Integer parse(String arg) {
    if (null == arg) {
      return 0;
    }
    return Integer.valueOf(arg);
  }

  @Override
  public String type() {
    return "port: ";
  }
}
