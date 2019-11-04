package pers.chen.kata.args.parsers;

public class DirectorySchemaParser implements SchemaParser<String> {

  @Override
  public String parse(String arg) {
    if (null == arg) {
      return "";
    }
    return arg;
  }

  @Override
  public String type() {
    return "directory: ";
  }
}
