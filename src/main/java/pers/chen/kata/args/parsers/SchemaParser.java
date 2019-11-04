package pers.chen.kata.args.parsers;

public interface SchemaParser<T> {

  T parse(String arg);

  String type();
}
