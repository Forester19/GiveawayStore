package test;

/**
 * Created by Владислав on 12.02.2018.
 */
public class Test {

  class TestExep extends Exception{
      public void runTest() throws TestExep{
      }
      public void test() throws Exception {
          runTest();
      }
  }

}
